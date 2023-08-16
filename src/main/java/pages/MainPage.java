package pages;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.Path;
import annotations.Template;
import annotations.UrlTemplates;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;


//@UrlTemplates(
//templates = {
//@Template (name = "news", template = "/{1}/news/{2}"),
//@Template(name = "articles", template = "/{1}")
//}
//)
@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  public MainPage(WebDriver driver) {
    super(driver);
  }

  //@FindBy(css = "[class=\"sc-1r3ji37-2 bHvktM\"]")
  //private List<WebElement> articleThumbs;

  private String articleTileSelector = "[class=\"sc-1pljn7g-6 kbUYTE\"]";
  private String articleTitleSelector = articleTileSelector + "a";


  public MainPage pageArticleThumbsShouldBeVisible() {
    List<WebElement> titles = $$(articleTileSelector);
    long actualArticleThumbs = titles.stream().filter(
        (WebElement articleThumb) -> waiters.waitForElementVisible(articleThumb)).count();
    assertThat(actualArticleThumbs)
        .as("")
        .isEqualTo(titles.size());

    return this;
  }

  public String getArticleThumbsTitle(int index) {
    return $$(articleTitleSelector).get(--index).getText();
  }

  public ArticlePage clickArticleThumbsByTitle(String title) {
    List<WebElement> titles = $$(articleTitleSelector)
        .stream()
        .filter((WebElement element) -> element.getText().equals(title))
        .collect(Collectors.toList());

    assertThat(titles)
        .as("Size of titles list should be 1")
        .hasSize(1);
    titles.get(0).click();

    return new ArticlePage(driver);
  }

}
