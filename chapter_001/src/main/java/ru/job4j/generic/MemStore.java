package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean ret = false;
        T lModel = findById(id);
        if (lModel != null) {
            mem.set(mem.indexOf(lModel), model);
            ret = true;
        }

        return ret;
    }

    @Override
    public boolean delete(String id) {
        boolean ret = false;
        T lModel = findById(id);
        if (lModel != null) {
            mem.remove(lModel);
            ret = true;
        }

        return ret;
    }

    @Override
    public T findById(String id) {
        for (T model : mem) {
            if (model.getId().equals(id)) {
                return model;
            }
        }
        return null;
    }
}