package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class AccountingLedgerAppMain {
    static Scanner scanner = new Scanner(System.in);
    public void main () {
        homeScreen();
    }
    public void homeScreen() {
        try {
            System.out.println("\nWelcome To Your Account Ledger Application!\n\n" +
                    "What would you like to do today? Please enter command for desired option:\n\n" +
                    "(L) View Ledger\n" +
                    "(D) Make Deposit\n" +
                    "(P) Make Payment\n" +
                    "(X) Exit\n\n" +
                    "Enter Command:");

            String menuChoice = scanner.nextLine();
            switch (menuChoice.toUpperCase()) {
                case "L":
                    ledgerMenu();
                    break;
                case "D":
                    makeDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "X":
                    exit();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Input Error");
            e.printStackTrace();
        }
    }

    public void ledgerMenu(){
        getUser();
        System.out.println("\nWhat would you like to do with your ledger today? Please enter command for desired option: \n\n" +
                "A) All - Display all entries\n" +
                "D) Deposits - Display all deposits\n" +
                "P) Payments - Display all payments\n" +
                "R) Reports - Display reports menu\n" +
                "H) Home - Return to main menu\n\n" +
                "Enter Command:");

        String menuChoice = scanner.nextLine();
        switch (menuChoice.toUpperCase()) {
            case "A":
                viewLedger();
                break;
            case "D":
                makeDeposit();
                break;
            case "P":
                makePayment();
                break;
            case "R":
                homeScreen();
                break;
            case "H":
                homeScreen();
                break;
        }
    }

    public void viewLedger() {
        try {
            System.out.println("Here is your current account ledger as of: " + LocalDate.now() + "\n");
            FileInputStream transactions = new FileInputStream("transactions.csv");
            Scanner scanner = new Scanner(transactions);

            String input = scanner.nextLine();
            int lineCount = 0;

            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                List<String> ledger = List.of(input);
                for (int i = 0; i < ledger.size(); i++) {
                    lineCount++;
                    System.out.println(ledger.get(i));
                }
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("Incorrect Input");
            throw new RuntimeException(e);
        }
    }

    public void getUser() {

        System.out.println("\nPlease enter your name: ");
        String name = scanner.nextLine();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        System.out.println("\nHello " + name + "\n" + "User Entry Logged At: " + formattedDateTime +"\n");

    }

    public void makeDeposit() {
        try {
            getUser();
            System.out.println("~NEW DEPOSIT~");

            FileWriter fileWriter = new FileWriter("transactions2.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("Please enter the amount of your deposit: ");
            double deposit = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Please enter a description of the deposit: ");
            String description = scanner.nextLine();

            System.out.println("Please enter the vendor name for the deposit: ");
            String vendor = scanner.nextLine();

            UserLedgers paymentLedger = new UserLedgers(deposit, description, vendor, LocalTime.now(), LocalDate.now());
            String adjustedLedger = "\n" + paymentLedger.getDate() + "|" + paymentLedger.getTime() + "|" + description + "|" + vendor + "|" + deposit;
            String LocalDateTime;
            System.out.println("Your deposit of " + deposit + " for item " + description + " by " + vendor + " has been successfuly added to your account ledger on " + paymentLedger.getDate() + " " + paymentLedger.getTime());
            bufferedWriter.write(adjustedLedger);

            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Incorrect Input");
            e.printStackTrace();
        }
    }
    public void makePayment() {
        try {
            getUser();
            System.out.println("~NEW PAYMENT~");

            FileWriter fileWriter = new FileWriter("transactions2.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("Please enter the amount of your payment: ");
            double payment = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Please enter a description of the payment: ");
            String description = scanner.nextLine();

            System.out.println("Please enter the vendor name for the payment: ");
            String vendor = scanner.nextLine();

            UserLedgers paymentLedger = new UserLedgers(payment, description, vendor, LocalTime.now(), LocalDate.now());
            String adjustedLedger = "\n" + paymentLedger.getDate() + "|" + paymentLedger.getTime() + "|" + description + "|" + vendor + "|" + "-" + payment;
            String LocalDateTime;
            System.out.println("Your payment of " + payment + " for item " + description + " by " + vendor + " has been successfuly added to your account ledger on " + paymentLedger.getDate() + " " + paymentLedger.getTime());
            bufferedWriter.write(adjustedLedger);

            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Incorrect Input");
            e.printStackTrace();
        }
    }

    public void displayMenu() {
        homeScreen();
    }

    public void exit(){
        System.out.println("Thank you for using our app" +
                "\nHave a good day!");

    }
}

