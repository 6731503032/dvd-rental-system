class MovieDVD extends DVD {
    
    public MovieDVD(int id, String title, double rentalPrice) {
        super(id, title, rentalPrice);
    }
    
    @Override
    public String getDVDType() {
        return "Movie";
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        // Movies have a higher late fee - 2x the rental price
        return daysLate * (getRentalPrice() * 2.0);
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Type: Movie");
    }
}