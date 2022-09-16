package org.example.testdata;

public class TestDataManager {

    RandomDataGenerator randomData = new RandomDataGenerator();
    StaticDataGenerator staticData = new StaticDataGenerator();

    public String getProjectName() {
        String dataType = System.getProperty("td");

        if (dataType.equals("random")) {
            return randomData.getProjectName();
        } else if (dataType.equals("static")) {
            return staticData.getProjectName();
        }

        throw new RuntimeException("Invalid td parameter");
    }

    public String getTaskName() {
        String dataType = System.getProperty("td");

        if (dataType.equals("random")) {
            return randomData.getTaskName();
        } else if (dataType.equals("static")) {
            return staticData.getTaskName();
        }

        throw new RuntimeException("Invalid td parameter");
    }
}
