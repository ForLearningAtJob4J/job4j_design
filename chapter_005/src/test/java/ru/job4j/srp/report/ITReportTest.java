package ru.job4j.srp.report;

import org.junit.Test;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.MemStore;

import java.util.Calendar;

import static org.junit.Assert.*;

public class ITReportTest {

    @Test
    public void generateTest() {
        MemStore<Employee> store = new MemStore<>();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Employee worker2 = new Employee("Marcel", now, now, 200);
        store.add(worker2);
        Report<Employee> engine = new ITReport(store);

        String expect =
            "<html><body><table width = '100%' border = '1' style='border-collapse: collapse; word-break: break-all;'>" + System.lineSeparator()
            + "<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>" + System.lineSeparator()
            + "<tr><td>Ivan</td><td>" + worker.getHired() + "</td><td>" + worker.getFired() + "</td><td>100.0</td></tr>" + System.lineSeparator()
            + "<tr><td>Marcel</td><td>" + worker2.getHired() + "</td><td>" + worker2.getFired() + "</td><td>200.0</td></tr>" + System.lineSeparator()
            + "</table></body></html>";
        assertEquals(expect, engine.generate(em -> true));
    }
}