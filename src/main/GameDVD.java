class GameDVD extends DVD {
    
    public GameDVD(int id, String title, double rentalPrice) {
        super(id, title, rentalPrice);
    }
    
    
    @Override
    public String getDVDType() {
        return "Game";
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Type: Game");
    }
}