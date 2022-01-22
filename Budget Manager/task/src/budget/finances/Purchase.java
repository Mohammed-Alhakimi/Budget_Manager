package budget.finances;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Purchase implements Serializable {
    private static final long serialVersionUID = 12345L;
    private static final DecimalFormat dfZero = new DecimalFormat("0.00");
    private String name;
    private double amount;
    private String type;

    public String getName() {
        return name;
    }

    public Purchase(String name, double amount, String type) {
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + " $" + dfZero.format(getAmount());
    }

    public double getAmount() {
        return amount;
    }
}
