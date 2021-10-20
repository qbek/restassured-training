package org.example.data;

import org.example.model.ProjectRequestPayload;
import org.example.model.TaskRequestPayload;

public class CustomDataGenerator implements DataGeneratorInterface {


    @Override
    public ProjectRequestPayload getProjectData() {
        return new RandomDataGenerator().getProjectData();
    }

    @Override
    public TaskRequestPayload getTaskData() {
        return new StaticDataGenerator().getTaskData();
    }
}
