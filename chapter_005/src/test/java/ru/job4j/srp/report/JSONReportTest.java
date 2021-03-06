package ru.job4j.srp.report;

import org.junit.Test;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.MemStore;

import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.*;

public class JSONReportTest {
    @Test
    public void generateTest() {
        MemStore<Employee> store = new MemStore<>();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Employee worker2 = new Employee("Marcel", now, now, 200);
        store.add(worker2);
        Report<Employee> engine = new JSONReport(store);

        String expect = String.format(Locale.ROOT,
                "[%n"
                + "  {%n"
                + "    \"name\": \"%1$s\"%n"
                + "    \"hired\": \"%2$td.%2$tm.%2$tY\"%n"
                + "    \"fired\": \"%3$td.%3$tm.%3$tY\"%n"
                + "    \"salary\": %4$.2f%n"
                + "  },%n"
                + "  {%n"
                + "    \"name\": \"%5$s\"%n"
                + "    \"hired\": \"%6$td.%6$tm.%6$tY\"%n"
                + "    \"fired\": \"%7$td.%7$tm.%7$tY\"%n"
                + "    \"salary\": %8$.2f%n"
                + "  }%n"
                + "]",
                worker.getName(), worker.getHired(), worker.getFired(), worker.getSalary(),
                worker2.getName(), worker2.getHired(), worker2.getFired(), worker2.getSalary());
        assertEquals(expect, engine.generate(all -> true));
    }
}