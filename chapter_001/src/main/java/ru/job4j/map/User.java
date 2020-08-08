package ru.job4j.map;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class User {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    String name;
    Calendar birthday;
    int children;

    public User(String aName) {
        this.name = aName;
        birthday = new GregorianCalendar(2000, Calendar.JANUARY, 1);
    }

    public User() {
        this("Anonymous");
    }

    public User setName(String aName) {
        name = aName;
        return this;
    }

    public User setBirthday(Calendar aDate) {
        birthday = aDate;
        return this;
    }

    public User setChildren(int aChildren) {
        children = aChildren;
        return this;
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

        if (!name.equals(user.name)) {
            return false;
        }
        return birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        return name.charAt(0);
//        int result = name.hashCode();
//        result = 31 * result + birthday.hashCode();
//        return result;
    }
    //    @Override
//    public String toString() {
//        return "User: {" + name + "; ("
//                + dateFormat.format(birthday.getTime()) + ") has "
//                + (children == 0 ? "no" : children)
//                + (children == 1 ? " child" : " children") + "}";
//    }
}
