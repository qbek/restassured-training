package org.example.data;

import org.example.model.ProjectRequestPayload;
import org.example.model.TaskRequestPayload;

public class StaticDataGenerator implements DataGeneratorInterface {
    @Override
    public ProjectRequestPayload getProjectData() {
        return new ProjectRequestPayload("Projekt statyczny");
    }

    @Override
    public TaskRequestPayload getTaskData() {
        return new TaskRequestPayload("Task statyczny");
    }
}
