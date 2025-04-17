import java.util.Date;

abstract class DVD implements Rentable {

    private int id;
    private String title;
    private double rentalPrice;
    private boolean isAvailable;
    private Date rentDate;
    private int rentalDays;

    public DVD(int id, String title, double rentalPrice) {
        this.id = id;
        this.title = title;
        this.rentalPrice = rentalPrice;
        this.isAvailable = true;
        this.rentDate = null;
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

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
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

    public double calculateLateFee(int daysLate) {
        return daysLate * (rentalPrice * 1.5); // Default late fee is 1.5x the daily rental price
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
        setRentDate(new Date());
        setRentalDays(days);
    }

    @Override
    public double returnItem() {
        if (isAvailable()) {
            return 0.0;
        }
        Date returnDate = new Date();
        long diffInMillis = returnDate.getTime() - getRentDate().getTime();
        int actualDays = (int) (diffInMillis / (1000 * 60 * 60 * 24)) + 1;
        int daysLate = actualDays - getRentalDays();
        double lateFee = daysLate > 0 ? calculateLateFee(daysLate) : 0.0;

        setAvailable(true);
        setRentDate(null);
        setRentalDays(0);

        return lateFee;
    }

    @Override
    public boolean isRented() {
        return !isAvailable();
    }
}
