package budget.sorting;

import budget.finances.Purchase;

import java.text.DecimalFormat;
import java.util.*;

public class SortAllPurchases implements Sort {
    private static final DecimalFormat dfZero = new DecimalFormat("0.00");

    Collection<Purchase> purchases;

    public SortAllPurchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public void sort(Collection<Purchase> purchases) {
        ArrayList<Purchase> listSorted = new ArrayList<>(purchases);
        Collections.sort(listSorted, Comparator.comparing(Purchase::getAmount).thenComparing(Purchase::getName));
        Collections.reverse(listSorted);
        System.out.println("\nAll");
        listSorted.forEach(purchase -> {
            System.out.println(purchase.toString());
        });
        double totalAmount = listSorted.stream().mapToDouble(Purchase::getAmount).sum();
        System.out.println("Total sum: $" + dfZero.format(totalAmount) + "\n");
    }
}
