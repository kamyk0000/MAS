package MedicalProcedure.ByType;

import MedicalProcedure.ByLocation.ProcedureLocation;

public class TherapeuticVaccination extends Immunotherapy implements IVaccination {
    public int vaccinationFrequency;
    public String forForeignAgent;

    //public Vaccination vaccination;
    //public String vaccineType;
    //public String vaccineAdministration;
    //public String postVaccinationNotes;

    public TherapeuticVaccination(double baseCost, String description, boolean forAppointmentOnly, ProcedureLocation location, String cancerName, String vaccineType, String vaccineAdministration, String postVaccinationNotes, String forForeignAgent, int vaccinationFrequency) {
        super(baseCost, description, forAppointmentOnly, location, cancerName);
        addLink("Vaccine used", "Used in immunotherapy",
                new Vaccination(0, null, false, null, vaccineType, vaccineAdministration, postVaccinationNotes));
        this.forForeignAgent = forForeignAgent;
        this.vaccinationFrequency = vaccinationFrequency;
    }

    public TherapeuticVaccination(double baseCost, String description, boolean forAppointmentOnly, ProcedureLocation location, String cancerName, Vaccination vaccine, String forForeignAgent, int vaccinationFrequency) {
        super(baseCost, description, forAppointmentOnly, location, cancerName);
        addLink("Vaccine used", "Used in immunotherapy", vaccine);
        this.forForeignAgent = forForeignAgent;
        this.vaccinationFrequency = vaccinationFrequency;
    }

    public TherapeuticVaccination(Immunotherapy immunotherapy, ProcedureLocation location, String cancerName, Vaccination vaccine, String forForeignAgent, int vaccinationFrequency) {
        super(immunotherapy.baseCost, immunotherapy.description, immunotherapy.forAppointmentOnly, location, cancerName);
        addLink("Vaccine used", "Used in immunotherapy", vaccine);
        this.forForeignAgent = forForeignAgent;
        this.vaccinationFrequency = vaccinationFrequency;
    }

    @Override
    public String getVaccineType() { //autologus, allogenic
        return "";
    }

    @Override
    public void setVaccineType(String vaccineType) {
    }

    @Override
    public String getVaccineAdministration() {
        return "";
    }

    @Override
    public void setVaccineAdministration() {

    }

    @Override
    public void updateVaccinationNotes() {

    }

    @Override
    public void showInfo() {

    }

    @Override
    public double getCost() {
        return baseCost + 200;
    }
}
