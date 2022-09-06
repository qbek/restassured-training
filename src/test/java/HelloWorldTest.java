import io.restassured.RestAssured;
import lombok.Getter;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    @Test
    public void helloWorld() {
        RestAssured
                .given()
                    .baseUri("http://numbersapi.com")
                    .log().all()
                .when()
                    .get("/1410/year")
                .then()
                    .log().all();

    }
}
