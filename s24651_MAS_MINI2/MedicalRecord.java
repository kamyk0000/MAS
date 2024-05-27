import java.time.LocalDate;
import java.util.Objects;

public class MedicalRecord extends ObjectPlusPlus {
    public LocalDate date;

    public MedicalRecord(MedicalProcedure procedureUsed, LocalDate date) {
        super();
        this.date = Objects.requireNonNullElseGet(date, LocalDate::now);
        addLink("record includes", "procedure included in", procedureUsed, procedureUsed.uniqueProcedureName);
    }

    public void addProcedureUsed(MedicalProcedure procedureUsed) {
        addLink("record includes", "procedure included in", procedureUsed, procedureUsed.uniqueProcedureName);
    }

    //public void removeProcedureUsed(MedicalProcedure procedureUsed) {}

    public boolean wasProcedureUsed(MedicalProcedure procedureUsed) {
        return wasProcedureUsed(procedureUsed.uniqueProcedureName);
    }

    public boolean wasProcedureUsed(String procedureUsed) {
        ObjectPlusPlus objectPlusPlus = null;
        try {
            objectPlusPlus = getLinkedObject("record includes", procedureUsed);
        } catch (Exception ignored){
        }
        return objectPlusPlus != null;
    }

    public String showProcedureUsed() {
        try {
            return ((MedicalProcedure)getLinks("record includes")[0]).uniqueProcedureName;
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String toString(){
        return "Medical Record" +
                " -> date: " + date +
                ", procedure: " + showProcedureUsed();
    }
}
