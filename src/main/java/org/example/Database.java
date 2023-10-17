package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static Connection conn;

    public Database() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:cars.db");
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS cars (" +
                    "id INTEGER PRIMARY KEY," +
                    "make TEXT NOT NULL," +
                    "model TEXT NOT NULL," +
                    "year INTEGER NOT NULL)";

            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addCar(Car car) {
        String sql = "INSERT INTO cars (make, model, year) VALUES(?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());

            boolean res = pstmt.executeUpdate() > 0;
            pstmt.close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Car> getCars() {
        ArrayList<Car> listOfCars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                listOfCars.add(new Car(id, make, model, year));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfCars;
    }

    public static boolean updateCar(Car car) {
        String sql = "UPDATE cars SET make = ?, model = ?, year = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());
            pstmt.setInt(4, car.getId());
            boolean res = pstmt.executeUpdate() > 0;
            pstmt.close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean removeCar(int id) {
        String sql = "DELETE FROM cars WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            boolean res = pstmt.executeUpdate() > 0;
            pstmt.close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Car findCarById(int id) {
        String sql = "SELECT * FROM cars WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            int id_ = rs.getInt("id");
            String make = rs.getString("make");
            String model = rs.getString("model");
            int year = rs.getInt("year");

            pstmt.close();
            rs.close();
            return new Car(id_, make, model, year);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
