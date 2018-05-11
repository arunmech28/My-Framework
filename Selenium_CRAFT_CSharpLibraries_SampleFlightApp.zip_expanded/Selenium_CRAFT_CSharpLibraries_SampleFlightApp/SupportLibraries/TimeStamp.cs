using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Reflection;
using System.Runtime.CompilerServices;
using Framework_Core;
using Framework_Utilities;
using System.Configuration;

namespace CRAFT.SupportLibraries
{
    /// <summary>
    /// Singleton class which manages the creation of timestamped result folders for every test batch execution
    /// </summary>

    public sealed class TimeStamp
    {
        private static volatile String _timeStamp;
        private static object _syncRoot = new Object();

        /// <summary>
        /// Function to return the timestamped result folder path
        /// </summary>
        /// <returns>The timestamped result folder path</returns>
        [MethodImpl(MethodImplOptions.Synchronized)]
        public static String getInstance()
        {
            if (_timeStamp == null)
            {
                lock (_syncRoot)
                {
                    if (_timeStamp == null)
                    {
                        FrameworkParameters frameworkParameters = FrameworkParameters.GetInstance();
                       
                        _timeStamp = frameworkParameters.RunConfiguration + Util.GetFileSeparator() + "Run_" +
                                        Util.GetCurrentFormattedTime(ConfigurationManager.AppSettings["DateFormatString"]).Replace(" ", "_").Replace(":", "-");

                        String reportPathWithTimeStamp = frameworkParameters.RelativePath +
                                                            Util.GetFileSeparator() + "Results" +
                                                            Util.GetFileSeparator() + _timeStamp;

                        Directory.CreateDirectory(reportPathWithTimeStamp);
                        Directory.CreateDirectory(reportPathWithTimeStamp + Util.GetFileSeparator() + "Screenshots");
                    }
                }
            }

            return _timeStamp;
        }

    }
}
