import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresInGetRequestTests extends TestBase {

    @Test
    void listUserTotalTest(){
        given()
                .log().uri()
                .when()
                .get(listUsers)
                .then()
                .log().body()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void listUserIdValuesTest(){
        given()
                .log().uri()
                .when()
                .get(listUsers)
                .then()
                .log().body()
                .statusCode(200)
                .body("data.id", hasItems(7, 8, 9, 10, 11, 12));
    }

    @Test
    void singleUserFirstNameTest(){
        given()
                .log().all()
                .when()
                .get(singleUser)
                .then()
                .log().all()
                .statusCode(200)
                .body("data.first_name", is("Janet"));
    }
}
