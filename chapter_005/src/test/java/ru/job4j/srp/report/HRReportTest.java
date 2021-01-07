package ru.job4j.srp.report;

import org.junit.Test;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.MemStore;

import java.util.Calendar;

import static org.junit.Assert.*;

public class HRReportTest {

    @Test
    public void generateTest() {
        MemStore<Employee> store = new MemStore<>();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Employee worker2 = new Employee("Marcel", now, now, 200);
        store.add(worker2);
        Report<Employee> engine = new HRReport(store);

        String expect = "Name; Salary"
                + System.lineSeparator()
                + worker2.getName() + "; "
                + worker2.getSalary()
                + System.lineSeparator()
                + worker.getName() + "; "
                + worker.getSalary()
                + System.lineSeparator();
        assertEquals(expect, engine.generate(all -> true));
    }
}