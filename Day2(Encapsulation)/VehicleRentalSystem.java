import java.util.*;

interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

abstract class Vehicle implements Insurable {
    private String vehicleNumber;
    private String type;
    protected double rentalRate;

    public Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public abstract double calculateRentalCost(int days);
}

class Car extends Vehicle {
    private String insurancePolicyNumber;

    public Car(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Car", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        return rentalRate * days;
    }

    @Override
    public double calculateInsurance() {
        return 500.0;
    }

    @Override
    public String getInsuranceDetails() {
        return "Car Insurance: Policy# ****" + insurancePolicyNumber.substring(insurancePolicyNumber.length() - 4);
    }
}

class Bike extends Vehicle {
    private String insurancePolicyNumber;

    public Bike(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Bike", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        return rentalRate * days * 0.9; 
    }

    @Override
    public double calculateInsurance() {
        return 200.0;
    }

    @Override
    public String getInsuranceDetails() {
        return "Bike Insurance: Policy# ****" + insurancePolicyNumber.substring(insurancePolicyNumber.length() - 4);
    }
}

class Truck extends Vehicle {
    private String insurancePolicyNumber;

    public Truck(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Truck", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        return rentalRate * days + 100 * days; 
    }

    @Override
    public double calculateInsurance() {
        return 1000.0;
    }

    @Override
    public String getInsuranceDetails() {
        return "Truck Insurance: Policy# ****" + insurancePolicyNumber.substring(insurancePolicyNumber.length() - 4);
    }
}
public class VehicleRentalSystem {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Car("CAR123", 100.0, "CARPOL1234"));
        vehicles.add(new Bike("BIKE456", 50.0, "BIKEPOL5678"));
        vehicles.add(new Truck("TRUCK789", 150.0, "TRUCKPOL9012"));
        int rentalDays = 5;
        for (Vehicle v : vehicles) {
            System.out.println("Vehicle Number: " + v.getVehicleNumber());
            System.out.println("Type: " + v.getType());
            System.out.println("Rental Cost for " + rentalDays + " days: $" + v.calculateRentalCost(rentalDays));
            System.out.println("Insurance Cost: $" + v.calculateInsurance());
            System.out.println(v.getInsuranceDetails());
            System.out.println("--------------------------------");
        }
    }
}
