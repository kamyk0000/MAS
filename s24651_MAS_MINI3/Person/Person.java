package Person;

import Base.ObjectPlusPlus;

import java.time.LocalDate;

public class Person extends ObjectPlusPlus {
    public String fullName;
    public LocalDate dateOfBirth;
    private boolean isIntern;
    private boolean isVet;
    private boolean isOwner;

    public Person(String fullName, LocalDate dateOfBirth) {
        super();
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.isIntern = false;
        this.isVet = false;
        this.isOwner = false;
    }

    public void linkIntern(Intern intern) throws Exception {
        if (isIntern) {
            throw new Exception("This Person is already a Intern!");
        }
        if (isVet) {
            throw new Exception("This Person is a Vet, a Vet cannot become an Intern!");
        }
        addPart("is intern", "is person", intern);
        isIntern = true;
    }


    public void linkVet(Vet vet) throws Exception {
        if (isIntern) {
            throw new Exception("This Person is an Intern, if you want to make them a Vet use makeVet() method!");
        }
        if (isVet) {
            throw new Exception("This Person is already a Vet!");
        }
        addPart("is vet", "is person", vet);
        isVet = true;
    }

    public void linkOwner(Owner owner) throws Exception {
        // is already owner
        if (!isOwner) {
            addPart("is owner", "is person", owner);
            isOwner = true;
        }
    }

    public void makeVet(String specialization) throws Exception {
        if (isVet) {
            throw new Exception("This Person is already Vet");
        } else if (!isIntern) {
            throw new Exception("This Person is not an Intern");
        } else {
            ObjectPlusPlus[] intern = getLinks("is intern");
            removeLink("is intern", "is person", intern[0]);
            // remove intern part
            addPart("is vet", "is person", new Vet(specialization, 0));
            isVet = true;
            isIntern = false;
        }
    }

    public void showEstimatedInternshipEndDate() throws Exception {
        ((Intern)getLinks("is intern")[0]).showEstimatedInternshipEndDate();
    }
}

