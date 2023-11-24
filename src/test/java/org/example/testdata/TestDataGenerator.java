package org.example.testdata;

import org.example.testdata.generators.IDataGenerator;
import org.example.testdata.generators.JiraDataGenerator;
import org.example.testdata.generators.RandomDataGenerator;
import org.example.testdata.generators.StaticDataGenerator;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    private List<IDataGenerator> generators = new ArrayList<>();

    public TestDataGenerator() {
        generators.add(new RandomDataGenerator());
        generators.add(new StaticDataGenerator());
        generators.add(new JiraDataGenerator());
    }

    public Project createNewProjectData() {
        return selectDataGenerator().getProject();
    }

    public Task createNewTaskData() {
        return selectDataGenerator().getTask();
    }

    private IDataGenerator selectDataGenerator() {
        var dataType = System.getProperty("td", "random");
        for (var generator : generators) {
            if (generator.getType().equals(dataType)) return generator;
        }
        throw new RuntimeException("Test data generator not found");
    }
}
