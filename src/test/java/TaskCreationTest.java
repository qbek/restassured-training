import org.junit.jupiter.api.Test;
import steps.ProjectSteps;
import steps.TaskSteps;

public class TaskCreationTest extends BaseTestClass {

    ProjectSteps project = new ProjectSteps();

    TaskSteps task = new TaskSteps();

    @Test
    public void userCanAddTaskToTheProject() {
        String projectName = "Projekt z zadaniem2";
        String taskName = "to jest moje zadanie";
        project.create(projectName);
        task.addToProject(taskName, project.getId());
        task.checkDetails();
        task.checkIfAddedToTasksList();
    }

}
