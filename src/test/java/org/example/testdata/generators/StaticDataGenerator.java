package org.example.testdata.generators;

import org.example.testdata.Project;
import org.example.testdata.Task;

public class StaticDataGenerator {

    public Project getProject() {
        return new Project("Statyczna nazwa projektu");
    }

    public Task getTask() {
        return new Task("Statyczna nazwa zadania");
    }
}
