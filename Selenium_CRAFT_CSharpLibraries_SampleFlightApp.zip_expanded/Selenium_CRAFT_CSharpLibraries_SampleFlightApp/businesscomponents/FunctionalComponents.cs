using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Framework_Reporting;
using OpenQA.Selenium;
using CRAFT.SupportLibraries;
using System.Configuration;

namespace CRAFT.BusinessComponents
{
    /// <summary>
    ///Functional Components class
    /// </summary>
    public class FunctionalComponents : ReusableLibrary
    {
        /// <summary>
        ///  Constructor to initialize the component library
        /// </summary>
        /// <param name="scriptHelper">The ScriptHelper object passed from the DriverScript</param>
        public FunctionalComponents(ScriptHelper scriptHelper): base(scriptHelper)
        {
           
        }

        public void Login()
        {
            Driver.Url = ConfigurationManager.AppSettings["ApplicationUrl"];
            String userName = DataTable.GetData("General_Data", "Username");
            String password = DataTable.GetData("General_Data", "Password");

            Driver.FindElement(By.Name("userName")).SendKeys(userName);
            Driver.FindElement(By.Name("password")).SendKeys(password);
            Driver.FindElement(By.Name("login")).Click();

            Report.UpdateTestLog("Login", "Enter login credentials: " +
                                            "Username = " + userName + ", " +
                                            "Password = " + password, Status.PASS);
        }

        public void RegisterUser()
        {
            Driver.FindElement(By.LinkText("REGISTER")).Click();
            Driver.FindElement(By.Name("firstName")).SendKeys(DataTable.GetData("RegisterUser_Data", "FirstName"));
            Driver.FindElement(By.Name("lastName")).SendKeys(DataTable.GetData("RegisterUser_Data", "LastName"));
            Driver.FindElement(By.Name("phone")).SendKeys(DataTable.GetData("RegisterUser_Data", "Phone"));
            Driver.FindElement(By.Name("userName")).SendKeys(DataTable.GetData("RegisterUser_Data", "Email"));
            Driver.FindElement(By.Name("address1")).SendKeys(DataTable.GetData("RegisterUser_Data", "Address"));
            Driver.FindElement(By.Name("city")).SendKeys(DataTable.GetData("RegisterUser_Data", "City"));
            Driver.FindElement(By.Name("state")).SendKeys(DataTable.GetData("RegisterUser_Data", "State"));
            Driver.FindElement(By.Name("postalCode")).SendKeys(DataTable.GetData("RegisterUser_Data", "PostalCode"));
            Driver.FindElement(By.Name("email")).SendKeys(DataTable.GetData("General_Data", "Username"));
            String password = DataTable.GetData("General_Data", "Password");
            Driver.FindElement(By.Name("password")).SendKeys(password);
            Driver.FindElement(By.Name("confirmPassword")).SendKeys(password);
            Driver.FindElement(By.Name("register")).Click();
            Report.UpdateTestLog("Registration", "Enter user details for registration", Status.DONE);
        }

        public void ClickSignIn()
        {
            Driver.FindElement(By.LinkText("sign-in")).Click();
            Report.UpdateTestLog("Click sign-in", "Click the sign-in link", Status.DONE);
        }

        public void FindFlights()
        {
            Driver.FindElement(By.Name("passCount")).SendKeys((DataTable.GetData("Passenger_Data", "PassengerCount")));
            Driver.FindElement(By.Name("fromPort")).SendKeys((DataTable.GetData("Flights_Data", "FromPort")));
            Driver.FindElement(By.Name("fromMonth")).SendKeys((DataTable.GetData("Flights_Data", "FromMonth")));
            Driver.FindElement(By.Name("fromDay")).SendKeys((DataTable.GetData("Flights_Data", "FromDay")));
            Driver.FindElement(By.Name("toPort")).SendKeys((DataTable.GetData("Flights_Data", "ToPort")));
            Driver.FindElement(By.Name("toMonth")).SendKeys((DataTable.GetData("Flights_Data", "ToMonth")));
            Driver.FindElement(By.Name("toDay")).SendKeys((DataTable.GetData("Flights_Data", "ToDay")));
            Driver.FindElement(By.Name("airline")).SendKeys((DataTable.GetData("Flights_Data", "Airline")));
            Driver.FindElement(By.Name("findFlights")).Click();
            Report.UpdateTestLog("Find Flights", "Search for flights using given test data", Status.DONE);
        }

        public void SelectFlights()
        {
            Driver.FindElement(By.Name("reserveFlights")).Click();
            Report.UpdateTestLog("Select Flights", "Select the first available flights", Status.DONE);
        }

        public void BookFlights()
        {
            String[] passengerFirstNames = DataTable.GetData("Passenger_Data", "PassengerFirstNames").Split(",".ToCharArray());
            String[] passengerLastNames = DataTable.GetData("Passenger_Data", "PassengerLastNames").Split(",".ToCharArray());
            int passengerCount = Convert.ToInt32(DataTable.GetData("Passenger_Data", "PassengerCount"));

            for (int i = 0; i < passengerCount; i++)
            {
                Driver.FindElement(By.Name("passFirst" + i)).SendKeys(passengerFirstNames[i]);
                Driver.FindElement(By.Name("passLast" + i)).SendKeys(passengerLastNames[i]);
            }
            Driver.FindElement(By.Name("creditCard")).SendKeys(DataTable.GetData("Passenger_Data", "CreditCard"));
            Driver.FindElement(By.Name("creditnumber")).SendKeys(DataTable.GetData("Passenger_Data", "CreditNumber"));

            Report.UpdateTestLog("Book Tickets", "Enter passenger details and book tickets", Status.SCREENSHOT);

            Driver.FindElement(By.Name("buyFlights")).Click();
        }

        public void BackToFlights()
        {
            Driver.FindElement(By.XPath("//a/img")).Click();
        }

        public void Logout()
        {
            Driver.FindElement(By.LinkText("SIGN-OFF")).Click();
            Report.UpdateTestLog("Logout", "Click the sign-off link", Status.DONE);
        }
    }
}
