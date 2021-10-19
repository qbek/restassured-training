package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.Before;
import org.junit.Test;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class TaskCreationTest extends Base {

    @Steps
    TaskSteps steps;

    @Test
    public void user_can_add_task_to_the_project() {
        steps.userAddsTaskToTheProject();
        steps.userChecksTaskDetails();
        steps.userChecksIfTaskIsListedWithAllTasks();
    }

}
