import java.io.*;
import java.util.*;

class RentalSystem {
    private Map<Integer, DVD> dvdInventory;
    private Map<Integer, Customer> customerDatabase;
    private static final String DVD_FILE = "dvds.dat";
    private static final String CUSTOMER_FILE = "customers.dat";
    
    public RentalSystem() {
        dvdInventory = new HashMap<>();
        customerDatabase = new HashMap<>();
    }
    
    public int getNextDVDId() {
        return dvdInventory.size() + 1;
    }
    
    public int getNextCustomerId() {
        return customerDatabase.size() + 1;
    }
    
    public void addDVD(DVD dvd) {
        dvdInventory.put(dvd.getId(), dvd);
    }
    
    public void addCustomer(Customer customer) {
        customerDatabase.put(customer.getId(), customer);
    }
    
    public void displayAllDVDs() {
        System.out.println("\n--- DVD INVENTORY ---");
        if (dvdInventory.isEmpty()) {
            System.out.println("No DVDs in inventory.");
            return;
        }
        
        for (DVD dvd : dvdInventory.values()) {
            System.out.println(dvd);
        }
    }
    
    public void displayAllCustomers() {
        System.out.println("\n--- CUSTOMER DATABASE ---");
        if (customerDatabase.isEmpty()) {
            System.out.println("No registered customers.");
            return;
        }
        
        for (Customer customer : customerDatabase.values()) {
            System.out.println(customer);
        }
    }
    
    public void rentDVD(int dvdId, int customerId, int days) throws RentalException {
        // Check if DVD exists
        DVD dvd = dvdInventory.get(dvdId);
        if (dvd == null) {
            throw new RentalException("DVD with ID " + dvdId + " not found!");
        }
        
        // Check if customer exists
        Customer customer = customerDatabase.get(customerId);
        if (customer == null) {
            throw new RentalException("Customer with ID " + customerId + " not found!");
        }
        
        // Check if DVD is available
        if (!dvd.isAvailable()) {
            throw new RentalException("DVD is currently not available for rent!");
        }
        
        // Process rental
        dvd.rent(customerId, days);
        
        // Update customer rental history
        customer.addToRentalHistory(dvdId);
        
        System.out.printf("Rental cost for %d days: $%.2f\n", days, dvd.calculateRentalCost());
    }
    
    public double returnDVD(int dvdId) throws RentalException {
        // Check if DVD exists
        DVD dvd = dvdInventory.get(dvdId);
        if (dvd == null) {
            throw new RentalException("DVD with ID " + dvdId + " not found!");
        }
        
        // Return the DVD
        return dvd.returnItem();
    }
    
    @SuppressWarnings("unchecked")
    public void loadData() throws IOException, ClassNotFoundException {
        File dvdFile = new File(DVD_FILE);
        File customerFile = new File(CUSTOMER_FILE);
        
        // Load DVDs
        if (dvdFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dvdFile))) {
                dvdInventory = (Map<Integer, DVD>) ois.readObject();
            }
        }
        
        // Load Customers
        if (customerFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(customerFile))) {
                customerDatabase = (Map<Integer, Customer>) ois.readObject();
            }
        }
    }
    
    public void saveData() throws IOException {
        // Save DVDs
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DVD_FILE))) {
            oos.writeObject(dvdInventory);
        }
        
        // Save Customers
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CUSTOMER_FILE))) {
            oos.writeObject(customerDatabase);
        }
    }
}