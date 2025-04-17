class MovieDVD extends DVD {
    
    public MovieDVD(int id, String title, double rentalPrice) {
        super(id, title, rentalPrice);
    }
    
    @Override
    public String getDVDType() {
        return "Movie";
    }
    
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Type: Movie");
    }
}