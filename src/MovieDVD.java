import java.io.Serializable;

class MovieDVD extends DVD {
    private static final long serialVersionUID = 1L;
    
    private String director;
    private String genre;
    
    public MovieDVD(int id, String title, double rentalPrice, String director, String genre) {
        super(id, title, rentalPrice);
        this.director = director;
        this.genre = genre;
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