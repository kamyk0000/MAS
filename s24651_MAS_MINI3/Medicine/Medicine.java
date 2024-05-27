package Medicine;

import Base.ObjectPlusPlus;

import java.time.LocalDate;

public abstract class Medicine extends ObjectPlusPlus {
    public String name;
    public String manufacturer;
    public String batchNumber;
    public LocalDate expirationDate;

    public Medicine(String name, String manufacturer, String batchNumber, LocalDate expirationDate) {
        super();
        this.name = name;
        this.manufacturer = manufacturer;
        this.batchNumber = batchNumber;
        this.expirationDate = expirationDate;
    }

    // Abstract method
    public abstract void displayLabel();

    // Non-abstract method
    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "Medicine -> " + name + ", " + manufacturer;
    }
}
