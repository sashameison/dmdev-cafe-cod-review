package com.narozhnyi.generator;

import com.narozhnyi.model.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;

@UtilityClass
public class UserGenerator {

    public static User generateRandomUser() {
        return buildRandomUser(new Random());
    }

    private static User buildRandomUser(Random random) {
        var userNames = List.of("Alex", "Dima", "Denis", "Kolya", "Vanya", "Masha", "Katya", "Vika");
        var username = userNames.get(random.nextInt(0, userNames.size()));

        return new User(random.nextInt(1, 10000), username);
    }
}
