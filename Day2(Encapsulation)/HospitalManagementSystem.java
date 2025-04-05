import java.util.*;
interface MedicalRecord {
    void addRecord(String record);
    List<String> viewRecords();
}
abstract class Patient implements MedicalRecord {
    private String patientId;
    private String name;
    private int age;
    private List<String> medicalHistory = new ArrayList<>();

    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }
    public abstract double calculateBill();

    public String getPatientDetails() {
        return "ID: " + patientId + ", Name: " + name + ", Age: " + age;
    }

    @Override
    public void addRecord(String record) {
        medicalHistory.add(record);
    }

    @Override
    public List<String> viewRecords() {
        return new ArrayList<>(medicalHistory); 
    }
}

class InPatient extends Patient {
    private int daysAdmitted;
    private double roomRate;
    private double treatmentCost;

    public InPatient(String patientId, String name, int age, int daysAdmitted, double roomRate, double treatmentCost) {
        super(patientId, name, age);
        this.daysAdmitted = daysAdmitted;
        this.roomRate = roomRate;
        this.treatmentCost = treatmentCost;
    }

    @Override
    public double calculateBill() {
        return (daysAdmitted * roomRate) + treatmentCost;
    }
}

class OutPatient extends Patient {
    private double consultationFee;
    private double testCharges;

    public OutPatient(String patientId, String name, int age, double consultationFee, double testCharges) {
        super(patientId, name, age);
        this.consultationFee = consultationFee;
        this.testCharges = testCharges;
    }

    @Override
    public double calculateBill() {
        return consultationFee + testCharges;
    }
}

public class HospitalManagementSystem {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();

        InPatient inPatient = new InPatient("P101", "Alice", 45, 5, 2000.0, 3000.0);
        inPatient.addRecord("Diagnosed with pneumonia.");
        inPatient.addRecord("Treated with antibiotics.");

        OutPatient outPatient = new OutPatient("P102", "Bob", 30, 500.0, 250.0);
        outPatient.addRecord("Routine check-up.");
        outPatient.addRecord("Blood test performed.");

        patients.add(inPatient);
        patients.add(outPatient);
        for (Patient p : patients) {
            System.out.println(p.getPatientDetails());
            System.out.println("Medical Records: ");
            for (String record : p.viewRecords()) {
                System.out.println("- " + record);
            }
            System.out.println("Total Bill: $" + String.format("%.2f", p.calculateBill()));
            System.out.println("--------------------------------");
        }
    }
}
