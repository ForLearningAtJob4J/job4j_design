package ru.job4j;

import java.util.*;

public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        Info ret = new Info();
        Hashtable<Integer, User> ht = new Hashtable<>(previous.size() + current.size());
        for (User user: previous) {
            ht.put(user.id, user);
        }
        for (User user: current) {
            User prevUser = ht.get(user.id);
            if (prevUser == null) {
                ret.added++;
            } else {
                if (!prevUser.name.equals(user.name)) {
                    ret.changed++;
                }
                ht.remove(user.id);
            }
        }
        ret.deleted = ht.size();
        return ret;
    }

    public static class User implements Comparable<User> {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int compareTo(User o) {
            return Integer.compare(id, o.id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            User user = (User) o;

            return id == user.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    public static class Info {

        int added;
        int changed;
        int deleted;
    }
}