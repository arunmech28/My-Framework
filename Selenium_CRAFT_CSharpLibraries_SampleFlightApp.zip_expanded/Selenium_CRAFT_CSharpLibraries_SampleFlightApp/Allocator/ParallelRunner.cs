using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using Framework_Utilities;
using Framework_Core;
using System.Runtime;
using System.Threading;
using System.Reflection;
using Framework_Reporting;
using CRAFT.SupportLibraries;

namespace CRAFT.Allocator
{

    /// <summary>
    ///  Class to facilitate parallel execution of test scripts
    /// </summary>
    public class ParallelRunner
    {
        private FrameworkParameters _frameworkParameters = FrameworkParameters.GetInstance();
        private TestParameters _testParameters;
        private DateTime _startTime, _endTime;
        private String _testStatus;

        private SeleniumReport _report;


        /// <summary>
        /// Constructor to initialize the details of the test case to be executed
        /// </summary>
        /// <param name="testParameters">The TestParameters object (passed from the Allocator)</param>
        /// <param name="report">The SeleniumReport object (passed from the Allocator)</param>
        public ParallelRunner(TestParameters testParameters, SeleniumReport report)
        {
            this._testParameters = testParameters;
            this._report = report;
        }


        public void Run()
        {
            _startTime = Convert.ToDateTime(Util.GetCurrentTime());
            _testStatus = InvokeTestScript();
            _endTime = Convert.ToDateTime(Util.GetCurrentTime());
            String executionTime = Util.GetTimeDifference(_startTime, _endTime);
            _report.UpdateResultSummary(_testParameters.CurrentScenario,
                                        _testParameters.CurrentTestcase,
                                        _testParameters.CurrentTestDescription,
                                        executionTime, _testStatus);
        }

        private String InvokeTestScript()
        {
            if (_frameworkParameters.StopExecution)
            {
                _testStatus = "Test Execution Aborted";
            }
            else
            {
                bool classFound = false;
                Assembly asm = Assembly.GetExecutingAssembly();
                try
                {
                    Type type = asm.GetType("CRAFT.SupportLibraries.DriverScript");

                    Type[] types = new Type[0];
                    //types[0] = typeof(TestParameters);

                    ConstructorInfo ctor = type.GetConstructor(BindingFlags.Instance | BindingFlags.Public, null,
                                    CallingConventions.HasThis, types, null);
                    if (ctor != null)
                    {
                        object[] argVals = new object[] { };
                        object instance = ctor.Invoke(argVals);

                        FieldInfo fieldTestParameters = type.GetField("TestParameters", BindingFlags.Instance | BindingFlags.NonPublic);
                        fieldTestParameters.SetValue(instance, this._testParameters);

                        MethodInfo methodInfo = type.GetMethod("DriveTestExecution");
                        object[] parameters = new object[] { };
                        methodInfo.Invoke(instance, parameters);

                        FieldInfo testReport = type.GetField("_report", BindingFlags.Instance | BindingFlags.NonPublic);
                        Report fieldReport = (Report)testReport.GetValue(instance);
                        _testStatus = fieldReport.TestStatus;
                    }
                }

                //    foreach (Type t in asm.GetTypes())
                //    {
                //        if (t.Name.ToLower().Equals(_testParameters.CurrentTestcase.ToLower()))
                //        {
                //            classFound = true;
                //            Type[] types = new Type[0];
                //            //types[0] = typeof(TestParameters);

                //            ConstructorInfo ctor = t.GetConstructor(BindingFlags.Instance | BindingFlags.Public, null,
                //                            CallingConventions.HasThis, types, null);
                //            if (ctor != null)
                //            {
                //                object[] argVals = new object[] { };
                //                object instance = ctor.Invoke(argVals);

                //                FieldInfo fieldTestParameters = t.BaseType.GetField("TestParameters", BindingFlags.Instance | BindingFlags.NonPublic);
                //                fieldTestParameters.SetValue(instance, this._testParameters);

                //                MethodInfo methodInfo = t.GetMethod("DriveTestExecution");
                //                object[] parameters = new object[] { };
                //                methodInfo.Invoke(instance, parameters);

                //                FieldInfo testReport = t.BaseType.GetField("Report", BindingFlags.Instance | BindingFlags.NonPublic);
                //                Report fieldReport = (Report)testReport.GetValue(instance);
                //                _testStatus = fieldReport.TestStatus;
                //            }
                //        }
                //        if (classFound) break;
                //    }

                //}
                catch (Exception ex)
                {
                    _testStatus = "Failed";
                    //e.printStackTrace();
                }

            }

            return _testStatus;
        }

        public void ThreadPoolCallBack(Object doneEvent)
        {
            try
            {
                //Calling thread execution method
                Run();

                //Setting the thread execution completion
                ManualResetEvent _doneEvent = (ManualResetEvent)doneEvent;
                _doneEvent.Set();
            }
            catch (Exception)
            {
                throw new FrameworkException("Error occured while executing a thread");
            }
        }
    }
}






