import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private String phone;
    private String address;
    private List<Integer> rentalHistory;
    
    public Customer(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
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
    
    public String getAddress() {
        return address;
    }
    
    public List<Integer> getRentalHistory() {
        return rentalHistory;
    }
    
    public void addToRentalHistory(int dvdId) {
        rentalHistory.add(dvdId);
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Phone: %s | Address: %s", 
            id, name, phone, address);
    }
}