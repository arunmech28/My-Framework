using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OpenQA.Selenium;
using System.Text.RegularExpressions;
using OpenQA.Selenium.Remote;

namespace CRAFT.SupportLibraries
{
    /// <summary>
    /// Class to provide common assertions/verifications
    /// </summary>
   public class Asserts
{
	/// <summary>
	///  Function to verify whether a given text is present within the page
		/// </summary>
	/// <param name="driver"> The WebDriver object</param>
	/// <param name="textToVerify">The text to be verified within the page</param>
	/// <returns></returns>
	public Boolean IsTextPresent(RemoteWebDriver driver, String textToVerify)
	{
		textToVerify = textToVerify.Replace(" ", "\\s*");
		String pageSource = driver.PageSource;
		String[] pageSourceLines = pageSource.Trim().Split("\\n".ToCharArray());
		String pageSourceWithoutNewlines = "";
		foreach (String pageSourceLine in pageSourceLines)
		{
			pageSourceWithoutNewlines += pageSourceLine + " ";
		}
		
		pageSourceWithoutNewlines = pageSourceWithoutNewlines.Trim();
        Regex p = new Regex(textToVerify);
        if (p.IsMatch(pageSourceWithoutNewlines))
            return true;
		
		return false;
	}
}
}
