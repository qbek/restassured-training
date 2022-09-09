package org.example.testdata;

public class StaticDataGenerator implements IDataGenerator {

    @Override
    public String generateProjectName() {
        return "Statyczna nazwa projektu";
    }

    @Override
    public String generateTaskName() {
        return "Statyczna nazwa taska";
    }

    @Override
    public String getType() {
        return "static";
    }
}
