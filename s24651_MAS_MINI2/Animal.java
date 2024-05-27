import java.time.LocalDate;
import java.util.List;

public class Animal extends ObjectPlusPlus {
    private static int nextID = 0;

    private int ID;
    private String name;
    private String species;
    private String gender;
    private double weight;
    private final LocalDate birthDate;
    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public static void showExtent() throws Exception {
        showExtent(Animal.class);
    }

    // clean this up
    public ObjectPlus remove() throws Exception {
        // removing all parts when whole is deleted
        ObjectPlusPlus[] medicalHistory = getLinks("has");
        for (ObjectPlusPlus medicalRecord : medicalHistory){
            removeLink("has","belongs to", medicalRecord);
            // removing part
            removeFromExtent(medicalRecord);
        }
        // removing all other links pointing at the object being removed //-will be easier with enums and such
        ObjectPlusPlus[] owners = getLinks("is owned by");
        for (ObjectPlusPlus owner : owners){
            removeLink("is owned by","owns", owner);
        }
        ObjectPlusPlus[] prescriptions = getLinks("has prescription");
        for (ObjectPlusPlus prescription : prescriptions){
            removeLink("has prescription","prescribed to", prescription);
        }
        // removing whole
        removeFromExtent();
        return null;
    }

    public static Animal create (String name, String species, String gender, double weight, LocalDate birthDate, Owner owner) throws Exception {
        if (name == null || species == null || gender == null || birthDate == null || owner == null) {
            throw new Exception("Arguments cannot be null");
        }
        return new Animal(name, species, gender, weight, birthDate, owner);
    }

    public static Animal getAnimal(int ID) throws Exception {
        List<Animal> extent = (List<Animal>) ObjectPlus.getExtent(Animal.class);
        for (Animal animal : extent) {
            if (animal.ID == ID) return animal;
        }
        throw new Exception("Animal of ID: " + ID + " not found");
    }

    private Animal(String name, String species, String gender, double weight, LocalDate birthDate, Owner owner) {
        super();
        this.ID = nextID++;
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.weight = weight;
        this.birthDate = birthDate;
        this.linkOwner(owner);
    }

    // asocjacja zwykła
    public void linkOwner(Owner owner) {
        addLink("is owned by","owns", owner);
    }
    public void unlinkOwner(Owner owner) throws Exception { removeLink("is owned by","owns", owner); }

    // metoda po asocjacji zwykłej
    public String showOwners() throws Exception {
        ObjectPlusPlus[] owners = getLinks("is owned by");
        StringBuilder ownerBuilder = new StringBuilder();
        ownerBuilder.append(owners[0]);
        boolean isFirst = true;
        for (ObjectPlusPlus owner : owners){
            if (isFirst) {
                isFirst = false;
                continue;
            }
            ownerBuilder.append(",\n").append(owner);
        }
        return ownerBuilder.toString();
    }

    // asocjacja kompozycja
    public void linkMedicalRecord(MedicalRecord record) throws Exception {
        addPart("has", "belongs to", record);
    }
    public void unlinkMedicalRecord(MedicalRecord record) throws Exception {
        removeLink("is owned by","owns", record);
        removeFromExtent(record);
    }

    public String showMedicalHistory() throws Exception {
        ObjectPlusPlus[] medicalHistory;
        try {
            medicalHistory = getLinks("has");
        } catch (Exception e) {
            return "No medical history found!";
        }
        StringBuilder ownerBuilder = new StringBuilder();
        ownerBuilder.append(medicalHistory[0]);
        boolean isFirst = true;
        for (ObjectPlusPlus record : medicalHistory){
            if (isFirst) {
                isFirst = false;
                continue;
            }
            ownerBuilder.append(", ").append(record);
        }
        return ownerBuilder.toString();
    }

    // asocjacja z atrybutem
    public List getPrescriptions() throws Exception {
        return List.of(getLinks("has prescription"));
    }

    @Override
    public String toString() {
        return "Animal[" + ID +
                "] -> name: " + name +
                ", species: " + species  +
                ", gender: " + gender +
                ", weight: " + weight +
                ", age: " + getAge();
    }
}
