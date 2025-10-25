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

    }
}