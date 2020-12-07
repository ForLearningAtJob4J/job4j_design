package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        Config config = new Config("../data/app.properties");
        config.load();
        assertEquals(
                config.value("hibernate.dialect"),
                "org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test
    public void whenPair() {
        Config config = new Config("../data/pair_without_comment.properties");
        config.load();
        assertNull(config.value("hibernate.connection.url"));
    }
}