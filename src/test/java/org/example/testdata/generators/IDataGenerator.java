package org.example.testdata.generators;

import org.example.testdata.Project;
import org.example.testdata.Task;

public interface IDataGenerator {

    Project getProject();
    Task getTask();
}
