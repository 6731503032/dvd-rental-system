import java.io.Serializable;

class RentalException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public RentalException(String message) {
        super(message);
    }
}