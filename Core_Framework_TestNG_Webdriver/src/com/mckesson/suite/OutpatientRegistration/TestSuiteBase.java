package com.mckesson.suite.OutpatientRegistration;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.mckesson.base.TestBase;
import com.mckesson.util.TestUtil;

public class TestSuiteBase extends TestBase{

	// check if the suite ex has to be skiped
		@BeforeSuite
		public void checkSuiteSkip() throws Exception{
			initialize();
			APP_LOGS.debug("Checking Runmode of OutpatientRegistration Suite");
			if(!TestUtil.isSuiteRunnable(suiteXls, "OutpatientRegistration Suite")){
				APP_LOGS.debug("Skipped  OutpatientRegistration Suite as the runmode was set to NO");
				throw new SkipException("Runmode of  OutpatientRegistration Suite set to no. So Skipping all tests in Suite  OutpatientRegistration");
			}
			
		}
}
