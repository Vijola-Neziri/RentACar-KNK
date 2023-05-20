package repository.Interface;

import models.Komentet;

import java.sql.SQLException;
import java.util.List;

public interface KomentetInterface {
    void addKomentet(Komentet komentet) throws SQLException;

    List<Komentet> getAllKomentet() throws SQLException;

    void deleteKomentet(String email) throws SQLException;
}
