package org.example.testdata.generators;

import org.example.testdata.Project;
import org.example.testdata.Task;

public class JiraDataGenerator implements IDataGenerator {
    @Override
    public Project getProject() {
        return new Project("Data form JIRA");
    }

    @Override
    public Task getTask() {
        return new Task("Task from JIRA");
    }
}
