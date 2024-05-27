package proj_1;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        // Tworzenie obiektów
        Animal a1 = Animal.create("Puszek", "Kot środkowo-europejski", "M", 10.2, LocalDate.of(2018, 6, 2), "Kamil");
        Animal a2 = Animal.create("Felicja", "Pies labrador", "K", 20.3, LocalDate.of(2017, 2, 11), "Eliza");
        Animal a3 = Animal.create("Arnold", "Pies chiuaua", "K", 3.5, LocalDate.of(2012, 12, 11), "Jakub");
        // Sprawdzenie rzucania wyjątków
        try {
            Animal aEx = Animal.create(null, "Pies labrador", "K", 20.3, LocalDate.of(2017, 2, 11), "Eliza");
        } catch (Exception e) {
            System.out.println("Illegal creation of Animal object because of: \n"+e);
        }
        // Zapis i wyświetlenie ekstensji
        Animal.showExtent();
        Animal.saveExtent();

        // Działanie metod na obiektach
        a1.addMedicalRecord("Rabies");
        a1.addOwner("Malwina");
        a2.addMedicalRecord("Debugging", LocalDate.of(2024, 4, 26)); // odrobacznie ;)
        a1.showMedicalRecords();
        a2.showMedicalRecords();
        System.out.println(a1.showOwners());
        Animal.removeAnimal(a3);

        // Zapis i wyświetlenie ekstensji
        Animal.showExtent();
        Animal.saveExtent();

        // Załadowanie zapisanej ekstensji
        Animal.loadExtent();

        // Otrzymanie obiektów i działanie metod na nich (wykazanie trwałości ekstensji)
        Animal a4 = Animal.getAnimal(0);
        Animal a5 = Animal.getAnimal(1);
        // Sprawdzenie rzucania wyjątków
        try {
            Animal aEx = Animal.getAnimal(69);
        } catch (Exception e) {
            System.out.println("Illegal get of Animal object because of: \n"+e);
        }
        a4.showMedicalRecords();
        a5.showMedicalRecords();
        System.out.println(a4.showOwners());

    }
}
