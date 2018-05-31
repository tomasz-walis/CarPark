package parkinglot;

/**
 * Created by Tom on 04/11/2016.
 */
public class Van extends Vehicle {
    private double cargoVolume;

    public Van(String regPlate, String brand, String vehicleType,String date, String time, double cargoVolume) {
        super(regPlate, brand, vehicleType,date, time);
        this.cargoVolume = cargoVolume;
    }

    //setters
    public void setCargoVolume(double cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    //getters
    public double getCargoVolume() {
        return cargoVolume;
    }





}