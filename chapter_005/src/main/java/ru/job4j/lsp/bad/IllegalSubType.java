package ru.job4j.lsp.bad;

import java.util.Objects;

/**
 * Нарушение - усиление предусловий
 */
public class IllegalSubType extends SuperType {
    public String formatName(String name) {
        if (Objects.isNull(name) || name.isEmpty() || name.length() < 4) {
            throw new IllegalArgumentException("name must be at least 4 characters long");
        }
        return name;
    }
}

class SuperType {
    public String formatName(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        return name;
    }
}