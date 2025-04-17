import java.io.IOException;
import java.util.Scanner;

/**
 * Main class that provides a command-line interface for the DVD Rental System
 * Handles user interactions and delegates operations to the RentalSystem class
 */
public class Main {
    /**
     * Entry point of the application
     * Sets up the rental system and processes user commands
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize the rental system
        RentalSystem rentalSystem = new RentalSystem();

        // Attempt to load existing data
        try {
            rentalSystem.loadData();
        } catch (IOException e) {
            System.out.println("Could not load data: " + e.getMessage());
        }
        
        // Set up scanner for user input
        Scanner scanner = new Scanner(System.in);
        boolean choosing = true;

        // Main program loop
        while (choosing) {
            // Display menu options
            System.out.println("\n===== DVD RENTAL SYSTEM =====");
            System.out.println("1. Display All DVDs");
            System.out.println("2. Rent DVD");
            System.out.println("3. Return DVD");
            System.out.println("4. Add New DVD");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");

            try {
                // Process user selection
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        rentalSystem.displayAllDVDs();  // Show inventory
                        break;
                    case 2:
                        rentDVD(scanner, rentalSystem);  // Handle rental process
                        break;
                    case 3:
                        returnDVD(scanner, rentalSystem);  // Handle return process
                        break;
                    case 4:
                        addNewDVD(scanner, rentalSystem);  // Add new DVD to inventory
                        break;
                    case 5:
                        rentalSystem.saveData();  // Save data before exit
                        System.out.println("Data saved. Exiting...");
                        choosing = false;  // Exit program loop
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                // Handle invalid numeric input
                System.out.println("Please enter a valid number.");
            } catch (RentalException e) {
                // Handle rental-specific errors
                System.out.println("Rental Error: " + e.getMessage());
            } catch (IOException e) {
                // Handle file I/O errors
                System.out.println("Error saving data: " + e.getMessage());
            }
        }

        // Clean up resources
        scanner.close();
    }

    /**
     * Handles the DVD rental process
     * Gets DVD ID and rental duration from user and processes the rental
     * 
     * @param scanner Scanner for user input
     * @param rentalSystem Reference to the rental system
     * @throws RentalException If the rental cannot be completed
     */
    private static void rentDVD(Scanner scanner, RentalSystem rentalSystem) throws RentalException {
        System.out.println("\n-- Rent DVD --");
        rentalSystem.displayAllDVDs();  // Show available DVDs
        System.out.print("Enter DVD ID to rent: ");
        int dvdId = Integer.parseInt(scanner.nextLine());  // Get DVD ID
        System.out.print("Enter rental days: ");
        int days = Integer.parseInt(scanner.nextLine());  // Get rental duration
        rentalSystem.rentDVD(dvdId, days);  // Process rental
        System.out.println("DVD rented successfully!");
    }

    /**
     * Handles the DVD return process
     * Gets DVD ID from user and processes the return
     * 
     * @param scanner Scanner for user input
     * @param rentalSystem Reference to the rental system
     * @throws RentalException If the return cannot be completed
     */
    private static void returnDVD(Scanner scanner, RentalSystem rentalSystem) throws RentalException {
        System.out.println("\n-- Return DVD --");
        System.out.print("Enter DVD ID to return: ");
        int dvdId = Integer.parseInt(scanner.nextLine());  // Get DVD ID
        
        // Check if DVD is actually rented and can be returned
        double charges = rentalSystem.returnDVD(dvdId);
        
        if (charges == 0.0) {
            System.out.println("DVD returned successfully!");
        } else {
            System.out.println("This DVD was not rented.");
        }
    }
    
    /**
     * Handles adding a new DVD to the inventory
     * Gets DVD type, title, and price from user and adds to system
     * 
     * @param scanner Scanner for user input
     * @param rentalSystem Reference to the rental system
     */
    private static void addNewDVD(Scanner scanner, RentalSystem rentalSystem) {
        System.out.println("\n-- Add New DVD --");

        // Get and validate DVD type
        System.out.print("Enter DVD Type (movie/game): ");
        String type = scanner.nextLine().toLowerCase();

        if (!type.equals("movie") && !type.equals("game")) {
            System.out.println("Invalid type. Only 'movie' or 'game' allowed.");
            return;
        }

        // Get DVD title
        System.out.print("Enter DVD Title: ");
        String title = scanner.nextLine();

        // Get and validate rental price
        System.out.print("Enter Rental Price: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price input.");
            return;
        }

        // Add DVD to the system
        try {
            rentalSystem.addDVD(type, title, price);
            System.out.println("DVD added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding DVD: " + e.getMessage());
        }
    }
}