package proj_1;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Animal extends ObjectPlus implements Serializable {

    // Atrybut klasowy
    private static int nextID = 0;
    // Atrybuty proste
    private int ID;
    private String name;
    private String species;
    private String gender;
    private double weight;
    // Atrybut złożony
    private final LocalDate birthDate;
    // Atrybut opcjonalny
    private List<String> medicalHistory = new ArrayList<>();
    // Atrybut powtarzalny
    private List<String> owners = new ArrayList<>();
    // Atrybut pochodny
    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
    // Ekstensja
    //private static List<Animal> extent = new ArrayList<>();
    // Metody ekstensji
    public static void showExtent() throws Exception {
        ObjectPlus.showExtent(Animal.class);
    }
    public static List getExtent() throws Exception {
        List<Animal> extent = null;
        extent = (List<Animal>) ObjectPlus.getExtent(Animal.class);
        return extent;
    }
    // Trwałość ekstensji
    private static final String EXTENT_FILE_NAME = "animals.json";

    public static void saveExtent() {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(EXTENT_FILE_NAME))) {
            ObjectPlus.writeExtents(out);
        } catch (IOException e) {
            System.err.println("Error occurred while saving extent: " + e.getMessage());
        }
    }

    public static void loadExtent() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(EXTENT_FILE_NAME))) {
            ObjectPlus.readExtents(in);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error occurred while loading extent: " + e.getMessage());
        }
    }

    public static void removeAnimal(Animal animal) throws Exception {
        ObjectPlus.removeFromExtent(animal);
    }

    // Metody klasowe
    public static Animal create (String name, String species, String gender, double weight, LocalDate birthDate, String owner) throws Exception {
        if (name == null || species == null || gender == null || birthDate == null || owner == null) {
            throw new Exception("Arguments cannot be null");
        }
        return new Animal(name, species, gender, weight, birthDate, owner);
    }

    public static Animal getAnimal (int ID) throws Exception {
        List<Animal> extent = getExtent();
        for (Animal animal : extent) {
            if (animal.ID == ID) return animal;
        }
        throw new Exception("Animal of ID: " + ID + " not found");
    }

    private Animal(String name, String species, String gender, double weight, LocalDate birthDate, String owner) {
        super();
        this.ID = nextID++;
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.weight = weight;
        this.birthDate = birthDate;
        this.owners.add(owner);
        //extent.add(this);
    }

    // Metody obiektowe
    public void addOwner (String owner) {
        this.owners.add(owner);
    }

    public void addMedicalRecord(String record, LocalDate specificDate) {
        this.medicalHistory.add(specificDate.toString() + ": " + record);
    }
    // Przeciążenie metody obiektowej
    public void addMedicalRecord(String record) {
        addMedicalRecord(record, LocalDate.now());
    }

    public void showMedicalRecords() {
        if (this.medicalHistory.isEmpty()) {
            System.out.println("Medical history not found");
        }else {
            for (String str:medicalHistory) {
                System.out.println(str);
            }
        }
    }

    public String showOwners () {
        StringBuilder ownerBuilder = new StringBuilder();
        ownerBuilder.append(owners.get(0));
        boolean isFirst = true;
        for (String owner : owners){
            if (isFirst) {
                isFirst = false;
                continue;
            }
            ownerBuilder.append(", ").append(owner);
        }
        return ownerBuilder.toString();
    }

    // Przesłonięcie metody
    @Override
    public String toString() {
        return "Animal[" + ID +
                "] -> name: " + name +
                ", species: " + species  +
                ", gender: " + gender +
                ", weight: " + weight +
                ", age: " + getAge() +
                ", owners: " + showOwners();
    }
}
