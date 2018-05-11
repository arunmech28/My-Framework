package com.mckesson.suite.PrescriberRegistration;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.mckesson.base.TestBase;
import com.mckesson.util.TestUtil;

public class TestSuiteBase extends TestBase{
    // check if the suite ex has to be skiped
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		APP_LOGS.debug("Checking Runmode of PrescriberRegistration Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "PrescriberRegistration Suite")){
			APP_LOGS.debug("Skipped PrescriberRegistration Suite as the runmode was set to NO");
			throw new SkipException("Runmode of PrescriberRegistration Suite set to no. So Skipping all tests in PrescriberRegistration Suite");
		}
		
	}
}
