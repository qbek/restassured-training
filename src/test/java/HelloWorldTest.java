import io.restassured.RestAssured;
import org.junit.Test;

public class HelloWorldTest {

//    @Test
    public void helloWrold() {
        System.out.println("Hello world");

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
