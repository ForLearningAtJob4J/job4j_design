package ru.job4j.srp.report;

import org.junit.Test;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.MemStore;

import java.util.Calendar;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class CommonReportTest {

    @Test
    public void generateTest() {
        MemStore<Employee> store = new MemStore<>();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Employee worker2 = new Employee("Marcel", now, now, 200);
        store.add(worker2);
        Report<Employee> engine = new CommonReport<>(store)
                .setFields(
                        new LinkedHashMap<>() { {
                            put("Name", Employee::getName);
                            put("Hired", e -> e.getHired().toString());
                            put("Fired", e -> e.getFired().toString());
                            put("Salary", e -> Double.toString(e.getSalary()));
                        } });

        String expect = "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + worker.getName() + "; "
                + worker.getHired() + "; "
                + worker.getFired() + "; "
                + worker.getSalary()
                + System.lineSeparator()
                + worker2.getName() + "; "
                + worker2.getHired() + "; "
                + worker2.getFired() + "; "
                + worker2.getSalary()
                + System.lineSeparator();
        assertEquals(expect, engine.generate(all -> true));
    }
}