using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Framework_DataTable;
using OpenQA.Selenium.Remote;

namespace CRAFT.SupportLibraries
{
 /// <summary>
 /// Wrapper class for common framework objects, to be used across the entire test case and dependent libraries
  /// </summary>
public class ScriptHelper
{
    private CraftDataTable _dataTable;
    private SeleniumReport _report;
    private RemoteWebDriver _driver;

    /// <summary>
    /// The DataTable object of the  ScriptHelper class
    /// </summary>
    public CraftDataTable DataTable { get; private set; }
    /// <summary>
    /// The SeleniumReport object
    /// </summary>
    public SeleniumReport Report { get; private set; }
    /// <summary>
    /// The  WebDriver object
    /// </summary>
    public RemoteWebDriver Driver { get; private set; }

    /// <summary>
    /// Constructor to initialize all the objects wrapped by the ScriptHelper class
    /// </summary>
    /// <param name="dataTable">The CraftDataTable object</param>
    /// <param name="report">The SeleniumReport object</param>
    /// <param name="driver"></param>
    public ScriptHelper(CraftDataTable dataTable, SeleniumReport report, RemoteWebDriver driver)
    {
        this.DataTable = dataTable;
        this.Report = report;
        this.Driver = driver;
    }

	
}
}
