package org.example.data;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    List<IDataGenerator> dataGenerators;

    public DataGenerator() {
        dataGenerators = new ArrayList<>();
        dataGenerators.add(new RandomDataGenerator());
        dataGenerators.add(new StaticDataGenerator());
        dataGenerators.add(new DatabaseDataGenerator());
    }

    public IDataGenerator getDataGenerator() {
        String td = System.getProperty("td");

        for (IDataGenerator generator : dataGenerators) {
            if(generator.getType().equals(td)) {
                return generator;
            }
        }
        throw new RuntimeException("Invalid test data generator in cmd line: " + td);
    }
}
