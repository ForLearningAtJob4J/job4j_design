package ru.job4j.srp.report;

import org.junit.Test;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.MemStore;

import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.*;

public class HTMLReportTest {

    @Test
    public void generateTest() {
        MemStore<Employee> store = new MemStore<>();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Employee worker2 = new Employee("Marcel", now, now, 200);
        store.add(worker2);
        Report<Employee> engine = new HTMLReport(store);

        String expect = String.format(Locale.ROOT,
                "<html><body><table width = '100%%' border = '1' style='border-collapse: collapse; word-break: break-all;'>%n"
                 + "<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>%n"
                 + "<tr><td>%1$s</td><td>%2$td.%2$tm.%2$tY</td><td>%3$td.%3$tm.%3$tY</td><td>%4$.2f</td></tr>%n"
                 + "<tr><td>%5$s</td><td>%6$td.%6$tm.%6$tY</td><td>%7$td.%7$tm.%7$tY</td><td>%8$.2f</td></tr>%n"
                 + "</table></body></html>",
                worker.getName(), worker.getHired(), worker.getFired(), worker.getSalary(),
                worker2.getName(), worker2.getHired(), worker2.getFired(), worker2.getSalary());
        assertEquals(expect, engine.generate(all -> true));
    }
}