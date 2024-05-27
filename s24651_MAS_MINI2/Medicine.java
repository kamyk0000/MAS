import java.time.LocalDate;

public class Medicine extends ObjectPlusPlus{
    String name;
    String producer;
    // nullable used only in procedures not perscriptions
    String batchNumber;
    LocalDate expirationDate;

    public Medicine(String name, String producer, String batchNumber, LocalDate expirationDate) {
        super();
        this.name = name;
        this.producer = producer;
        this.batchNumber = batchNumber;
        this.expirationDate = expirationDate;
    }

    public Medicine(String name, String producer) {
        this.name = name;
        this.producer = producer;
    }

    @Override
    public String toString() {
        return name + " <" + producer + ">";
    }
}
