package nn;

import java.util.ArrayList;
import java.util.List;

/*
    NNFF v0.1
    Author: Pedro Fortes (c) 2018
    https://github.com/fortesp
 */


public class CellLayer {

    List<Cell> cells = new ArrayList<Cell>();


    public List<Cell> getCells() {
        return cells;
    }

    public void addCell(Cell cell) {
        this.cells.add(cell);
    }
}
