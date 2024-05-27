package Medicine;

import java.time.LocalDate;

public class Tablets extends Medicine {
    public double maxDailyIntakePerKilo;

    public Tablets(double maxDailyIntakePerKilo, String name, String manufacturer) {
        super(name, manufacturer, null, null);
        this.maxDailyIntakePerKilo = maxDailyIntakePerKilo;
    }

    @Override
    public void displayLabel() {
        System.out.println("\n====== Label ======" +
                "\nName:\t\t\t" + super.name +
                "\nManufacturer:\t" + super.manufacturer +
                "\nType:\t\t\t" + this.getClass().getSimpleName() +
                "\n====== Warning ======" +
                "\nMax daily intake per kilo of mas is " + maxDailyIntakePerKilo + " tabs");
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Max daily intake per kilo of mass: " + maxDailyIntakePerKilo;
    }
}
