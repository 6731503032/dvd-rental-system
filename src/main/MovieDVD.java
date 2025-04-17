class MovieDVD extends DVD {
    
    private String director;
    private String genre;
    
    public MovieDVD(int id, String title, double rentalPrice) {
        super(id, title, rentalPrice);
    }
    
    public String getDirector() {
        return director;
    }
    
    public String getGenre() {
        return genre;
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
        return super.toString() + String.format(" | Type: Movie | Director: %s | Genre: %s", 
            director, genre);
    }
}