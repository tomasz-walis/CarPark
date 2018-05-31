package parkinglot;

/**
 * Created by Tom on 04/11/2016.
 */
public class Motorbike extends Vehicle {
    private double engineSize;

    public Motorbike(String regPlate, String brand, String vehicleType,String date, String time, double engineSize) {
        super(regPlate, brand, vehicleType,date, time);
        this.engineSize = engineSize;
    }

    //setters
    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    //getters
    public double getEngineSize() {
        return engineSize;
    }




}
