package MedicalProcedure.ByType;

public interface IVaccination {
    public abstract String getVaccineType();
    public abstract void setVaccineType(String vaccineType);
    public abstract String getVaccineAdministration();
    public abstract void setVaccineAdministration();
    public abstract void updateVaccinationNotes();
    public abstract void showInfo();
}
