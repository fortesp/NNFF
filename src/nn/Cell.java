package nn;

import java.util.ArrayList;
import java.util.List;

/*
    NNFF v0.1
    Author: Pedro Fortes (c) 2018
    https://github.com/fortesp
 */

public class Cell {

    private String id;
    private List<Connection> connections =  new ArrayList<Connection>();
    private boolean bias = false;
    private float output = 0;

    public Cell(String id) {

        this.id = id + java.lang.System.identityHashCode(this);
    }

    public Cell(String id, float input, boolean bias) {

        this(id);
        setInput(input);
        this.bias = bias;
    }

    public void setInput(float input) {

        output = input;
    }

    public void addConnection(Connection newConnection) {

        connections.add(newConnection);
    }

    public void calculateOutput() {

        if(bias) return;

        float sum = 0;
        float bias = 0;

        for(Connection conn : connections) {

            Cell to   = conn.getTo();
            if (to == this) {

                Cell from = conn.getFrom();
                if (from.bias) {
                    bias = from.getOutput() * conn.getWeight();
                } else {
                    sum += from.getOutput() * conn.getWeight();
                }
            }
        }

        output = activationFunction(bias + sum);
    }

    float activationFunction(float value) {

        return sigmoid(value);
    }

    float sigmoid(float x) {
        return 1.0f / (1.0f + (float) Math.exp(-x));
    }

    public float getOutput() {

        return output;
    }

    public List<Connection> getConnections() {
        return connections;
    }
}
