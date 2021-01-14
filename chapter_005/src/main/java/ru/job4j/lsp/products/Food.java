package ru.job4j.lsp.products;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ru.job4j.lsp.ControlQuality.datePointer;

public abstract class Food {
    private String name;
    private LocalDateTime expiredDate;
    private LocalDateTime createdDate;
    private Double price = 0d;
    private int discount = 0;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setDiscount(int i) {
        discount = i;
    }

    public int getDiscount() {
        return discount;
    }

    public int getTimePercent() {
        if (datePointer.isBefore(createdDate)) {
            return 0;
        }
        if (datePointer.isAfter(expiredDate)) {
            return 100;
        }
        long total = createdDate.until(expiredDate, ChronoUnit.DAYS);
        long spent = createdDate.until(datePointer, ChronoUnit.DAYS);
        return (int) ((double) spent / total * 100);
    }
}