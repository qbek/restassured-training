package org.example.testdata;

import org.example.testdata.generators.IDataGenerator;
import org.example.testdata.generators.JiraDataGenerator;
import org.example.testdata.generators.RandomDataGenerator;
import org.example.testdata.generators.StaticDataGenerator;

public class TestDataGenerator {

    private IDataGenerator randomData = new RandomDataGenerator();
    private IDataGenerator staticData = new StaticDataGenerator();
    private IDataGenerator jiraData = new JiraDataGenerator();

    public Project createNewProjectData() {
        return selectDataGenerator().getProject();
    }

    public Task createNewTaskData() {
        return selectDataGenerator().getTask();
    }

    private IDataGenerator selectDataGenerator() {
        var dataType = System.getProperty("td", "random");

        switch (dataType) {
            case "random": return randomData;
            case "static": return staticData;
            case "jira": return jiraData;
            default: throw new RuntimeException("Not supported test data generator");
        }
    }
}
