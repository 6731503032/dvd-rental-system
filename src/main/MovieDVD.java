/**
 * MovieDVD class that extends the abstract DVD class
 * Represents DVD movies that can be rented in the system
 */
class MovieDVD extends DVD {
    
    /**
     * Constructor for creating a new movie DVD
     * Calls the parent DVD constructor with the provided parameters
     * 
     * @param id Unique identifier for the movie
     * @param title Title of the movie
     * @param rentalPrice Daily rental price for the movie
     */
    public MovieDVD(int id, String title, double rentalPrice) {
        super(id, title, rentalPrice);  // Initialize using the parent constructor
    }
    
    /**
     * Implementation of the abstract method from DVD class
     * Identifies this DVD as a Movie type
     * 
     * @return String indicating this is a Movie DVD
     */
    @Override
    public String getDVDType() {
        return "Movie";  // Returns the type identifier for this DVD
    }
    
}