package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.function.Function;

public class Cache {
    private final HashMap<String, SoftReference<String>> cache = new HashMap<>();
    Function<String, String> loader;

    public Cache(Function<String, String> loader) {
        this.loader = loader;
    }

    public String get(String key) {
        SoftReference<String> value = null;
        if (cache.containsKey(key)) {
            value = cache.get(key);
        }

        if (value == null || value.get() == null) {
            System.out.println(value);
            System.out.println("Needs to be loaded because of value is " + (value == null ? "null" : "not null, but its reference is null"));
            value = new SoftReference<>(loader.apply(key));
            cache.put(key, value);
        } else {
            System.out.println("No need to be loaded.");
        }

        return value.get();
    }
}
