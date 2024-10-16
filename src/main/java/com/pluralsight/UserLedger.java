package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UserLedger {
    LocalDate date;
    LocalTime time;
    String vendor;
    String itemDescription;
    Double amountChanged;

    public UserLedger(String date, String time, String itemDescription, String vendor, Double amountChanged) {
        this.amountChanged = amountChanged;
        this.itemDescription = itemDescription;
        this.vendor = vendor;
        this.time = LocalTime.parse(String.valueOf(time));
        this.date = LocalDate.parse(String.valueOf(date));
    }

    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(dtf);
        return formattedDate;
    }

    public String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(dtf);
        return formattedTime;
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