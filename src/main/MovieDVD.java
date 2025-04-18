class MovieDVD extends DVD {

    public MovieDVD(int id, String title, double rentalPrice) {
        super(id, title, rentalPrice);  // Initialize using the parent constructor
    }
    @Override
    public String getDVDType() {
        return "Movie";  // Returns the type identifier for this DVD
    }
    
}