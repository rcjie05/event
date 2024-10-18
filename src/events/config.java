package events;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class config {

    // Connection Method to SQLITE
    public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); // Load the SQLite JDBC driver
            con = DriverManager.getConnection("jdbc:sqlite:events.db"); // Establish connection
            System.out.println("Connection Successful");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }

    public void addEvents(String sql, Object... values) {
        try (Connection conn = this.connectDB(); // Use the connectDB method
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Loop through the values and set them in the prepared statement dynamically
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) values[i]); // If the value is Integer
                } else if (values[i] instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) values[i]); // If the value is Double
                } else if (values[i] instanceof Float) {
                    pstmt.setFloat(i + 1, (Float) values[i]); // If the value is Float
                } else if (values[i] instanceof Long) {
                    pstmt.setLong(i + 1, (Long) values[i]); // If the value is Long
                } else if (values[i] instanceof Boolean) {
                    pstmt.setBoolean(i + 1, (Boolean) values[i]); // If the value is Boolean
                } else if (values[i] instanceof java.util.Date) {
                    pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); // If the value is Date
                } else if (values[i] instanceof java.sql.Date) {
                    pstmt.setDate(i + 1, (java.sql.Date) values[i]); // If it's already a SQL Date
                } else if (values[i] instanceof java.sql.Timestamp) {
                    pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]); // If the value is Timestamp
                } else {
                    pstmt.setString(i + 1, values[i].toString()); // Default to String for other types
                }
            }

            pstmt.executeUpdate();
            System.out.println("Record added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    public void viewEvents(String qry, String[] hdrs, String[] clms) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(qry);
             ResultSet rs = pstmt.executeQuery()) {

            // Print the headers
            System.out.println("----------------------------------------------------------------");
            for (String hdr : hdrs) {
                System.out.print(hdr + "\t");
            }
            System.out.println();
            System.out.println("----------------------------------------------------------------");

            // Print the data
            while (rs.next()) {
                for (int i = 0; i < clms.length; i++) {
                    System.out.print(rs.getString(clms[i]) + "\t");
                }
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Error viewing records: " + e.getMessage());
        }
    }

    public void updateEvents(String qry, String ename, String edate, String elocal, String edes, String eorga, String eid) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(qry)) {

            pstmt.setString(1, ename);
            pstmt.setString(2, edate);
            pstmt.setString(3, elocal);
            pstmt.setString(4, edes);
            pstmt.setString(5, eorga);
            pstmt.setString(6, eid);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("No record found with the given ID to update.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }

    public void deleteEvents(String qry, String id) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(qry)) {

            pstmt.setString(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("No record found with the given ID to delete.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }

    void updateEvents(String qry, String ename, String edate, String elocal, String edes, String eorga, int eid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}