package ru.job4j.srp.report;

import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.Store;

import java.util.LinkedHashMap;
import java.util.function.Predicate;

public class ITReport implements Report<Employee> {
    CommonReport<Employee> commonReport;
    public ITReport(Store<Employee> store) {
        commonReport = new CommonReport<>(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        commonReport
                .setHeader("<html><body><table width = '100%' border = '1' style='border-collapse: collapse; word-break: break-all;'>")
                .setCaptionBounds("<tr><th>", "</th></tr>" + System.lineSeparator())
                .setCaptionSeparator("</th><th>")
                .setLineBounds("<tr><td>", "</td></tr>" + System.lineSeparator())
                .setLineSeparator("</td><td>")
                .setFields(
                        new LinkedHashMap<>() { {
                            put("Name", Employee::getName);
                            put("Hired", e -> e.getHired().toString());
                            put("Fired", e -> e.getFired().toString());
                            put("Salary", e -> Double.toString(e.getSalary()));
                        } })
                .setFooter("</table></body></html>");
        return commonReport.generate(filter);
    }

}
