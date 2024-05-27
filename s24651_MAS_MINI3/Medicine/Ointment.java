package Medicine;

import java.time.LocalDate;

public class Ointment extends Medicine {
    public String amount;

    public Ointment(String amount, String name, String manufacturer, LocalDate expirationDate) {
        super(name, manufacturer, null, expirationDate);
        this.amount = amount;
    }

    @Override
    public void displayLabel() {
        System.out.println("\n====== Label ======" +
                "\nName:\t\t\t" + super.name +
                "\nManufacturer:\t" + super.manufacturer +
                "\nType:\t\t\t" + this.getClass().getSimpleName() +
                "\nExpiry Date:\t" + super.expirationDate +
                "\n====== Note ======" +
                "\nApply a amount of " + amount);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", " + super.expirationDate +
                ", Amount: " + amount;
    }
}