package ru.job4j.gc;

import com.sun.management.ThreadMXBean;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    static final int ARRAY_SIZE = 2000;

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public long getAge() {
        return age;
    }

    User(String aName, int aAge) {
        name = "name";
        age = aAge;
    }

    @Override
    protected void finalize() {
        System.out.println("GC is sweeping " + name);
    }

    public static void main(String[] args) throws Exception {
        String a = "name".trim();

        long tid = Thread.currentThread().getId();

        System.out.println(VM.current().details());
        User user = new User(a, 1);

        System.out.println(ClassLayout.parseInstance(new char[1]).toPrintable());
        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        System.out.println(ClassLayout.parseInstance(a.getBytes()).toPrintable());
        System.out.println(ClassLayout.parseClass(Class.forName("java.lang.String")).toPrintable());

        System.out.println("Waiting outside function");
        new Scanner(System.in).next();
        user = new User(a, 1);
        generate("Name");
        new Scanner(System.in).next();
        System.out.println(user.getAge());
//
        generate("Flame");
        System.out.println("Waiting outside function");
        new Scanner(System.in).next();
        System.out.println("Sleeping for 5 seconds");
        Thread.sleep(5000);
    }

    static void generate(String name) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            users.add(new User(name + i, i));
        }
    }
}
