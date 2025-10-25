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
                    saveDataToFile();
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

    //Distance management functions
    static void setDistanceBetweenCities(Scanner sc) {
        allCities();
        System.out.print("Enter source city number: ");
        int source = sc.nextInt() - 1;
        System.out.print("Enter destination city number: ");
        int dest = sc.nextInt() - 1;


        if(source == dest) {
            System.out.println("Source and destination cannot be the same!");
            return;
        }

        System.out.print("Enter distance (km): ");
        int distance = sc.nextInt();


        setDistance(source,dest,distance);
        System.out.println("Distance set successfully!");
    }

    static void setDistance(int source,int dest,int distance) {
        distances[source][dest] = distance;
        distances[dest][source] = distance;
    }

    static void displayDistanceTable() {

        System.out.println("----------DISTANCE TABLE----------");
        System.out.printf("%-15s","");
        for(int i = 0; i < cityCount; i++) {
            System.out.printf("%-15s",cities[i]);
        }
        System.out.println();

        for(int i = 0; i < cityCount; i++) {
            System.out.printf("%-15s",cities[i]);
            for(int j = 0; j < cityCount; j++) {
                if(distances[i][j] == -1) {
                    System.out.printf("%-15s","N/A"); //N/A means not available
                } else {
                    System.out.printf("%-15d",distances[i][j]);
                }
            }
            System.out.println();
        }
    }

    //Print all vehicle data
    static void displayVehicleTypes() {
        System.out.println("----------VEHICLE TYPES----------");
        System.out.println("1.Van   - Capacity: 1000kg,  Rate: 30 LKR/km, Speed: 60 km/h");
        System.out.println("2.Truck - Capacity: 5000kg,  Rate: 40 LKR/km, Speed: 50 km/h");
        System.out.println("3.Lorry - Capacity: 10000kg, Rate: 80 LKR/km, Speed: 45 km/h");
    }


    //Delivery Request Handling
    static void handleDeliveryRequest() {

        Scanner scanner = new Scanner(System.in);

        allCities();
        System.out.print("Enter source city number: ");
        int source = scanner.nextInt() - 1;
        System.out.print("Enter destination city number: ");
        int dest = scanner.nextInt() - 1;

        if(source == dest) {
            System.out.println("Source and destination cannot be the same!");
            return;
        }

        System.out.print("Enter package weight (kg): ");
        double weight = scanner.nextDouble();

        displayVehicleTypes();
        System.out.print("Select vehicle type (1-3): ");
        int vehicleType = scanner.nextInt() - 1;

        if(weight > capacities[vehicleType]) {
            System.out.println("Weight exceeds vehicle capacity! Maximum: " + capacities[vehicleType] + "kg");
            return;
        }

        // Find minimum distance (using direct distance for now)
        int minDistance = distances[source][dest];

        //Calculate and display delivery cost
        calculateAndDisplayDeliveryCost(source,dest,minDistance,weight,vehicleType);
    }

    static void calculateAndDisplayDeliveryCost(int source,int dest,int distance,double weight,int vehicleType) {
        String vehicleName = vehicleTypes[vehicleType];
        int ratePerKm = ratesPerKm[vehicleType];
        int speed = avgSpeeds[vehicleType];
        int efficiency = fuelEfficiencies[vehicleType];

        //Calculate base cost
        double baseCost = distance * ratePerKm * (1 + weight / 10000.0);

        //Calculate fuel consumption
        double fuelUsed = (double)distance / efficiency;
        double fuelCost = fuelUsed * FUEL_PRICE;

        //Calculate operational cost
        double operationalCost = baseCost + fuelCost;

        //Calculate profit (25% markup on base cost)
        double profit = baseCost * 0.25;

        //Calculate final charge
        double customerCharge = operationalCost + profit;

        //Calculate estimated time
        double estimatedTime = (double)distance / speed;

        //Display results
        System.out.println("----------DELIVERY COST ESTIMATION----------");
        System.out.println("From: " + cities[source]);
        System.out.println("To: " + cities[dest]);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Vehicle: " + vehicleName);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("--------------------------------");
        System.out.printf("Base Cost: %.2f LKR\n",baseCost);
        System.out.printf("Fuel Used: %.2f L\n",fuelUsed);
        System.out.printf("Fuel Cost: %.2f LKR\n",fuelCost);
        System.out.printf("Operational Cost: %.2f LKR\n",operationalCost);
        System.out.printf("Profit: %.2f LKR\n",profit);
        System.out.printf("Customer Charge: %.2f LKR\n",customerCharge);
        System.out.printf("Estimated Time: %.2f hours\n",estimatedTime);
        System.out.println("--------------------------------");
        System.out.println("");

        //Save delivery record
        saveDeliveryRecord(cities[source],cities[dest],distance,weight,vehicleName,customerCharge,estimatedTime);
    }

    static void saveDeliveryRecord(String from,String to,int distance,double weight,String vehicle,double charge,double time) {

        String record = String.format("From: %s, To: %s, Distance: %d km, Weight: %.1f kg, Vehicle: %s, Charge: %.2f LKR, Time: %.2f hrs",
                from,to,distance,weight,vehicle,charge,time);
        deliveryRecords[deliveryCount] = record;
        deliveryCount++;

        System.out.println("Delivery record saved!");
    }

    //Display delivery records
    static void viewDeliveryRecords() {
        System.out.println("----------DELIVERY RECORDS---------");
        if(deliveryCount == 0) {
            System.out.println("No delivery records available");
            return;
        }

        for(int i = 0; i < deliveryCount; i++) {
            System.out.println((i + 1) + "." + deliveryRecords[i]);
        }
        System.out.println("");
    }

    //Performance Reports
    static void generateReports() {

        double totalDistance = 0;
        double totalTime = 0;
        double totalRevenue = 0;
        double minDistance = Double.MAX_VALUE;
        double maxDistance = 0;

        for(int i = 0; i < deliveryCount; i++) {
            String record = deliveryRecords[i];
            String[] parts = record.split(",");

            //Distance
            String distStr = parts[2].split(":")[1].trim().replace("km", "").trim();
            double distance = Double.parseDouble(distStr);
            totalDistance += distance;

            // Revenue
            String chargeStr = parts[5].split(":")[1].trim().replace("LKR", "").trim();
            double revenue = Double.parseDouble(chargeStr);
            totalRevenue += revenue;

            //Average time
            String timeStr = parts[6].split(":")[1].trim().replace("hrs", "").trim();
            double time = Double.parseDouble(timeStr);
            totalTime += time;

            if(distance < minDistance) minDistance = distance;
            if(distance > maxDistance) maxDistance = distance;
        }

        double averageTime = totalTime / deliveryCount;
        double totalProfit = totalRevenue * 0.2; // Approximate profit

        System.out.println("-----------PERFORMANCE REPORTS----------");
        System.out.println("Total Deliveries Completed: " + deliveryCount);
        System.out.printf("Total Distance Covered: %.2f km\n",totalDistance);
        System.out.printf("Average Delivery Time: %.2f hours\n",averageTime);
        System.out.printf("Total Revenue: %.2f LKR\n",totalRevenue);
        System.out.printf("Total Profit: %.2f LKR\n",totalProfit);
        System.out.printf("Shortest Route: %.2f km\n",minDistance);
        System.out.printf("Longest Route: %.2f km\n",maxDistance);
        System.out.println("");
    }

    //File Handling methods
    static void saveDataToFile() {
        saveRoutesToFile();
        saveDeliveriesToFile();
    }

    static void saveRoutesToFile() {
        try {
            PrintWriter writer = new PrintWriter(ROUTES_FILE);

            // Save cities
            for(int i = 0; i < cityCount; i++) {
                writer.print(cities[i]);
                if(i < cityCount - 1) writer.print(",");
            }
            writer.println();

            // Save distances
            for(int i = 0; i < cityCount; i++) {
                for(int j = 0; j < cityCount; j++) {
                    writer.print(distances[i][j]);
                    if(j < cityCount - 1) writer.print(",");
                }
                writer.println();
            }
            writer.close();

        } catch(IOException e) {
            System.out.println("Error saving routes: " + e.getMessage());
        }
    }

    static void saveDeliveriesToFile() {
        try {
            PrintWriter writer = new PrintWriter(DELIVERIES_FILE);
            for(int i = 0; i < deliveryCount; i++) {
                writer.println(deliveryRecords[i]);
            }
            writer.close();

        } catch(IOException e) {
            System.out.println("Error saving deliveries: " + e.getMessage());
        }
    }

}