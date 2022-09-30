package org.example.data;

public class StaticDataGenerator implements IDataGenerator {
    @Override
    public String getProjectName() {
        return "Jestem statycznym projektem";
    }

    @Override
    public String getTaskName() {
        return "JEstem statycznym taskiem";
    }

    @Override
    public String getGeneratorType() {
        return "static";
    }
}
