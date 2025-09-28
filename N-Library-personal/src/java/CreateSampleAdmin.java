import com.team2.Enums.UserType;
import com.team2.controller.utill.DBConnection;
import com.team2.controller.utill.PasswordGenarator;
import com.team2.models.User;
import com.team2.service.UserService;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateSampleAdmin {
    public static void main(String[] args) {
        System.out.println("Testing database connection and creating sample admin...");
        
        // Test database connection
        try (Connection connection = DBConnection.getConnection()) {
            System.out.println("✓ Database connection successful!");
            System.out.println("Connected to: " + connection.getMetaData().getURL());
            
            // Create sample admin
            UserService userService = new UserService();
            
            // Check if admin already exists
            String adminEmail = "admin@nlibrary.com";
            
            // Create admin user
            String firstName = "Admin";
            String lastName = "User";
            String userNic = "123456789V";
            String phoneNumber = "0771234567";
            String address = "123 Library Street, Colombo";
            String password = "admin123"; // Simple password for demo
            String image = "default_user.png";
            
            User admin = new User(0, firstName, lastName, userNic, image, adminEmail, 
                                phoneNumber, address, password, true, UserType.ADMIN);
            
            int insertedId = userService.addUser(admin);
            
            if (insertedId > 0) {
                System.out.println("✓ Sample admin created successfully!");
                System.out.println("Admin Details:");
                System.out.println("Email: " + adminEmail);
                System.out.println("Password: " + password);
                System.out.println("Name: " + firstName + " " + lastName);
                System.out.println("User ID: " + insertedId);
            } else {
                System.out.println("✗ Failed to create admin user (may already exist)");
            }
            
        } catch (SQLException e) {
            System.out.println("✗ Database connection failed!");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Make sure MySQL is running and the database 'library_manage' exists.");
        } catch (Exception e) {
            System.out.println("✗ Error creating admin: " + e.getMessage());
            e.printStackTrace();
        }
    }
}