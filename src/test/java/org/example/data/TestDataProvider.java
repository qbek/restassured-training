package org.example.data;

import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    private String dataType = System.getProperty("td");

    private List<IDataGenerator> generators = new ArrayList<>();

    public TestDataProvider() {
        generators.add(new RandomDataGenerator());
        generators.add(new StaticDataGenerator());
    }


    public String getProjectName() {
        return getGenerator().getProjectName();
    }

    public String getTaskName() {
        return getGenerator().getTaskName();
    }

    private IDataGenerator getGenerator() {
        for (IDataGenerator generator : generators) {
            if (dataType.equals(generator.getGeneratorType())) {
                return generator;
            }
        }
        throw new RuntimeException("You need to declare test data type");
    }

}
