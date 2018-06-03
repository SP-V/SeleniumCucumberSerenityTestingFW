package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class PaymentPage extends _Page {

  public PaymentPage(WebDriver driver) {
    super(driver);
  }

  //Log in
  @FindBy(xpath = "//button[@class=\"core-btn-secondary\"]") private WebElement logInButton;
  @FindBy(xpath = "//input[@placeholder='Email address']")private WebElement emailInput;
  @FindBy(xpath = "//input[@name=\"password\"]") private WebElement passwordInput;
  @FindBy(xpath = "//button[@type=\"submit\"]") private WebElement submitButton;

  //Passenger data
  @FindBy(className="core-select")private List<WebElement> passengerTitleDropdownList;
  @FindBy(xpath = "//option[@label='Mrs']") private List<WebElement> mrsOptionList;
  @FindBy(xpath = "//option[@label='Mr']") private List<WebElement> mrOptionList;
  @FindBy(xpath = "//input[@placeholder='e.g. John']") private List<WebElement> passengerNameInputList;
  @FindBy(xpath = "//input[@placeholder='e.g. Smith']") private List<WebElement> passengerSurnameInputList;

   //Payment data
  @FindBy(name = "cardNumber") private WebElement cardNumberInput;
  @FindBy(name = "cardType") private WebElement cardTypeDropDown;
  @FindBy(xpath = "//option[@label='MasterCard']") private WebElement masterCardOption;
  @FindBy(name = "expiryMonth") private WebElement expirationMonthDropDown;
  @FindBy(name = "expiryYear") private WebElement expirationYearDropDown;
  @FindBy(name = "securityCode") private WebElement ccvCodeInput;
  @FindBy(name = "cardHolderName") private WebElement cardHolderNameInput;

  //Billing address
  @FindBy(id = "billingAddressAddressLine1") private WebElement address1Input;
  @FindBy(id = "billingAddressAddressLine2") private WebElement address2Input;
  @FindBy(id = "billingAddressCity") private WebElement cityInput;
  @FindBy(id = "billingAddressPostcode") private WebElement zipCodeInput;
  @FindBy(id = "billingAddressCountry") private WebElement countryDropDown;
  @FindBy(xpath = "//option[@label='Spain']") private WebElement spainCountryOption;

  //Acept terms & conditions
  @FindBy(name="acceptPolicy") private WebElement termsCheckBox;
  //Submit
  @FindBy(xpath = "//button[@class=\"core-btn-primary core-btn-medium\"]") private WebElement payNowButton;
  //Unauthorized payment error
  @FindBy(xpath = "//prompt[@text=\"common.components.payment_forms.error_explain_declined\"]")
  private WebElement unauthorizedPaymentErrorMessage;

  public void logIn(String email, String password){
    click(logInButton);
    setText(emailInput, email);
    setText(passwordInput, password);
    click(submitButton);
  }

  public void insertPassengersData(int adults, int children){
    int count = 0;
    int countChild=0;
    String name = "Name";
    String surname = "Surname";
    while (count < adults) {
      click(passengerTitleDropdownList.get(count));
      if (count%2==0){click(mrsOptionList.get(count));}
      else{click(mrOptionList.get(count));}
      name = "Name" + getCharForNumber(count+1);
      surname = "Surname" + getCharForNumber(count+1);
      setText(passengerNameInputList.get(count), name);
      setText(passengerSurnameInputList.get(count), surname);
      count++;
    }

    while (countChild<children){
      name = "Name" + getCharForNumber(count+1);
      surname = "Surname" + getCharForNumber(count+1);
      setText(passengerNameInputList.get(count), name);
      setText(passengerSurnameInputList.get(count), surname);
      countChild++;
      count++;
    }
  }

  public void insertPaymentData(String cardNumber, String expirationMonth, String expirationYear,
      String ccvCode, String holderName) {
    String cardFirst4Digits = trim(cardNumber.substring(0, cardNumber.length() - 15));
    String cardSecond4Digits = trim(cardNumber.substring(5, cardNumber.length() - 10));
    String cardThird4Digits = trim(cardNumber.substring(10, cardNumber.length() - 5));
    String cardFourth4Digits = (trim(cardNumber.substring(cardNumber.length() - 5)));
    setText(cardNumberInput, cardFirst4Digits);
    cardNumberInput.sendKeys(cardSecond4Digits);
    cardNumberInput.sendKeys(cardThird4Digits);
    cardNumberInput.sendKeys(cardFourth4Digits);
    click(cardTypeDropDown);
    click(masterCardOption);
    setTextInDrop(expirationMonthDropDown, expirationMonth);
    setTextInDrop(expirationYearDropDown, expirationYear);
    setText(ccvCodeInput, ccvCode);
    setText(cardHolderNameInput, holderName);
  }

  public void insertBillingAddressData(String address1, String address2, String city, String zipCode, String country) {
    setText(address1Input, address1);
    setText(address2Input, address2);
    setText(cityInput, city);
    setText(zipCodeInput, zipCode);
    setTextInDrop(countryDropDown, country);
  }

  public void acceptTermsAndCondition() {
    Actions actions = new Actions(driver);
    actions.moveToElement(termsCheckBox);
    actions.click();
    actions.perform();
  }

  public void finishPayment() {
    click(payNowButton);
  }

  public WebElement getUnathorizedPaymentErrorMessage(){
    elementIsPresent(driver, unauthorizedPaymentErrorMessage,5000);
    return driver.findElement(By.cssSelector("prompt[text=\"common.components.payment_forms.error_explain_declined\"]"));
  }

}











