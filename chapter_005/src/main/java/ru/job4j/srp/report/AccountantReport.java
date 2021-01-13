package ru.job4j.srp.report;

import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.Store;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.function.Predicate;

public class AccountantReport implements Report<Employee> {
    public static final int RATE = 75;
    CommonReport<Employee> commonReport;
    public AccountantReport(Store<Employee> store) {
        commonReport = new CommonReport<>(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        commonReport.setFields(
                new LinkedHashMap<>() { {
                    put("Name", Employee::getName);
                    put("Hired", e -> String.format("%1$td.%1$tm.%1$tY", e.getHired()));
                    put("Fired", e -> String.format("%1$td.%1$tm.%1$tY", e.getFired()));
                    put("Salary", e -> "$" + String.format(Locale.ROOT, "%.2f", e.getSalary() / RATE));
                } });
        return commonReport.generate(filter);
    }
}
