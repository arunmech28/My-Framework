using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Android;

namespace CRAFT.SupportLibraries
{
   
public class PlatformFactory
{
	/// <summary>
	///  Function to return the appropriate Platform object based on the platform name passed
		/// </summary>
	/// <param name="platformName"> The name of the platform</param>
	/// <returns> The corresponding Platform object</returns>
	public static Platform GetPlatform(String platformName)
	{
        PlatformType platformType = PlatformType.Any;
		
		if(platformName.Equals("windows",StringComparison.CurrentCultureIgnoreCase))
            platformType = PlatformType.Windows;
		if(platformName.Equals("android",StringComparison.CurrentCultureIgnoreCase))
            platformType = PlatformType.Any;
		if(platformName.Equals("any",StringComparison.CurrentCultureIgnoreCase))
            platformType = PlatformType.Any;
		if(platformName.Equals("xp",StringComparison.CurrentCultureIgnoreCase))
            platformType = PlatformType.XP;
		if(platformName.Equals("vista",StringComparison.CurrentCultureIgnoreCase))
            platformType = PlatformType.Vista;
		if(platformName.Equals("unix",StringComparison.CurrentCultureIgnoreCase))
            platformType = PlatformType.Unix;
		if(platformName.Equals("mac",StringComparison.CurrentCultureIgnoreCase))
            platformType = PlatformType.Mac;
		if(platformName.Equals("linux",StringComparison.CurrentCultureIgnoreCase))
            platformType = PlatformType.Linux;

        Platform platform = new Platform(platformType);
		
		return platform;
	}
}
}
