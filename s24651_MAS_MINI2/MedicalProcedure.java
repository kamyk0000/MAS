public class MedicalProcedure extends ObjectPlusPlus {
    String uniqueProcedureName; //for qualifier
    String procedureType; //

    public MedicalProcedure(String uniqueProcedureName, String procedureType) {
        this.uniqueProcedureName = uniqueProcedureName;
        this.procedureType = procedureType;
    }
}
