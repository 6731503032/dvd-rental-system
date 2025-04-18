
/*
 Custom exception class for handling rental system specific errors.
 */
class RentalException extends Exception {   

    public RentalException(String message) {
        super(message);
    }
}