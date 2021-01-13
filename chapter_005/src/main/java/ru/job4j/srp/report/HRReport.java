package ru.job4j.srp.report;

import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.Store;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.function.Predicate;

public class HRReport implements Report<Employee> {
    CommonReport<Employee> commonReport;
    public HRReport(Store<Employee> store) {
        commonReport = new CommonReport<>(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        commonReport.setFields(
                new LinkedHashMap<>() { {
                    put("Name", Employee::getName);
                    put("Salary", e -> String.format(Locale.ROOT, "%.2f", e.getSalary()));
                } })
                .setOrder(Comparator.comparing(Employee::getSalary).reversed());
        return commonReport.generate(filter);
    }
}
