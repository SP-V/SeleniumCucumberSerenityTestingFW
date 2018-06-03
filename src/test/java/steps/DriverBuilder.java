package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.concurrent.TimeUnit;

public class DriverBuilder {
  public static WebDriver driver;
  public long executionWaitTimeOutInSeconds;

  @Before
  public void setupTest(){
    setUpDriver();
    driver.manage().window().maximize();
  }

  private void setUpDriver() {

    String chromeDriverPath = System.getProperty("chrome.driver.path");
    String safariDriverPath = System.getProperty("safari.driver.path");

    if(chromeDriverPath != null) {
      driver = getChromeDriver(chromeDriverPath);
    } else if(safariDriverPath != null) {
      driver = getSafariDriver(safariDriverPath);
    } else {
      driver = getChromeClasspathDriver();
    }
  }

  private WebDriver getSafariDriver(String safariDriverPath) {
    System.setProperty("webdriver.safari.driver", safariDriverPath);
    SafariOptions options = new SafariOptions();
    return new SafariDriver(options);
  }

  private WebDriver getChromeDriver(String chromeDriverPath) {
    this.executionWaitTimeOutInSeconds=15;
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("test-type","--start-maximized");
    System.setProperty("webdriver.chrome.driver",chromeDriverPath);
    capabilities.setCapability("chrome.debuggerAddress", "127.0.0.1:4723");
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    driver.manage().timeouts().implicitlyWait(this.executionWaitTimeOutInSeconds, TimeUnit.SECONDS);
    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    return new ChromeDriver(capabilities);
  }

  private WebDriver getChromeClasspathDriver() {
    System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
    return new ChromeDriver();
  }

  private String getChromeDriverPath (){
    return getClass().getClassLoader().getResource("chromedriver/chromedriver.exe").getPath();
  }

  @After
  public void quit(){
    driver.quit();
  }
}
