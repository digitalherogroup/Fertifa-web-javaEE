package com.fertifa.app.Connection;

import com.fertifa.app.constants.Constances;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnectionToDatabase() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fertifab_db?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false", com.fertifa.app.Constances.USERNAMEINDATA, com.fertifa.app.Constances.PASSWORDINDATA);
            // for home connection
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fertifab_db?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false", com.fertifa.app.Constances.USERNAMEINDATA, com.fertifa.app.Constances.PASSWORDINDATA);
            //for online connection
            //connection = DriverManager.getConnection("jdbc:mysql://204.93.169.168:3306/fertifab_db?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT", com.fertifa.app.Constances.USERNAMEINDATAWEB, com.fertifa.app.Constances.PASSWORDINDATAWEB);
           // System.out.println("jdbc:mysql://204.93.169.168:3306/fertifab_db?");
            connection = DriverManager.getConnection("jdbc:mysql://204.93.169.168:3306/fertifab_db2?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT", "fertifab_root", Constances.PASSWORDINDATAWEB);
            //System.out.println("jdbc:mysql://204.93.169.168:3306/fertifab_db2?");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
        }
        //System.out.println("connection : " + connection + " connectionDatabase : ");
        return connection;
    }


}

