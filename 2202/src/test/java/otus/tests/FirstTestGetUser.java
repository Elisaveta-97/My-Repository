package otus.tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.testng.annotations.Test;

import static com.consol.citrus.actions.EchoAction.Builder.echo;

public class FirstTestGetUser extends TestNGCitrusSpringSupport {
  private TestContext context;

  @Test(description =  "Получение информации о пользователе")
  @CitrusTest
  public void getTestAction() {
    this.context = citrus.getCitrusContext().createTestContext();


    context.setVariable("value","superValue");
    $(echo("Property \"value\" = " + context.getVariable("value")));

    $(echo("We have userId = " + context.getVariable("userId")));

    $(echo("Property \"value\" = " + "${userId}"));

    variable("now", "citrus:currentDate()");
    $(echo("Today is: ${now}"));

  }
}
