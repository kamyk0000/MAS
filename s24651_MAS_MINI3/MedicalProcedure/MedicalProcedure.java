package MedicalProcedure;

import Base.ObjectPlusPlus;
import MedicalProcedure.ByLocation.ProcedureLocation;

public class MedicalProcedure extends ObjectPlusPlus {
    //type
    //public ProcedureLocation procedureLocation;
    //vet
    //patient kinda
    public boolean forAppointmentOnly;
    public double baseCost;
    public String description;

    public MedicalProcedure(double baseCost, String description, boolean forAppointmentOnly, ProcedureLocation procedureLocation) {
        super();
        this.baseCost = baseCost;
        this.description = description;
        this.forAppointmentOnly = forAppointmentOnly;
        //this.procedureLocation = procedureLocation;
        try {
            addPart("performed in", "medical procedure", procedureLocation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
