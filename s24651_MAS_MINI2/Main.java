import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String EXTENT_FILE_NAME = "extents.json";

        Owner o1 = new Owner("Kamil", "Kulesza", "jakis adress", null);
        Owner o2 = new Owner("Halina", "Gołąbek", "jakis adress", "122321121");
        Animal a1 = Animal.create("Puszek", "Kot środkowo-europejski", "M", 10.2, LocalDate.of(2018, 6, 2), o1);
        Animal a2 = Animal.create("Aza", "Pies owczarek niemiecki", "K", 25.5, LocalDate.of(2020, 3, 5), o2);

        // asocjacja zwykła
        System.out.println(" \n===== Regular association =====");
        a1.linkOwner(o2);
        System.out.println(a1.showOwners());
        System.out.println("\n" + a2.showOwners());

        MedicalProcedure mp1 = new MedicalProcedure("Deworming", "Parasite prevention");
        MedicalProcedure mp2 = new MedicalProcedure("Rabies vaccine", "Vaccination");
        MedicalProcedure mp3 = new MedicalProcedure("Standard checkup", "Checkup");

        // asocjacja kwalifikowana
        System.out.println(" \n===== Qualified association =====");
        MedicalRecord m1 = new MedicalRecord(mp1, null);
        m1.addProcedureUsed(mp3);

        // znajdowanie obiektu za pomocą asocjacji (metoda)
        System.out.println(m1.wasProcedureUsed(mp3));
        System.out.println(m1.wasProcedureUsed("Deworming"));
        System.out.println(m1.wasProcedureUsed("Rabies vaccine"));

        // asocjacja-kompozycja
        System.out.println(" \n===== Composition association =====");
        a1.linkMedicalRecord(m1);
        try {
            a2.linkMedicalRecord(m1);
        } catch (Exception e) {
            System.out.println("Illegal operation: " + e.getMessage());
        }
        a2.linkMedicalRecord(new MedicalRecord(mp2, LocalDate.of(2023, 5, 10)));

        // asocjacja z atrybutem
        System.out.println(" \n===== Attribute association =====");
        Medicine med1 = new Medicine("Animalex", "Paxal");
        Medicine med2 = new Medicine("PetVit", "VitaMix");
        Medicine med3 = new Medicine("PaxalFix", "Paxal");

        Dosage d1 = new Dosage(3, Periods.Day, 2, Periods.Week);
        Dosage d2 = new Dosage(2, Periods.Day);

        Prescription p1 = new Prescription("11AW23D7", a1, med1, d1, "Get healthy!");
        p1.addMedicine(med2, d1);
        p1.addMedicine(med3, d2);

        List<Prescription> prescriptions = a1.getPrescriptions();
        prescriptions.get(0).showPrescription();


        // displaying the extent and links
        System.out.println(" \n===== Extents and links =====");
        ObjectPlusPlus.showExtent(Animal.class);
        ObjectPlusPlus.showExtent(MedicalRecord.class);

        a1.showLinks("is owned by", System.out);
        o2.showLinks("owns", System.out);
        a1.showLinks("has", System.out);
        m1.showLinks("belongs to", System.out);
        a1.showLinks("has prescription", System.out);
        p1.showLinks("prescribed to", System.out);

        // severing some links
        System.out.println(" \n===== Removing an object =====");
        a1 = (Animal) a1.remove();

        // saving the extent
        ObjectPlusPlus.writeExtents(new ObjectOutputStream(new FileOutputStream(EXTENT_FILE_NAME)));
        ObjectPlusPlus.readExtents(new ObjectInputStream(new FileInputStream(EXTENT_FILE_NAME)));
        ObjectPlusPlus.showExtent(Animal.class);
        ObjectPlusPlus.showExtent(MedicalRecord.class);

        System.out.println(" \n===== Extents and links after the deletion =====");
        try {
            a1.showLinks("is owned by", System.out);
        } catch (Exception e) {
            System.out.println("Illegal operation: " + e.getMessage());
        }
        o2.showLinks("owns", System.out);
        try {
            a1.showLinks("has", System.out);
        } catch (Exception e) {
            System.out.println("Illegal operation: " + e.getMessage());
        }
        try {
            m1.showLinks("belongs to", System.out);
        } catch (Exception e) {
            System.out.println("Illegal operation: " + e.getMessage());
        }
        try {
            a1.showLinks("has prescription", System.out);
        } catch (Exception e) {
            System.out.println("Illegal operation: " + e.getMessage());
        }
        try {
            p1.showLinks("prescribed to", System.out);
        } catch (Exception e) {
            System.out.println("Illegal operation: " + e.getMessage());
        }

    }
}