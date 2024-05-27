package Person;

import Base.ObjectPlusPlus;

public class Owner extends ObjectPlusPlus {
    public String address;
    public boolean hasAnimalAbuseRecord;

    public Owner(String address, boolean hasAnimalAbuseRecord) {
        super();
        this.address = address;
        this.hasAnimalAbuseRecord = hasAnimalAbuseRecord;
    }

    public void showOwnedPets() {

    }
}
