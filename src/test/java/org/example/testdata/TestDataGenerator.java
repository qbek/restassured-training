package org.example.testdata;

public class TestDataGenerator {

    IDataGenerator randomData = new RandomDataGenerator();
    IDataGenerator staticData = new StaticDataGenerator();


    public String getProjectName() {
        return getGenerator().generateProjectName();
    }

    public String getTaskName() {
       return getGenerator().generateTaskName();
    }

    private IDataGenerator getGenerator() {
        String testDataType = System.getProperty("td");
        if (testDataType.equals("random")) {
            return randomData;
        } else if (testDataType.equals("static")) {
            return staticData;
        }

        throw new RuntimeException("Missing td parameter in test execution");
    }
}
