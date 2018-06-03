package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class _Page {
  protected final WebDriver driver;

  public _Page(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public WebDriver getDriver() {
    return driver;
  }

  private void waitForElementToBeEnable(WebElement element) {
    WebDriverWait wait = new WebDriverWait(getDriver(), 15);
    sleep(); //Not optimal solution, but makes sure all elements are enable when operating on them
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }

   private void sleep() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  protected static void elementIsPresent(final WebDriver driver, WebElement element, int time) {
    try {

      WebDriverWait wait = new WebDriverWait(driver, time);
      wait.until(ExpectedConditions.visibilityOf(element));
      Assert.assertTrue(element.isDisplayed());

    } catch (Exception e) {
      System.out.println("Element is not present: " + e);
    }
  }

  public void click(WebElement element) {
    waitForElementToBeEnable(element);
    element.click();
  }

  public void setText(WebElement element, String text) {
    waitForElementToBeEnable(element);
    element.clear();
    element.sendKeys(text);
  }

  public void setTextInDrop(WebElement element, String text) {
    waitForElementToBeEnable(element);
    element.sendKeys(text);
  }

  public String getCharForNumber(int i) {
    return i > 0 && i < 27 ? String.valueOf((char)(i + 'A' - 1)) : null;
  }
}
