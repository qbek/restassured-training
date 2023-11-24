package org.example.testdata.generators;

import com.github.javafaker.Faker;
import org.example.testdata.Project;
import org.example.testdata.Task;

public class RandomDataGenerator {

    private static Faker faker = new Faker();

    public Project getProject() {
        return new Project(faker.witcher().monster());
    }

    public Task getTask() {
        return new Task(faker.chuckNorris().fact());
    }
}
