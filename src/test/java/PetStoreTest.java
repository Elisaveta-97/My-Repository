import dto.UserDTO;
import dto.UserResponseDTO;
import dto.UserIdDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.PetApi;

import static org.hamcrest.Matchers.equalTo;

public class PetStoreTest {

  //Отправка запроса с заполненными полями валидными значениями
  @Test
  public void createUser() {
    PetApi petApi = new PetApi();

    UserDTO userDTO = UserDTO.builder()
        .phone("890")
        .email("q@w.ru")
        .password("12121")
        .username("Oleg")
        .firstName("FirstName")
        .lastName("LastName")
        .userStatus(405L)
        .id(87078L)
        .build();

    petApi.createUser(userDTO)
        .statusCode(HttpStatus.SC_OK)
        .body("code", equalTo(200))
        .body("type", equalTo("unknown"))
        .body("message", equalTo("87078"))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));
  }

  //Отправка запроса без полей
  @Test
  public void createEmptyUser() {
    PetApi petApi = new PetApi();

    UserDTO userDTO = UserDTO.builder()
        .build();

    ValidatableResponse response = petApi.createUser(userDTO);
    UserResponseDTO actualUser = response.extract().body().as(UserResponseDTO.class);

    String actualMessage = response.extract().jsonPath().get("message");

    Assertions.assertEquals(200L, actualUser.getCode(), "Incorrect code");
    Assertions.assertEquals("unknown", actualUser.getType(), "Incorrect type");
    Assertions.assertEquals("0", actualMessage, "Incorrect message");
  }

  //Отправка запроса с невалидным значением поля id
  @Test
  public void createUserInvalid() {
    PetApi petApi = new PetApi();

    UserIdDTO userIdDTO = UserIdDTO.builder()
        .phone("890")
        .email("q@w.ru")
        .password("12121")
        .username("Oleg")
        .firstName("FirstName")
        .lastName("LastName")
        .userStatus(405L)
        .id("ntn")
        .build();

    petApi.createUserInvalid(userIdDTO)
        .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
        .body("code", equalTo(500))
        .body("type", equalTo("unknown"))
        .body("message", equalTo("something bad happened"))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));
  }

}
