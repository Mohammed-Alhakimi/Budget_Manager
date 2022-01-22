package budget.finances;


import budget.sorting.Sort;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;

public class Wallet implements Serializable {
    public Wallet() {
        this.balance = 0.0;
    }

    private Double balance;
    private Queue<Purchase> purchases = new ArrayDeque<>();
    private static final long serialVersionUID = 12345L;
    private static final DecimalFormat dfZero = new DecimalFormat("0.00");
    private Sort sortingType;

    public void setSortingType(Sort sortingType) {
        this.sortingType = sortingType;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addIncome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter income:");
        double amount = scanner.nextDouble();
        this.balance += amount;
        System.out.println("Income was added!\n");
    }

    public void analyze() {
        this.sortingType.sort(purchases);
    }

    public void addPurchase() {
        Scanner scanner = new Scanner(System.in);
        String type = chooseTheTypeofPurchase();
        while (!type.equals("Back")) {
            System.out.println("\nEnter purchase name:");
            String purchaseName = scanner.nextLine();
            System.out.println("Enter its price:");
            double purchasePrice = scanner.nextDouble();
            scanner.nextLine();
            Purchase purchase = new Purchase(purchaseName, purchasePrice, type);
            if (purchase.getAmount() <= balance) {
                purchases.offer(purchase);
                this.balance -= purchasePrice;
                System.out.println("Purchase was added!\n");
            } else {
                System.out.println("Not enough money!");
            }
            type = chooseTheTypeofPurchase();
        }
    }

    private String chooseTheTypeofPurchase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
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
                choice = "Back";
                System.out.println();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }

        return choice;
    }

    public Queue<Purchase> getPurchases() {
        return purchases;
    }
}