import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresInPostAndPutRequestsTests extends TestBase {

    @Test
    void loginSuccessfulTest(){
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .post(login)
                .then()
                .log().all()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void loginUnsuccessfulTest(){
        String data = "{ \"email\": \"peter@klaven\" }";

        given()
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .post(login)
                .then()
                .log().all()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void createUserTest(){

        String data = "{ \"name\": \"Timur\", \"job\": \"Qa Lead\" }";

        given()
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .post(createUser)
                .then()
                .log().body()
                .log().status()
                .body("name", is("Timur"))
                .body("job", is("Qa Lead"))
                .statusCode(201);
    }

    @Test
    void updateUserTest(){

        String data = "{ \"name\": \"Timur Maksyutov\", \"job\": \"Qa Head Lead\" }";

        given()
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .put(singleUser)
                .then()
                .log().body()
                .log().status()
                .body("name", is("Timur Maksyutov"))
                .body("job", is("Qa Head Lead"))
                .statusCode(200);
    }

}
