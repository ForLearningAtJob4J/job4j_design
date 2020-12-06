package ru.job4j;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void testConvert() {
        List<Task.User> users  = new ArrayList<>() { {
            add(new Task.User("user1", new HashSet<>() {
                {
                    add("xxx@ya.ru");
                    add("foo@gmail.com");
                    add("lol@mail.ru");
                }
            }));
            add(new Task.User("user2", new HashSet<>() {
                {
                    add("foo@gmail.com");
                    add("ups@pisem.net");
                }
            }));
            add(new Task.User("user3", new HashSet<>() {
                {
                    add("xyz@pisem.net");
                    add("vasya@pupkin.com");
                }
            }));
            add(new Task.User("user4", new HashSet<>() {
                {
                    add("ups@pisem.net");
                    add("aaa@bbb.ru");
                }
            }));
            add(new Task.User("user5", new HashSet<>() {
                {
                    add("xyz@pisem.net");
                }
            }));
        } };

        Map<Task.User, Set<String>> realUsers = Task.convert(users);
        Task.convert(users).forEach((key, value) -> System.out.println(key.name + "=" + value.toString()));
        assertEquals(realUsers.keySet().size(), 2);
    }
}