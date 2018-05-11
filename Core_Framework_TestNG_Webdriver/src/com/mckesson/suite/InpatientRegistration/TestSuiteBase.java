package com.mckesson.suite.InpatientRegistration;


import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.mckesson.base.TestBase;
import com.mckesson.util.TestUtil;

public class TestSuiteBase extends TestBase{

	// check if the suite PrescriberRegistration has to be skipped
		@BeforeSuite
		public void checkSuiteSkip() throws Exception{
			initialize();
			APP_LOGS.debug("Checking Runmode of InpatientRegistration Suite");
			if(!TestUtil.isSuiteRunnable(suiteXls, "InpatientRegistration Suite")){
				APP_LOGS.debug("Skipped InpatientRegistration Suite as the runmode was set to NO");
				throw new SkipException("Runmode of InpatientRegistration Suite set to no. So Skipping all tests in Suite InpatientRegistration");
			}
			
		}
}