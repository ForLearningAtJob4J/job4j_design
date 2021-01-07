package ru.job4j.srp.report;

import org.junit.Test;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.MemStore;

import java.util.Calendar;

import static org.junit.Assert.*;

public class AccountantReportTest {

    @Test
    public void generate() {
        MemStore<Employee> store = new MemStore<>();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 75);
        store.add(worker);
        Employee worker2 = new Employee("Marcel", now, now, 150);
        store.add(worker2);
        Report<Employee> engine = new AccountantReport(store);

        String expect = "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + worker.getName() + "; "
                + worker.getHired() + "; "
                + worker.getFired() + "; "
                + "$1.0"
                + System.lineSeparator()
                + worker2.getName() + "; "
                + worker2.getHired() + "; "
                + worker2.getFired() + "; "
                + "$2.0"
                + System.lineSeparator();
        assertEquals(expect, engine.generate(em -> true));
   }
}