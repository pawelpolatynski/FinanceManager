package financemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Repository {

    Connection conn;
    Statement stmt = null;

    Repository(String url) {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.
                    getConnection(url, "sa", "");

            stmt = conn.createStatement();


            String sql =  "CREATE TABLE IF NOT EXISTS USERS " +
                    "(id INT auto_increment, " +
                    " name VARCHAR(255) NOT NULL , " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);

            sql =  "CREATE TABLE IF NOT EXISTS TYPES " +
                    "(id INT not NULL, " +
                    " name VARCHAR(255) NOT NULL , " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);

            sql =  "CREATE TABLE IF NOT EXISTS EXPANSES " +
                    "(id INT not NULL, " +
                    " description VARCHAR(255) NOT NULL, " +
                    " value DECIMAL(8,2) NOT NULL , " +
                    " type INT, " +
                    " date DATE NOT NULL, " +
                    " user INT, " +
                    " PRIMARY KEY ( id )," +
                    " FOREIGN KEY (user) references USERS(id)," +
                    " FOREIGN KEY (type) references TYPES(id))";
            stmt.executeUpdate(sql);

            sql =  "SELECT name FROM USERS";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {

                String first = rs.getString("name");
                System.out.println("Name: " + first);

            }
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
        }
    }

    ArrayList<?> getContent(String table) {
        ArrayList<User> output = new ArrayList<User>();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM " + table;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                output.add(new User(name));
            }
            rs.close();
            for (User u : output) {
                System.out.println(u.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    ArrayList<User> getUsers() {
        ArrayList<User> output = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, name FROM USERS";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                output.add(new User(name));
            }
            rs.close();
            for (User u : output) {
                System.out.println(u.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    void close(){
        try {
            this.conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
