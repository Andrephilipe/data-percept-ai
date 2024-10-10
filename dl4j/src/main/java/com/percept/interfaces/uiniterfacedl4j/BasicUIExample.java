package com.percept.interfaces.uiniterfacedl4j;


import java.io.IOException;

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.model.stats.StatsListener;
import org.deeplearning4j.ui.model.storage.InMemoryStatsStorage;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import com.percept.configuration.NeuralNetwork;

public class BasicUIExample {

    public void basicUIExample(DataSet dataSet) throws IOException {
        // Configuração da rede neural
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(123)
                .list()
                .layer(new DenseLayer.Builder().nIn(1).nOut(1)
                        .activation(Activation.RELU)
                        .build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.XENT)
                        .activation(Activation.SIGMOID)
                        .nIn(1).nOut(1).build())
                .build();
        
        MultiLayerNetwork model2 = NeuralNetwork.createNetwork(dataSet.getFeatures().columns(), dataSet.getLabels().columns());
        model2.init();
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        // Iniciar o servidor UI
        UIServer uiServer = UIServer.getInstance();
        InMemoryStatsStorage statsStorage = new InMemoryStatsStorage();
        uiServer.attach(statsStorage);
        model2.setListeners(new StatsListener(statsStorage));

        

        // Carregar dados MNIST

        // Treinamento do modelo
       for (int i = 0; i < 10; i++) { // Treinar por 10 épocas
        model2.fit(dataSet);
       }

        // Treinamento do modelo (exemplo simplificado)
        //model.fit(dataSet);

        // Manter o servidor UI ativo
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

