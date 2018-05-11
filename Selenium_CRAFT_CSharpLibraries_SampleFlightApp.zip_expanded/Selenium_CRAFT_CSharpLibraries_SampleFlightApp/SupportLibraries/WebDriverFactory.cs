using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OpenQA.Selenium.Remote;
using Framework_Core;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.IE;
using System.Configuration;
using System.IO;

namespace CRAFT.SupportLibraries
{
    /// <summary>
    /// Factory for creating the Driver object based on the required browser
    /// </summary>
    public class WebDriverFactory
    {

        /// <summary>
        ///  Function to return the appropriate RemoteWebDriver object based on the Browser passed
        /// </summary>
        /// <param name="browser">The Browser to be used for the test execution</param>
        /// <returns>The RemoteWebDriver object corresponding to the Browser specified</returns>
        public static RemoteWebDriver GetDriver(Browser browser)
        {
            RemoteWebDriver driver = null;

            switch (browser)
            {
                case Browser.chrome:
                    System.Environment.SetEnvironmentVariable("webdriver.chrome.driver", ConfigurationManager.AppSettings["ChromeDriverPath"]);
                    string chrome = ConfigurationManager.AppSettings["ChromeDriverPath"];
                    driver = new ChromeDriver(Path.GetDirectoryName(chrome));
                    break;
                case Browser.firefox:
                    driver = new FirefoxDriver();
                    break;
                case Browser.htmlunit:
                    driver = new RemoteWebDriver(DesiredCapabilities.HtmlUnit());
                    break;
                case Browser.iexplore:
                    System.Environment.SetEnvironmentVariable("webdriver.ie.driver", ConfigurationManager.AppSettings["InternetExplorerDriverPath"]);
                    string ie = ConfigurationManager.AppSettings["InternetExplorerDriverPath"];

                 

                    InternetExplorerOptions options = new InternetExplorerOptions();
                    options.IntroduceInstabilityByIgnoringProtectedModeSettings = true;
                    
                    driver = new InternetExplorerDriver(Path.GetDirectoryName(ie),options);

                    break;
                case Browser.opera:
                    driver = new RemoteWebDriver(DesiredCapabilities.Opera());
                    break;
            }

            return (RemoteWebDriver)driver;
        }
    }
}
