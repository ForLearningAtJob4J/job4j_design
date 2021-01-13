package ru.job4j.srp.report;

import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.Store;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.function.Predicate;

public class XMLReport implements Report<Employee> {
    CommonReport<Employee> commonReport;

    public XMLReport(Store<Employee> store) {
        commonReport = new CommonReport<>(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        final String LS = System.lineSeparator();
        commonReport
                .setHasCaption(false)
                .setHeader("<employees>")
                .setLineBounds("  <employee>" + LS + "    ", LS + "  </employee>")
                .setLineDelimiter(LS + "    ")
                .setLineSeparator(LS)
                .setFields(
                        new LinkedHashMap<>() {
                            {
                                put("Name", e -> "<name>" + e.getName() + "</name>");
                                put("Hired", e -> "<hired>" + String.format("%1$td.%1$tm.%1$tY", e.getHired())  + "</hired>");
                                put("Fired", e -> "<fired>" + String.format("%1$td.%1$tm.%1$tY", e.getFired())  + "</fired>");
                                put("Salary", e -> "<salary>" + String.format(Locale.ROOT, "%.2f", e.getSalary()) + "</salary>");
                            }
                        })
                .setFooter(LS + "</employees>");
        return commonReport.generate(filter);
    }
}