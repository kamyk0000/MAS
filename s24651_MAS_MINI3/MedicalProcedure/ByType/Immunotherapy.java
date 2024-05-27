package MedicalProcedure.ByType;

import MedicalProcedure.ByLocation.ProcedureLocation;
import MedicalProcedure.MedicalProcedure;

public class Immunotherapy extends MedicalProcedure {
    public String cancerName;

    public Immunotherapy(double baseCost, String description, boolean forAppointmentOnly, ProcedureLocation location, String cancerName) {
        super(baseCost, description, forAppointmentOnly, location);
        this.cancerName = cancerName;
    }

    public double getCost() {
        return baseCost + 100;
    }
}
