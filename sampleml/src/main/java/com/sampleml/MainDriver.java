package com.sampleml;

import java.util.Enumeration;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class MainDriver {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Attribute Attr1 = new Attribute("numeric1");
        Attribute Attr2 = new Attribute("numeric2");

        FastVector<String> fvval = new FastVector<String>(3);
        fvval.add("low");
        fvval.add("medium");
        fvval.add("high");

        Attribute Attr3 = new Attribute("aNominal", fvval);
        FastVector<String> fvClassVal = new FastVector<String>(2);
        fvClassVal.addElement("done");
        fvClassVal.addElement("notdone");

        Attribute CAttr = new Attribute("theClass", fvClassVal);

        FastVector wekaFvAttr = new FastVector(4);
        wekaFvAttr.addElement(Attr1);
        wekaFvAttr.addElement(Attr2);
        wekaFvAttr.addElement(Attr3);
        wekaFvAttr.addElement(CAttr);

        Instances trainingSet = new Instances("Rel", wekaFvAttr, 10);
        trainingSet.setClassIndex(3);

        Instance exampleInstance = new DenseInstance(4);
        exampleInstance.setValue((Attribute) wekaFvAttr.elementAt(0), 1.0);
        exampleInstance.setValue((Attribute) wekaFvAttr.elementAt(1), 0.5);
        exampleInstance.setValue((Attribute) wekaFvAttr.elementAt(2), "medium");
        exampleInstance.setValue((Attribute) wekaFvAttr.elementAt(3), "done");

        trainingSet.add(exampleInstance);

        Classifier classifierModel = (Classifier) new NaiveBayes();
        classifierModel.buildClassifier(trainingSet);

        Evaluation modelTest = new Evaluation(trainingSet);
        modelTest.evaluateModel(classifierModel, trainingSet);

        String summaryReport = modelTest.toSummaryString();
        System.out.println(summaryReport);

    }

}
