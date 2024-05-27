package Medicine;

import java.time.LocalDate;

public class Shot extends Medicine {
    public String applicationInstructions;

    public Shot(String applicationInstructions, String name, String manufacturer, String batchNumber, LocalDate expirationDate) {
        super(name, manufacturer, batchNumber, expirationDate);
        this.applicationInstructions = applicationInstructions;
    }

    @Override
    public void displayLabel() {
        System.out.println("\n====== Label ======" +
                "\nName:\t\t\t" + super.name +
                "\nManufacturer:\t" + super.manufacturer +
                "\nBatch number:\t" + super.batchNumber +
                "\nType:\t\t\t" + this.getClass().getSimpleName() +
                "\nExpiry Date:\t" + super.expirationDate +
                "\n====== Note ======\n" +
                applicationInstructions);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", BN: " + super.batchNumber +
                ", Use before: " + super.expirationDate +
                ", " + applicationInstructions;
    }
}
