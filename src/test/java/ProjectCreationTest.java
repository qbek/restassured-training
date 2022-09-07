import org.junit.jupiter.api.Test;
import steps.ProjectSteps;


public class ProjectCreationTest extends BaseTestClass {

    ProjectSteps project = new ProjectSteps();

    @Test
    public void userCanCreateAProject() {
        String projectName = "Szkolenie RestAssured";
        project.create(projectName);
        project.checkDetails();
        project.checkIfAddedToProjectList();
    }
}