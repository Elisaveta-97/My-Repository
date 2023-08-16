package factory;


import driver.ChromeWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class FactoryDriver {

  private final String browserType = System.getProperty("browser");

  public EventFiringWebDriver getDriver() throws Exception {
    switch (this.browserType.trim().toLowerCase(Locale.ROOT)) {
      case "firefox": {
        WebDriverManager.firefoxdriver().setup();
        return new EventFiringWebDriver(new FirefoxDriver());
      }
      case "opera": {
        WebDriverManager.operadriver().setup();
        return new EventFiringWebDriver(new OperaDriver());
      }
      case "chrome": {
        WebDriverManager.chromedriver().setup();
        return new EventFiringWebDriver(new ChromeWebDriver().newDriver());      }
      default:
        throw new Exception(this.browserType);
    }
  }
}
