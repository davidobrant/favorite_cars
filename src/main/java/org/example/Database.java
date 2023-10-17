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

    public static ArrayList<Car> listCars() {
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
                System.out.println("id: "+id+"\tmake: "+make+"\tmodel: "+model+"\tyear: "+year);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfCars;
    }
}
