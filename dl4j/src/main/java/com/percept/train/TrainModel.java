package com.percept.train;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.DataSet;

public class TrainModel {

    public static void train(DataSet dataSet, MultiLayerNetwork model) {
        int numEpochs = 100;
        System.out.println("Inicio train");
        for (int i = 0; i < numEpochs; i++) {
            model.fit(dataSet);
        }
    }
}

