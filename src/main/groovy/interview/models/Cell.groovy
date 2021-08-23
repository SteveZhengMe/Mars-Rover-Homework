package interview.models

/**
 * A Cell
 *
 * Author: Steve
 * Date: 2021-08-19
 */

class Cell {
    int row = 0
    int column = 0

    Cell(int row, int column) {
        this.row = row
        this.column = column
    }

    String getPositionName() {
        return this.row + "" + (char)(64 + this.column)
    }

    @Override
    int hashCode() {
        this.getPositionName().hashCode()
    }

    @Override
    boolean equals(Object another) {
        if(another instanceof Cell) {
            return this.row == ((Cell)another).row && this.column == ((Cell)another).column
        }
        return false
    }

    @Override
    String toString() {
        return this.getClass().getSimpleName() + "=>" + this.getPositionName()
    }

}