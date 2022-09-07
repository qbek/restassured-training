import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;


public class ProjectCreationTest extends BaseTestClass {

    @Test
    public void userCanCreateAProject() {
        String projectName = "Szkolenie RestAssured ze zmiennej";
        long projectId = createNewProject(projectName);
        checkIfProjectIsCreated(projectId, projectName);
        checkIfProjectIsOnAllProjectList(projectId, projectName);
    }


    private void checkIfProjectIsOnAllProjectList(long projectId, String projectName) {
        RestAssured
                .given()
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(format("find{ it.id == %d }.name", projectId), equalTo(projectName));
    }

    private void checkIfProjectIsCreated(long projectId, String projectName) {
        RestAssured
                .given()
                    .pathParam("id", projectId)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("id", equalTo(projectId))
                        .body("name", equalTo(projectName));
    }

    private long createNewProject(String projectName) {
        long projectId = RestAssured
                .given()
                    .body(format("{\"name\": \"%s\"}" , projectName))
                .when()
                    .post("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", equalTo(projectName))
                    .and()
                        .extract().path("id");
        return projectId;
    }


}




