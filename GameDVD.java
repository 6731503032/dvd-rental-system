import java.io.Serializable;

class GameDVD extends DVD {
    private static final long serialVersionUID = 1L;
    
    private String platform;
    private int ageRating;
    
    public GameDVD(int id, String title, double rentalPrice, String platform, int ageRating) {
        super(id, title, rentalPrice);
        this.platform = platform;
        this.ageRating = ageRating;
    }
    
    public String getPlatform() {
        return platform;
    }
    
    public int getAgeRating() {
        return ageRating;
    }
    
    @Override
    public String getDVDType() {
        return "Game";
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        // Games might have a different late fee calculation
        return daysLate * (getRentalPrice() * 1.75);
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Type: Game | Platform: %s | Age Rating: %d",
            platform, ageRating);
    }
}