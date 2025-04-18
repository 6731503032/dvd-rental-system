
class GameDVD extends DVD {

    public GameDVD(int id, String title, double rentalPrice) {
        super(id, title, rentalPrice);  // Initialize using the parent constructor
    }
    
    @Override
    public String getDVDType() {
        return "Game";  // Returns the type identifier for this DVD
    }
}