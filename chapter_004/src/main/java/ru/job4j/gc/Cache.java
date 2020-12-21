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
        String value = null;
        if (cache.containsKey(key) && cache.get(key) != null) {
            try {
                value = cache.get(key).get();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        if (value == null) {
            System.out.println("Needs to be loaded");
            cache.put(key, new SoftReference<>(loader.apply(key)));
        } else {
            System.out.println("No need to be loaded.");
        }

        return value;
    }
}
