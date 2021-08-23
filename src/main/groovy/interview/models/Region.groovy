package interview.models

/**
 * The Row*Column Region
 * Author: Steve
 * Date: 2021-08-19
 */
class Region {
    static int REGION_ROW = 25
    static int REGION_COLUMN = 25

    private static Region regionInstance = null
    private Set<Cell> cells = []

    private Region(int rowSize, int columnSize) {
        (1..rowSize).each {row ->
            (1..columnSize).each {column ->
                cells.add(new Cell(row,column))
            }
        }
    }

    static Region getInstance() {
        if(regionInstance == null) {
            regionInstance = new Region(Region.REGION_ROW, Region.REGION_COLUMN)
        }
        return regionInstance
    }

    Set<Cell> getCellsSet() {
        return this.cells
    }
}