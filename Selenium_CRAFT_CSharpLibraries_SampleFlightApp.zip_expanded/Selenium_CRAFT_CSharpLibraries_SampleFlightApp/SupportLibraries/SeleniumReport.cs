using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Framework_Reporting;
using OpenQA.Selenium.Remote;
using Framework_Utilities;
using OpenQA.Selenium;
using System.IO;
using System.Drawing.Imaging;

namespace CRAFT.SupportLibraries
{
  /// <summary>
  ///  Class to extend the reporting features of the framework
   /// </summary>
public class SeleniumReport : Report
{
    /// <summary>
    /// The WebDriver object
    /// </summary>
    public RemoteWebDriver Driver { get; set; }
	
	/// <summary>
	///Constructor to initialize the Report
	 	/// </summary>
	/// <param name="reportSettings">The ReportSettings object</param>
	/// <param name="reportTheme">The ReportTheme object</param>
	public SeleniumReport(ReportSettings reportSettings, ReportTheme reportTheme):base(reportSettings, reportTheme)
	{
		
	}
	
	protected override void TakeScreenshot(String screenshotPath)
	{
        if (Driver == null)
        {
            throw new FrameworkException("Report.driver is not initialized!");
        }
        Screenshot src = ((ITakesScreenshot)Driver).GetScreenshot();
        string screenshot = src.AsBase64EncodedString;
        byte[] screenshotAsByteArray = src.AsByteArray;
        try
        {
            src.SaveAsFile(screenshotPath, ImageFormat.Png);
        }
        catch (IOException e)
        {
            throw new FrameworkException("Error while writing screenshot to file");
        }
	}
}
}
