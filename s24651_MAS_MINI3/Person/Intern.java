package Person;

import Base.ObjectPlusPlus;

import java.time.LocalDate;

public class Intern extends ObjectPlusPlus {
    public LocalDate internshipStartDate;
    public double internshipHours;
    public double hoursWorked;
    public double grade;

    public Intern(double internshipHours, double hoursWorked, double grade) {
        super();
        this.internshipStartDate = LocalDate.now();
        this.internshipHours = internshipHours;
        this.hoursWorked = hoursWorked;
        this.grade = grade;
    }

    public void showEstimatedInternshipEndDate() {
        double days = (internshipHours - hoursWorked) / 8.0;
        LocalDate updatedDate = LocalDate.now().plusDays((long) days);
        System.out.println("Estimated date of internship end: " + updatedDate);
    }
}
