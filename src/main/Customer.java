import java.util.ArrayList;
import java.util.List;

class Customer{
    
    private int id;
    private String name;
    private String phone;
    private List<Integer> rentalHistory;
    
    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.rentalHistory = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhone() {
        return phone;
    }

    
    public List<Integer> getRentalHistory() {
        return rentalHistory;
    }
    
    public void addToRentalHistory(int dvdId) {
        rentalHistory.add(dvdId);
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Phone: %s", 
            id, name, phone);
    }
}