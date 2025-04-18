import java.io.*;
import java.util.*;

class RentalSystem {
    private List<DVD> dvdInventory;
    private static final String DVD_FILE = "src/main/dvd.txt";


    public RentalSystem() {
        dvdInventory = new ArrayList<>();
    }

    public int getNextDVDId() {
        int maxId = 0;
        for (DVD dvd : dvdInventory) {
            if (dvd.getId() > maxId) {
                maxId = dvd.getId();
            }
        }
        return maxId + 1;
    }

    public void addDVD(String type, String title, double price) throws IOException {
        int id = getNextDVDId();
        DVD dvd;

        // Create appropriate DVD subclass based on the type
        if (type.equalsIgnoreCase("movie")) {
            dvd = new MovieDVD(id, title, price);
        } else if (type.equalsIgnoreCase("game")) {
            dvd = new GameDVD(id, title, price);
        } else {
            throw new IllegalArgumentException("Unknown DVD type: " + type);
        }

        // Add the DVD to inventory and save changes to file
        dvdInventory.add(dvd);
        saveData();
    }

    /*
     Shows DVD type, ID, title, price, and availability status in a formatted table
     */
    public void displayAllDVDs() {
        System.out.println("\n--- DVD INVENTORY ---");

        File file = new File(DVD_FILE);
        if (!file.exists()) {
            System.out.println("No DVDs in inventory. File not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean hasDVDs = false;

            // Print header row for the inventory table
            System.out.printf("%-10s %-5s %-25s %-12s %-10s\n", "Type", "ID", "Title", "Price", "Available");
            System.out.println("--------------------------------------------------------------------------");

            // Read each line from the file and display DVD information
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue; // Skip invalid lines
                String type = parts[0];
                int id = Integer.parseInt(parts[1]);
                String title = parts[2];
                double price = Double.parseDouble(parts[3]);
                boolean isAvailable = Boolean.parseBoolean(parts[4]);

                // Format and print DVD information
                System.out.printf("%-10s %-5d %-25s %-12.2f %-10s\n",
                type,
                id,
                title,
                price,
                isAvailable ? "Yes" : "No");
                hasDVDs = true;
            }

            // Display message if no DVDs were found
            if (!hasDVDs) {
                System.out.println("No DVDs in inventory.");
            }

        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
    }
    public void rentDVD(int dvdId, int days) throws RentalException {
        DVD dvd = findDVDById(dvdId);
        if (dvd == null) {
            throw new RentalException("DVD with ID " + dvdId + " not found!");
        }
        if (!dvd.isAvailable()) {
            throw new RentalException("DVD is currently not available for rent!");
        }

        // Process the rental and display the cost
        dvd.rent(days);
        System.out.printf("Rental cost for %d days: $%.2f\n", days, dvd.calculateRentalCost());
    }

    public double returnDVD(int dvdId) throws RentalException {
        DVD dvd = findDVDById(dvdId);
        if (dvd == null) {
            throw new RentalException("DVD with ID " + dvdId + " not found!");
        }
        return dvd.returnItem();
    }

    private DVD findDVDById(int id) {
        for (DVD dvd : dvdInventory) {
            if (dvd.getId() == id) {
                return dvd;
            }
        }
        return null;
    }
    
    /*
      Checks if a DVD is available for rent
     */
    public boolean isDVDAvailable(int dvdId) {
        DVD dvd = findDVDById(dvdId);
        return dvd != null && dvd.isAvailable();
    }

    /*
     Loads DVD data from the file into memory
     */
    public void loadData() throws IOException {
        File file = new File(DVD_FILE);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue; // Skip invalid lines

                String type = parts[0];
                int id = Integer.parseInt(parts[1]);
                String title = parts[2];
                double price = Double.parseDouble(parts[3]);
                boolean isAvailable = Boolean.parseBoolean(parts[4]);

                // Create appropriate DVD object based on type
                DVD dvd = null;
                switch (type.toLowerCase()) {
                    case "movie":
                        dvd = new MovieDVD(id, title, price);
                        break;
                    case "game":
                        dvd = new GameDVD(id, title, price);
                        break;
                }

                // Add DVD to inventory if successfully created
                if (dvd != null) {
                    dvd.setAvailable(isAvailable);
                    dvdInventory.add(dvd);
                }
            }
        }
    }

    /*
     Saves all DVD data from memory to the file
     */
    public void saveData() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DVD_FILE))) {
            for (DVD dvd : dvdInventory) {
                // Determine DVD type and format data for storage
                String type = (dvd instanceof MovieDVD) ? "movie" : (dvd instanceof GameDVD) ? "game" : "unknown";
                writer.write(String.format("%s,%d,%s,%.2f,%b\n",
                        type,
                        dvd.getId(),
                        dvd.getTitle().replace(",", " "), // Replace commas in titles to avoid CSV format issues
                        dvd.getRentalPrice(),
                        dvd.isAvailable()
                ));
            }
        }
    }
}