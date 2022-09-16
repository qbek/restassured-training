package org.example.testdata;

import java.util.ArrayList;
import java.util.List;

public class TestDataManager {

    List<IDataGenerator> generators;

    String dataType = System.getProperty("td");

    public TestDataManager() {
        generators = new ArrayList<>();
        generators.add(new RandomDataGenerator());
        generators.add(new StaticDataGenerator());
    }


    public String getProjectName() {
      return selectGenerator().getProjectName();
    }

    public String getTaskName() {
        return selectGenerator().getTaskName();
    }

    private IDataGenerator selectGenerator() {
        for (IDataGenerator generator : generators) {
            if (generator.getGeneratorType().equals(dataType)) {
                return generator;
            }
        }
        throw new RuntimeException("Invalid td parameter");
    }
}
