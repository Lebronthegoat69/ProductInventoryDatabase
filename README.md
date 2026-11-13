
# Product Inventory Database

## Project Description

This project is a Java console application that uses Java Database Connectivity (JDBC) to connect to a local SQLite database.

The program manages a product inventory by creating a Products table, inserting sample products, displaying inventory records, finding low-stock products, and updating product quantities.

## Features

- Connects to a local SQLite database using JDBC.
- Creates the Products table if it does not already exist.
- Inserts sample product records.
- Displays all products in the inventory.
- Finds products with fewer than 10 items in stock.
- Updates the quantity of an existing product.
- Uses PreparedStatement, Statement, ResultSet, and Connection.
- Handles database errors using try-catch.

## Technologies Used

- Java
- JDBC (Java Database Connectivity)
- SQLite
- IntelliJ IDEA

## Database Information

Database file: `inventory.db`

Table name: `Products`

Columns:
- ProductID
- ProductName
- Category
- Price
- Quantity

## How to Run the Program

1. Download or clone this repository.
2. Open the project in IntelliJ IDEA.
3. Make sure the SQLite JDBC driver is included in the project.
4. Confirm that `inventory.db` is located in the project folder.
5. Run `Main.java`.

## SQLite JDBC Setup

The project uses the SQLite JDBC driver:

`sqlite-jdbc-3.54.1.0.jar`

The driver is located in the `lib` folder.

If IntelliJ does not automatically recognize the driver:

1. Right-click the JAR file.
2. Select Add as Library.
3. Make sure it is included in the project dependencies.

## Program Output

The program successfully connects to the SQLite database and displays the product inventory.

It also displays low-stock products and updates the Laptop quantity from 7 to 15.

See `ProductInventoryOutput.png` for a screenshot of the program running.

<img width="975" height="238" alt="image" src="https://github.com/user-attachments/assets/9ce013a0-0faa-4ee1-a200-70fcaf14a7af" />
<img width="799" height="344" alt="image" src="https://github.com/user-attachments/assets/43cadb08-d900-4751-8d0a-4a29a90122b1" />
<img width="603" height="505" alt="image" src="https://github.com/user-attachments/assets/709bc260-7b49-4659-b568-f22675e29a8c" />


Sam
