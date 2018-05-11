using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OpenQA.Selenium;
using Framework_Reporting;
using Framework_Core;
using OpenQA.Selenium.Remote;
using Framework_DataTable;


namespace CRAFT.SupportLibraries
{
    /// <summary>
    /// Abstract base class for reusable libraries created by the user
    /// </summary>
    public abstract class ReusableLibrary
    {
        /// <summary>
        /// The ScriptHelper object (required for calling one reusable library from another)
        /// </summary>
        protected ScriptHelper ScriptHelper;

        /// <summary>
        /// The Webdriver object
        /// </summary>
        protected RemoteWebDriver Driver;

        /// <summary>
        /// The craftLite DataTable object (passed from the main test script)
        /// </summary>
        protected CraftDataTable DataTable;

        /// <summary>
        /// The SeleniumReport object (passed from the test script)
        /// </summary>
        protected SeleniumReport Report;

        /// <summary>
        /// The FrameworkParameters object
        /// </summary>
        protected FrameworkParameters FrameworkParameters = FrameworkParameters.GetInstance();

        /// <summary>
        /// Constructor to initialize the ScriptHelper object and in turn the objects wrapped by it
        /// </summary>
        /// <param name="scriptHelper">The ScriptHelper object</param>
        public ReusableLibrary(ScriptHelper scriptHelper)
        {
            this.ScriptHelper = scriptHelper;
            this.Driver = scriptHelper.Driver;

            this.DataTable = scriptHelper.DataTable;
            this.Report = scriptHelper.Report;
        }

    }


}
