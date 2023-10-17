package org.example;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            int choice = options(scanner);
            switch (choice) {
                case 0: {
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                }
                case 1: { // List cars
                    listCarsOptions();
                    break;
                }
                case 2: { // Add
                    addCarOptions(scanner);
                    break;
                }
                case 3: { // Update
                    updateCarOptions(scanner);
                    break;
                }
                case 4: { // Remove
                    deleteCarOptions(scanner);
                    break;
                }
                default: {
                    System.out.println("Invalid command... Please try again!");
                    break;
                }
            }
        }
    }

    private static int options(Scanner scanner) {
        System.out.print("""
            -----OPTIONS MENU-----\s
            (1) List cars.
            (2) Add a new car.
            (3) Update car.
            (4) Remove car.
            (0) Exit.
            > Enter option: """);
        return scanner.nextInt();
    }

    private static void listCarsOptions() {
        System.out.println("_____LIST OF CARS_____");
        ArrayList<Car> cars = Database.getCars();
        for (Car car : cars) {
            System.out.println("id: "+car.getId()+
                    "\tmake: "+car.getMake()+
                    "\tmodel: "+car.getModel()+
                    "\tyear: "+car.getYear());
        }
        //System.out.println(Database.listCars());
    }

    private static boolean addCarOptions(Scanner scanner) {
        System.out.println("_____ADD CAR_____");
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        Car newCar = new Car(make, model, year);
        return Database.addCar(newCar);
    }

    private static void updateCarOptions(Scanner scanner) {
        System.out.println("_____UPDATE CAR_____");
        printCars();
        System.out.print("> Enter id: ");
        int id = scanner.nextInt();
        boolean running = true;
        while (running) {
            Car car = Database.findCarById(id);
            printCar(id);
            System.out.print("""
                    (1) Update make.\s
                    (2) Update model.
                    (3) Update year.
                    (0) Back to main menu.
                    > Enter option:
                    """);
            int option = scanner.nextInt();
            switch(option) {
                case 1: {
                    System.out.print("Enter new make: ");
                    String newMake = scanner.next();
                    car.setMake(newMake);
                    updateCar(car);
                    break;
                }
                case 2: {
                    System.out.print("Enter new model: ");
                    String newModel = scanner.next();
                    car.setModel(newModel);
                    updateCar(car);
                    break;
                }
                case 3: {
                    System.out.print("Enter new year: ");
                    int newYear = scanner.nextInt();
                    car.setYear(newYear);
                    updateCar(car);
                    break;
                }
                default: {
                    running = false;
                    break;
                }
            }
        }
    }

    private static void updateCar(Car car) {
        boolean res = Database.updateCar(car);
        if (res) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }
    }

    private static void deleteCarOptions(Scanner scanner) {
        System.out.println("_____REMOVE CAR_____");
        printCars();
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        boolean res = Database.removeCar(id);
        if (res) {
            System.out.println("Success! Car (id: "+id+") removed.");
        } else {
            System.out.println("Failed to delete car. Please try again!");
            deleteCarOptions(scanner);
        }
    }

    private static void printCars() {
        ArrayList<Car> cars = Database.getCars();
        for (Car car : cars) {
            System.out.println("id: "+car.getId()+
                    "\tmake: "+car.getMake()+
                    "\tmodel: "+car.getModel()+
                    "\tyear: "+car.getYear());
        }
    }

    private static void printCar(int id) {
        Car car = Database.findCarById(id);
        System.out.println(car);
    }


}
