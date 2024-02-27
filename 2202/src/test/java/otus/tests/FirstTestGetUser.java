package otus.tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;
import otus.pojo.Data;
import otus.pojo.Support;
import otus.pojo.User;

import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;


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

    run(http()
        .client("restClientReqres")
        .send()
        .get("users/" + context.getVariable("userId"))
    );

    run(http()
        .client("restClientReqres")
        .receive()
        .response(HttpStatus.OK)
        .message()
        //.body("{\n" +
            //"    \"data\": {\n" +
           // "        \"id\": 2,\n" +
           // "        \"email\": \"janet.weaver@reqres.in\",\n" +
           // "        \"first_name\": \"Janet\",\n" +
           // "        \"last_name\": \"Weaver\",\n" +
           // "        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" +
           // "    },\n" +
           // "    \"support\": {\n" +
           // "        \"url\": \"https://reqres.in/#support-heading\",\n" +
           // "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
           // "    }\n" +
           // "}")
        //.body(new ObjectMappingPayloadBuilder(getResponseData(),"objectMapper"))
        .body(new ClassPathResource("json/user2.json"))
    );

  }

  public User getResponseData() {
    User user = new User();

    Data data = new Data();
    data.setId(Integer.valueOf(context.getVariable("userId")));
    data.setEmail("janet.weaver@reqres.in");
    data.setFirstName("Janet");
    data.setLastName("Weaver");
    data.setAvatar("https://reqres.in/img/faces/2-image.jpg");
    user.setData(data);

    Support support = new Support();
    support.setUrl("https://reqres.in/#support-heading");
    support.setText("To keep ReqRes free, contributions towards server costs are appreciated!");

    user.setSupport(support);

    return user;
  }
}
