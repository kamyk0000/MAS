package MedicalProcedure.ByType;

import MedicalProcedure.ByLocation.ProcedureLocation;
import MedicalProcedure.MedicalProcedure;

public class Vaccination extends MedicalProcedure implements IVaccination {
    public String vaccineType;
    public String vaccineAdministration;
    public String postVaccinationNotes;

    public Vaccination(double baseCost, String description, boolean forAppointmentOnly, ProcedureLocation location, String vaccineType, String vaccineAdministration, String postVaccinationNotes) {
        super(baseCost, description, forAppointmentOnly, location);
        this.vaccineType = vaccineType;
        this.vaccineAdministration = vaccineAdministration;
        this.postVaccinationNotes = postVaccinationNotes;
    }

    @Override
    public String getVaccineType() {
        return vaccineType;
    }

    @Override
    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    @Override
    public String getVaccineAdministration() {
        return vaccineAdministration;
    }

    @Override
    public void setVaccineAdministration() {
        this.vaccineAdministration = vaccineAdministration;
    }

    @Override
    public void updateVaccinationNotes() {
        this.postVaccinationNotes = postVaccinationNotes;
    }

    @Override
    public void showInfo() {

    }

    public double getCost() {
        return baseCost + 50;
    }
}
