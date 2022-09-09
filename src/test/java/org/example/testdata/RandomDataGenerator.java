package org.example.testdata;

import com.github.javafaker.Faker;

public class RandomDataGenerator implements IDataGenerator {

    private Faker randomData = new Faker();

    public String generateProjectName() {
        return randomData.funnyName().name();
    }

    public String generateTaskName() {
        return randomData.backToTheFuture().quote();
    }

    public void hello() {};
}
