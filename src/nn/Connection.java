package nn;

/*
    NNFF v0.1
    Author: Pedro Fortes (c) 2018
    https://github.com/fortesp
 */

public class Connection {

    private Cell from;
    private Cell to;

    private float weight;

    public Connection(Cell from, Cell to) {

        this.from = from;
        this.to = to;
        this.weight = (float) Math.random() * 2 - 1;
    }

    public Cell getFrom() {
        return from;
    }

    public void setFrom(Cell from) {
        this.from = from;
    }

    public Cell getTo() {
        return to;
    }

    public void setTo(Cell to) {
        this.to = to;
    }

    public float getWeight() {
        return weight;
    }

    public void adjustWeight(float weight) {
        this.weight += weight;
    }

    public static void createConnection(Cell c1, Cell c2) {

        Connection newConnection = new Connection(c1, c2);
        c1.addConnection(newConnection);
        c2.addConnection(newConnection);
    }
}
