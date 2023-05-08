package ConnectionMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs {
    Connection dbconnection;

    public Connection getConnection() {
        String connectionString = "jdbc:mysql://"+dbhost+":"+dbport+"/"+dbname;
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        try {
            dbconnection = DriverManager.getConnection(connectionString,dbuser,dbpass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbconnection;
    }
}

