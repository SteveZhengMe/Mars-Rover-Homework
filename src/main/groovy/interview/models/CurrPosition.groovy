package interview.models

class CurrPosition extends Cell {
    static final enum Heading{
        NORTH,
        SOUTH,
        WEST,
        EAST
    }

    Heading currentHeading

    CurrPosition(int row, int column) {
        super(row, column)
    }

    CurrPosition(Cell cell) {
        super(cell.row, cell.column)
        this.currentHeading = Heading.NORTH
    }

    CurrPosition(Cell cell, Heading heading) {
        super(cell.row, cell.column)
        this.currentHeading = heading
    }

    CurrPosition(int row, int column, Heading heading) {
        super(row, column)
        this.currentHeading = heading
    }

    @Override
    boolean equals(Object another) {
        if(another instanceof CurrPosition) {
            return super.equals(another) && this.currentHeading == ((CurrPosition)another).currentHeading
        }
        return false
    }

    @Override
    String toString() {
        return super.toString() + "(Heading: " + this.currentHeading + ")"
    }
}
