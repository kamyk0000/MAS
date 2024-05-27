import java.util.List;

public class Prescription extends ObjectPlusPlus{
    String prescriptionCode;
    String note;

    // asocjacja z atrybutem
    public Prescription(String prescriptionCode, Animal recipient, Medicine medicine, Dosage dose, String note){
        super();
        this.prescriptionCode = prescriptionCode;
        addLink("prescribed to", "has prescription", recipient);
        addMedicine(medicine, dose);
        this.note = note;
    }

    public void addMedicine(Medicine medicine, Dosage dose){
        addLink("has dosage", "included in", dose);
        dose.addLink("dose for", "has dosage", medicine);
    }

    public void showPrescription() throws Exception {
        List doses = List.of(getLinks("has dosage"));
        System.out.println("=============== Perscription ===============\n" +
                "Code: " + prescriptionCode + "\n" +
                "================ Medicine ==================");
        for(Object dose : doses){
            Dosage dosage = (Dosage) dose;
            List meds = List.of(dosage.getLinks("dose for"));
            for (Object med : meds){
                System.out.println(med);
                System.out.println(dose);
            }
        }
        System.out.println("=============== Note ===============\n" + note);
    }

    @Override
    public String toString() {
        return "Prescription -> " + prescriptionCode;
    }
}



