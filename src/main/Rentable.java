/*
 This interface is implemented by classes that represent rentable items in the system
 */
public interface Rentable {
    void rent(int days);
    double returnItem();
    boolean isRented();
}