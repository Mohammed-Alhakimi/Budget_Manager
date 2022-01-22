package budget.sorting;

import budget.finances.Purchase;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class SortCertainType implements Sort {
    private static final DecimalFormat dfZero = new DecimalFormat("0.00");
    Collection<Purchase> purchases;


    public SortCertainType(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public void sort(Collection<Purchase> purchases) {
        Scanner scanner = new Scanner(System.in);
        double total = 0.0;
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                List<Purchase> foodList = purchases
                        .stream()
                        .filter(purchase -> purchase.getType().equals("Food"))
                        .collect(Collectors.toList());
                if (!foodList.isEmpty()) {
                    System.out.println("\nFood:");
                    Collections.sort(foodList, Comparator
                            .comparing(Purchase::getAmount)
                            .thenComparing(Purchase::getName));
                    Collections.reverse(foodList);
                    foodList.forEach(System.out::println);
                    total = foodList
                            .stream()
                            .mapToDouble(Purchase::getAmount)
                            .sum();
                    System.out.println("Total: $" + dfZero.format(total) + "\n");
                } else {
                    System.out.println("\nThe purchase list is empty!\n");
                }

                break;
            case 2:
                List<Purchase> clothesList = purchases
                        .stream()
                        .filter(purchase -> purchase.getType().equals("Clothes"))
                        .collect(Collectors.toList());
                if (!clothesList.isEmpty()) {
                    System.out.println("\nClothes:");
                    Collections.sort(clothesList, Comparator
                            .comparing(Purchase::getAmount)
                            .thenComparing(Purchase::getName));
                    Collections.reverse(clothesList);
                    clothesList.forEach(System.out::println);
                    total = clothesList.stream().mapToDouble(Purchase::getAmount).sum();
                    System.out.println("Total: $" + dfZero.format(total) + "\n");
                } else {
                    System.out.println("\nThe purchase list is empty!\n");
                }

                break;
            case 3:
                List<Purchase> entertainmentList = purchases
                        .stream()
                        .filter(purchase -> purchase.getType().equals("Entertainment"))
                        .collect(Collectors.toList());
                if (!entertainmentList.isEmpty()) {
                    System.out.println("\nEntertainment:");
                    Collections.sort(entertainmentList, Comparator
                            .comparing(Purchase::getAmount)
                            .thenComparing(Purchase::getName));
                    Collections.reverse(entertainmentList);
                    entertainmentList.forEach(System.out::println);
                    total = entertainmentList
                            .stream()
                            .mapToDouble(Purchase::getAmount)
                            .sum();
                    System.out.println("Total: $" + dfZero.format(total) + "\n");
                } else {
                    System.out.println("\nThe purchase list is empty!\n");
                }

                break;
            case 4:
                List<Purchase> otherList = purchases
                        .stream()
                        .filter(purchase -> purchase.getType().equals("Other"))
                        .collect(Collectors.toList());
                if (!otherList.isEmpty()) {
                    System.out.println("\nOther:");
                    Collections.sort(otherList
                            , Comparator
                                    .comparing(Purchase::getAmount)
                                    .thenComparing(Purchase::getName));
                    Collections.reverse(otherList);
                    otherList.forEach(System.out::println);
                    total = otherList
                            .stream()
                            .mapToDouble(Purchase::getAmount)
                            .sum();
                    System.out.println("Total: $" + dfZero.format(total) + "\n");
                } else {
                    System.out.println("\nThe purchase list is empty!\n");
                }
                break;
        }
    }
}
