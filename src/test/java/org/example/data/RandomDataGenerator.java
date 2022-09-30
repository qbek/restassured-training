package org.example.data;

import com.github.javafaker.Faker;

public class RandomDataGenerator {

    private static Faker generator = new Faker();

    public String getProjectName() {
        return generator.gameOfThrones().character();
    }

    public String getTaskName() {
        return generator.chuckNorris().fact();
    }
}
