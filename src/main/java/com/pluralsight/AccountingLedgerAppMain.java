package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
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
                    "What would you like to do today? Please enter command for selected option:\n\n" +
                    "(L) View Ledger\n" +
                    "(D) Make Deposit\n" +
                    "(P) Make Payment\n" +
                    "(X) Exit");


            String menuChoice = scanner.nextLine();
            switch (menuChoice.toUpperCase()) {
                case "L":
                    viewLedger();
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
//           if (menuChoice.equalsIgnoreCase("L"));{
//               getUser();
//           } else if (menuChoice.equalsIgnoreCase("D")); {
//                makeDeposit();
//           } else if (menuChoice.equalsIgnoreCase("P")); {
//               makePayment();
//           }
        } catch (Exception e) {
            System.out.println("Input Error");
            throw new RuntimeException(e);
        }

    }

    public void viewLedger() {
        try {getUser();
            System.out.println("Here is your current account ledger");
          FileInputStream transactions = new FileInputStream("transactions.csv");
          Scanner scanner = new Scanner(transactions);

            String input = scanner.nextLine();
            int lineCount = 0;

            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                List<String> ledger = List.of(input);
                for (int i = 0; i < ledger.size(); i++) {
                    lineCount++;
                    System.out.println(lineCount + ledger.get(i));

                }
            }

        } catch (IOException e) {
            System.out.println("Incorrect Input");
            throw new RuntimeException(e);
        }
    }

    public void getUser() {

        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        System.out.println("\nHello " + name + "\n" + "User Entry Logged At: " + formattedDateTime +"\n");

    }

    public void makeDeposit() {
        try {
            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufreader = new BufferedReader(fileReader);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void makePayment() {
        try {
            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufreader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("transactions2.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("Please enter the amount of the payment: ");
            double payment = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Please enter a description of the payment: ");
            String description = scanner.nextLine();

            System.out.println("Please enter the vendor name for the payment: ");
            String vendor = scanner.nextLine();


            String input = bufreader.readLine();

            while ((input = bufreader.readLine()) != null) {
                String[] ledger = input.split("\\|");
                UserLedgers paymentLedger = new UserLedgers(ledger[0], ledger[1], ledger[2], ledger[3], Double.parseDouble(ledger[4]));
                String adjustedLedger = paymentLedger.getDate() + "|" + paymentLedger.time + "|" + description + "|" + vendor + "|" + Double.toString(payment);
                System.out.println(adjustedLedger);
                bufferedWriter.write(adjustedLedger);


            }
            bufreader.close();
            bufferedWriter.close();


        } catch (FileNotFoundException e) {
            System.out.println("Incorrect Input");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Incorrect Input");
            throw new RuntimeException(e);
        }
    }

    public void displayMenu() {
        homeScreen();
    }

    public void exit(){
        System.out.println("Thank you and have a good day!");

    }
}

