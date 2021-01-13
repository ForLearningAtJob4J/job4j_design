package ru.job4j.srp.report;

import org.junit.Test;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.MemStore;

import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.*;
import static ru.job4j.srp.report.AccountantReport.RATE;

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
        String expect = String.format(Locale.ROOT,
                "Name; Hired; Fired; Salary%n"
                + "%1$s; %2$td.%2$tm.%2$tY; %3$td.%3$tm.%3$tY; $%4$.2f%n"
                + "%5$s; %6$td.%6$tm.%6$tY; %7$td.%7$tm.%7$tY; $%8$.2f",
                worker.getName(), worker.getHired(), worker.getFired(), worker.getSalary() / RATE,
                worker2.getName(), worker2.getHired(), worker2.getFired(), worker2.getSalary() / RATE);
        assertEquals(expect, engine.generate(all -> true));
   }
}