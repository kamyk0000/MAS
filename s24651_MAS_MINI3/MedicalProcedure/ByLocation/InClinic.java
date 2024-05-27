package MedicalProcedure.ByLocation;

public class InClinic extends ProcedureLocation {
    public String clinicAddress;
    public double inClinicDiscount;

    public InClinic(String clinicAddress, double inClinicDiscount) {
        this.clinicAddress = clinicAddress;
        this.inClinicDiscount = inClinicDiscount;
    }
}
