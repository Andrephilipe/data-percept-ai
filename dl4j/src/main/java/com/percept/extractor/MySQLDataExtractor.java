package com.percept.extractor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLDataExtractor {

    public static List<Object[]> mySQLDataExtractor() throws IOException {

        String url = "jdbc:mysql://127.0.0.1:3306/percept?useSSL=false";
        String user = "root";
        String password = "12345";

        List<Object[]> dataList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM custumer");
                
                while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                row[i] = rs.getObject(i + 1);
                }
                    dataList.add(row);
                }
                
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dataList;
        
    }
}


