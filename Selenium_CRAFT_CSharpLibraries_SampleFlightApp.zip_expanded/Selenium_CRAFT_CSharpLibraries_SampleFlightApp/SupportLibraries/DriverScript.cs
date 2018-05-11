using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Reflection;
using Framework_Core;
using Framework_DataTable;
using Framework_Reporting;
using Framework_Utilities;
using System.Runtime.CompilerServices;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Remote;
using System.Configuration;

namespace CRAFT.SupportLibraries
{

    /// <summary>
    ///  Driver script class which encapsulates the core logic of the CRAFT framework
    /// </summary>
    public class DriverScript
    {
        private List<String> _businessFlowData;
        private int _currentIteration, _currentSubIteration;
        private DateTime _startTime, _endTime;
        private String _timeStamp;
        private String reportPath;

        private CraftDataTable _dataTable;
        private ReportSettings _reportSettings;
        private SeleniumReport _report;
        private RemoteWebDriver _driver;
        private ScriptHelper _scriptHelper;


        private String _gridMode;
        private FrameworkParameters _frameworkParameters = FrameworkParameters.GetInstance();

        /// <summary>
        ///  The TestParameters object
        /// </summary>
        protected TestParameters TestParameters = new TestParameters();


        /// <summary>
        ///  Constructor to initialize the DriverScript
        /// </summary>
        public DriverScript()
        {
            SetRelativePath();
            SetDefaultTestParameters();
        }

        private void SetRelativePath()
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

        private void SetDefaultTestParameters()
        {
            TestParameters.CurrentScenario = this.GetType().Name;
            TestParameters.IterationMode = IterationOptions.RunAllIterations;
            string browser = ConfigurationManager.AppSettings["DefaultBrowser"].ToLower();
            TestParameters.Browser = (Browser)Enum.Parse(typeof(Browser), browser);
            TestParameters.BrowserVersion = ConfigurationManager.AppSettings["DefaultBrowserVersion"];
            TestParameters.Platform = PlatformFactory.GetPlatform(ConfigurationManager.AppSettings["DefaultPlatform"]);
        }

        /// <summary>
        /// Function to execute the given test case
        /// </summary>
        public void DriveTestExecution()
        {
            InitializeWebDriver();
            InitializeTestReport();
            InitializeDatatable();
            InitializeTestScript();
            InitializeTestIterations();

            SetUp();
            ExecuteTestIterations();
            TearDown();

            WrapUp();
        }

        private void InitializeWebDriver()
        {
            _startTime = Convert.ToDateTime(Util.GetCurrentTime());

            _gridMode = ConfigurationManager.AppSettings["GridMode"];

            if (_gridMode.Equals("on", StringComparison.CurrentCultureIgnoreCase))
            {
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities(TestParameters.Browser.ToString(), TestParameters.BrowserVersion, TestParameters.Platform);

                Uri gridHubUrl;
                try
                {
                    gridHubUrl = new Uri(ConfigurationManager.AppSettings["GridHubUrl"]);
                }
                catch (Exception e)
                {

                    throw new FrameworkException("The specified Selenium Grid Hub URL is malformed");
                }

                _driver = new RemoteWebDriver(gridHubUrl, desiredCapabilities);
            }
            else
            {
                _driver = WebDriverFactory.GetDriver(TestParameters.Browser);
            }
        }

        private void InitializeTestReport()
        {
            _frameworkParameters.RunConfiguration = ConfigurationManager.AppSettings["RunConfiguration"];
            _timeStamp = TimeStamp.getInstance();

            InitializeReportSettings();

            string theme = ConfigurationManager.AppSettings["ReportTheme"];
            ReportTheme reportTheme =
                    ReportThemeFactory.GetReportsTheme((Framework_Reporting.ReportThemeFactory.Theme)Enum.Parse(typeof(Framework_Reporting.ReportThemeFactory.Theme), theme));

            _report = new SeleniumReport(_reportSettings, reportTheme);

            _report.InitializeReportTypes();
            _report.Driver = _driver;

            _report.InitializeTestLog();
            _report.AddTestLogHeading(_reportSettings.ProjectName +
                                        " - " + _reportSettings.ReportName +
                                        " Automation Execution Results");
            _report.AddTestLogSubHeading("Date & Time",
                                            ": " + Util.GetCurrentFormattedTime(ConfigurationManager.AppSettings["DateFormatString"]),
                                            "Iteration Mode", ": " + TestParameters.IterationMode);
            _report.AddTestLogSubHeading("Start Iteration", ": " + TestParameters.StartIteration,
                                        "End Iteration", ": " + TestParameters.EndIteration);

            if (_gridMode.Equals("on", StringComparison.CurrentCultureIgnoreCase))
            {
                _report.AddTestLogSubHeading("Browser", ": " + TestParameters.Browser,
                                                "Version", ": " + TestParameters.BrowserVersion);
                _report.AddTestLogSubHeading("Platform", ": " + TestParameters.Platform.ToString(),
                                            "Application URL", ": " + ConfigurationManager.AppSettings["ApplicationUrl"]);
            }
            else
            {
                _report.AddTestLogSubHeading("Browser", ": " + TestParameters.Browser,
                                                "Application URL", ": " + ConfigurationManager.AppSettings["ApplicationUrl"]);
            }

            _report.AddTestLogTableHeadings();
        }

