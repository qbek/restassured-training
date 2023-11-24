package org.example.testdata;

import com.github.javafaker.Faker;
import org.example.testdata.generators.RandomDataGenerator;
import org.example.testdata.generators.StaticDataGenerator;

public class TestDataGenerator {

    private RandomDataGenerator randamData = new RandomDataGenerator();
    private StaticDataGenerator staticData = new StaticDataGenerator();

    public Project createNewProjectData() {
        var dataType = System.getProperty("td", "random");
        if (dataType.equals("random")) {
            return randamData.getProject();
        } else {
            return staticData.getProject();
        }
    }

    public Task createNewTaskData() {
        var dataType = System.getProperty("td", "random");
        if (dataType.equals("random")) {
            return randamData.getTask();
        } else {
            return staticData.getTask();
        }
    }
}
