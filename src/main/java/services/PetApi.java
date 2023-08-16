package services;

import dto.UserDTO;
import dto.UserIdDTO;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PetApi {

    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private static final String BASE_PATH = "/user";
    private RequestSpecification spec;

    public PetApi(){
        spec = given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .log().all();
    }

    public ValidatableResponse createUser(UserDTO userDTO) {

        return given(spec)
                 .body(userDTO)
                .when()
                  .post()
                .then()
                  .log().all();
    }

    public ValidatableResponse createUserInvalid(UserIdDTO userIdDTO) {

        return given(spec)
            .body(userIdDTO)
            .when()
            .post()
            .then()
            .log().all();
    }

    public ValidatableResponse getUserName(String url,String schema, int code) {

        return given(spec)
            .when()
            .get(url)
            .then()
            .statusCode(code)
            .body(matchesJsonSchemaInClasspath(schema));
    }
}
