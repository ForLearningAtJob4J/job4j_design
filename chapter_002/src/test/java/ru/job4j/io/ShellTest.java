package ru.job4j.io;

import org.junit.Test;

import java.nio.file.FileSystems;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShellTest {
    static final String PS = FileSystems.getDefault().getSeparator();

    @Test
    public void whenCdBack() {
        Shell shell = new Shell();
        shell.cd(PS + "user" + PS + "..");
        assertThat(
                shell.pwd(), is(PS)
        );
    }

    @Test
    public void whenCdRoot() {
        Shell shell = new Shell();
        shell.cd(PS);
        assertThat(
                shell.pwd(), is(PS)
        );
    }

    @Test
    public void whenCdUserLocal() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("local");
        assertThat(
                shell.pwd(), is(PS + "user" + PS + "local")
        );
    }

    @Test
    public void whenCdUserBack() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("..");
        assertThat(
                shell.pwd(), is(PS)
        );
    }
}