
/*
 Custom exception class for handling rental system specific errors.
 */
class RentalException extends Exception {  //checked exception 

    public RentalException(String message) {
        super(message);
    }
}