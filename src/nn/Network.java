package nn;

import java.util.ArrayList;
import java.util.List;

/*
    NNFF v0.1
    Author: Pedro Fortes (c) 2018
    https://github.com/fortesp
 */

public class Network {

    private CellLayer inputLayer   = new CellLayer();
    private List<CellLayer> hiddenLayers  = new ArrayList<CellLayer>();
    // Support only 2 hidden layers
    private CellLayer hiddenLayer1;
    private CellLayer hiddenLayer2;
    private CellLayer outputLayer  = new CellLayer();

    private float[] results;


    public static final float LEARNING_CONSTANT = 0.05f;


    public Network(float[][] inputValues, int totalHidden1, int totalHidden2, int totalOutput) {

        this.results = new float[totalOutput];

        // Create Cells, add to layers
        for(int i = 0; i < inputValues.length; i++) {
            for(int j = 0; j < inputValues[0].length; j++) {
                inputLayer.addCell(new Cell("INPUT_", inputValues[i][j], false));
            }
        }
        // Bias
        inputLayer.addCell(new Cell("BINPUT_", 1, true));

        // Hidden layers
        hiddenLayers.add(new CellLayer());
        hiddenLayers.add(new CellLayer());
        hiddenLayer1 = hiddenLayers.get(0);
        hiddenLayer2 = hiddenLayers.get(1);

        for(int i = 0; i < totalHidden1; i++) {
            hiddenLayer1.addCell(new Cell("HIDDEN1_"));
        }
        // Bias
        hiddenLayer1.addCell(new Cell("BHIDDEN1_", 1, true));

        for(int i = 0; i < totalHidden2; i++) {
            hiddenLayer2.addCell(new Cell("HIDDEN2_"));
        }
        // Bias
        hiddenLayer2.addCell(new Cell("BHIDDEN2_", 1, true));


        for(int i = 0; i < totalOutput; i++) {
            outputLayer.addCell(new Cell("OUTPUT_"));
        }

        connectCells();
    }


    private void connectCells() {

        // Connect Cells ---
        // input to hidden layer 1
        for(Cell c1 : inputLayer.getCells()) {
            for(Cell c2 : hiddenLayer1.getCells()) {
                Connection.createConnection(c1, c2);
            }
        }

        // hidden layer 1 to hidden layer 2
        for(Cell c2 : hiddenLayer1.getCells()) {
            for(Cell c22 : hiddenLayer2.getCells()) {
                Connection.createConnection(c2, c22);
            }
        }

        // hidden layer 2 to output layer
        for(Cell c22 : hiddenLayer2.getCells()) {
            for(Cell c3 : outputLayer.getCells()) {
                Connection.createConnection(c22, c3);
            }
        }

    }

    public float[] feedForward() {

        for(Cell c2 : hiddenLayer1.getCells()) {
            c2.calculateOutput();
        }

        for(Cell c22 : hiddenLayer2.getCells()) {
            c22.calculateOutput();
        }

        for(Cell c3 : outputLayer.getCells()) {
            c3.calculateOutput();
        }

        int i = 0;
        for(Cell c3 : outputLayer.getCells()) {
            this.results[i++] = c3.getOutput();
        }

        return this.results;
    }

    public float[] feedForward(float[][] inputValues) {

        int k = 0;
        for (int i = 0; i < inputValues.length; i++) {
            for (int j = 0; j < inputValues[0].length; j++) {
                inputLayer.getCells().get(k++).setInput(inputValues[i][j]);
            }
        }

        return feedForward();
    }


    public void learn(float[][] inputValues, float[] targets) {

        feedForward(inputValues);

        // Backpropagation
        float error;
        error = backPropagationLayer(outputLayer, 0, targets);   // From hidden 2 to output layer
        error = backPropagationLayer(hiddenLayer2, error, null); // From hidden 2 to hidden 1 layer
                backPropagationLayer(hiddenLayer1, error, null); // From hidden 1 to input layer
    }


    float backPropagationLayer(CellLayer cellLayer, float inError, float[] targets) {

        float outError = 0;
        int i = 0;

        for (Cell toCell : cellLayer.getCells()) {

            if(targets != null) { // For the output layer.
                inError = targets[i++] - toCell.getOutput();
            }

            for (Connection conn : toCell.getConnections()) {

                if (toCell == conn.getTo()) {

                    float deltaToCell = (toCell.getOutput() * (1 - toCell.getOutput())) * inError;

                    Cell fromCell = conn.getFrom();
                    float dWeight = fromCell.getOutput() * deltaToCell;
                    conn.adjustWeight(LEARNING_CONSTANT * dWeight);
                    outError += dWeight;
                }
            }
        }

        return outError;
    }

    public static float[][] normalizeInputData(float[][] data) {

        float max = -1f;
        float min = Float.MAX_VALUE;

        for(int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][j] > max) max = data[i][j];
                else if (data[i][j] < min) min = data[i][j];
            }
        }

        for(int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                data[i][j] = (data[i][j] - min) / (max - min);
            }
        }

        return data;
    }


}
