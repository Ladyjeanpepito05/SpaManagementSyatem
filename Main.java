package spa.management;

import Config.config;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            config config = new config();
            int action;
            
            System.out.println("=== Welcome to the Spa Management System ===");
            System.out.println("1. Manage Spa Transactions");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            switch (choice) {
                
                // ---------------- SPA TRANSACTIONS ----------------
                case 1:
                    System.out.println("\n=== Spa Transaction Management ===");
                    System.out.println("1. Add Spa Transaction");
                    System.out.println("2. View Transactions");
                    System.out.println("3. Update Transaction");
                    System.out.println("4. Delete Transaction");
                    System.out.print("Enter Action: ");
                    action = sc.nextInt();
                    sc.nextLine(); // consume newline
                    
                    switch (action) {
                        case 1:
                            // ADD TRANSACTION
                            System.out.print("Enter Customer Name: ");
                            String customer = sc.nextLine();
                            System.out.print("Enter Customer Phone: ");
                            String phone = sc.nextLine();
                            System.out.print("Enter Customer Gender: ");
                            String gender = sc.nextLine();
                            System.out.print("Enter Service Availed: ");
                            String service = sc.nextLine();
                            System.out.print("Enter Price: ");
                            double price = sc.nextDouble();
                            sc.nextLine();
                            
                            String sqlInsert = "INSERT INTO tbl_spa (customer_name, phone, gender, service, price) VALUES (?, ?, ?, ?, ?)";
                            config.addRecord(sqlInsert, customer, phone, gender, service, price);
                            System.out.println("✅ Spa Transaction added successfully!");
                            break;
                            
                        case 2:
                            // VIEW TRANSACTIONS
                            String sqlView = "SELECT * FROM tbl_spa";
                            config.viewRecords(sqlView);
                            break;
                            
                        case 3:
                            // UPDATE TRANSACTION
                            System.out.print("Enter Transaction ID to update: ");
                            int updateId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter New Customer Name: ");
                            String newCustomer = sc.nextLine();
                            System.out.print("Enter New Phone: ");
                            String newPhone = sc.nextLine();
                            System.out.print("Enter New Gender: ");
                            String newGender = sc.nextLine();
                            System.out.print("Enter New Service: ");
                            String newService = sc.nextLine();
                            System.out.print("Enter New Price: ");
                            double newPrice = sc.nextDouble();
                            sc.nextLine();
                            
                            String sqlUpdate = "UPDATE tbl_spa SET customer_name = ?, phone = ?, gender = ?, service = ?, price = ? WHERE spa_id = ?";
                            config.updateRecord(sqlUpdate, newCustomer, newPhone, newGender, newService, newPrice, updateId);
                            System.out.println("✅ Spa Transaction updated successfully!");
                            break;
                            
                        case 4:
                            // DELETE TRANSACTION
                            System.out.print("Enter Transaction ID to delete: ");
                            int deleteId = sc.nextInt();
                            
                            String sqlDelete = "DELETE FROM tbl_spa WHERE spa_id = ?";
                            config.deleteRecord(sqlDelete, deleteId);
                            System.out.println("✅ Transaction deleted successfully!");
                            break;
                            
                        default:
                            System.out.println("❌ Invalid Action!");
                    }
                    break;
                    
                case 2:
                    System.out.println("👋 Exiting... Thank you for using the Spa Management System!");
                    break;
                    
                default:
                    System.out.println("❌ Invalid Choice!");
            }
        }
    }
}