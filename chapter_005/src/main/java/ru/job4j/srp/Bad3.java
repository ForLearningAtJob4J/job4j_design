package ru.job4j.srp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Bad3 {

    public void save() {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/db",
                "vasyan",
                "password")) {
            Scanner s = new Scanner(System.in);
            s.next();
            //....
            con.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
