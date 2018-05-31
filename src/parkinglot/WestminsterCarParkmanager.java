package parkinglot;

/**
 * Created by Tom on 04/11/2016.
 */

import java.text.*;
import java.util.*;
import java.lang.*;


public class WestminsterCarParkmanager implements CarParkManager {

    private ArrayList<Vehicle> vehicles;
    private static int spaces = 20;

    public WestminsterCarParkmanager(int parkLength) {
        this.spaces = parkLength;
        vehicles = new ArrayList<>();
    }

    //Method to add vehicle
    @Override
    public void addVehicle() {

        if (spaces<=0){
            System.out.println("No spaces available, please delete first");
        }else {

            System.out.println("What type of vehicle to park?");
            System.out.println("Please Enter:");
            System.out.println("\033[34mC : For Car");
            System.out.println("\033[35mV : For Van");
            System.out.println("\033[33mB : Motorbike");
            System.out.println("\033[0m");

            // boolean variable to check if vehicle should be added
            boolean added = false;

            //boolean for input validation
            boolean valid = false;

            Scanner input = new Scanner(System.in);



        //string choice validation, loop until characters from menu are inputted correctly
        String choice = null;
        while (!valid){
            try{
                choice = input.next();
                valid = true;
                if (!(choice.equalsIgnoreCase("C") || choice.equalsIgnoreCase("V") || choice.equalsIgnoreCase("B"))) {
                    System.err.println("Wrong Input Please use menu options");
                    valid=false;
                }
            }catch (InputMismatchException e){
                System.out.println("Wrong input, please try again");
                input.next();
            }
        }

        //brand string validation to prevent user from entering unknown characters like .....
        System.out.println("Enter Brand");
        while (!input.hasNext("[a-zA-Z,0-9,-]+"))
        {
            System.out.println("That's not a valid brand, try again");
            input.next();
        }
        String brand = input.next();

        //regPalte string validation to prevent user from entering unknown characters
        System.out.println("Enter ID");
        while (!input.hasNext("[a-zA-Z,0-9]+"))
        {
            System.out.println("That's not a valid ID, try again");
            input.next();
        }
        String regPlate = input.next();

        //this checks if the vehicle with entered id Plate is already parked if YES it prompts ti input valid Id plate
        for (Vehicle vehicle : vehicles){
            if (vehicle.getRegPlate().equalsIgnoreCase(regPlate)) {
                System.out.println("Vehicle with this registration has already parked today. Please delete first");
                do {
                    System.out.println("Input Another Valid Id Number");
                    regPlate=input.next();
                }while (vehicle.getRegPlate().equalsIgnoreCase(regPlate));
            }
        }


        //Date format and date range validation
        String date = null;
        do {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            try {
                System.out.println("Enter date: DD/MM/YYYY");
                date = input.next();
                Date passedDate = dateFormat.parse(date);
                Date startDate = dateFormat.parse("01/01/2016");
                Date endDate = dateFormat.parse("12/12/2018");
                valid = true;

                if (passedDate.before(startDate)|| passedDate.after(endDate )) {
                    System.err.println("Wrong date please try again!!");
                    valid = false;
                }
            } catch (ParseException e) {
                System.err.println("Invalid Date, Please Try Again" );
                valid = false;
            }
        }while (!valid);

        //time format and range validation
        String time = null;
        do {
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            timeFormat.setLenient(false);
            try {
                System.out.println("Enter time: HH:MM");
                time = input.next();
                valid = true;
                timeFormat.parse(time);
            } catch (ParseException e) {
                System.err.println("Invalid time, Please Try Again");
                valid = false;
            }
        }while (!valid);


        //start of switch case, user inputs what type of vehicle to add
        switch (choice.toUpperCase().charAt(0)){

            case 'C':
                //string colour validation to prevent entering unknown characters
                System.out.println("Enter Car Colour");
                while (!input.hasNext("[a-zA-Z ,]+"))
                {
                    System.out.println("That's not a valid colour");
                    input.next();
                }
                String colour = input.next();

                // number of doors validation
                boolean validDoor= false;
                int doorNum =0;
                do {
                    try{
                        System.out.println("Enter Door Number");
                        doorNum = input.nextInt();
                        validDoor = true;
                        if (doorNum > 5 || doorNum < 2) {
                            System.err.println("Please try again, car can have max 5 and minimum 2 doors");
                            validDoor = false;
                        }

                    }catch (InputMismatchException e){
                        System.out.println("Wrong input, please try again");
                        input.next();

                    }
                }while (!validDoor);

                String vehicleType = "Car";
                Car car = new Car(regPlate, brand, vehicleType, date, time, doorNum, colour);

                //add car only is spaces available and return true
                if (spaces > 0) {
                    vehicles.add(car);
                    added = true;
                }
                break;

            case 'V':

                //cargo volume validation
                boolean validCargo= false;
                double cargoVolume = 0;
                do {
                    try{
                        System.out.println("Enter Cargo Volume for Van");
                        cargoVolume = input.nextDouble();
                        validCargo = true;
                        if (cargoVolume < 0) {
                            System.out.println("Cargo Volume cant be less than 0");
                            validCargo = false;
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Wrong input, please try again");
                        input.next();
                    }
                }while (!validCargo);

                vehicleType = "Van";
                Van van = new Van(regPlate, brand, vehicleType, date, time, cargoVolume);

                //adds van only if num of spaces more than 1 as van takes 2 spaces
                if (spaces > 1) {
                    vehicles.add(van);
                    added = true;
                }
                break;

            case 'B':

                //engine size validation
                boolean validEngine= false;
                double engineSize = 0;
                do {
                    try{
                        System.out.println("Enter Engine Size for Motorbike");
                        engineSize = input.nextDouble();
                        validEngine = true;
                        if (engineSize < 0) {
                            System.out.println("Cargo Volume cant be less than 0");
                            validEngine = false;
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Wrong Input please try again");
                        input.next();
                    }
                }while (!validEngine);


                vehicleType = "Motorbike";
                Motorbike bike = new Motorbike(regPlate, brand, vehicleType, date, time, engineSize);

                //add motorbike only is spaces available and return true
                if (spaces > 0) {
                    vehicles.add(bike);
                    added = true;
                }
                break;
        }

        //if boolean is false no more space left
        if (!added) {
            System.out.println("No spots available");
        } else {
            // if van is added remove 2 spaces, if not van remove 1 space
            if (choice.equalsIgnoreCase("V")) {
                spaces -= 2;
            } else {
                spaces--;
            }

            System.out.println("Added\nSpaces left: " + spaces);
        }
      }
    }

    //Method to delete vehicle
    @Override
    public void deleteVehicle() {

        //if car park is empty
        if (vehicles.isEmpty()) {
            System.out.println("Car Park is Empty Nothing to delete");

        }else{

            //user input to delete vehicle by Id Plate
            System.out.println("Enter Registration Plate to delete:");
            Scanner input = new Scanner(System.in);
            String type = input.nextLine();

            //loops trough array to find matching id plate
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegPlate().equalsIgnoreCase(type)) {

                //return vehicle type which was deleted
                System.out.println("Type of Vehicle: " + vehicle.getVehicleType());

                //van takes 2 spaces this is to return 2 spaces if type of vehicle deleted is Van, if its not van spaces++
                if (vehicle.getVehicleType().equalsIgnoreCase("van")) {
                    spaces += 2;
                } else {
                    spaces++;
                }

                //function to remove vehicle
                vehicles.remove(vehicle);
                System.out.println("");
                System.out.println("Spaces left: " + spaces);
                break;
            }
        }
        }
    }

