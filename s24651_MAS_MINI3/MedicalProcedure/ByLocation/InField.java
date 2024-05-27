package MedicalProcedure.ByLocation;

public class InField extends ProcedureLocation {
    public String address;
    public double distanceFromClosestClinic;
    public double addedCosts;
    public boolean toClinicTransportation;

    public InField(String address, double distanceFromClosestClinic, double addedCosts, boolean toClinicTransportation) {
        this.address = address;
        this.distanceFromClosestClinic = distanceFromClosestClinic;
        this.addedCosts = addedCosts;
        this.toClinicTransportation = toClinicTransportation;
    }
}
