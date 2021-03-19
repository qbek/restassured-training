package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class ProjecTestDataFactory {

    private static List<ProjectTestDataGenerator> generators;

    static {
        generators = new ArrayList<>();
        generators.add(new RandomProjectTestData());
        generators.add(new StaticProjectTestData());
    }


    public static ProjectRequest createTestProjectData() {
        return getGenerator().getProject();
    }


    private static ProjectTestDataGenerator getGenerator() {
        String data = System.getProperty("data", "random");
        for(int i = 0; i<generators.size(); i++) {
            if (generators.get(i).getType().equals(data)) {
                return generators.get(i);
            }
        }
        return generators.get(0);
    }


}
