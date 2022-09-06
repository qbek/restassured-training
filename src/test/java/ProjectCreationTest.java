import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;


public class ProjectCreationTest {

    @Test
    public void userCanCreateAProject() {
        long projectId = RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .contentType(ContentType.JSON)
                    .body("{\"name\": \"Szkolenie RestAssured\"}")
                    .log().all()
                .when()
                    .post("/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("name", equalTo("Szkolenie RestAssured"))
                    .and()
                        .extract().path("id");

        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .pathParam("id", projectId)
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .log().all()
                .when()
                    .get("/projects/{id}")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("id", equalTo(projectId))
                        .body("name", equalTo("Szkolenie RestAssured"));


        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .log().all()
                .when()
                    .get("/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body(format("find{ it.id == %d }.name", projectId), equalTo("Szkolenie RestAssured"));



    }

}
