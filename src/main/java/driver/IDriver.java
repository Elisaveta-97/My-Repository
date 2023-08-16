package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public interface IDriver {

  public WebDriver newDriver();

  default void downloadLocalWebDriver(String browser) throws Exception {
    Config wdmConfig = WebDriverManager.getInstance().config();
    wdmConfig.setAvoidBrowserDetection(true);

    String browserVersion = System.getProperty("browser.version", "115.0");

    if (!browserVersion.isEmpty()) {
      switch (browser) {
        case "chrome":
          wdmConfig.setChromeDriverVersion(browserVersion);
          break;
        default:
          throw new Exception("Browser not supported");
      }
    }
    WebDriverManager.getInstance().setup();
  }
}

