package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String ret = values.get(key);
        if (ret == null) {
            throw new IllegalArgumentException("Args don't have such key!");
        }
        return ret;
    }

    private void parse(String[] args) {
        for (String arg: args) {
            if (arg.startsWith("-") && arg.contains("=")) {
                int pos = arg.indexOf("=");
                String k = arg.substring(1, pos);
                String v = arg.substring(pos + 1);
                values.put(k, v);
            } else {
                throw new IllegalArgumentException("Usage: java -jar argsname.jar -name1=value1 -name2=value2 ...");
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(jvm.get("out"));
    }
}