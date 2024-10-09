package com.percept.train;

import org.deeplearning4j.eval.RegressionEvaluation;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;


public class EvaluateModel {

    public static void evaluate(DataSet dataSet, MultiLayerNetwork model) {
        System.out.println("Inicio evaluate");
        RegressionEvaluation eval = new RegressionEvaluation();
        INDArray output = model.output(dataSet.getFeatures());
        eval.eval(dataSet.getLabels(), output);
        System.out.println("teste de lei" + eval.stats());
    }
}

