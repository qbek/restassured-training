package org.example.data;

import com.github.javafaker.Faker;
import org.example.model.ProjectRequestPayload;
import org.example.model.TaskRequestPayload;

public class RandomDataGenerator implements DataGeneratorInterface {

    private static Faker generator = new Faker();

    @Override
    public ProjectRequestPayload getProjectData () {
        return new ProjectRequestPayload(generator.beer().name());
    }

    @Override
    public TaskRequestPayload getTaskData() {
        return new TaskRequestPayload(generator.princessBride().quote());
    }


}
