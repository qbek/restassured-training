package org.example.data;

public class StaticDataGenerator implements IDataGenerator {

    public String getProjectName() {
        return "Jestem statycznym projektem";
    }

    public String getTaskName() {
        return "Jestem zadaniem statycznym";
    }

    @Override
    public String getType() {
        return "static";
    }
}
