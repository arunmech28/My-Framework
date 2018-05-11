using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using Framework_Core;
using Framework_Reporting;
using Framework_Utilities;
using System.Reflection;
using System.Threading;
using System.Diagnostics;
using System.Configuration;
using CRAFT.SupportLibraries;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CRAFT.Allocator
{
    /// <summary>
    /// Class to manage the batch execution of test scripts within the framework
    /// </summary>
    [TestClass]
    public class Allocator
    {
        private static List<TestParameters> _testInstancesToRun;
        private static SeleniumReport _report;

        private static FrameworkParameters _frameworkParameters = FrameworkParameters.GetInstance();

        private static DateTime _startTime, _endTime;
        private static ReportSettings _reportSettings;
        private static String _timeStamp;
        private static String _reportPath;

        [TestMethod]
        public void StartBatchExecution()
        {
            try
            {
                SetRelativePath();
                InitializeTestBatch();
                InitializeSummaryReport();
                SetupErrorLog();
                DriveBatchExecution();
                WrapUp();
            }
            catch (FileNotFoundException e)
            {

            }
        }

        private static void SetRelativePath()
        {
            String relativePath = Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location);
            if (relativePath.Contains("bin\\Debug"))
            {
                relativePath = relativePath.Substring(0, relativePath.IndexOf("bin"));
            }
            else
            {
                relativePath = relativePath.Substring(0, relativePath.IndexOf("TestResults"));
            }
            if (relativePath.Contains("allocator"))
            {
                relativePath = Directory.GetParent(relativePath).ToString();
            }
            _frameworkParameters.RelativePath = relativePath;
        }


        private static void InitializeTestBatch()
        {
            _startTime = Convert.ToDateTime(Util.GetCurrentTime());
            _testInstancesToRun = GetRunInfo(ConfigurationManager.AppSettings["RunConfiguration"]);
        }

        private static void InitializeSummaryReport()
        {
            _frameworkParameters.RunConfiguration = ConfigurationManager.AppSettings["RunConfiguration"];
            _timeStamp = TimeStamp.getInstance();

            _reportSettings = InitializeReportSettings();
            string theme = ConfigurationManager.AppSettings["ReportTheme"];
            ReportTheme reportTheme =
                    ReportThemeFactory.GetReportsTheme((Framework_Reporting.ReportThemeFactory.Theme)Enum.Parse(typeof(Framework_Reporting.ReportThemeFactory.Theme), theme));
            _report = new SeleniumReport(_reportSettings, reportTheme);

            _report.InitializeReportTypes();

            _report.InitializeResultSummary();
            _report.AddResultSummaryHeading(_reportSettings.ProjectName +
                                                " - " + " Automation Execution Result Summary");
            _report.AddResultSummarySubHeading("Date & Time",
                                            ": " + Util.GetCurrentFormattedTime(ConfigurationManager.AppSettings["DateFormatString"]),
                                            "OnError", ": " + ConfigurationManager.AppSettings["OnError"]);

            _report.AddResultSummaryTableHeadings();
        }

        private static ReportSettings InitializeReportSettings()
        {
            _reportPath = _frameworkParameters.RelativePath +
                            Util.GetFileSeparator() + "Results" +
                            Util.GetFileSeparator() + _timeStamp;
            ReportSettings reportSettings = new ReportSettings(_reportPath, "");

            reportSettings.DateFormatString = ConfigurationManager.AppSettings["DateFormatString"];
            reportSettings.ProjectName = ConfigurationManager.AppSettings["ProjectName"];
            reportSettings.GenerateExcelReports = Convert.ToBoolean(ConfigurationManager.AppSettings["ExcelReport"]);
            reportSettings.GenerateHtmlReports = Convert.ToBoolean(ConfigurationManager.AppSettings["HtmlReport"]);
            return reportSettings;
        }

        private static void SetupErrorLog()
        {
            try
            {
                String errorLogFile = _reportPath + Util.GetFileSeparator() + "ErrorLog.txt";
                Console.SetError(new StreamWriter(errorLogFile));

            }
            catch (FileNotFoundException)
            { }
        }

        private static List<TestParameters> GetRunInfo(String sheetName)
        {
            ExcelDataAccess runManagerAccess = new ExcelDataAccess(_frameworkParameters.RelativePath, "Run Manager");
            runManagerAccess.DatasheetName = sheetName;

            int nTestInstances = runManagerAccess.GetLastRowNum();
            List<TestParameters> testInstancesToRun = new List<TestParameters>();

            for (int currentTestInstance = 1; currentTestInstance <= nTestInstances; currentTestInstance++)
            {
                String executeFlag = runManagerAccess.GetValue(currentTestInstance, "Execute");

                if (executeFlag.Equals("Yes", StringComparison.CurrentCultureIgnoreCase))
                {
                    TestParameters testParameters = new TestParameters();

                    testParameters.CurrentScenario = runManagerAccess.GetValue(currentTestInstance, "Test_Scenario");
                    testParameters.CurrentTestcase = runManagerAccess.GetValue(currentTestInstance, "Test_Case");
                    testParameters.CurrentTestDescription = runManagerAccess.GetValue(currentTestInstance, "Description");

                    testParameters.IterationMode = (IterationOptions)Enum.Parse(typeof(IterationOptions), runManagerAccess.GetValue(currentTestInstance, "Iteration_Mode"));
                    String startIteration = runManagerAccess.GetValue(currentTestInstance, "Start_Iteration");
                    if (!startIteration.Equals(""))
                    {
                        testParameters.StartIteration = Convert.ToInt32(startIteration);
                    }
                    String endIteration = runManagerAccess.GetValue(currentTestInstance, "End_Iteration");
                    if (!endIteration.Equals(""))
                    {
                        testParameters.EndIteration = Convert.ToInt32(endIteration);
                    }

                    String browser = runManagerAccess.GetValue(currentTestInstance, "Browser");
                    if (!browser.Equals(""))
                    {
                        testParameters.Browser = (Browser)Enum.Parse(typeof(Browser), browser);
                    }
                    String browserVersion = runManagerAccess.GetValue(currentTestInstance, "Browser_Version");
                    if (!browserVersion.Equals(""))
                    {
                        testParameters.BrowserVersion = browserVersion;
                    }
                    String platform = runManagerAccess.GetValue(currentTestInstance, "Platform");
                    if (!platform.Equals(""))
                    {
                        testParameters.Platform = PlatformFactory.GetPlatform(platform);
                    }

                    testInstancesToRun.Add(testParameters);
                }
            }

            return testInstancesToRun;
        }

        private static void DriveBatchExecution()
        {
            int nThreads = Convert.ToInt32(ConfigurationManager.AppSettings["NumberOfThreads"]);
            //ExecutorService parallelExecutor = Executors.newFixedThreadPool(nThreads);
            //ThreadPool.SetMaxThreads(nThreads, 0);
            ManualResetEvent[] doneEvents = new ManualResetEvent[nThreads];
            int i = 0;
            for (int currentTestInstance = 0; currentTestInstance < _testInstancesToRun.Count(); currentTestInstance++)
            {
                if (i < nThreads)
                {
                    doneEvents[i] = new ManualResetEvent(false);
                    ParallelRunner testRunner = new ParallelRunner(_testInstancesToRun[currentTestInstance], _report);
                    ThreadPool.QueueUserWorkItem(testRunner.ThreadPoolCallBack, (object)doneEvents[i]);
                    //parallelExecutor.execute(testRunner);

                    if (_frameworkParameters.StopExecution)
                    {
                        break;
                    }
                    i++;
                }
                //If the thread count reaches the ini thread count then wait for completion of any one thread
                if (i == nThreads && nThreads > 1)
                {
                    WaitHandle.WaitAny(doneEvents);
                    i--;
                }
                else if (nThreads == 1)
                {
                    WaitHandle.WaitAll(doneEvents);
                    i--;
                }
            }
            //Wait for all threads to complete their execution before proceeding to next step
            WaitHandle.WaitAll(doneEvents);
        }

        private static void WrapUp()
        {
            _endTime = Convert.ToDateTime(Util.GetCurrentTime());
            CloseSummaryReport();

            if (_reportSettings.GenerateHtmlReports)
            {
                try
                {
                    Process.Start(_reportPath + "\\HTML Results\\Summary.Html");
                }
                catch (IOException e)
                {
                    Console.WriteLine(e.StackTrace);
                    throw new FrameworkException("Error opening Result file");
                }
            }
            else if (_reportSettings.GenerateExcelReports)
            {
                try
                {
                    Process.Start(_reportPath + "\\HTML Results\\Summary.xls");
                }
                catch (IOException e)
                {
                    Console.WriteLine(e.StackTrace);
                    throw new FrameworkException("Error opening Result file");
                }
            }
        }

        private static void CloseSummaryReport()
        {
            String totalExecutionTime = Util.GetTimeDifference(_startTime, _endTime);
            _report.AddResultSummaryFooter(totalExecutionTime);
        }
    }
}






