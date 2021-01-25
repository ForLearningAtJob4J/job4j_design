package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonGSONTest {

    @Test
    public void testObjectToJsonByGson() {
        final Person person = new Person(false, 30, new Contact("11-111"),
                "Employed", "Married");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        assertEquals(gson.toJson(person), "{\"male\":false,\"age\":30,\"contact\":{\"phone\":\"11-111\"},\"statuses\":[\"Employed\",\"Married\"]}");
    }

    @Test
    public void testJsonToObjectByGson() {
        final Gson gson = new GsonBuilder().create();

        final String personJson =
                "{"
                        + "\"male\":true,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(111)111-111-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Unemployed\",\"Free\"]"
                        + "}";
        final Person personGson = gson.fromJson(personJson, Person.class);
        assertEquals(personGson, new Person(true, 35, new Contact("+7(111)111-111-11"),
                "Unemployed", "Free"));
    }
}