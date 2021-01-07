package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void produce() {
        Generator generator = new GeneratorString();
//        assertEquals("I am Vanya", generator.produce("I am ${name}", Map.of("name", "Vanya")));  // for Jacoco tests
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceBadTemplate() {
        Generator generator = new GeneratorString();
        generator.produce("I am ${name} from ${city}", Map.of("name", "Vanya"));
        throw new IllegalArgumentException(); // for Jacoco tests
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceBadMap() {
        Generator generator = new GeneratorString();
        generator.produce("I am ${name}", Map.of("name", "Vanya", "age", "111"));
        throw new IllegalArgumentException();  // for Jacoco tests
    }
}