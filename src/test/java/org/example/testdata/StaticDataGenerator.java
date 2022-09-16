package org.example.testdata;

public class StaticDataGenerator implements IDataGenerator {

    public String getProjectName() {
        return "Statyczna nazwa projektu";
    }

    public String getTaskName() {
        return "Statyczna nazwa taska";
    }

    @Override
    public String getGeneratorType() {
        return "static";
    }
}
