package org.example.testdata.generators;

import com.github.javafaker.Faker;
import org.example.testdata.Project;
import org.example.testdata.Task;

public class RandomDataGenerator implements IDataGenerator {

    private static Faker faker = new Faker();

    public Project getProject() {
        return new Project(faker.witcher().monster());
    }

    public Task getTask() {
        return new Task(faker.chuckNorris().fact());
    }

    @Override
    public String getType() {
        return "random";
    }
}
