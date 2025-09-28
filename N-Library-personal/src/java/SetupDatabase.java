import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class SetupDatabase {
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String username = "root";
    private static final String password = "Kavindu12345";
    
    public static void main(String[] args) {
        System.out.println("Setting up database and tables...");
        
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to MySQL without database
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            
            // Create database
            System.out.println("Creating database 'library_manage'...");
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS library_manage");
            System.out.println("✓ Database created successfully!");
            
            // Use the database
            statement.executeUpdate("USE library_manage");
            
            // Create user table
            System.out.println("Creating 'user' table...");
            String createUserTable = """
                CREATE TABLE IF NOT EXISTS user (
                    userId INT AUTO_INCREMENT PRIMARY KEY,
                    firstName VARCHAR(50) NOT NULL,
                    lastName VARCHAR(50) NOT NULL,
                    userNic VARCHAR(20) UNIQUE NOT NULL,
                    image VARCHAR(255) DEFAULT 'default_user.png',
                    email VARCHAR(100) UNIQUE NOT NULL,
                    phoneNumber VARCHAR(15),
                    address TEXT,
                    password VARCHAR(255) NOT NULL,
                    active BOOLEAN DEFAULT TRUE,
                    userType ENUM('ADMIN', 'STUDENT') NOT NULL,
                    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            """;
            statement.executeUpdate(createUserTable);
            System.out.println("✓ User table created successfully!");
            
            // Create book_categories table
            System.out.println("Creating 'book_categories' table...");
            String createCategoriesTable = """
                CREATE TABLE IF NOT EXISTS book_categories (
                    categoryId INT AUTO_INCREMENT PRIMARY KEY,
                    categoryName VARCHAR(100) UNIQUE NOT NULL,
                    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """;
            statement.executeUpdate(createCategoriesTable);
            System.out.println("✓ Book categories table created successfully!");
            
            // Create books table
            System.out.println("Creating 'books' table...");
            String createBooksTable = """
                CREATE TABLE IF NOT EXISTS books (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    title VARCHAR(255) NOT NULL,
                    bookId VARCHAR(50) UNIQUE NOT NULL,
                    author VARCHAR(255) NOT NULL,
                    categoryId INT,
                    quantity INT DEFAULT 1,
                    description TEXT,
                    image VARCHAR(255) DEFAULT 'default_book.png',
                    active BOOLEAN DEFAULT TRUE,
                    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (categoryId) REFERENCES book_categories(categoryId)
                )
            """;
            statement.executeUpdate(createBooksTable);
            System.out.println("✓ Books table created successfully!");
            
            // Create barrowed_books table
            System.out.println("Creating 'barrowed_books' table...");
            String createBarrowedBooksTable = """
                CREATE TABLE IF NOT EXISTS barrowed_books (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    userId INT NOT NULL,
                    bookId VARCHAR(50) NOT NULL,
                    barrowDate DATE NOT NULL,
                    returnDate DATE,
                    actualReturnDate DATE,
                    status ENUM('BORROWED', 'RETURNED', 'OVERDUE') DEFAULT 'BORROWED',
                    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (userId) REFERENCES user(userId),
                    FOREIGN KEY (bookId) REFERENCES books(bookId)
                )
            """;
            statement.executeUpdate(createBarrowedBooksTable);
            System.out.println("✓ Borrowed books table created successfully!");
            
            // Insert sample book categories
            System.out.println("Inserting sample book categories...");
            String[] categories = {"Fiction", "Science", "Technology", "History", "Literature"};
            for (String category : categories) {
                statement.executeUpdate("INSERT IGNORE INTO book_categories (categoryName) VALUES ('" + category + "')");
            }
            System.out.println("✓ Sample categories inserted!");
            
            connection.close();
            System.out.println("✓ Database setup completed successfully!");
            
        } catch (ClassNotFoundException e) {
            System.out.println("✗ MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("✗ Database setup failed: " + e.getMessage());
        }
    }
}