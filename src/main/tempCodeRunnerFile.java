import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();

        try {
            rentalSystem.loadData();
        } catch (FileNotFoundException e) {
            System.out.println("Data files not found. Starting with empty inventory.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        } 

        Scanner scanner = new Scanner(System.in);
        boolean choosing = true;

        while (choosing) {
            try {
                System.out.println("\n===== DVD RENTAL SYSTEM =====");
                System.out.println("1. Display All DVDs");
                System.out.println("2. Rent DVD");
                System.out.println("3. Return DVD");
                System.out.println("4. Save and Exit");
                System.out.print("Choose an option: ");

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
                        try {
                            rentalSystem.saveData();
                            System.out.println("Data saved successfully!");
                        } catch (IOException e) {
                            System.out.println("Error saving data: " + e.getMessage());
                        }
                        choosing = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (RentalException e) {
                System.out.println("Rental Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Thank you for using the DVD Rental System!");
    }

    private static void rentDVD(Scanner scanner, RentalSystem rentalSystem) throws RentalException {
        System.out.println("\n-- Rent DVD --");

        rentalSystem.displayAllDVDs();
        System.out.print("Enter DVD ID to rent: ");
        int dvdId = Integer.parseInt(scanner.nextLine());

        // Skipping customer selection
        System.out.print("Enter rental days: ");
        int days = Integer.parseInt(scanner.nextLine());

        rentalSystem.rentDVD(dvdId, days);
        System.out.println("DVD rented successfully!");
    }

    private static void returnDVD(Scanner scanner, RentalSystem rentalSystem) throws RentalException {
        System.out.println("\n-- Return DVD --");

        System.out.print("Enter DVD ID to return: ");
        int dvdId = Integer.parseInt(scanner.nextLine());

        double charges = rentalSystem.returnDVD(dvdId);
        System.out.println("DVD returned successfully!");
        System.out.printf("Late charges (if any): $%.2f\n", charges);
    }
}
