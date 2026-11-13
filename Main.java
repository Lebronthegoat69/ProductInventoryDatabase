
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:inventory.db";

        System.out.println("Product Inventory Database");
        System.out.println("---------------------------");

        try (Connection connection = DriverManager.getConnection(url)) {

            // Database connection
            System.out.println("Connecting to database...");
            System.out.println("Connection successful!");

            // Create Products table if it does not exist
            String createTable = """
                    CREATE TABLE IF NOT EXISTS Products (
                        ProductID INTEGER PRIMARY KEY,
                        ProductName TEXT NOT NULL,
                        Category TEXT NOT NULL,
                        Price REAL NOT NULL,
                        Quantity INTEGER NOT NULL
                    );
                    """;

            try (Statement statement = connection.createStatement()) {
                statement.execute(createTable);
                System.out.println("\nProducts table is ready!");
            }

            // Insert sample products
            System.out.println("\nInserting sample products...");

            String insertSQL = """
                    INSERT INTO Products
                    (ProductID, ProductName, Category, Price, Quantity)
                    VALUES
                    (1, 'Laptop', 'Electronics', 899.99, 7),
                    (2, 'Mouse', 'Electronics', 24.99, 35),
                    (3, 'Keyboard', 'Electronics', 49.99, 12),
                    (4, 'Desk Chair', 'Furniture', 149.99, 5),
                    (5, 'Notebook', 'Office Supplies', 3.99, 100),
                    (6, 'Monitor', 'Electronics', 199.99, 8)
                    ON CONFLICT(ProductID) DO NOTHING;
                    """;

            try (Statement insertStatement = connection.createStatement()) {
                int rowsInserted = insertStatement.executeUpdate(insertSQL);
                System.out.println("Sample products inserted: " + rowsInserted);
            }

            // Display all products
            System.out.println("\nAll Products:");
            displayProducts(connection,
                    "SELECT * FROM Products ORDER BY ProductID");

            // Display low-stock products
            System.out.println("\nLow Stock Products (Quantity below 10):");

            displayProducts(connection,
                    "SELECT * FROM Products WHERE Quantity < 10 ORDER BY ProductID");

            // Update product quantity
            System.out.println("\nUpdating quantity for Laptop...");

            String updateSQL =
                    "UPDATE Products SET Quantity = ? WHERE ProductName = ?";

            try (PreparedStatement updateStatement =
                         connection.prepareStatement(updateSQL)) {

                updateStatement.setInt(1, 15);
                updateStatement.setString(2, "Laptop");

                int rowsUpdated = updateStatement.executeUpdate();

                System.out.println("Products updated: " + rowsUpdated);
            }

            // Display updated products
            System.out.println("\nUpdated Product List:");

            displayProducts(connection,
                    "SELECT * FROM Products ORDER BY ProductID");

            System.out.println("\nInventory program completed successfully!");

        } catch (SQLException e) {

            System.out.println("Database error: " + e.getMessage());

        }
    }

    // Method to display products
    public static void displayProducts(Connection connection, String sql)
            throws SQLException {

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {

                int id = result.getInt("ProductID");
                String name = result.getString("ProductName");
                String category = result.getString("Category");
                double price = result.getDouble("Price");
                int quantity = result.getInt("Quantity");

                System.out.printf(
                        "%d | %s | %s | $%.2f | Quantity: %d%n",
                        id, name, category, price, quantity
                );
            }
        }
    }
}
