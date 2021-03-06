package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean ret = false;
        int i = findIndexById(id);
        if (i != -1) {
            mem.set(i, model);
            ret = true;
        }

        return ret;
    }

    @Override
    public boolean delete(String id) {
        boolean ret = false;
        int i = findIndexById(id);
        if (i != -1) {
            mem.remove(i);
            ret = true;
        }

        return ret;
    }

    @Override
    public T findById(String id) {
        int i = findIndexById(id);
        if (i != -1) {
            return mem.get(i);
        }
        return null;
    }

    public int findIndexById(String id) {
        for (int i = 0; i < mem.size(); i++) {
            if (Objects.equals(mem.get(i).getId(), id)) {
                return i;
            }
        }
        return -1;
    }
}