package ru.job4j.srp.report;

import ru.job4j.srp.store.Store;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class CommonReport<T> implements Report<T> {
    private final Store<T> store;
    private String header = "";
    private String footer = "";
    private String lineBegin = "";
    private String lineSeparator = "; ";
    private String lineEnd = System.lineSeparator();
    private String captionBegin = "";
    private String captionSeparator = "; ";
    private String captionEnd = System.lineSeparator();

    private Map<String, Function<T, String>> fieldGen = new LinkedHashMap<>();

    private Comparator<T> comparator;

    public CommonReport(Store<T> store) {
        this.store = store;
    }

    public CommonReport<T> setOrder(Comparator<T> comparator) {
        this.comparator = comparator;
        return this;
    }

    public CommonReport<T> setFields(Map<String, Function<T, String>> fieldGen) {
        this.fieldGen = fieldGen;
        return this;
    }

    public CommonReport<T> setHeader(String header) {
        this.header = header;
        return this;
    }

    public CommonReport<T> setFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public CommonReport<T> setLineBounds(String begin, String end) {
        this.lineBegin = begin;
        this.lineEnd = end;
        return this;
    }

    public CommonReport<T> setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
        return this;
    }

    public CommonReport<T> setCaptionBounds(String begin, String end) {
        this.captionBegin = begin;
        this.captionEnd = end;
        return this;
    }

    public CommonReport<T> setCaptionSeparator(String captionSeparator) {
        this.captionSeparator = captionSeparator;
        return this;
    }

    private void generateHeader(StringBuilder result) {
        if (!header.isEmpty()) {
            result.append(header).append(System.lineSeparator());
        }
        if (!captionBegin.isEmpty()) {
            result.append(captionBegin);
        }
        for (var fieldName: fieldGen.keySet()) {
            result.append(fieldName).append(captionSeparator);
        }
        result.delete(result.length() - captionSeparator.length(), result.length());
        if (!captionEnd.isEmpty()) {
            result.append(captionEnd);
        }
    }

    private void generateBody(StringBuilder result, Predicate<T> filter) {
        List<T> tempList = new ArrayList<>(store.findBy(filter));
        if (comparator != null) {
            tempList.sort(comparator);
        }
        for (var employee: tempList) {
            if (!lineBegin.isEmpty()) {
                result.append(lineBegin);
            }
            for (var getter: fieldGen.values()) {
                result.append(getter.apply(employee)).append(lineSeparator);
            }
            result.delete(result.length() - lineSeparator.length(), result.length());
            if (!lineEnd.isEmpty()) {
                result.append(lineEnd);
            }
        }
    }

    private void generateFooter(StringBuilder result) {
        if (!footer.isEmpty()) {
            result.append(footer);
        }
    }

    @Override
    public String generate(Predicate<T> filter) {
        StringBuilder result = new StringBuilder();

        generateHeader(result);
        generateBody(result, filter);
        generateFooter(result);

        return result.toString();
    }
}