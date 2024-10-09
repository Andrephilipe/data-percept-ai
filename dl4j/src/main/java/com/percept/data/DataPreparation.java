package com.percept.data;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import java.util.List;

public class DataPreparation {

    public static DataSet prepareData(List<Object[]> dataList) {
        int numRows = dataList.size();
        int numColumns = dataList.get(0).length;

        Object[][] features = new Object[numRows][numColumns - 1];
        Object[] labels = new Object[numRows];

        for (int i = 0; i < numRows; i++) {
            System.arraycopy(dataList.get(i), 0, features[i], 0, numColumns - 1);
            labels[i] = dataList.get(i)[numColumns - 1];
        }

        // Convertendo os dados para INDArray
        INDArray featuresArray = convertToINDArray(features);
        INDArray labelsArray = convertToINDArray(labels);

        return new DataSet(featuresArray, labelsArray);
    }

    private static INDArray convertToINDArray(Object[][] data) {
        int numRows = data.length;
        int numColumns = data.length;
        double[][] doubleData = new double[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                doubleData[i][j] = convertToDouble(data[i][j]);
            }
        }

        return Nd4j.create(doubleData);
    }

    private static INDArray convertToINDArray(Object[] data) {
        int numRows = data.length;
        double[] doubleData = new double[numRows];

        for (int i = 0; i < numRows; i++) {
            doubleData[i] = convertToDouble(data[i]);
        }

        return Nd4j.create(doubleData, new int[]{numRows, 1});
    }

    private static double convertToDouble(Object value) {
        if (value == null) {
            return 1.00; // ou outro valor padrão, como 0.0
        }
        if (value instanceof Number number) {
            return number.doubleValue();
        } else {
            throw new IllegalArgumentException("Valor não numérico encontrado: " + value);
        }
    }
}



