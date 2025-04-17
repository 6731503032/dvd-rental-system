public interface Rentable {
    void rent(int days);
    double returnItem();
    boolean isRented();
}