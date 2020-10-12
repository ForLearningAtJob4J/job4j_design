package ru.job4j.serialization.json;

import java.util.Arrays;
import java.util.Objects;

public class Person {
    private final boolean male;
    private int age;
    private final Contact contact;
    private final String[] statuses;

    public Person(boolean male, int age, Contact contact, String... statuses) {
        this.male = male;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "male=" + male
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return male == person.male
                && age == person.age
                && Objects.equals(contact, person.contact)
                && Arrays.equals(statuses, person.statuses);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(male, age, contact);
        result = 31 * result + Arrays.hashCode(statuses);
        return result;
    }
}