package org.example.testdata.generators;

import org.example.testdata.Project;
import org.example.testdata.Task;

public class StaticDataGenerator implements IDataGenerator {

    public Project getProject() {
        return new Project("Statyczna nazwa projektu");
    }

    public Task getTask() {
        return new Task("Statyczna nazwa zadania");
    }

    @Override
    public String getType() {
        return "static";
    }
}
