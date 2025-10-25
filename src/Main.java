import java.util.*;
import java.io.*;

public class Main {

    //Constants
    static final int MAX_CITIES = 30;
    static final int MAX_DELIVERIES = 50;
    static final double FUEL_PRICE = 310.0;

    //Arrays
    static String[] cities = new String[MAX_CITIES];
    static int[][] distances = new int[MAX_CITIES][MAX_CITIES];
    static String[] deliveryRecords = new String[MAX_DELIVERIES];
    static int cityCount = 0;
    static int deliveryCount = 0;

    //Vehicle data
    static final String[] vehicleTypes = {"Van", "Truck", "Lorry"};
    static final int[] capacities = {1000, 5000, 10000};
    static final int[] ratesPerKm = {30, 40, 80};
    static final int[] avgSpeeds = {60, 50, 45};
    static final int[] fuelEfficiencies = {12, 6, 4};

    //File names
    static final String ROUTES_FILE = "C:\\Users\\User\\Desktop\\LogisticsManagementSystem\\routes.txt";
    static final String DELIVERIES_FILE = "C:\\Users\\User\\Desktop\\LogisticsManagementSystem\\deliveries.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("----------LOGISTICS MANAGEMENT SYSTEM----------");
            System.out.println("1.City Management");
            System.out.println("2.Distance Management");
            System.out.println("3.Delivery Request Handling");
            System.out.println("4.Delivery Records");
            System.out.println("5.Performance Reports");
            System.out.println("6.Save and Exit");
            System.out.println("-----------------------------------------------");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    cityManagement();
                    break;
                case 2:
                    distanceManagement();
                    break;
                case 3:
                    handleDeliveryRequest();
                    break;
                case 4:
                    viewDeliveryRecords();
                    break;
                case 5:
                    generateReports();
                    break;
                case 6:
                    saveDataToFiles();
                    System.out.println("Data Successfully Saved");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(choice != 6);

        sc.close();
    }

    //City Management
    static void cityManagement() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("----------CITY MANAGEMENT----------");
            System.out.println("1.Add New City");
            System.out.println("2.Rename City");
            System.out.println("3.Remove City");
            System.out.println("4.Back");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    addNewCity(sc);
                    break;
                case 2:
                    renameCity(sc);
                    break;
                case 3:
                    removeCity(sc);
                    break;
                case 4:
                    System.out.println("Returning");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(choice != 4);
    }

    //Print all cities
    static void allCities() {
        System.out.println("----------LIST OF CITIES----------");

        for(int i = 0; i < cityCount; i++) {
            System.out.println((i + 1) + "." + cities[i]);
        }
    }

    //City management functions
    static void addNewCity(Scanner sc) {
        if(cityCount >= MAX_CITIES) {
            System.out.println("Maximum city limit reached!");
            return;
        }

        System.out.print("Enter city name: ");
        String cityName = sc.nextLine().trim();

        cities[cityCount] = cityName;
        cityCount++;
        System.out.println(cityName + " added successfully!");
    }

    static void renameCity(Scanner sc) {
        allCities();
        System.out.print("Enter city number to rename: ");
        int cityIndex = sc.nextInt() - 1;
        sc.nextLine(); // consume newline


        System.out.print("Enter new name for " + cities[cityIndex] + ": ");
        String newName = sc.nextLine().trim();


        cities[cityIndex] = newName;
        System.out.println("City renamed");
    }

    static void removeCity(Scanner sc) {
        allCities();
        System.out.print("Enter city number to remove: ");
        int cityIndex = sc.nextInt() - 1;

        //Shift cities array
        for(int i = cityIndex; i < cityCount - 1; i++) {
            cities[i] = cities[i + 1];
        }
        cities[cityCount - 1] = null;
        cityCount--;

        //Shift distances matrix
        shiftDistancesMatrix(cityIndex);

        System.out.println("City removed");
    }

    static void shiftDistancesMatrix(int removedIndex) {
        //Shift rows up
        for(int i = removedIndex; i < cityCount; i++) {
            for(int j = 0; j < MAX_CITIES; j++) {
                distances[i][j] = distances[i + 1][j];
            }
        }

        //Shift columns left
        for(int i = 0; i < cityCount; i++) {
            for(int j = removedIndex; j < cityCount; j++) {
                distances[i][j] = distances[i][j + 1];
            }
        }
    }

    //Distance Management
    static void distanceManagement() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("----------DISTANCE MANAGEMENT----------");
            System.out.println("1.Set/Edit Distance");
            System.out.println("2.View Distance Table");
            System.out.println("3.Back");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    setDistanceBetweenCities(sc);
                    break;
                case 2:
                    displayDistanceTable();
                    break;
                case 3:
                    System.out.println("Returning");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(choice != 3);
    }
}