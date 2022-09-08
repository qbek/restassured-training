package steps;

import io.restassured.RestAssured;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class ProjectSteps {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void checkIfAddedToProjectList() {
        RestAssured
                .given()
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(format("find{ it.id == %d }.name", id), equalTo(name));
    }

    public void checkDetails() {
        RestAssured
                .given()
                    .pathParam("id", id)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("id", equalTo(id))
                        .body("name", equalTo(name));
    }

    public void create(String projectName) {
        name = projectName;
        id = RestAssured
                .given()
                    .body(format("{\"name\": \"%s\"}" , name))
                .when()
                    .post("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", equalTo(name))
                    .and()
                        .extract().path("id");
    }
}
