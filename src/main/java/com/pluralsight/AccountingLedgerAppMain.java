package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                default:
                    System.out.println("Invalid Input");
                    homeScreen();
            }
        } catch (Exception e) {
            System.out.println("Input Error");
            e.printStackTrace();
        }
    }

    public void ledgerMenu() throws InterruptedException {
        Thread.sleep(1000);
        getUser();
        System.out.println("\n~LEDGER OPTIONS~");
        System.out.println("What would you like to do with your ledger today? Please enter command for desired option:\n\n" +
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
            default:
                System.out.println("Invalid Input");
                ledgerMenu();
        }
    }

    public void viewLedger() {
        try {
            System.out.println("Loading your information...");
            Thread.sleep(2000);
            System.out.println("\n~CURRENT UP-TO-DATE LEDGER~");
            System.out.println("Here is your current account ledger as of: " + LocalDate.now() + "\n");
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

        } catch (IOException | InterruptedException e) {
            System.out.println("Invalid Input");
            throw new RuntimeException(e);
        }
    }

    public void getUser() {
        System.out.println("\n~USER LOGIN~");
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);


        System.out.println("\n\n\nWELCOME " + name.toUpperCase() + "\n\n\n\nUser Entry Logged At: " + formattedDateTime +"\n\n\n");
        System.out.println("Loading your information...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void makeDeposit() {
        try {
            getUser();
            System.out.println("\n~NEW DEPOSIT~");

            FileWriter fileWriter = new FileWriter("transactions2.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("Please enter the amount of your deposit: ");
            double deposit = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Please enter a description of the deposit: ");
            String description = scanner.nextLine();

            System.out.println("Please enter the vendor name for the deposit: ");
            String vendor = scanner.nextLine();

            UserLedger paymentLedger = new UserLedger(String.valueOf(LocalDate.now()), String.valueOf(LocalTime.now()), description, vendor, deposit);
            String adjustedLedger = "\n" + paymentLedger.getDate() + "|" + paymentLedger.getTime() + "|" + description + "|" + vendor + "|" + deposit;
            System.out.println("Processing information...");
            Thread.sleep(2000);
            System.out.println("\n\nYour deposit of " + deposit + " for item " + description + "by " + vendor + " has been successfuly added to your account ledger on: " + paymentLedger.getDate() + " " + paymentLedger.getTime());
            bufferedWriter.write(adjustedLedger);

            bufferedWriter.close();
            returnHomeprompt();

        } catch (IOException | InterruptedException e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }

    public void makePayment() {
        try {
            getUser();
            System.out.println("\n~NEW PAYMENT~");

            FileWriter fileWriter = new FileWriter("transactions2.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("Please enter the amount of your payment: ");
            double payment = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Please enter a description of the payment: ");
            String description = scanner.nextLine();

            System.out.println("Please enter the vendor name for the payment: ");
            String vendor = scanner.nextLine();

            UserLedger paymentLedger = new UserLedger(String.valueOf(LocalDate.now()), String.valueOf(LocalTime.now()), description, vendor, payment);
            String adjustedLedger = "\n" + paymentLedger.getDate() + "|" + paymentLedger.getTime() + "|" + description + "|" + vendor + "|" + payment;
            System.out.println("Processing information...");
            Thread.sleep(2000);
            System.out.println("\n\nYour payment of " + payment + " for item " + description + " by " + vendor + " has been successfuly added to your account ledger on: " + paymentLedger.getDate() + " " + paymentLedger.getTime());
            bufferedWriter.write(adjustedLedger);

            bufferedWriter.close();
            returnHomeprompt();

        } catch (IOException | InterruptedException e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }

    public void viewDeposits() {
        try {
            System.out.println("Loading your information...");
            Thread.sleep(2000);
            System.out.println("\n~DEPOSIT REPORTS~");
            System.out.println("Here are all of your current deposit transactions as of " + LocalDate.now() + "\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double. parseDouble(arrTransactions[4]));
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
            System.out.println("Loading your information...");
            Thread.sleep(2000);
            System.out.println("\n~PAYMENT REPORT");
            System.out.println("Here are all of your current payment transactions as of " + LocalDate.now() + "\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                double deposit = Double.parseDouble(arrTransactions[4]);
                String description = arrTransactions[2];
                String vendor = arrTransactions[3];
                //for (int i = 0; i < arrTransactions.length; i++) {
                UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double. parseDouble(arrTransactions[4]));
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
            Thread.sleep(500);
            System.out.println("\n~LEDGER REPORTS~");
            System.out.println("Which report would you like to view? Please enter command corresponding to your desired type of report:\n\n" +
                    "1) Month to Date\n" +
                    "2) Previous Month\n" +
                    "3) Year to Date\n" +
                    "4) Previous Year\n" +
                    "5) By Vendor\n" +
                    "6) Custom Search\n" +
                    "0) Go Back to Ledgers Menu\n" +
                    "H) Go to Home Screen\n\n" +
                    "Enter Command:");

            String menuChoice = scanner.nextLine();

            switch (menuChoice.toUpperCase()) {
                case "1":
                    mtdReport();
                    break;
                case "2":
                    previousMonthReport();
                    break;
                case "3":
                    yearToDateReport();
                    break;
                case "4":
                    previousYearReport();
                    break;
                case "5":
                    vendorReport();
                    break;
                case "6":
                    customSearch();
                    break;
                case "0":
                    ledgerMenu();
                    break;
                case "H":
                    homeScreen();
                    break;
                default:
                    System.out.println("Invalid Input");
                    reportsMenu();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
            returnHomeprompt();
        }
    }

    public void mtdReport() {
        try {
            System.out.println("Loading your information...");
            Thread.sleep(2000);
            System.out.println("\n~MONTH TO DATE REPORT~");
            System.out.println("Here are all of your current month to date transactions as of " + LocalDate.now() + ":\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                double deposit = Double.parseDouble(arrTransactions[4]);
                String description = arrTransactions[2];
                String vendor = arrTransactions[3];
                UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                double now = LocalDate.now().getMonthValue();
                String[] getTransactionMonth = arrTransactions[0].split("-");
                double transactionMonth = Double.parseDouble(getTransactionMonth[1]);
                //for (int i = 0; i < transactionMonth; i++ ) {
                    if (transactionMonth == now) {
                        System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                    } if (transactionMonth != now) {
                    System.out.println("You have no transactions for the current month");
                    break;
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
            System.out.println("\n~SEARCH BY VENDOR REPORT~");
            System.out.println("Enter the vendor name of the transactions you would like to view: ");

            String vendorName = scanner.nextLine();
            System.out.println("Loading your information...");
            Thread.sleep(2000);

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                String findName = arrTransactions[3];
                if (vendorName.equalsIgnoreCase(findName)) {
                    System.out.println("\nTransactions found under " + vendorName + ":\n");
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } if (vendorName != findName) {
                    System.out.println("You have no transactions under '" + vendorName + "'");
                    break;
                }
            }
            returnHomeprompt();
        } catch (IOException e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void previousMonthReport() throws InterruptedException {
        try {
            System.out.println("Loading your information...");
            Thread.sleep(2000);
            System.out.println("\n~PREVIOUS MONTH REPORT~");
            System.out.println("Here are all of your last month's' transactions as of " + LocalDate.now() + ":\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                double deposit = Double.parseDouble(arrTransactions[4]);
                String description = arrTransactions[2];
                String vendor = arrTransactions[3];
                UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                double now = LocalDate.now().getMonthValue() - 1;
                String[] getTransactionMonth = arrTransactions[0].split("-");
                double transactionMonth = Double.parseDouble(getTransactionMonth[1]);
                boolean c = false;
                //for (int i = 0; i < transactionMonth; i++ ) {
                if (transactionMonth == now) {
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } if (transactionMonth != now) {
                    System.out.println("You have no transactions for the previous month");
                    break;
                }
            }
            bufReader.close();
            returnHomeprompt();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }

    public void yearToDateReport() {
        try {
            System.out.println("Loading your information...");
            Thread.sleep(2000);
            System.out.println("\n~YEAR TO DATE REPORT~");
            System.out.println("Here are all of your current year to date transactions as of " + LocalDate.now() + ":\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                double deposit = Double.parseDouble(arrTransactions[4]);
                String description = arrTransactions[2];
                String vendor = arrTransactions[3];
                UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                double now = LocalDate.now().getYear();
                String[] getTransactionMonth = arrTransactions[0].split("-");
                double transactionYear = Double.parseDouble(getTransactionMonth[0]);
                //for (int i = 0; i < transactionMonth; i++ ) {
                if (transactionYear == now) {
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } if (transactionYear != now) {
                    System.out.println("You have no transactions for the current year");
                    break;
                }
            }
            bufReader.close();
            returnHomeprompt();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }

    public void previousYearReport() {
        try {
            System.out.println("Loading your information...");
            Thread.sleep(2000);
            System.out.println("\n~MONTH TO DATE REPORT~");
            System.out.println("Here are all of your current month to date transactions as of " + LocalDate.now() + ":\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                double deposit = Double.parseDouble(arrTransactions[4]);
                String description = arrTransactions[2];
                String vendor = arrTransactions[3];
                UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                double now = LocalDate.now().getYear() - 1;
                String[] getTransactionMonth = arrTransactions[0].split("-");
                double transactionYear = Double.parseDouble(getTransactionMonth[0]);
                  //for (int i = 0; i < transactionMonth; i++ ) {
                if (transactionYear == now) {
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } else {
                    System.out.println("You have no transaction for the previous year");
                    break;
                }
            }
            bufReader.close();
            returnHomeprompt();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }

    public void customSearch() {
    try {
        Thread.sleep(500);
        System.out.println("\n~CUSTOM SEARCH~");
        System.out.println("Please enter vendor name, amount of deposit or payment, item description, or time period (i.e 2024/10/01-2024/10/31) for your search: ");

        String menuChoice = scanner.nextLine();
        FileReader fileReader = new FileReader("transactions2.csv");
        BufferedReader bufReader = new BufferedReader(fileReader);
        String input = bufReader.readLine();

        while ((input = bufReader.readLine()) != null) {
            String[] arrTransactions = input.split("\\|");
            double date = Double.parseDouble(arrTransactions[0]);
            double amount = Double.parseDouble(arrTransactions[4]);
            String description = arrTransactions[2];
            String[] descriptionSplit = description.split(" ");
            String[] timePeriod = menuChoice.split("-");
            double startDate = Double.parseDouble(timePeriod[0]);
            double endDate = Double.parseDouble(timePeriod[1]);
            double choiceTimeDifference = endDate - startDate;
            double timeDifference = endDate - date;
            ArrayList<String> descriptionArr = new ArrayList<>();
//            for (int i = 0; i < descriptionSplit.length; i++ ) {
//                System.out.println(i);
//            }
            String vendor = arrTransactions[3];
            UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));

            if (menuChoice.equalsIgnoreCase(vendor)) {
                System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
            } if (menuChoice.equalsIgnoreCase(String.valueOf(descriptionSplit[0].equalsIgnoreCase(String.valueOf(descriptionSplit[1]))))) {
                System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
            } if (menuChoice.equals(String.valueOf(amount))) {
                System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
            } if (choiceTimeDifference > timeDifference ) {
                System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
            }
        }
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        System.out.println("Error retrieving data");
        throw new RuntimeException(e);
    }
    returnHomeprompt();
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
        System.out.println("User Entry Logged Out At: " + LocalDateTime.now());
        System.out.println("\nThank You For Using Our Account Ledger Application!" +
                "\nHave A Nice Day!");
    }
}
