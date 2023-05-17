package repository;

import models.Komentet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KomentetRepository {
    private Connection connection;

    public KomentetRepository(Connection connection) {
        this.connection = connection;
    }

    public void addKomentet(Komentet komentet) throws SQLException {
        String query = "INSERT INTO komentet (emri, e_mail, mesazhi) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, komentet.getEmri());
        statement.setString(2, komentet.getEmail());
        statement.setString(3, komentet.getMesazhi());
        statement.executeUpdate();
    }

    public List<Komentet> getAllKomentet() throws SQLException {
        List<Komentet> komentetList = new ArrayList<>();
        String query = "SELECT * FROM komentet";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String emri = resultSet.getString("emri");
            String email = resultSet.getString("e_mail");
            String mesazhi = resultSet.getString("mesazhi");
            Komentet komentet = new Komentet(emri, email, mesazhi);
            komentetList.add(komentet);
        }
        return komentetList;
    }

    public void deleteKomentet(String email) throws SQLException {
        String query = "DELETE FROM komentet WHERE e_mail = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.executeUpdate();
    }
}
