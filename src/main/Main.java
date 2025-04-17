import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();

        try {
            rentalSystem.loadData();
        } catch (IOException e) {
            System.out.println("Could not load data: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        boolean choosing = true;

        while (choosing) {
            System.out.println("\n===== DVD RENTAL SYSTEM =====");
            System.out.println("1. Display All DVDs");
            System.out.println("2. Rent DVD");
            System.out.println("3. Return DVD");
            System.out.println("4. Add New DVD");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        rentalSystem.displayAllDVDs();
                        break;
                    case 2:
                        rentDVD(scanner, rentalSystem);
                        break;
                    case 3:
                        returnDVD(scanner, rentalSystem);
                        break;
                    case 4:
                        addNewDVD(scanner, rentalSystem);
                        break;
                    case 5:
                        rentalSystem.saveData();
                        System.out.println("Data saved. Exiting...");
                        choosing = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (RentalException e) {
                System.out.println("Rental Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error saving data: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void rentDVD(Scanner scanner, RentalSystem rentalSystem) throws RentalException {
        System.out.println("\n-- Rent DVD --");
        rentalSystem.displayAllDVDs();
        System.out.print("Enter DVD ID to rent: ");
        int dvdId = Integer.parseInt(scanner.nextLine());
        if (!rentalSystem.isDVDAvailable(dvdId)) {
            System.out.println("Rental Error: DVD is currently not available for rent!");
            return;
        }
        System.out.print("Enter rental days: ");
        int days = Integer.parseInt(scanner.nextLine());
        rentalSystem.rentDVD(dvdId, days);
        System.out.println("DVD rented successfully!");
    }

    private static void returnDVD(Scanner scanner, RentalSystem rentalSystem) throws RentalException {
        System.out.println("\n-- Return DVD --");
        System.out.print("Enter DVD ID to return: ");
        int dvdId = Integer.parseInt(scanner.nextLine());
        
        // Check if DVD is actually rented and can be returned
        double charges = rentalSystem.returnDVD(dvdId);
        
        if (charges == 0.0) {
            System.out.println("DVD returned successfully!");
        } else {
            System.out.println("This DVD was not rented.");
        }
    }
    

    private static void addNewDVD(Scanner scanner, RentalSystem rentalSystem) {
        System.out.println("\n-- Add New DVD --");

        System.out.print("Enter DVD Type (movie/game): ");
        String type = scanner.nextLine().toLowerCase();

        if (!type.equals("movie") && !type.equals("game")) {
            System.out.println("Invalid type. Only 'movie' or 'game' allowed.");
            return;
        }

        System.out.print("Enter DVD Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Rental Price: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price input.");
            return;
        }

        try {
            rentalSystem.addDVD(type, title, price);
            System.out.println("DVD added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding DVD: " + e.getMessage());
        }
    }
}

