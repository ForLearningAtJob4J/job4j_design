package ru.job4j.srp.report;

import org.junit.Test;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.MemStore;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;

import static java.lang.String.format;
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
                            put("Hired", e -> format("%1$td.%1$tm.%1$tY", e.getHired()));
                            put("Fired", e -> format("%1$td.%1$tm.%1$tY", e.getFired()));
                            put("Salary", e -> format(Locale.ROOT, "%.2f", e.getSalary()));
                        } });

        String expect = format(Locale.ROOT,
                "Name; Hired; Fired; Salary%n"
                + "%1$s; %2$td.%2$tm.%2$tY; %3$td.%3$tm.%3$tY; %4$.2f%n"
                + "%5$s; %6$td.%6$tm.%6$tY; %7$td.%7$tm.%7$tY; %8$.2f",
                worker.getName(), worker.getHired(), worker.getFired(), worker.getSalary(),
                worker2.getName(), worker2.getHired(), worker2.getFired(), worker2.getSalary()
        );
        assertEquals(expect, engine.generate(all -> true));
    }
}