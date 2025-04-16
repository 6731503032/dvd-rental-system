public interface Rentable {
    void rent(int customerId, int days);
    double returnItem();
    boolean isRented();
}