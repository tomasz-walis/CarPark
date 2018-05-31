package parkinglot;

/**
 * Created by Tom on 04/11/2016.
 */
public class Car extends Vehicle {
    private int doors;
    private String color;


    public Car(String regPlate, String brand, String vehicleType,String date, String time, int doors, String color) {
        super(regPlate, brand, vehicleType,date, time);
        this.doors = doors;
        this.color = color;

    }

    //setters
    public void setDoors(int doors) {
        this.doors = doors;
    }
    public void setColor(String color) {
        this.color = color;
    }

    //getters
    public int getDoors() {
        return doors;
    }

    public String getColor() {
        return color;
    }




}
