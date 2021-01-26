package ru.job4j.serialization.json;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PersonJSONTest {

    @Test
    public void testObjectToJsonByJSON() {
        final Person person = new Person(false, 30, new Contact("11-111"),
                "Employed", "Married");

        /* Преобразуем объект person в json-строку. */
        JSONObject jsonObjectFromString = new JSONObject("{\"male\":false,\"age\":30,\"contact\":{\"phone\":\"11-111\"},\"statuses\":[\"Employed\",\"Married\"]}");
        JSONObject jsonObjectFromObject = new JSONObject(person);
        assertTrue(jsonObjectFromString.similar(jsonObjectFromObject));
    }
}
