
/**
 * Custom exception class for handling rental system specific errors.
 * Extends the standard Java Exception class to provide specialized
 * error handling for DVD rental operations.
 */
class RentalException extends Exception {   
    
    /**
     * Constructor that accepts an error message describing the exception.
     * 
     * @param message A descriptive error message explaining what went wrong
     *                in the rental operation
     */
    public RentalException(String message) {
        super(message); // Pass the message to the parent Exception constructor
    }
}