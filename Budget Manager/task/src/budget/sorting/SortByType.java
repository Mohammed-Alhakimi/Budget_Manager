package budget.sorting;

import budget.finances.Purchase;


import java.text.DecimalFormat;
import java.util.Collection;

public class SortByType implements Sort {
    private static final DecimalFormat dfZero = new DecimalFormat("0.00");
    Collection<Purchase> purchases;

    public SortByType(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public void sort(Collection<Purchase> purchases) {
        double foodTotal;
        double entertainmentTotal;
        double clothesTotal;
        double otherTotal;
        double totalSum;
        System.out.println("\nTypes:");
        foodTotal = purchases
                .stream()
                .filter(purchase -> purchase.getType().equals("Food"))
                .mapToDouble(Purchase::getAmount)
                .sum();
        entertainmentTotal = purchases
                .stream()
                .filter(purchase -> purchase.getType().equals("Entertainment"))
                .mapToDouble(Purchase::getAmount)
                .sum();
        clothesTotal = purchases
                .stream()
                .filter(purchase -> purchase.getType().equals("Clothes"))
                .mapToDouble(Purchase::getAmount)
                .sum();
        otherTotal = purchases
                .stream()
                .filter(purchase -> purchase.getType().equals("Other"))
                .mapToDouble(Purchase::getAmount)
                .sum();
        totalSum = purchases
                .stream()
                .mapToDouble(Purchase::getAmount)
                .sum();
        System.out.println("Food - $" + dfZero.format(foodTotal));
        System.out.println("Entertainment - $" + dfZero.format(entertainmentTotal));
        System.out.println("Clothes - $" + dfZero.format(clothesTotal));
        System.out.println("Other - $" + dfZero.format(otherTotal));
        System.out.println("Total sum:  $" + dfZero.format(totalSum));
    }
}
