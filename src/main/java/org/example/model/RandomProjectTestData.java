package org.example.model;

import com.github.javafaker.Faker;

public class RandomProjectTestData implements ProjectTestDataGenerator {

    private static Faker faker = new Faker();

    @Override
    public ProjectRequest getProject() {
        ProjectRequest project = new ProjectRequest();
        project.setName(faker.zelda().character());
        return project;
    }

    @Override
    public String getType() {
        return "random";
    }
}