        private void InitializeReportSettings()
        {
            reportPath = _frameworkParameters.RelativePath +
                                    Util.GetFileSeparator() + "Results" +
                                    Util.GetFileSeparator() + _timeStamp;

            _reportSettings = new ReportSettings(reportPath,
                                                    TestParameters.CurrentScenario +
                                                    "_" + TestParameters.CurrentTestcase);

            _reportSettings.DateFormatString = ConfigurationManager.AppSettings["DateFormatString"];
            _reportSettings.LogLevel = Convert.ToInt32(ConfigurationManager.AppSettings["LogLevel"]);
            _reportSettings.ProjectName = ConfigurationManager.AppSettings["ProjectName"];
            _reportSettings.GenerateExcelReports =
                    Boolean.Parse(ConfigurationManager.AppSettings["ExcelReport"]);
            _reportSettings.GenerateHtmlReports =
                    Boolean.Parse(ConfigurationManager.AppSettings["HtmlReport"]);
            _reportSettings.IncludeTestDataInReport =
                    Boolean.Parse(ConfigurationManager.AppSettings["IncludeTestDataInReport"]);
            _reportSettings.TakeScreenshotFailedStep =
                    Boolean.Parse(ConfigurationManager.AppSettings["TakeScreenshotFailedStep"]);
            _reportSettings.TakeScreenshotPassedStep =
                    Boolean.Parse(ConfigurationManager.AppSettings["TakeScreenshotPassedStep"]);
        }

        private void InitializeDatatable()
        {
            String datatablePath = _frameworkParameters.RelativePath +
                                        Util.GetFileSeparator() + "Datatables";

            String runTimeDatatablePath;
            if (_reportSettings.IncludeTestDataInReport)
            {
                runTimeDatatablePath = reportPath + Util.GetFileSeparator() + "Datatables";
                if (!File.Exists(runTimeDatatablePath + Util.GetFileSeparator() + TestParameters.CurrentScenario + ".xls"))
                {
                    try
                    {
                        File.Copy(datatablePath + Util.GetFileSeparator() + TestParameters.CurrentScenario + ".xls", runTimeDatatablePath + Util.GetFileSeparator() + TestParameters.CurrentScenario + ".xls");
                    }
                    catch (IOException e)
                    {
                        Console.WriteLine(e.StackTrace);
                        throw new FrameworkException("Error in creating run-time datatable: Copying the datatable failed...");
                    }
                }

                if (!File.Exists(runTimeDatatablePath + Util.GetFileSeparator() + "Common Testdata.xls"))
                {
                    try
                    {
                        File.Copy(datatablePath + Util.GetFileSeparator() + "Common Testdata.xls", runTimeDatatablePath + Util.GetFileSeparator() + "Common Testdata.xls");
                    }
                    catch (IOException e)
                    {
                        Console.WriteLine(e.StackTrace);

                        throw new FrameworkException("Error in creating run-time datatable: Copying the common datatable failed...");
                    }
                }
            }
            else
            {
                runTimeDatatablePath = datatablePath;
            }

            _dataTable = new CraftDataTable(runTimeDatatablePath, TestParameters.CurrentScenario);
        }

        private void InitializeTestScript()
        {
            _scriptHelper = new ScriptHelper(_dataTable, _report, _driver);

            _businessFlowData = GetBusinessFlow();
        }

