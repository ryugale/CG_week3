import java.util.*;

interface GPS {
    String getCurrentLocation();
    void updateLocation(String newLocation);
}

abstract class Vehicle implements GPS {
    private String vehicleId;
    private String driverName;
    protected double ratePerKm;
    private String currentLocation;

    public Vehicle(String vehicleId, String driverName, double ratePerKm) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
        this.currentLocation = "Unknown";
    }


    public abstract double calculateFare(double distance);

    public String getVehicleDetails() {
        return "Vehicle ID: " + vehicleId + ", Driver: " + driverName + ", Rate/km: $" + ratePerKm;
    }

    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getDriverName() {
        return driverName;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }
}

class Car extends Vehicle {
    public Car(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
    }

    @Override
    public double calculateFare(double distance) {
        return ratePerKm * distance + 10; 
    }
}

class Bike extends Vehicle {
    public Bike(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
    }

    @Override
    public double calculateFare(double distance) {
        return ratePerKm * distance; 
}
}

class Auto extends Vehicle {
    public Auto(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
    }

    @Override
    public double calculateFare(double distance) {
        return ratePerKm * distance + 5; 
    }
}
public class RideHailingApp {
    public static void main(String[] args) {
        List<Vehicle> rides = new ArrayList<>();

        rides.add(new Car("CAR001", "Alice", 12.0));
        rides.add(new Bike("BIKE002", "Bob", 5.0));
        rides.add(new Auto("AUTO003", "Charlie", 8.0));
        rides.get(0).updateLocation("Downtown");
        rides.get(1).updateLocation("Uptown");
        rides.get(2).updateLocation("Midtown");

        double distance = 15.5;
        System.out.println("Fare Estimates for " + distance + " km ride:\n");

        for (Vehicle v : rides) {
            System.out.println(v.getVehicleDetails());
            System.out.println("Current Location: " + v.getCurrentLocation());
            System.out.println("Estimated Fare: $" + String.format("%.2f", v.calculateFare(distance)));
            System.out.println("----------------------------------");
        }
    }
}
