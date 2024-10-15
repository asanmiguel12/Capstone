package com.pluralsight;

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


    UserLedgers(String date, String time, String itemDescription, String vendor, Double amountChanged) {
        this.date = date;
        this.time = time;
        this.itemDescription = itemDescription;
        this.vendor = vendor;
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