package com.percept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Teste {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/seu_banco_de_dados?useSSL=false";
        String user = "seu_usuario";
        String password = "sua_senha";

        List<Object[]> dataList = new ArrayList<>();

        try {
            // Carregar o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sua_tabela");
            ResultSetMetaData metaData = rs.getMetaData();

            while (rs.next()) {
                Object[] row = new Object[metaData.getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    int columnType = metaData.getColumnType(i + 1);
                    switch (columnType) {
                        case java.sql.Types.INTEGER:
                            row[i] = rs.getInt(i + 1);
                            break;
                        case java.sql.Types.DOUBLE:
                            row[i] = rs.getDouble(i + 1);
                            break;
                        case java.sql.Types.VARCHAR:
                            row[i] = rs.getString(i + 1);
                            break;
                        // Adicione mais casos conforme necessário para outros tipos de dados
                        default:
                            row[i] = rs.getObject(i + 1);
                            break;
                    }
                }
                dataList.add(row);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Exibindo os dados recuperados
        for (Object[] row : dataList) {
            for (Object value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