        private List<String> GetBusinessFlow()
        {
            ExcelDataAccess businessFlowAccess =
                    new ExcelDataAccess(_frameworkParameters.RelativePath +
                                            Util.GetFileSeparator() + "Datatables",
                                            TestParameters.CurrentScenario);
            businessFlowAccess.DatasheetName = "Business_Flow";

            int rowNum = businessFlowAccess.GetRowNum(TestParameters.CurrentTestcase, 0);
            if (rowNum == -1)
            {
                throw new FrameworkException("The test case \"" + TestParameters.CurrentTestcase + "\" is not found in the Business Flow sheet!");
            }

            String dataValue;
            List<String> businessFlowData = new List<String>();
            int currentColumnNum = 1;
            while (true)
            {
                dataValue = businessFlowAccess.GetValue(rowNum, currentColumnNum);
                if (dataValue.Equals(""))
                {
                    break;
                }
                businessFlowData.Add(dataValue);
                currentColumnNum++;
            }

            if (businessFlowData.Count() == 0)
            {
                throw new FrameworkException("No business flow found against the test case \"" + TestParameters.CurrentTestcase + "\"");
            }

            return businessFlowData;
        }

        [MethodImpl(MethodImplOptions.Synchronized)]
        private void InitializeTestIterations()
        {
            int nTestcaseRows = 1;
            int nSubIterations = 1;

            switch (TestParameters.IterationMode)
            {
                case IterationOptions.RunAllIterations:

                    String datatablePath = _frameworkParameters.RelativePath +
                                            Util.GetFileSeparator() + "Datatables";
                    ExcelDataAccess testDataAccess =
                            new ExcelDataAccess(datatablePath, TestParameters.CurrentScenario);
                    testDataAccess.DatasheetName = ConfigurationManager.AppSettings["DefaultDataSheet"];

                    int startRowNum = testDataAccess.GetRowNum(TestParameters.CurrentTestcase, 0);
                    nTestcaseRows = testDataAccess.GetRowCount(TestParameters.CurrentTestcase, 0, startRowNum);
                    nSubIterations = testDataAccess.GetRowCount("1", 1, startRowNum);	// Assumption: Every test case will have at least one iteration
                    int nIterations = nTestcaseRows / nSubIterations;
                    TestParameters.EndIteration = nIterations;
                    _currentIteration = 1;
                    break;


                case IterationOptions.RunOneIterationOnly:
                    _currentIteration = 1;
                    break;

                case IterationOptions.RunRangeOfIterations:
                    if (TestParameters.StartIteration > TestParameters.EndIteration)
                    {
                        throw new FrameworkException("Error", "StartIteration cannot be greater than EndIteration!");
                    }
                    _currentIteration = TestParameters.StartIteration;
                    break;
            }
        }
        /// <summary>
        ///  Function to do required setup activities before starting the test execution
        /// </summary>
        protected void SetUp()
        {
            _driver.Manage().Timeouts().ImplicitlyWait(new TimeSpan(0, 0, 30));
      //      _driver.Url = ConfigurationManager.AppSettings["ApplicationUrl"];
        }

        private void ExecuteTestIterations()
        {
            while (_currentIteration <= TestParameters.EndIteration)
            {
                _report.AddTestLogSection("Iteration: " + _currentIteration.ToString());

                try
                {
                    ExecuteTestcase(_businessFlowData);
                }
                catch (FrameworkException fx)
                {
                    ExceptionHandler(fx, fx.errorName);
                }
                catch (Exception ix)
                {
                    ExceptionHandler(ix, "Error");
                } //catch (Exception ex) {
                //    ExceptionHandler(ex, "Error");
                //} 

                _currentIteration++;
            }
        }

        private void ExecuteTestcase(List<String> businessFlowData)
        {
            Dictionary<String, Int32> keywordDirectory = new Dictionary<String, Int32>();

            for (int currentKeywordNum = 0; currentKeywordNum < businessFlowData.Count(); currentKeywordNum++)
            {
                String[] currentFlowData = businessFlowData[currentKeywordNum].Split(",".ToCharArray());
                String currentKeyword = currentFlowData[0];

                int nKeywordIterations;
                if (currentFlowData.Length > 1)
                {
                    nKeywordIterations = Convert.ToInt32(currentFlowData[1]);
                }
                else
                {
                    nKeywordIterations = 1;
                }

                for (int currentKeywordIteration = 0; currentKeywordIteration < nKeywordIterations; currentKeywordIteration++)
                {
                    if (keywordDirectory.ContainsKey(currentKeyword))
                    {
                        keywordDirectory[currentKeyword] = keywordDirectory[currentKeyword] + 1;
                    }
                    else
                    {
                        keywordDirectory.Add(currentKeyword, 1);
                    }
                    _currentSubIteration = keywordDirectory[currentKeyword];

                    _dataTable.SetCurrentRow(TestParameters.CurrentTestcase, _currentIteration, _currentSubIteration);

                    if (_currentSubIteration > 1)
                    {
                        _report.AddTestLogSubSection(currentKeyword + " (Sub-Iteration: " + _currentSubIteration + ")");
                    }
                    else
                    {
                        _report.AddTestLogSubSection(currentKeyword);
                    }

                    InvokeBusinessComponent(currentKeyword);

                }
            }
        }

