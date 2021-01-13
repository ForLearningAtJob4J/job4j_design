package ru.job4j.srp.report;

import ru.job4j.srp.store.Store;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class CommonReport<T> implements Report<T> {
    public static final String LS = System.lineSeparator(); //"\n"
    private final Store<T> store;

    private String header = "";
    private String footer = "";

    private boolean hasCaption = true;
    private String captionBegin = "";
    private String captionEnd = LS;
    private String captionDelimiter = "; ";

    private String lineBegin = "";
    private String lineSeparator = LS;
    private String lineEnd = "";

    private String lineDelimiter = "; ";

    private Map<String, Function<T, String>> fieldGen = new LinkedHashMap<>();

    private Comparator<T> comparator;

    public CommonReport<T> setHasCaption(boolean hasCaption) {
        this.hasCaption = hasCaption;
        return this;
    }

    public CommonReport<T> setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
        return this;
    }

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

    public CommonReport<T> setLineDelimiter(String lineDelimiter) {
        this.lineDelimiter = lineDelimiter;
        return this;
    }

    public CommonReport<T> setCaptionBounds(String begin, String end) {
        this.captionBegin = begin;
        this.captionEnd = end;
        return this;
    }

    public CommonReport<T> setCaptionDelimiter(String captionDelimiter) {
        this.captionDelimiter = captionDelimiter;
        return this;
    }

    private void generateHeader(StringBuilder result) {
        if (!header.isEmpty()) {
            result.append(header).append(LS);
        }
        if (hasCaption) {
            if (!captionBegin.isEmpty()) {
                result.append(captionBegin);
            }

            for (var fieldName: fieldGen.keySet()) {
                result.append(fieldName).append(captionDelimiter);
            }
            result.delete(result.length() - captionDelimiter.length(), result.length());

            if (!captionEnd.isEmpty()) {
                result.append(captionEnd);
            }
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
                result.append(getter.apply(employee)).append(lineDelimiter);
            }
            result.delete(result.length() - lineDelimiter.length(), result.length());
            if (!lineEnd.isEmpty()) {
                result.append(lineEnd);
            }
            if (!lineSeparator.isEmpty()) {
                result.append(lineSeparator);
            }
        }
        if (!lineSeparator.isEmpty()) {
            result.delete(result.length() - lineSeparator.length(), result.length());
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