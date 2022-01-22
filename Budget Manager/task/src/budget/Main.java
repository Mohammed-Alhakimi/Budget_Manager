package budget;

import budget.serialization.SerializationUtils;
import budget.finances.*;
import budget.sorting.*;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Wallet wallet = new Wallet();
    private static final File data = new File("purchases.txt");
    private static final DecimalFormat dfZero = new DecimalFormat("0.00");

    public static void main(String[] args) {
        showActions();
    }

    public static void showActions() {

        boolean notFinished = true;

        while (notFinished) {
            System.out.println("Choose your action:\n" +
                    "1) Add income\n" +
                    "2) Add purchase\n" +
                    "3) Show list of purchases\n" +
                    "4) Balance\n" +
                    "5) Save\n" +
                    "6) Load\n" +
                    "7) Analyze (Sort)\n" +
                    "0) Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    wallet.addIncome();
                    break;
                case 2:
                    wallet.addPurchase();
                    break;
                case 3:
                    if (wallet.getPurchases().isEmpty()) {
                        System.out.println("\nThe purchase list is empty\n");
                    } else {
                        String typeOfPurchases = chooseType();
                        while (!typeOfPurchases.equals("Back")) {
                            System.out.println();
                            double sumPurchases = 0;
                            for (Purchase purchase : wallet.getPurchases()
                            ) {
                                if (typeOfPurchases.equals("All")) {
                                    sumPurchases += purchase.getAmount();
                                    System.out.println(purchase);
                                } else {
                                    if (purchase.getType().equals(typeOfPurchases)) {
                                        sumPurchases += purchase.getAmount();
                                        System.out.println(purchase);
                                    }
                                }
                            }
                            System.out.println("Total sum: $" + sumPurchases + "\n");
                            typeOfPurchases = chooseType();
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nBalance: $" + dfZero.format(wallet.getBalance()) + "\n");
                    break;
                case 5:
                    saveWallet(wallet);
                    break;
                case 6:
                    if (data.exists()) {
                        loadWallet();
                    }
                    System.out.println("\nPurchases were loaded!\n");
                    break;
                case 7:
                    chooseSortingType();
                    break;
                case 0:
                    notFinished = false;
                    System.out.println("\nBye!");
                    break;
                default:
                    System.out.println("\nWrong entry!\n");
            }
        }
    }

    private static void chooseSortingType() {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            System.out.println("\nHow do you want to sort?\n" +
                    "1) Sort all purchases\n" +
                    "2) Sort by type\n" +
                    "3) Sort certain type\n" +
                    "4) Back");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (!wallet.getPurchases().isEmpty()) {
                        wallet.setSortingType(new SortAllPurchases(wallet.getPurchases()));
                        wallet.analyze();
                    } else {
                        System.out.println("\nThe purchase list is empty!");
                    }
                    break;
                case 2:
                    wallet.setSortingType(new SortByType(wallet.getPurchases()));
                    wallet.analyze();
                    break;
                case 3:
                    wallet.setSortingType(new SortCertainType(wallet.getPurchases()));
                    wallet.analyze();
                    break;
                case 4:
                    done = true;
                    System.out.println();
                    break;
            }
        }
    }

    private static void loadWallet() {
        try {
            wallet = (Wallet) SerializationUtils.deserialize("purchases.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveWallet(Wallet wallet) {
        try {
            SerializationUtils.serialize(wallet, "purchases.txt");
            System.out.println("\nPurchases were saved!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String chooseType() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) ALL\n" +
                "6) Back");
        int number = scanner.nextInt();
        String choice;
        switch (number) {
            case 1:
                choice = "Food";
                break;
            case 2:
                choice = "Clothes";
                break;
            case 3:
                choice = "Entertainment";
                break;
            case 4:
                choice = "Other";
                break;
            case 5:
                choice = "All";
                System.out.println();
                break;
            case 6:
                choice = "Back";
                System.out.println();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }
        return choice;
    }
}
