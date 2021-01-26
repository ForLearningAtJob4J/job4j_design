package ru.job4j.isp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MenuTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void callPrinterTest() {
        Menu mainMenu = new Menu();

        String menuItem1Key = mainMenu.add("", "Morning duties");
        String menuSubItem1Key = mainMenu.add(menuItem1Key, "Wash hands");
        String menuSubSubItem1Key = mainMenu.add(menuSubItem1Key, "Wash left hand", () -> System.out.println("Changed from add"));
        String menuSubSubItem2Key = mainMenu.add(menuSubItem1Key, "Wash right hand");
        mainMenu.update(menuSubSubItem2Key, "Wash right leg");
        String  menuSubItem2Key = mainMenu.add(menuItem1Key, "Wash face WILL BE REMOVED!");
        mainMenu.remove(menuSubItem2Key);
        String menuSubItem3Key = mainMenu.add(menuItem1Key, "Clean teeth");

        String menuItem2Key = mainMenu.add("", "Daily duties");
        String menuItem3Key = mainMenu.add("", "Evening duties");

        System.setOut(new PrintStream(this.out));
        mainMenu.callPrinter(System.out::print);

        mainMenu.getAction(menuItem1Key).doAction();
        mainMenu.getAction(menuSubSubItem1Key).doAction();

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
                                + "Changed from add%n"),
                out.toString()
        );
        System.setOut(this.stdout);
    }
}