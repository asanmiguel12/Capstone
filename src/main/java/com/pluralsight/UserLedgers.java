package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserLedgers {
    String entries;
    Double deposits;
    Double payments;
    String reports;
    String date;
    String time;
    String vendor;
    String itemDescription;
    Double amountChanged;


     public UserLedgers(String date, String time, String itemDescription, String vendor, Double amountChanged) {
        this.date = date;
        this.time = time;
        this.itemDescription = itemDescription;
        this.vendor = vendor;
        this.amountChanged = amountChanged;



    }

    public String getEntries() {
        return entries;
    }

    public void setEntries(String entries) {
        this.entries = entries;
    }

    public Double getDeposits() {
        return deposits;
    }

    public void setDeposits(Double deposits) {
        this.deposits = deposits;
    }

    public Double getPayments() {
        return payments;
    }

    public void setPayments(Double payments) {
        this.payments = payments;
    }

    public String getReports() {
        return reports;
    }

    public void setReports(String reports) {
        this.reports = reports;
    }

    public String getDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(date);
        return formattedDate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(time);
        return formattedTime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Double getAmountChanged() {
        return amountChanged;
    }

    public void setAmountChanged(Double amountChanged) {
        this.amountChanged = amountChanged;
    }

    public void reports() {
        System.out.println("");
        return;



    }
    public void deposit() {
        System.out.println("What is your");



    }
}