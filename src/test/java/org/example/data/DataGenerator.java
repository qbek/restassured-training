package org.example.data;

import org.example.model.ProjectRequestPayload;
import org.example.model.TaskRequestPayload;
import org.jruby.RubyProcess;

public class DataGenerator {

    public static ProjectRequestPayload getProjectData () {
        return getInterface().getProjectData();
    }

    public static TaskRequestPayload getTaskData() {
        return getInterface().getTaskData();
    }

    private static DataGeneratorInterface getInterface() {
        switch (System.getProperty("data", "random")) {
            case "random":
                return new RandomDataGenerator();
            case "static":
                return new StaticDataGenerator();
            case "custom":
                return new CustomDataGenerator();
            default:
                throw new RuntimeException("You want to use not existing generator");
        }
    }
}
