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
public class Scenario2 : DriverScript
{
[TestMethod]
	public void runTC4()
	{
	    TestParameters.CurrentTestcase="TC4";
		TestParameters.IterationMode=IterationOptions.RunOneIterationOnly;
		TestParameters.Browser=Browser.iexplore;
		DriveTestExecution();
	}
}
}
