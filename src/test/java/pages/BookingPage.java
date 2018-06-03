package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookingPage extends _Page {
  public BookingPage(WebDriver driver){super (driver);}

  @FindBy(xpath="//*[@class=\"flight-header__min-price hide-mobile\"]") private WebElement firstFlightRetrieved;
  @FindBy(className="standard") private WebElement standardFare;
  @FindBy(xpath="//*[@class=\"core-btn-primary core-btn-block core-btn-medium\"]") private WebElement continueButton;
  @FindBy(xpath="//button[@class=\"core-btn-primary same-seats\"]") private WebElement okButton;
  @FindBy(xpath="//div[contains(@class,'seat-group-ONSALE')]/span[not(contains(@class,'reserved'))]") List<WebElement> availableOnSaleSeatsList;
  @FindBy(xpath="//button[@class=\"core-btn-primary dialog-overlay-footer__ok-button\"]") private WebElement reviewSeatsButton;
  @FindBy(xpath="//button[@class=\"core-btn-primary dialog-overlay-footer__ok-button\"]") private WebElement confirmSeatsButton;
  @FindBy(xpath="//*[@class=\"priority-boarding-with-bags-popup__close core-link-inline\"]") private WebElement noPriorityButton;
  @FindBy(xpath="//*[@class=\"core-btn-primary core-btn-block core-btn-medium btn-text\"]") private WebElement checkOutButton;
  @FindBy(xpath="//*[@class=\"popup-msg__button popup-msg__button--cancel\"]")private WebElement noThanksButton;

  public void selectFirstFlightRetrieved(){
    click(firstFlightRetrieved);
  }

  public void selectStandardFare(){
    click(standardFare);
  }

  public void clickContinueButton(){
    click(continueButton);
  }

  public void selectPassengerOnSaleSeats(int adults, int children){
    int count=0;
    int seats = adults + children;
    click(okButton);
    while(count<seats&&(!availableOnSaleSeatsList.isEmpty())){
    click(availableOnSaleSeatsList.get(count));
    count++;
    }
  }

  public void confirmSeatsSelection(){
    click(reviewSeatsButton);
    click(confirmSeatsButton);
  }

  public void discardPriority(){
    click(noPriorityButton);
  }

  public void clickCheckOutButton(){
    click(checkOutButton);
  }

}
