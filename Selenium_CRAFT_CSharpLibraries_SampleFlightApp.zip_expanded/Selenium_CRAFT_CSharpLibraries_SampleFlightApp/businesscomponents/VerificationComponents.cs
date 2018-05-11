using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Framework_Utilities;
using Framework_Reporting;
using OpenQA.Selenium;
using CRAFT.SupportLibraries;

namespace CRAFT.BusinessComponents
{

    /// <summary>
    /// Verification Components class
    /// </summary>
    public class VerificationComponents : ReusableLibrary
    {
        private Asserts asserts = new Asserts();

        /// <summary>
        ///  Constructor to initialize the component library
        /// </summary>
        /// <param name="scriptHelper">The ScriptHelper object passed from the DriverScript</param>
        public VerificationComponents(ScriptHelper scriptHelper)
            : base(scriptHelper)
        {

        }

        public void VerifyLoginValidUser()
        {
            if (asserts.IsTextPresent(Driver, "SIGN-OFF"))
            {
                Report.UpdateTestLog("Verify Login", "Login succeeded for valid user", Status.PASS);
            }
            else
            {
                throw new FrameworkException("Verify Login", "Login failed for valid user");
            }
        }

        public void VerifyLoginInvalidUser()
        {
            if (!asserts.IsTextPresent(Driver, "SIGN-OFF"))
            {
                Report.UpdateTestLog("Verify Login", "Login failed for invalid user", Status.PASS);
            }
            else
            {
                Report.UpdateTestLog("Verify Login", "Login succeeded for invalid user", Status.FAIL);
            }
        }

        public void VerifyRegistration()
        {
            String userName = DataTable.GetData("General_Data", "Username");

            if (asserts.IsTextPresent(Driver, "Dear " +
                                        DataTable.GetExpectedResult("FirstName") + " " +
                                        DataTable.GetExpectedResult("LastName")))
            {
                Report.UpdateTestLog("Verify Registration",
                                            "User " + userName + " registered successfully", Status.PASS);
            }
            else
            {
                throw new FrameworkException("Verify Registration",
                                                "User " + userName + " registration failed");
            }
        }

        public void VerifyBooking()
        {
            if (asserts.IsTextPresent(Driver, "Your itinerary has been booked!"))
            {
                Report.UpdateTestLog("Verify Booking", "Tickets booked successfully", Status.PASS);

                //WebElement flightConfirmation = driver.findElement(By.xpath("//font/font/b/font"));
                IWebElement flightConfirmation =
                                    Driver.FindElement(By.CssSelector("font > font > b > font"));

                String flightConfirmationNumber = flightConfirmation.Text;
                flightConfirmationNumber = flightConfirmationNumber.Split("#".ToCharArray())[1].Trim();
                DataTable.PutData("Flights_Data", "FlightConfirmationNumber", flightConfirmationNumber);
                Report.UpdateTestLog("Flight Confirmation",
                        "The flight confirmation number is " + flightConfirmationNumber,
                        Status.DONE);
            }
            else
            {
                Report.UpdateTestLog("Verify Booking", "Tickets booking failed", Status.FAIL);
            }
        }
    }
}





