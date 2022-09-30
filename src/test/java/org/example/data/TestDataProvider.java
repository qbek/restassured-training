package org.example.data;

public class TestDataProvider {

    private String dataType = System.getProperty("td");

    private RandomDataGenerator random = new RandomDataGenerator();
    private StaticDataGenerator stat = new StaticDataGenerator();

    public String getProjectName() {
        if (dataType.equals("random")) {
            return random.getProjectName();
        } else if (dataType.equals("static")) {
            return stat.getProjectName();
        }
        throw new RuntimeException("You need to declare test data type");
    }

    public String getTaskName() {
        if (dataType.equals("random")) {
            return random.getTaskName();
        } else if (dataType.equals("static")) {
            return stat.getTaskName();
        }
        throw new RuntimeException("You need to declare test data type");
    }
}
