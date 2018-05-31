package parkinglot;

/**
 * Created by Tom on 04/11/2016.
 */
public interface CarParkManager {

    //interface methods that have implementation in WestminsterCarParkmanager
    void addVehicle();
    void deleteVehicle();
    void displayParkedVehicles();
    void statistics();
    void showDayList();
    void parkingCharges();
    boolean runMenu();
}