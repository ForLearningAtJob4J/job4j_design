package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShellTest {

    @Test
    public void whenCdBack() {
        Shell shell = new Shell();
        shell.cd("/user/..");
        assertEquals(shell.pwd(), "/");
    }

    @Test
    public void whenCdRoot() {
        Shell shell = new Shell();
        shell.cd("/");
        assertEquals(shell.pwd(), "/");
    }

    @Test
    public void whenCdUserLocal() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("local");
        assertEquals(shell.pwd(), "/user/local");
    }

    @Test
    public void whenCdUserBack() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("..");
        assertEquals(shell.pwd(), "/");
    }

    @Test
    public void whenCdRootBack() {
        Shell shell = new Shell();
        shell.cd("..");
        assertEquals(shell.pwd(), "/");
    }
}