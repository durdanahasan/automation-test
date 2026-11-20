package com.grocery.utils;

import net.datafaker.Faker;

public class DataGenerator {

    private static final Faker faker = new Faker();

    public static String randomName() {
        return faker.name().firstName();
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }
}