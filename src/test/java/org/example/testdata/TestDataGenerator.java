package org.example.testdata;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    List<IDataGenerator> generators;

    public TestDataGenerator() {
        generators = new ArrayList<>();
        generators.add(new RandomDataGenerator());
        generators.add(new StaticDataGenerator());
    }

    public String getProjectName() {
        return getGenerator().generateProjectName();
    }

    public String getTaskName() {
       return getGenerator().generateTaskName();
    }

    private IDataGenerator getGenerator() {
        String testDataType = System.getProperty("td");
        for (IDataGenerator generator : generators) {
            if (generator.getType().equals(testDataType)) {
                return generator;
            }
        }
        throw new RuntimeException("Missing td parameter in test execution");
    }
}
