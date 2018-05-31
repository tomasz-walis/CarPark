package parkinglot;

/**
 * Created by Tom on 04/11/2016.
 */
public abstract class Vehicle implements Comparable<Vehicle> {
    private String regPlate;
    private String brand;
    private String vehicleType;
    private String date;
    private String time;
    private DateTime dateTime;


    //vehicle class constructor
    public Vehicle(String regPlate, String brand, String vehicleType, String date, String time) {
        this.regPlate = regPlate;
        this.brand = brand;
        this.vehicleType = vehicleType;
        this.date = date;
        this.time = time;
        dateTime = new DateTime(date, time);
    }

    //setters
    public void setRegPlate(String regPlate) {
        this.regPlate = regPlate;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;

    }
    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }


    //getters

    public String getRegPlate() {
        return regPlate;
    }


    public String getBrand() {
        return brand;
    }


    public String getVehicleType() {
        return vehicleType;
    }

    public String entryDate()
    {
        return date;
    }

    public String entryTime()
    {
        return time;
    }


    public DateTime getDateTime() {
        return dateTime;
    }


    @Override
    public int compareTo(Vehicle o) {
        return getDateTime().getDateTimeAsDate().compareTo(o.getDateTime().getDateTimeAsDate());
    }

}

