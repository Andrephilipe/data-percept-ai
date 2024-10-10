package com.percept;

import java.io.IOException;
import java.util.List;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.DataSet;

import com.percept.configuration.NeuralNetwork;
import com.percept.data.DataPreparation;
import com.percept.extractor.MySQLDataExtractor;
import com.percept.interfaces.uiniterfacedl4j.BasicUIExample;
import com.percept.train.EvaluateModel;
import com.percept.train.TrainModel;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        List<Object[]> listaDados;
        listaDados = MySQLDataExtractor.mySQLDataExtractor();
        
        DataSet dataSet = DataPreparation.prepareData(listaDados);
        MultiLayerNetwork model = NeuralNetwork.createNetwork(dataSet.getFeatures().columns(), dataSet.getLabels().columns());
        TrainModel.train(dataSet, model);
        EvaluateModel.evaluate(dataSet, model);

        BasicUIExample execBasicUIExample = new BasicUIExample();
        execBasicUIExample.basicUIExample(dataSet);


        

    }

}