    //Method to display list of parked vehicles
    @Override
    public void displayParkedVehicles() {
        //array sort
        Collections.sort(vehicles);

        //reverse input last input is the first
        Collections.reverse(vehicles);

        //loops array to display list of vehicles
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getVehicleType() + " Plate: " + vehicle.getRegPlate());
            System.out.println("Time: " + vehicle.entryTime() + " Date " + vehicle.entryDate());
        }

        System.out.println("");
        System.out.println("Spaces left: " + spaces);
        System.out.println("");
        //if parking is empty
        if (vehicles.isEmpty()) {
            System.out.println("Car Park is Empty");

        }
    }

    //Method to display statistics of vehicles parked
    @Override
    public void statistics() {

        if (vehicles.isEmpty()) {
            System.out.println("Car Park is Empty Nothing display");
            System.out.println(" ");

        }else{


        int cars = 0;
        int vans = 0;
        int bikes = 0;
        int total = vehicles.size();
        for (Vehicle vehicle : vehicles) {
            switch (vehicle.getVehicleType().toLowerCase()) {
                case "car":
                    cars++;
                    break;
                case "van":
                    vans++;
                    break;
                default:
                    bikes++;
                    break;
            }
        }
        float percent = (cars * 100.0f) / total;
        System.out.println(percent + " cars");
        percent = (vans * 100.0f) / total;
        System.out.println(percent + " vans");
        percent = (bikes * 100.0f) / total;
        System.out.println(percent + " bikes");

            //display last vehicle parked
            System.out.println(" ");
            System.out.println("Last Vehicle Parked is::");
            Vehicle j = vehicles.get(vehicles.size()-1);
            System.out.println("Type: "+j.getVehicleType()+" ID Plate: "+j.getRegPlate()+" Time: "+j.entryTime()+" Date: "+j.entryDate());
            System.out.println(" ");


            //display longest vehicle parked
            long longest =  Long.MIN_VALUE;
            Vehicle vLongest = null;
            for (Vehicle vehicle : vehicles){
                long hours = vehicle.getDateTime().hours();
                if (hours > longest){
                    longest = hours;
                    vLongest = vehicle;
                }
            }
            if (vLongest!=null) {
                System.out.println("Longest Parked Vehicle is:");
                System.out.println("Type: "+vLongest.getVehicleType()+" ID Plate: "+vLongest.getRegPlate()+" Time: "+vLongest.entryTime()+" Date: "+vLongest.entryDate());
            }



        }
    }


    //Method to show vehicles parked on the specific day
    @Override
    public void showDayList() {
        //this is to display "Nothing to show, car park is empty!" when user chooses this option when parking is empty
        if (vehicles.isEmpty()) {
            System.out.println("Nothing to show, car park is empty!");
            System.out.println("");

        } else {
            //date input to display list of specified day
            Scanner input = new Scanner(System.in);
            System.out.println("View cars of specific day\nEnter day: DD/MM/YYYY");
            String day = input.nextLine();

            //boolean variable in order to determinate  if vehicles with matching date found, if false "No vehicles found"
            boolean found = false;

            //looping trough array to find matching date vehicles, dateEquals method used here
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getDateTime().dateEquals(day)) {
                    System.out.println(vehicle.getVehicleType() + " Plate: " + vehicle.getRegPlate());
                    System.out.println("Time: " + vehicle.entryTime() + " Date " + vehicle.entryDate());
                    found = true;


                }



                if (!found) {
                    System.out.println("No vehicles found");
                }
            }


        }

    }


    //Method to display parking charges
    @Override
    public void parkingCharges() {

        for (Vehicle vehicle : vehicles) {
            double price;
            long hours;

            hours = vehicle.getDateTime().hours();

                System.out.println(vehicle.getVehicleType() + " Plate: " + vehicle.getRegPlate());

                /*calculates parking charges every 24h == 30 pounds, this method works by modulating the number of hours,
                and uses math minimum function which returns the smaller of two values.
                The result is the value closer to negative infinity.
                This method considers negative zero to be strictly smaller than positive zero.
                If one argument is positive zero and the other is negative zero, the result is negative zero.*/
                if (hours >= 24) {
                    price = (hours / 24) * 30;
                    long h = hours % 24;
                    long h4 = Math.min(h, 3) * 2;
                    price += h4 + ((hours) % 24);

                } else if (hours < 24 && hours > 3) {
                    price = 9;
                    price += (hours - 3) * 1;
                } else {
                    price = hours * 3;
                }
                //the program allows to input future date, this is to prevent negative values of hours and charges being displayed
                if (hours < 0) {
                    System.out.println("Date is in the future");
                }
                System.out.println("Hours: " + hours + " Price: " + price);
            }
        //if parking is empty and user chooses to display charges, the message "No charges to display, car park is empty" will display
        if (vehicles.isEmpty()) {
            System.out.println("No charges to display, car park is empty");
            System.out.println("");

        }
        }



    //Main menu method
    @Override
    public boolean runMenu() {

        boolean exit = false;

        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Menu");
        System.out.println("A : Add Vehicle");
        System.out.println("D : Delete Vehicle");
        System.out.println("P : Show parked list");
        System.out.println("S : Show Statistics");
        System.out.println("L : Show list of the day");
        System.out.println("C : Show Parking charges");
        System.out.println("X : To Exit Program");

        String menu = input.next();


        switch (menu.toUpperCase().charAt(0)) {
            case 'A':
                System.out.println("You have chosen: A (Add Vehicle) option.");
                System.out.println(" ");
                this.addVehicle();
                break;
            case 'D':
                System.out.println("You have chosen: D (Delete Vehicle) option.");
                System.out.println(" ");
                this.deleteVehicle();
                break;
            case 'P':
                System.out.println("You have chosen: P (Print List) option.");
                System.out.println(" ");
                this.displayParkedVehicles();
                break;
            case 'S':
                System.out.println("You have chosen: S (Show Statistics) option.");
                System.out.println(" ");
                this.statistics();
                break;
            case 'L':
                System.out.println("You have chosen: L (Show List Of The Day) option.");
                System.out.println(" ");
                this.showDayList();
                break;
            case 'C':
                System.out.println("You have chosen: C (Show Parking Charges) option.");
                System.out.println(" ");
                this.parkingCharges();
                break;
            case 'X':
                System.out.println("Program Has ended");
                exit = true;
                break;
            default:
                System.err.println("WRONG INPUT, PLEASE USE MENU OPTIONS:");
                break;
        }
        return exit;
    }


    public static void main(String[] args) {

        //create parking, variable spaces to limit array to 20 indexes
        CarParkManager lot = new WestminsterCarParkmanager(spaces);
        boolean exit = false;

        while (!exit) {
            exit = lot.runMenu();
        }

    }
}
