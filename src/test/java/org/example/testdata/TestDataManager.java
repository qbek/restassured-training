package org.example.testdata;

import java.util.HashMap;
import java.util.Map;

public class TestDataManager {

    private TestDataGenerator dataGenerator = new TestDataGenerator();

    private Map<String, Object> data = new HashMap<>();

    public void setTestData(String key, Object value) {
        data.put(key, value);
    }

    public Object getTestData(String key) {
        return data.get(key);
    }

    public Project createProjectData() {
        var newProject = dataGenerator.createNewProjectData();
        data.put("project", newProject);
        return newProject;
    }

    public Task createTaskData() {
        var newTask = dataGenerator.createNewTaskData();
        data.put("task", newTask);
        return newTask;
    }

}
