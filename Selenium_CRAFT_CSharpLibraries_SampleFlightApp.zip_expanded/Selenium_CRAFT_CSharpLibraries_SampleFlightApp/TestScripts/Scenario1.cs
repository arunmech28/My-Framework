using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Framework_Core;
using CRAFT.SupportLibraries;


namespace CRAFT.TestScripts
{
    [TestClass]
    public class Scenario1 : DriverScript
{
	[TestMethod]
	public void RunTC1()
	{
        TestParameters.CurrentTestcase="TC1";
        TestParameters.IterationMode=IterationOptions.RunOneIterationOnly;
        TestParameters.Browser=Browser.iexplore;

        DriveTestExecution();
	}

    //[TestMethod]
    //public void RunTC2()
    //{
    //    TestParameters.CurrentTestcase="TC2";
    //    TestParameters.Browser = Browser.iexplore;

    //    DriveTestExecution();
    //}

    //[TestMethod]
    //public void RunTC3()
    //{
    //    TestParameters.CurrentTestcase="TC3";

    //    DriveTestExecution();
    //}
}
}



