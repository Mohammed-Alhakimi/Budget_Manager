package budget.sorting;

import budget.finances.Purchase;


import java.util.Collection;

public interface Sort {
    void sort(Collection<Purchase> purchases);
}
