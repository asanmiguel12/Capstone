package com.pluralsight;

import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class AccountingLedgerAppMain {
    static Scanner scanner = new Scanner(System.in);
    public void main () {
        homeScreen();
    }
    public void homeScreen() {
        try {
            System.out.println("\n~WELCOME TO YOUR ACCOUNT LEDGER APPLICATION!~\n\n" +
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
        System.out.println("~LEDGER OPTIONS~");
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
                viewDeposits();
                break;
            case "P":
                viewPayments();
                break;
            case "R":
                reportsMenu();
                break;
            case "H":
                homeScreen();
                break;
        }
    }

    public void viewLedger() {
        try {
            System.out.println("~CURRENT UP TO DATE LEDGER~");
            System.out.println("\nHere is your current account ledger as of: " + LocalDate.now() + "\n");
            FileInputStream transactions = new FileInputStream("transactions2.csv");
            Scanner scanner = new Scanner(transactions);

            String input = scanner.nextLine();

            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                List<String> ledger = List.of(input);
                for (int i = 0; i < ledger.size(); i++) {
                    System.out.println(ledger.get(i));
                }
            }
            scanner.close();
            returnHomeprompt();

        } catch (IOException e) {
            System.out.println("Invalid Input");
            throw new RuntimeException(e);
        }
    }

    public void getUser() {
        System.out.println("~NAME ENTRY LOGIN~");
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

            UserLedgers paymentLedger = new UserLedgers(String.valueOf(LocalDate.now()), String.valueOf(LocalTime.now()), description, vendor, deposit);
            String adjustedLedger = "\n" + paymentLedger.getDate() + "|" + paymentLedger.getTime() + "|" + description + "|" + vendor + "|" + deposit;
            System.out.println("Your deposit of " + deposit + " for item " + description + "by " + vendor + " has been successfuly added to your account ledger on " + paymentLedger.getDate() + " " + paymentLedger.getTime());
            bufferedWriter.write(adjustedLedger);

            bufferedWriter.close();
            returnHomeprompt();

        } catch (IOException e) {
            System.out.println("Invalid Input");
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

            UserLedgers paymentLedger = new UserLedgers(String.valueOf(LocalDate.now()), String.valueOf(LocalTime.now()), description, vendor, payment);
            String adjustedLedger = "\n" + paymentLedger.getDate() + "|" + paymentLedger.getTime() + "|" + description + "|" + vendor + "|" + payment;
            System.out.println("Your payment of " + payment + " for item " + description + " by " + vendor + " has been successfuly added to your account ledger on " + paymentLedger.getDate() + " " + paymentLedger.getTime());
            bufferedWriter.write(adjustedLedger);

            bufferedWriter.close();
            returnHomeprompt();

        } catch (IOException e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }

    public void viewDeposits() {
        try {
            System.out.println("~DEPOSIT REPORTS~");
            System.out.println("\nHere are all of your current deposit transactions as of " + LocalDate.now() + "\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                UserLedgers f = new UserLedgers(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double. parseDouble(arrTransactions[4]));
                double deposit = Double.parseDouble(arrTransactions[4]);
                String description = arrTransactions[2];
                String vendor = arrTransactions[3];

                if (f.getAmountChanged() > 0) {
                    System.out.println(f.getDate() + "|" + f.getTime() + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                }
            }
            returnHomeprompt();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }
    public void viewPayments() {
        try {
            System.out.println("~PAYMENT REPORT");
            System.out.println("\nHere are all of your current payment transactions as of " + LocalDate.now() + "\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                double deposit = Double.parseDouble(arrTransactions[4]);
                String description = arrTransactions[2];
                String vendor = arrTransactions[3];
                //for (int i = 0; i < arrTransactions.length; i++) {
                UserLedgers f = new UserLedgers(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double. parseDouble(arrTransactions[4]));
                if (f.getAmountChanged() < 0) {
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } else {
                    System.out.println(" ");
                }
                System.out.println("You have no transactions for the current month");
            }
            returnHomeprompt();

        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }
    public void reportsMenu() {
        try {
            System.out.println("~LEDGER REPORTS~");
            System.out.println("Which report would you like to view? Please enter command corresponding to your desired type of report:\n\n" +
                    "1) Month to Date\n" +
                    "2) Previous Month\n" +
                    "3) Year to Date\n" +
                    "4) Previous Year\n" +
                    "5) By Vendor\n" +
                    "0) Go Back to reports page\n" +
                    "H) Go to Home Screen\n\n" +
                    "Enter Command:");

            String menuChoice = scanner.nextLine();

            switch (menuChoice.toUpperCase()) {
                case "1":
                    mtdReport();
                    break;
                case "2":
                    viewDeposits();
                    break;
                case "3":
                    viewPayments();
                    break;
                case "4":
                    homeScreen();
                    break;
                case "5":
                    vendorReport();
                    break;
                case "H":
                    homeScreen();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }

    public void mtdReport() {
        try {
            System.out.println("~MONTH TO DATE REPORT~");
            System.out.println("\nHere are all of your current month to date transactions as of " + LocalDate.now() + ":\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                double deposit = Double.parseDouble(arrTransactions[4]);
                String description = arrTransactions[2];
                String vendor = arrTransactions[3];
                UserLedgers f = new UserLedgers(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                double now = LocalDate.now().getMonthValue();
                String[] getTransactionMonth = arrTransactions[0].split("-");
                double transactionMonth = Double.parseDouble(getTransactionMonth[1]);
//                for (int i = 0; i < transactionMonth; i++ ) {
                    if (transactionMonth == now) {
                        System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                    } else {
                        System.out.println(" ");
                    }
                }
            bufReader.close();
            returnHomeprompt();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }
    public void vendorReport() {
        try {
            System.out.println("~SEARCH BY VENDOR REPORT~");
            System.out.println("\nEnter the vendor name of the transactions you would like to view: ");

            String vendorName = scanner.nextLine();

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                UserLedgers f = new UserLedgers(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                String findName = arrTransactions[3];
                if (vendorName.equalsIgnoreCase(findName)) {
                    System.out.println("\nTransactions found under " + vendorName + ":\n");
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } else {
                    System.out.println(" ") ;
                }
            }
        } catch (IOException e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }
    public void returnHomeprompt() {
        System.out.println("\nWould you like to do something else with your ledger today?\n" +
                "Y) Yes\n" +
                "N) No\n\n" +
                "Enter Command: ");

        String menuChoice = scanner.nextLine();

        if (menuChoice.equalsIgnoreCase("Y")) {
            homeScreen();
        } else {
            exit();
        }
    }

    public void exit() {
        System.out.println("Thank you for using our app" +
                "\nHave a good day!");

    }
}

