package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Person {
    private final Boolean male;
    private Integer age;
    private final Contact contact;
    private final String[] statuses;

    public Boolean getMale() {
        return male;
    }

    public Integer getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

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
                && age.equals(person.age)
                && Objects.equals(contact, person.contact)
                && Arrays.equals(statuses, person.statuses);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(male, age, contact);
        result = 31 * result + Arrays.hashCode(statuses);
        return result;
    }

    public static void main(String[] args) throws JSONException {

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("male", person.getMale());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());
    }
}