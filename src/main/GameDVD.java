/**
 * GameDVD class that extends the abstract DVD class
 * Represents DVD games that can be rented in the system
 */
class GameDVD extends DVD {
    
    /**
     * Constructor for creating a new game DVD
     * Calls the parent DVD constructor with the provided parameters
     * 
     * @param id Unique identifier for the game
     * @param title Title of the game
     * @param rentalPrice Daily rental price for the game
     */
    public GameDVD(int id, String title, double rentalPrice) {
        super(id, title, rentalPrice);  // Initialize using the parent constructor
    }
    
    /**
     * Implementation of the abstract method from DVD class
     * Identifies this DVD as a Game type
     * 
     * @return String indicating this is a Game DVD
     */
    @Override
    public String getDVDType() {
        return "Game";  // Returns the type identifier for this DVD
    }
    
    /**
     * Overrides the toString method to include the DVD type
     * Builds upon the parent class toString implementation
     * 
     * @return Formatted string with DVD details including the Game type
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Type: Game");  // Appends game type to parent string representation
    }
}