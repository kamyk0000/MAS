package Person;

import Base.ObjectPlusPlus;

public class Vet extends ObjectPlusPlus {
    private String[] specializations;
    private int yearsOfExperience;

    public Vet(String[] specializations, int yearsOfExperience) {
        super();
        this.specializations = specializations;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Vet(String specialization, int yearsOfExperience) {
        super();
        this.specializations = new String[]{specialization};
        this.yearsOfExperience = yearsOfExperience;
    }
}
