package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.DataSet;
import java.sql.ResultSetMetaData;

import com.example.configuration.NeuralNetwork;
import com.example.data.DataPreparation;
import com.example.train.EvaluateModel;
import com.example.train.TrainModel;

public class MySQLDataExtractor {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/percept?useSSL=false";
        String user = "root";
        String password = "12345";

        List<Object[]> dataList = new ArrayList<>();

        try {
            // Carregar o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM custumer");
            ResultSetMetaData metaData = rs.getMetaData();

            /*while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                dataList.add(row);
            }*/
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

        DataSet dataSet = DataPreparation.prepareData(dataList);
        MultiLayerNetwork model = NeuralNetwork.createNetwork(dataSet.getFeatures().columns(), dataSet.getLabels().columns());
        TrainModel.train(dataSet, model);
        EvaluateModel.evaluate(dataSet, model);
    }
}


