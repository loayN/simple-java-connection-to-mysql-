package LoayNaser;

import java.sql.*;

public class ServerCon {

    private String choosen, username, password;

    protected ServerCon(String chosen, String username, String password){
        this.choosen = chosen;
        this.username = username;
        this.password = password;
    }



    protected Connection Connect() {

        try {
            String dburl = "jdbc:mysql://"+ choosen +":3306/bigDatadb"; //change the "bigDatadb" to your database name
            //get connection
            return (DriverManager.getConnection( dburl, username, password ));

            } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}