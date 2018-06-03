package steps;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import pages.BookingPage;
import pages.HomePage;
import pages.PaymentPage;

public class Behaviour {

  HomePage homePage;
  BookingPage bookingPage;
  PaymentPage paymentPage;


  public Behaviour(WebDriver driver){
    homePage = new HomePage(driver);
    bookingPage = new BookingPage (driver);
    paymentPage = new PaymentPage(driver);
  }

  @Step
  public void selectOneWayFlight(){homePage.selectOneWayFlight();}
  @Step
  public void setOriginAirport(String flyFrom){homePage.setOriginAirport(flyFrom);}
  @Step
  public void setDestinationAirport(String flyTo){homePage.setDestinationAirport(flyTo);}
  @Step
  public void clickContinueButton(){homePage.clickContinueButton();}
  @Step
  public void setFlyOutDate(String FlightDay, String flightMonth, String flightYear){
    homePage.setFlyOutDate(FlightDay,flightMonth,flightYear);
  }
  @Step
  public void selectPassengersNumber(int adults, int children) {homePage.selectPassengersNumber(adults, children);}
  @Step
  public void submitFlight(){homePage.submitFlight();}
  @Step
  public void selectFirstFlightRetrieved(){bookingPage.selectFirstFlightRetrieved();}
  @Step
  public void selectStandardFare(){bookingPage.selectStandardFare();}
  @Step
  public void continueBooking(){bookingPage.clickContinueButton();}
  @Step
  public void selectPassengerOnSaleSeats (int adults, int children) {bookingPage.selectPassengerOnSaleSeats(adults, children);}
  @Step
  public void confirmSeatsSelection(){bookingPage.confirmSeatsSelection();}
  @Step
  public void discardPriority(){bookingPage.discardPriority();}
  @Step
  public void checkOut(){bookingPage.clickCheckOutButton();}
  @Step
  public void logIn(String email, String password){paymentPage.logIn(email, password);}
  @Step
  public void insertPassengersData(int adult, int children){paymentPage.insertPassengersData(adult, children);}
  @Step
  public void insertPaymentData(String cardNumber, String expirationMonthValue, String expirationYearValueValue, String ccvCode, String holderName)
  {
    paymentPage.insertPaymentData(cardNumber, expirationMonthValue, expirationYearValueValue, ccvCode, holderName);
  }
  @Step
  public void insertBillingAddressData (String address1, String address2, String city, String zipCode, String country){
    paymentPage.insertBillingAddressData(address1, address2, city, zipCode, country);
  }
  @Step
  public void acceptTermsAndConditions(){paymentPage.acceptTermsAndCondition(); }
  @Step
  public void finishPayment(){paymentPage.finishPayment(); }
  @Step
  public void paymentDeclined(){paymentPage.getUnathorizedPaymentErrorMessage(); }

}
