package ru.job4j.srp.report;

import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.Store;

import java.util.LinkedHashMap;
import java.util.function.Predicate;

public class AccountantReport implements Report<Employee> {
    CommonReport<Employee> commonReport;
    public AccountantReport(Store<Employee> store) {
        commonReport = new CommonReport<>(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        commonReport.setFields(
                new LinkedHashMap<>() { {
                    put("Name", Employee::getName);
                    put("Hired", e -> e.getHired().toString());
                    put("Fired", e -> e.getFired().toString());
                    put("Salary", e -> "$" + (e.getSalary() / 75));
                } });
        return commonReport.generate(filter);
    }
}
