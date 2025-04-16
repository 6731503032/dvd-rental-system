import java.io.*;
import java.util.*;

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
        boolean running = true;
        
        while (running) {
            try {
                System.out.println("\n===== DVD RENTAL SYSTEM =====");
                System.out.println("1. Add New DVD");
                System.out.println("2. Add New Customer");
                System.out.println("3. Display All DVDs");
                System.out.println("4. Display All Customers");
                System.out.println("5. Rent DVD");
                System.out.println("6. Return DVD");
                System.out.println("7. Save and Exit");
                System.out.print("Choose an option: ");
                
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        addDVD(scanner, rentalSystem);
                        break;
                    case 2:
                        addCustomer(scanner, rentalSystem);
                        break;
                    case 3:
                        rentalSystem.displayAllDVDs();
                        break;
                    case 4:
                        rentalSystem.displayAllCustomers();
                        break;
                    case 5:
                        rentDVD(scanner, rentalSystem);
                        break;
                    case 6:
                        returnDVD(scanner, rentalSystem);
                        break;  
                    case 7:
                        try {
                            rentalSystem.saveData();
                            System.out.println("Data saved successfully!");
                        } catch (IOException e) {
                            System.out.println("Error saving data: " + e.getMessage());
                        }
                        running = false;
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
    
    private static void addDVD(Scanner scanner, RentalSystem rentalSystem) {
        System.out.println("\n-- Add New DVD --");
        System.out.println("Select DVD type:");
        System.out.println("1. Movie DVD");
        System.out.println("2. Game DVD");
        System.out.print("Choose type: ");
        
        try {
            int type = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            
            System.out.print("Enter rental price per day: ");
            double rentalPrice = Double.parseDouble(scanner.nextLine());
            
            DVD dvd;
            
            if (type == 1) {
                System.out.print("Enter director: ");
                String director = scanner.nextLine();
                
                System.out.print("Enter genre: ");
                String genre = scanner.nextLine();
                
                dvd = new MovieDVD(rentalSystem.getNextDVDId(), title, rentalPrice, director, genre);
            } else if (type == 2) {
                System.out.print("Enter platform: ");
                String platform = scanner.nextLine();
                
                System.out.print("Enter age rating: ");
                int ageRating = Integer.parseInt(scanner.nextLine());
                
                dvd = new GameDVD(rentalSystem.getNextDVDId(), title, rentalPrice, platform, ageRating);
            } else {
                System.out.println("Invalid type selected.");
                return;
            }
            
            rentalSystem.addDVD(dvd);
            System.out.println("DVD added successfully!");
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values.");
        }
    }
    
    private static void addCustomer(Scanner scanner, RentalSystem rentalSystem) {
        System.out.println("\n-- Add New Customer --");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        
        Customer customer = new Customer(rentalSystem.getNextCustomerId(), name, phone, address);
        rentalSystem.addCustomer(customer);
        
        System.out.println("Customer added successfully!");
    }
    
    private static void rentDVD(Scanner scanner, RentalSystem rentalSystem) throws RentalException {
        System.out.println("\n-- Rent DVD --");
        
        rentalSystem.displayAllDVDs();
        System.out.print("Enter DVD ID to rent: ");
        int dvdId = Integer.parseInt(scanner.nextLine());
        
        rentalSystem.displayAllCustomers();
        System.out.print("Enter Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter rental days: ");
        int days = Integer.parseInt(scanner.nextLine());
        
        rentalSystem.rentDVD(dvdId, customerId, days);
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