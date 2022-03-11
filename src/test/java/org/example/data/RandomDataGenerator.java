package org.example.data;

import com.github.javafaker.Faker;

public class RandomDataGenerator implements IDataGenerator {

    private static Faker generator = new Faker();

    @Override
    public String getProjectName() {
        return generator.commerce().productName();
    }

    public String getTaskName() {
        return generator.witcher().quote();
    }

    @Override
    public String getType() {
        return "random";
    }
}
