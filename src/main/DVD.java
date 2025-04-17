import java.util.Date;

/**
 * Abstract DVD class that implements the Rentable interface
 * Serves as a base class for specific DVD types
 */
abstract class DVD implements Rentable {

    // Core DVD properties
    private int id;                // Unique identifier for the DVD
    private String title;          // Title of the DVD
    private double rentalPrice;    // Daily rental price
    private boolean isAvailable;   // Availability status flag
    private Date rentDate;         // Date when the DVD was rented
    private int rentalDays;        // Number of days the DVD is rented for

    /**
     * Constructor to initialize a DVD with essential information
     * Sets the DVD as available by default
     */
    public DVD(int id, String title, double rentalPrice) {
        this.id = id;
        this.title = title;
        this.rentalPrice = rentalPrice;
        this.isAvailable = true;   // New DVDs are initially available
        this.rentDate = null;      // No rental date for new DVDs
        this.rentalDays = 0;       // No rental days for new DVDs
    }

    // Getters and setters for DVD properties
    
    /**
     * @return The unique identifier of this DVD
     */
    public int getId() {
        return id;
    }

    /**
     * @return The title of this DVD
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The daily rental price of this DVD
     */
    public double getRentalPrice() {
        return rentalPrice;
    }

    /**
     * @return True if the DVD is available for rent, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status of the DVD
     * @param available The new availability status
     */
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    /**
     * @return The date when this DVD was rented, or null if not rented
     */
    public Date getRentDate() {
        return rentDate;
    }

    /**
     * Sets the rental date for this DVD
     * @param rentDate The date when the DVD was rented
     */
    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * @return The number of days this DVD is rented for
     */
    public int getRentalDays() {
        return rentalDays;
    }

    /**
     * Sets the rental duration in days
     * @param rentalDays The number of days
     */
    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    /**
     * Calculates the total rental cost
     * @return Total cost based on daily price multiplied by rental days
     */
    public double calculateRentalCost() {
        return rentalPrice * rentalDays;
    }

    /**
     * Abstract method to be implemented by subclasses
     * Allows for polymorphic behavior based on DVD type
     * @return String representing the type of DVD
     */
    public abstract String getDVDType();

    /**
     * Provides a string representation of the DVD
     * @return Formatted string with DVD details
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Price: $%.2f | Available: %s",
                id, title, rentalPrice, isAvailable ? "Yes" : "No");
    }

    /**
     * Implementation of the rent method from Rentable interface
     * Updates DVD status when it is rented out
     * @param days The number of days the DVD is being rented for
     */
    @Override
    public void rent(int days) {
        setAvailable(false);       // Mark as unavailable
        setRentDate(new Date());   // Set rental date to current date
        setRentalDays(days);       // Set rental duration
    }

    /**
     * Implementation of the returnItem method from Rentable interface
     * Processes a DVD return and resets rental status
     * @return Late fee amount (currently always 0.0)
     */
    @Override
    public double returnItem() {
        // Check if the DVD is already available (not rented)
        if (isAvailable()) {
            return -1.0;  // Error condition: can't return an available DVD
        }

        // Reset the DVD status to available for rent
        setAvailable(true);
        setRentDate(null);
        setRentalDays(0);

        // Currently no late fee calculation is implemented
        return 0.0;
    }

    /**
     * Implementation of the isRented method from Rentable interface
     * @return True if the DVD is currently rented out, false otherwise
     */
    @Override
    public boolean isRented() {
        return !isAvailable();  // A DVD is rented if it's not available
    }
}