package Config;

import java.sql.*;

public class config {
    private static final String URL = "jdbc:sqlite:SpaDB.db"; // SQLite file SpaDB.db

    // Create connection
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            initializeDatabase(conn); // ✅ Ensure DB & table exist
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Initialize DB (create table if not exists)
    private static void initializeDatabase(Connection conn) {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS tbl_spa ("
                + "spa_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "customer_name TEXT NOT NULL, "
                + "phone TEXT NOT NULL, "
                + "gender TEXT CHECK(gender IN ('Male', 'Female')) NOT NULL, "
                + "service TEXT NOT NULL, "
                + "price REAL NOT NULL"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            System.out.println("❌ Initialization Error: " + e.getMessage());
        }
    }

    // ADD RECORD
    public void addRecord(String sql, Object... params) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Insert Error: " + e.getMessage());
        }
    }

    // VIEW RECORDS
    public void viewRecords(String sql) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== Spa Transactions ===");
            System.out.printf("%-5s %-20s %-15s %-10s %-20s %-10s\n",
                    "ID", "Customer Name", "Phone", "Gender", "Service", "Price");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-15s %-10s %-20s %-10.2f\n",
                        rs.getInt("spa_id"),
                        rs.getString("customer_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("service"),
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("❌ View Error: " + e.getMessage());
        }
    }

    // UPDATE RECORD
    public void updateRecord(String sql, Object... params) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Update Error: " + e.getMessage());
        }
    }

    // DELETE RECORD
    public void deleteRecord(String sql, Object... params) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Delete Error: " + e.getMessage());
        }
    }
}
