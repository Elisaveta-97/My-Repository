package pages;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.Path;
import annotations.Template;
import annotations.UrlTemplates;
import extensions.PathEmptyException;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.diff.Patch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.AbsPageObject;


public abstract class AbsBasePage<T> extends AbsPageObject {
  private static final String BASE_URL = System.getProperty("base.url");


  @FindBy(tagName = "h1")
  private WebElement header;

  public T pageHeaderShouldBeVisible() {
    assertThat(waiters.waitForElementVisible(header))
        .as("Header should be visible")
        .isTrue();

    return (T) this;
  }

  public T pageHeaderShouldBeSameAs(String header){
    assertThat(this.header.getText())
        .as("Header should be {}", header)
        .isEqualTo(header);

    return (T) this;
  }
  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  @FindBy
  private WebElement element;

  private String getPath() throws PathEmptyException {
    Class<? extends AbsBasePage> clazz = this.getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = clazz.getAnnotation(Path.class);
      return path.value();
    }

    throw new PathEmptyException();
  }

  public T open() throws PathEmptyException {
    String path = getPath();
    String url = "";
    url = BASE_URL + path;
    driver.get(BASE_URL + getPath());
    return (T) this;
  }


  public T open(String name, String[] params) {
    Class<? extends AbsBasePage> clazz = this.getClass();
    if (clazz.isAnnotationPresent(UrlTemplates.class)) {
      UrlTemplates urlTemplates = clazz.getAnnotation(UrlTemplates.class);
      Template[] templates = urlTemplates.templates();

      for (Template template : templates) {
        if (template.name().equals(name)) {
          String urlTemplate = template.template();
          for (int i = 0; i < params.length; i++) {
            urlTemplate.replace(String.format("{%d})", i + 1), params[i]);
          }

          driver.get(BASE_URL + urlTemplate);
        }
      }
    }
    return (T) this;
  }
}
