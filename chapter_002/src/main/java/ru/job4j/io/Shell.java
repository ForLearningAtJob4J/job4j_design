package ru.job4j.io;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Shell {
    List<String> curDir = new LinkedList<>();

    private void normalize() {
        int i = 0;
        while (i < curDir.size()) {
            if (curDir.get(i).equals("..")) {
                curDir.remove(i--);
                if (curDir.size() > 0 && i < curDir.size()) {
                    curDir.remove(i);
                }
            }
            i++;
        }
    }

    public void cd(String path) {
        String[] p = path.split("/");
        if (path.startsWith("/")) {
            curDir = Arrays.stream(p).collect(Collectors.toList());
        } else {
            curDir.addAll(Arrays.asList(p));
        }
        normalize();
    }

    public String pwd() {
        return "/" + String.join("/", curDir);
    }
}
