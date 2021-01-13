package ru.job4j.srp.report;

import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.Store;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.function.Predicate;

public class JSONReport implements Report<Employee> {
    CommonReport<Employee> commonReport;

    public JSONReport(Store<Employee> store) {
        commonReport = new CommonReport<>(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        final String LS = System.lineSeparator();
        commonReport
                .setHasCaption(false)
                .setHeader("[")
                .setLineBounds("  {" + LS + "    ", LS + "  }")
                .setLineDelimiter(LS + "    ")
                .setLineSeparator("," + LS)
                .setFields(
                        new LinkedHashMap<>() {
                            {
                                put("Name", e -> "\"name\": \"" + e.getName() + "\"");
                                put("Hired", e -> "\"hired\": \"" + String.format("%1$td.%1$tm.%1$tY", e.getHired())  + "\"");
                                put("Fired", e -> "\"fired\": \"" + String.format("%1$td.%1$tm.%1$tY", e.getFired())  + "\"");
                                put("Salary", e -> "\"salary\": " + String.format(Locale.ROOT, "%.2f", e.getSalary()));
                            }
                        })
                .setFooter(LS + "]");
        return commonReport.generate(filter);
    }
}