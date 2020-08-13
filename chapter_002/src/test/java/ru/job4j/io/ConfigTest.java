package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        Config config = new Config("../data/app.properties");
        config.load();
        assertThat(
                config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect")
        );
    }

    @Test
    public void whenPair() {
        Config config = new Config("../data/pair_without_comment.properties");
        config.load();
        assertNull(config.value("hibernate.connection.url"));
    }
}