# SeleniumCucumberSerenityTestingFW
Testing framework based on Selenium, Cucumber and JUnit executed with Maven and that creates Serenity reports.

The feature DeclinePayment is tested. It books a flight for three passengers using an invalid credit card and checks that payment is declined. Data provided to implement this test: 

  Given I make a booking from “DUB” to “SXF” on 12/03/2017 for 2 adults and 1 child
  When I pay for booking with card details “5555 5555 5555 5557”, “10/18” and “265”
  Then I should get payment declined message

The testing framework built is based on Cucumber, which allows us to run automated acceptance tests following BDD principles. Programing language used in test layer is Gherkin and for the rest of layers Java, following an approach based on POM (Page Object Model). Since the application under test is a web application, Selenium has also been integrated. For annotations to run the test, JUnit has been used.
An intermediate layer called Behavior.java has been implemented to isolate application pages from the definition of steps to facilitate the framework maintainability. 

Test requested can be found in DeclinePayment.feature, and can be executed by right clicking on it and selecting Run option for first time. For future times, just by selecting the configuration automatically created and clicking on run button . This way, test is successfully executed on Chrome browser.

Besides, this framework has been prepared to execute features also on Safari browser, in case it is required. For more information, refer to DriverBuilder. Maven command for this: 
mvn clean test -Dsafari.driver.path=pathToSafariDriver

In order to get result reports, open-source Serenity tool has been integrated. Reports are stored in serenity folder: target\site\serenity 
After updating Serenity dependencies in pom.xml file to the latest version, it has been detected that report is not registering passed results. This is a known issue by Serenity community and would need further analysis. For more details: https://github.com/serenity-bdd/serenity-core/issues/1260

NOTES: During the time that I have been working on this assignment, I have been facing some issues related to website under test: 
  •	Very frequently, when I was executing my feature, I got flights at the prize of 0€ for every date. I could not retrieve real prizes after this happened in a long while.  
  •	Very frequently, I got a message indicating that a booking with the same data had already been done, although I never completed any booking.   



