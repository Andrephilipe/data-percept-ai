package com.example.train;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.DataSet;

public class TrainModel {

    public static void train(DataSet dataSet, MultiLayerNetwork model) {
        int numEpochs = 100;
        for (int i = 0; i < numEpochs; i++) {
            model.fit(dataSet);
        }
    }
}

