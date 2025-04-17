

abstract class DVD implements Rentable {

    private int id;
    private String title;
    private double rentalPrice;
    private boolean isAvailable;
    private int rentalDays;

    public DVD(int id, String title, double rentalPrice) {
        this.id = id;
        this.title = title;
        this.rentalPrice = rentalPrice;
        this.isAvailable = true;
        this.rentalDays = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public double calculateRentalCost() {
        return rentalPrice * rentalDays;
    }
    

    // Abstract method for polymorphism
    public abstract String getDVDType();

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Price: $%.2f | Available: %s",
                id, title, rentalPrice, isAvailable ? "Yes" : "No");
    }

    @Override
    public void rent(int days) {
        setAvailable(false);
        setRentalDays(days);
    }

    @Override
    public double returnItem() {
        if (isAvailable()) {
            return -1.0;
        }

        // No late fee calculation, just reset
        setAvailable(true);
        setRentalDays(0);
        return 0.0;
    }

    @Override
    public boolean isRented() {
        return !isAvailable();
    }
}

