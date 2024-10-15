package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;


public class AccountingLedgerAppMain {
    public void main(String[] args) {
        //Home menu
        homeMenu();

        // make deposit
        makeDeposit();

        // make payment
        makePayment();

        // view ledger
        viewLedger();

        // exit

        // show current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
        String formattedDateTime = now.format(formatter);


    }

    static Scanner scanner = new Scanner(System.in);
    public void getUser() {

        System.out.println("What is your Name: ");
        String userName = scanner.nextLine();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        System.out.println("Welcome " + userName + "\n\nHere is your ledger as of : " + formattedDateTime);

    }

    public void displayMenu() {
        System.out.println("\nWelcome To Your Account Ledger!\n\n" +
                "What would you like to do today? Please Enter Command For Selected Option:\n\n" +
                "(L) View Ledger\n" +
                "(D) Make Deposit\n" +
                "(P) Make Payment");
        String menuChoice = scanner.nextLine();
        if (menuChoice.equalsIgnoreCase("L")); {
            getUser();

        }

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
            FileWriter fileWriter = new FileWriter("transactions2.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("Please enter the amount of the payment : ");
            double deposit = scanner.nextDouble();

            System.out.println("Please enter a description of the payment : ");
            String description = scanner.nextLine();

            System.out.println("Please enter the vendor name of the payment : ");
            String vendor = scanner.nextLine();


            String input = bufreader.readLine();

            while ((input = bufreader.readLine()) != null) {
                String[] ledger = input.split("\\|");
                UserLedgers depositLedger = new UserLedgers(ledger[0], ledger[1], ledger[2], ledger[3], Double.parseDouble(ledger[4]));



            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewLedger () {
        try {
            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufreader = new BufferedReader(fileReader);

            String input = bufreader.readLine();

            while ((input = bufreader.readLine()) != null) {
                String[] ledger = input.split("\\|");
                System.out.println(Arrays.toString(ledger));

            }

            bufreader.close();
            } catch(FileNotFoundException e){
                throw new RuntimeException(e);
            } catch (IOException e) {
            System.out.println("Incorrect Input");
            throw new RuntimeException(e);
        }
    }
    public void homeMenu () {
        displayMenu();
    }
}