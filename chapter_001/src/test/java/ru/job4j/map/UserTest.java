package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void justForIllustration() {
        User user1 = new User("Ivan");
        User user2 = new User("Ilona");
        User user3 = new User("Regina");
        User user4 = new User("Alex");
        User user5 = new User("Albert");
        User user6 = new User("Smurf");

        Map<User, Object> map = new HashMap<>();
        map.put(user1, "1");
        map.put(user2, "2");
        map.put(user3, "3");
        map.put(user4, "4");
        map.put(user5, "5");
        map.put(user6, "6");

        System.out.println(map);
        for (User u :map.keySet()) {
            System.out.println(u.hashCode());
        }
    }
}