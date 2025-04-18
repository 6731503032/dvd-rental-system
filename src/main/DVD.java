import java.util.Date;

/**
 * Abstract DVD class that implements the Rentable interface
 * Serves as a base class for specific DVD types
 */
abstract class DVD implements Rentable {

    // Core DVD properties
    private int id;          
    private String title;         
    private double rentalPrice;   
    private boolean isAvailable;  
    private Date rentDate;     
    private int rentalDays;      


    public DVD(int id, String title, double rentalPrice) {//default parameters set
        this.id = id;
        this.title = title;
        this.rentalPrice = rentalPrice;
        this.isAvailable = true; 
        this.rentDate = null; 
        this.rentalDays = 0;   
    }

    // Getters and setters for DVD properties
    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public double getRentalPrice() {
        return rentalPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    /*
      Calculates the total rental cost
     */
    public double calculateRentalCost() {
        return rentalPrice * rentalDays;
    }

    /*
      Abstract method to be implemented by subclasses
     */
    public abstract String getDVDType();

    /**
      Provides a string representation of the DVD
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Price: $%.2f | Available: %s",
                id, title, rentalPrice, isAvailable ? "Yes" : "No");
    }

    /*
     *Implementation of the rent method from Rentable interface
     */
    @Override
    public void rent(int days) {
        setAvailable(false);  
        setRentDate(new Date());
        setRentalDays(days);
    }

    /*
      Implementation of the returnItem method from Rentable interface
     */
    @Override
    public double returnItem() {
        // Check if the DVD is already available (not rented)
        if (isAvailable()) {
            return -1.0;  // Error condition: can't return an available DVD
        }

        setAvailable(true);
        setRentDate(null);
        setRentalDays(0);

        return 0.0;
    }

    @Override
    public boolean isRented() {
        return !isAvailable();  // A DVD is rented if it's not available
    }
}