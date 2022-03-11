package org.example.data;

import com.github.javafaker.Faker;

public class RandomDataGenerator {

    private static Faker generator = new Faker();

    public String getProjectName() {
        return generator.commerce().productName();
    }

    public String getTaskName() {
        return generator.witcher().quote();
    }

}
