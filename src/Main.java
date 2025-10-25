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
}