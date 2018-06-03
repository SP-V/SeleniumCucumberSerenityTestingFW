package steps;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {
  Behaviour behaviour;
  String baseURL = "https://www.ryanair.com/ie/en";
  String email = "rositagarciagarciagarcia@gmail.com";
  String password = "FlightWithRyanair12";
  String holderName ="NameA SurnameA";
  String address1 = "Calle Flores, Nº5";
  String address2= "Calle Petunias, Nº2";
  String city = "Getafe";
  String zipCode = "28901";
  String country ="Spain";

  @Given("^I search for flights from (.*) to (.*) on (.*)/(.*)/(.*) for (.*)adults and (.*) children$")
  public void searchForFlights(String origin, String destination, String flightDay, String flightMonth, String flightYear, int adults, int children){
    behaviour = new Behaviour(DriverBuilder.driver);
    DriverBuilder.driver.get(baseURL);

    behaviour.selectOneWayFlight();
    behaviour.setOriginAirport(origin);
    behaviour.setDestinationAirport(destination);
    behaviour.clickContinueButton();
    behaviour.setFlyOutDate(flightDay,flightMonth,flightYear);
    behaviour.selectPassengersNumber(adults, children);
    behaviour.submitFlight();
  }

  @And("^I select the first flight for (.*) adults and (.*)children$")
  public void selectFirstFlight(int adults, int children){
    behaviour.selectFirstFlightRetrieved();
    behaviour.selectStandardFare();
    behaviour.continueBooking();
    behaviour.selectPassengerOnSaleSeats(adults, children);
    behaviour.confirmSeatsSelection();
    behaviour.discardPriority();
    behaviour.checkOut();
  }

  @When ("^I log in$")
  public void logIn(){
    behaviour.logIn(email,password);
  }

  @And ("I insert valid data for (.*) adults and (.*) children$")
  public void insertPassengersData(int adults, int children){
    behaviour.insertPassengersData(adults, children);
  }

  @And ("I try to pay for booking with card details (.*), (.*)/(.*) and (.*)$")
  public void insertCardDetails(String cardNumber, String expirationMonth, String expirationYear, String ccvCode){
    behaviour.insertPaymentData(cardNumber, expirationMonth, expirationYear, ccvCode, holderName);
  }

  @And ("I insert billing address data$")
  public void insertBillingAddressData(){
    behaviour.insertBillingAddressData(address1, address2, city, zipCode, country);
  }

  @And ("I accept terms and conditions$")
  public void acceptTermsAndCondition(){
    behaviour.acceptTermsAndConditions();
  }

  @Then("I get payment declined message$")
  public void paymentDeclinedMessageDisplayed(){
    behaviour.finishPayment();
    behaviour.paymentDeclined();
  }
}

