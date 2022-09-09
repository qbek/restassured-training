package org.example.testdata;

import com.github.javafaker.Faker;

public class RandomDataGenerator implements IDataGenerator {

    private Faker randomData = new Faker();

    @Override
    public String generateProjectName() {
        return randomData.funnyName().name();
    }

    @Override
    public String generateTaskName() {
        return randomData.backToTheFuture().quote();
    }

    @Override
    public String getType() {
        return "random";
    }
}
