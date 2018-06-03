package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends _Page {
  public HomePage(WebDriver driver){super (driver);}

  @FindBy(id = "lbl-flight-search-type-one-way")private WebElement oneWayRadioButton;
  @FindBy(xpath = "//*[@aria-labelledby='label-airport-selector-from']") private WebElement originAirportInput;
  @FindBy(xpath = "//*[@aria-labelledby='label-airport-selector-to']") private WebElement destinationAirportInput;
  @FindBy(xpath="//*[@class='core-btn-primary core-btn-block core-btn-big']") private WebElement continueButton;
  @FindBy(xpath = "//*[@aria-label='Fly out: - YYYY']") private WebElement flyOutYearInput;
  @FindBy(xpath = "//*[@aria-label='Fly out: - MM']") private WebElement flyOutMonthInput;
  @FindBy(xpath = "//*[@aria-label='Fly out: - DD']") private WebElement flyOutDayInput;
  @FindBy(xpath = "//*[@aria-label='1 Adult (age 16+)']") private WebElement passenersNumberSelector;
  @FindBy(className="inc") private List<WebElement> incPassengersButton;
  @FindBy(xpath = "//*[@ng-keypress='searchFlights()']") private WebElement letsGoButton;
  @FindBy(xpath="//*[@class=\"cookie-popup__content\"]") private WebElement cookiesMessage;
  @FindBy(xpath="//*[@class=\"cookie-popup__close-btn\"]") private WebElement cookiesCloseCross;

  public void selectOneWayFlight(){
    click(cookiesCloseCross);
    click(oneWayRadioButton);
  }

  public void setOriginAirport(String flyFrom){
    setText(originAirportInput,flyFrom);
  }

  public void setDestinationAirport(String flyTo){
    setText(destinationAirportInput,flyTo);
  }

  public void clickContinueButton(){
    click(continueButton);
  }

  public void setFlyOutDate(String FlightDay, String flightMonth, String flightYear){
    setText(flyOutDayInput,FlightDay);
    setText(flyOutMonthInput,flightMonth);
    setText(flyOutYearInput,flightYear);
  }

  public void selectPassengersNumber(int adults, int children) {
    click(passenersNumberSelector);
    for (int count = 1; count < adults; count = count + 1) {
      click(incPassengersButton.get(0));
    }
    for (int count = 1; count <= children; count = count + 1) {
      click(incPassengersButton.get(2));
    }
  }

  public void submitFlight () {
     click(letsGoButton);
  }
}