        private void InvokeBusinessComponent(String currentKeyword)
        {
            bool methodFound = false;
            Assembly asm = Assembly.GetExecutingAssembly();

            foreach (Type type in asm.GetTypes())
            {
                MethodInfo[] methods = type.GetMethods();
                foreach (MethodInfo method in methods)
                {
                    if (method.Name.ToLower().Equals(currentKeyword.ToLower()))
                    {
                        methodFound = true;
                        Type[] types = new Type[1];
                        types[0] = typeof(ScriptHelper);


                        ConstructorInfo ctor = type.GetConstructor(BindingFlags.Instance | BindingFlags.Public, null,
                                        CallingConventions.HasThis, types, null);
                        if (ctor != null)
                        {
                            object[] argVals = new object[] { _scriptHelper };
                            object instance = ctor.Invoke(argVals);

                            MethodInfo methodInfo = method;
                            object[] parameters = new object[] { };
                            methodInfo.Invoke(instance, parameters);

                        }
                    }
                    if (methodFound) break;
                }
                if (methodFound) break;
            }

            if (!methodFound)
            {
                //Error reporting
                throw new FrameworkException("Keyword " + currentKeyword + " not found within any class inside the businesscomponents");
            }
        }


        private void ExceptionHandler(Exception ex, String exceptionName)
        {
            // Error reporting
            String exceptionDescription = ex.Message;
            if (exceptionDescription == null)
            {
                exceptionDescription = ex.ToString();
            }

            if (ex.Source != null)
            {
                _report.UpdateTestLog(exceptionName, exceptionDescription + " <b>Caused by: </b>" +
                                                                            ex.Source, Status.FAIL);
            }
            else
            {
                _report.UpdateTestLog(exceptionName, exceptionDescription, Status.FAIL);
            }
            Console.WriteLine(ex.StackTrace);

            // Error response
            if (_frameworkParameters.StopExecution)
            {
                _report.UpdateTestLog("CRAFT Info",
                        "Test execution terminated by user! All subsequent tests aborted...",
                        Status.DONE);
            }
            else
            {
                string obj = ConfigurationManager.AppSettings["OnError"];
                OnError onError = (OnError)Enum.Parse(typeof(OnError), obj);
                switch (onError)
                {
                    case OnError.NextIteration:
                        _report.UpdateTestLog("CRAFT Info",
                                "Test case iteration terminated by user! Proceeding to next iteration (if applicable)...",
                                Status.DONE);
                        _currentIteration++;
                        ExecuteTestIterations();
                        break;

                    case OnError.NextTestCase:
                        _report.UpdateTestLog("CRAFT Info",
                                "Test case terminated by user! Proceeding to next test case (if applicable)...",
                                Status.DONE);
                        break;

                    case OnError.Stop:
                        _frameworkParameters.StopExecution = true;
                        _report.UpdateTestLog("CRAFT Info",
                                "Test execution terminated by user! All subsequent tests aborted...",
                                Status.DONE);
                        break;
                }
            }

            // Wrap up execution
            TearDown();
            WrapUp();
        }

        /// <summary>
        /// Function to do required teardown activities at the end of the test execution
        /// </summary>
        protected void TearDown()
        {
            _driver.Quit();
        }

        private void WrapUp()
        {
            _endTime = Convert.ToDateTime(Util.GetCurrentTime());
            CloseTestReport();
            if (_report.TestStatus.Equals("Failed", StringComparison.CurrentCultureIgnoreCase))
            {
                Assert.Fail(_report.FailureDescription);
            }
        }

        private void CloseTestReport()
        {
            String executionTime = Util.GetTimeDifference(_startTime, _endTime);
            _report.AddTestLogFooter(executionTime);
        }
    }




}





