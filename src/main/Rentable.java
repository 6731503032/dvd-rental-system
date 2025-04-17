/**
 * Rentable interface defines the contract for objects that can be rented
 * This interface is implemented by classes that represent rentable items in the system
 */
public interface Rentable {
    
    /**
     * Processes the rental of an item
     * Should update the item's state to reflect it has been rented
     * 
     * @param days The number of days the item is being rented for
     */
    void rent(int days);
    
    /**
     * Processes the return of a rented item
     * Should update the item's state to reflect it is now available
     * 
     * @return The amount charged for the return (e.g., rental fee, late fee)
     *         or a negative value to indicate an error condition
     */
    double returnItem();
    
    /**
     * Checks if the item is currently rented out
     * 
     * @return true if the item is currently rented, false otherwise
     */
    boolean isRented();
}