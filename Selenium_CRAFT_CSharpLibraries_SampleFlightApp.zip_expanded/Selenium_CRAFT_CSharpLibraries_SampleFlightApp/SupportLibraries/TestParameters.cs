using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Framework_Core;
using OpenQA.Selenium;

namespace CRAFT.SupportLibraries
{
    /// <summary>
    ///   Class to encapsulate various input parameters required for each test script
    /// </summary>
    public class TestParameters
    {

        /// <summary>
        /// The current test scenario/module
        /// </summary>

        public String CurrentScenario { get; set; }

        /// <summary>
        /// The current test case
        /// </summary>
        public String CurrentTestcase { get; set; }

        /// <summary>
        /// The description of the current test case
        /// </summary>
        public String CurrentTestDescription { get; set; }

        /// <summary>
        ///The iteration mode
        /// </summary> 
        public IterationOptions IterationMode { get; set; }

        /// <summary>
        ///  The start iteration
        /// </summary>
        /// 
        private int _startIteration = 1;
        public int StartIteration { get { return _startIteration; } set { _startIteration = value; } }

        private int _endIteration = 1;
        public int EndIteration
        {
            get
            {
                return _endIteration;
            }
            set
            {
                if (value > 0)
                {
                    _endIteration = value;
                }
            }
        }
        
        /// <summary>
        ///  The end iteration
        /// </summary>
        //public int EndIteration { get { return EndIteration; } set { EndIteration = 1; } }

        /// <summary>
        ///  The browser for a specific test
        /// </summary>
        public Browser Browser { get; set; }

        /// <summary>
        ///  Function to get the browserVersion for a specific test
        /// </summary>
        public String BrowserVersion { get; set; }

        /// <summary>
        ///  Function to get the platform for a specific test
        /// </summary>
        public Platform Platform { get; set; }

    }
}
