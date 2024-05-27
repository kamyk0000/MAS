import MedicalProcedure.ByLocation.*;
import MedicalProcedure.ByType.*;
import Medicine.*;
import Person.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        testMulti_inheritanceAndMulti_aspectInheritance();
        testDynamicAndOverlappingInheritance();
        testAbstractInheritance();
    }

    public static void testMulti_inheritanceAndMulti_aspectInheritance() {
        // Add two-way composition from both location and med procedure perspective?

        // Abstract inheritance Medical Procedure:
        // Aspect: location
        InClinic ic1 = new InClinic("Akacjowa 12/3, 05-088, Warszawa", 0.05);
        InField if1 = new InField("'Farmex' sp. z o.o., Wiejska 5, 05-012, Lipków", 21.3, 120, false);
        InClinic ic2 = new InClinic("Akacjowa 12/3, 05-088, Warszawa", 0.05);
        InClinic ic3 = new InClinic("Akacjowa 12/3, 05-088, Warszawa", 0.05);
        // Aspect: type
        Vaccination vc1 = new Vaccination(150, "Standard Vaccine for Car and Dog rabies", false, if1,
                "Rabies vaccine", "To muscle", "Observe for suspicious behavior for the next 5h");
        Immunotherapy it1 = new Immunotherapy(400, "Standard practices against various cancers", true, ic1, "Cancer");
        Vaccination vc2 = new Vaccination(350, "Preventive vaccine for various cancers", true, ic2,
                "Cancer vaccine", "To muscle", "Needs to be advised regularly after a personal recognition");
        // Showing overrode method:
        System.out.println(vc1.getCost());
        // Multi-inheritance: Vaccination <-> Immunotherapy
        TherapeuticVaccination tv1 = new TherapeuticVaccination(120, "Vaccine boosting the immune system af an individual to fight cancerous cells", true, ic3,
                 "Advanced cancer", vc2, "Cancer", 3);
        // Showing overrode method:
        System.out.println(tv1.getCost());

    }

    public static void testDynamicAndOverlappingInheritance() throws Exception {
        Person p1 = new Person("Halina Gostowiak", LocalDate.of(1992, 10, 21));
        Person p2 = new Person("Jacek Borowik", LocalDate.of(1989, 11, 12));
        Person p3 = new Person("Halina Gostowiak", LocalDate.of(2002, 2, 1));
        // Overlapping inheritance: (Vet <-> Owner OR Intern <-> Owner) XOR Vet <> Intern
        Vet v1 = new Vet("Surgeon", 12);
        Owner o1 = new Owner("Akacjowa 12/2, 05-082, Warszawa", false);
        p1.linkVet(v1);
        p1.linkOwner(o1);
        // ==================================================================================
        Intern i1 = new Intern(280, 8, 0);
        Owner o2 = new Owner("Balonowa 5, 05-088, Warszawa", false);
        p2.linkIntern(i1);
        p2.linkOwner(o2);
        // Handling exception in overlapping:
        try {
            Intern i2 = new Intern(300, 0, 0);
            p1.linkIntern(i2);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Vet v2 = new Vet("Something", 90);
            p2.linkVet(v2);
        } catch (Exception e) {
            System.out.println(e);
        }
        // Methods
        p2.showEstimatedInternshipEndDate();
        // Dynamic inheritance: Intern -> Vet
        p2.makeVet("Radiologist");
        // Exception in method
        try {
            p2.showEstimatedInternshipEndDate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void testAbstractInheritance() throws Exception {
        Medicine m1 = new Tablets(2, "KeratinOff", "PetVit");
        Medicine m2 = new Ointment("a small blob", "Smearex", "PetVit", LocalDate.now());
        Medicine m3 = new Shot("Apply directly to the back muscles", "Painaway", "Alistro", "A22NSAW0012", LocalDate.now());
        Medicine[] medicines = {m1, m2, m3};
        for (Medicine medicine : medicines){
            System.out.println("\n" + medicine);
            medicine.displayLabel();
        }
    }
}