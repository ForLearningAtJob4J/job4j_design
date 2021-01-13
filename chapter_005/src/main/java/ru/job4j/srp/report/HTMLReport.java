package ru.job4j.srp.report;

import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.Store;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.function.Predicate;

public class HTMLReport implements Report<Employee> {
    CommonReport<Employee> commonReport;

    public HTMLReport(Store<Employee> store) {
        commonReport = new CommonReport<>(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        commonReport
                .setHeader("<html><body><table width = '100%' border = '1' style='border-collapse: collapse; word-break: break-all;'>")
                .setCaptionBounds("<tr><th>", "</th></tr>" + System.lineSeparator())
                .setCaptionDelimiter("</th><th>")
                .setLineBounds("<tr><td>", "</td></tr>")
                .setLineDelimiter("</td><td>")
                .setLineSeparator(System.lineSeparator())
                .setFields(
                        new LinkedHashMap<>() {
                            {
                                put("Name", Employee::getName);
                                put("Hired", e -> String.format("%1$td.%1$tm.%1$tY", e.getHired()));
                                put("Fired", e -> String.format("%1$td.%1$tm.%1$tY", e.getFired()));
                                put("Salary", e -> String.format(Locale.ROOT, "%.2f", e.getSalary()));
                            }
                        })
                .setFooter(System.lineSeparator() + "</table></body></html>");
        return commonReport.generate(filter);
    }
}