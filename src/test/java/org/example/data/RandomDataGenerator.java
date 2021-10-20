package org.example.data;

import com.github.javafaker.Faker;
import org.example.model.ProjectRequestPayload;

public class RandomDataGenerator {

    private static Faker generator = new Faker();

    public ProjectRequestPayload getProjectData () {
        return new ProjectRequestPayload(generator.beer().name());
    }



}
