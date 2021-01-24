package ru.job4j.isp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MenuItemTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void callPrinterTest() {
        MenuItem root = new MenuItem();

        MenuItem menuItem1 = root.add("Morning duties");
        MenuItem menuSubItem1 = menuItem1.add("Wash hands");
        MenuItem menuSubSubItem1 = menuSubItem1.add("Wash left hand", () -> System.out.println("Changed from cstr"));
        MenuItem menuSubSubItem2 = menuSubItem1.add("Wash right hand");
        menuSubSubItem2.update("Wash right leg");
        MenuItem menuSubItem2 = menuItem1.add("Wash face WILL BE REMOVED!");
        menuSubItem2.remove("1.2");
        MenuItem menuSubItem3 = menuItem1.add("Clean teeth", () -> System.out.println("For FIND"));

        MenuItem menuItem2 = root.add("Daily duties");
        MenuItem menuItem3 = root.add("Evening duties");

        System.setOut(new PrintStream(this.out));
        root.callPrinter(System.out::print);

        menuItem1.doAction();
        menuSubSubItem1.doAction();
        menuItem2.setAction(() -> System.out.println("Changed from setAction")).doAction();
        root.getAction("1.3").doAction();

        assertEquals(
                String.format(
                        "1 Morning duties%n"
                                + "----1.1 Wash hands%n"
                                + "--------1.1.1 Wash left hand%n"
                                + "--------1.1.2 Wash right leg%n"
                                + "----1.3 Clean teeth%n"
                                + "2 Daily duties%n"
                                + "3 Evening duties%n"
                                + "Making default action%n"
                                + "Changed from cstr%n"
                                + "Changed from setAction%n"
                                + "For FIND%n"),
                out.toString()
        );
        System.setOut(this.stdout);
    }
}