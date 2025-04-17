import java.io.*;
import java.util.*;

class RentalSystem {
    private List<DVD> dvdInventory;
    private static final String DVD_FILE = "src/main/dvd.txt";
    public RentalSystem() {
        dvdInventory = new ArrayList<>();
    }

    public int getNextDVDId() {
        return dvdInventory.size() + 1;
    }

    public void addDVD(DVD dvd) {
        dvdInventory.add(dvd);
    }

    public void displayAllDVDs() {
        System.out.println("\n--- DVD INVENTORY ---");

        File file = new File("src/main/dvd.txt");
        if (!file.exists()) {
            System.out.println("No DVDs in inventory. File not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean hasDVDs = false;

            System.out.printf("%-5s %-25s %-12s %-10s\n", "ID", "Title", "Price", "Available");
            System.out.println("--------------------------------------------------------------");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                int id = Integer.parseInt(parts[1]);
                String title = parts[2];
                double price = Double.parseDouble(parts[3]);
                boolean isAvailable = Boolean.parseBoolean(parts[4]);

                System.out.printf("%-5d %-25s %-11.2f %-10s\n",
                        id,
                        title,
                        price,
                        isAvailable ? "Yes" : "No"
                );
                hasDVDs = true;
            }
            

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

    public void loadData() throws IOException {
        File file = new File(DVD_FILE);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                String type = parts[0];
                int id = Integer.parseInt(parts[1]);
                String title = parts[2];
                double price = Double.parseDouble(parts[3]);
                boolean isAvailable = Boolean.parseBoolean(parts[4]);

                DVD dvd = null;
                switch (type.toLowerCase()) {
                    case "movie":
                        dvd = new MovieDVD(id, title, price);
                        break;
                    case "game":
                        dvd = new GameDVD(id, title, price);
                        break;
                }

                if (dvd != null) {
                    dvd.setAvailable(isAvailable);
                    dvdInventory.add(dvd);
                }
            }
        }
    }

    public void saveData() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DVD_FILE))) {
            for (DVD dvd : dvdInventory) {
                String type = (dvd instanceof MovieDVD) ? "movie" : (dvd instanceof GameDVD) ? "game" : "unknown";
                writer.write(String.format("%s,%d,%s,%.2f,%b\n",
                        type,
                        dvd.getId(),
                        dvd.getTitle().replace(",", " "),
                        dvd.getRentalPrice(),
                        dvd.isAvailable()
                ));
            }
        }
    }
}



