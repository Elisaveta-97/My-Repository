package main;

import annotations.Driver;
import extensions.PathEmptyException;
import extensions.UIExtension1;
import extensions.UIExtensions;

import factory.FactoryDriver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.MainPage;


@ExtendWith(UIExtension1.class)
public class OtusTest {

  @Driver
  public WebDriver driver;

  @Test
  public void testArticleThumbs() throws PathEmptyException {
    MainPage mainPage = new MainPage(driver)
        .open()
        //.pageHeaderShouldBeVisible()
        .pageArticleThumbsShouldBeVisible();

    String title = mainPage.getArticleThumbsTitle(1);
    mainPage
        .clickArticleThumbsByTitle(title)
        .pageHeaderShouldBeSameAs(title);

  }

}
