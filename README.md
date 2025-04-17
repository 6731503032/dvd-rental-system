Overview
The DVD Rental System is a Java application designed to manage the inventory and rental operations of a DVD rental store. The system handles both movie and game DVDs, allowing store owners to track inventory, process rentals and returns, and maintain persistent data storage.
Features

DVD Inventory Management: Add and track movies and games in inventory
Rental Processing: Rent DVDs to customers and calculate rental costs
Return Processing: Process DVD returns and handle any additional fees
Availability Checking: Check DVD availability before processing rentals
Persistent Storage: Save and load inventory data to/from file storage

Technical Details
Core Classes

RentalSystem: Main class handling inventory and rental operations
DVD: Base class/interface for DVD items (not shown in the provided code)
MovieDVD: Specialized class for movie DVDs
GameDVD: Specialized class for game DVDs
RentalException: Custom exception class for rental-specific error handling