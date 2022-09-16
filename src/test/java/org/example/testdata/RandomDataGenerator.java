package org.example.testdata;

import com.github.javafaker.Faker;

public class RandomDataGenerator {

    private static Faker generator = new Faker();

    public String getProjectName() {
        return generator.programmingLanguage().creator();
    }

    public String getTaskName() {
        return generator.chuckNorris().fact();
    }
}
