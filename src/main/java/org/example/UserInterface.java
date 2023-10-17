package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import org.example.Database;
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
                    System.out.println("3");
                    break;
                }
                case 4: { // Remove
                    System.out.println("4");
                    break;
                }
                default: {
                    System.out.println("Unvalid command... Please try again!");
                    break;
                }
            }

        }

    }

    private static int options(Scanner scanner) {
        System.out.println("""
                -----Choose option-----\s
                (1) List cars.
                (2) Add a new car.
                (3) Update car.
                (4) Remove car.
                (0) Exit.""");
        return scanner.nextInt();
    }

    private static void listCarsOptions() {
        System.out.println("_____LIST OF CARS_____");
        Database.listCars();
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

    private static boolean updateCarOptions(Scanner scanner) {
        System.out.println("_____UPDATE CAR_____");
        return true;
    }



}
