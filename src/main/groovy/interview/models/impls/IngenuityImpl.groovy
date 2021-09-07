package interview.models.impls

import groovy.util.logging.Slf4j
import interview.models.Cell
import interview.models.CopterIF
import interview.models.CurrPosition
import interview.models.Region
import org.springframework.stereotype.Component

import java.util.logging.Logger

@Component
@Slf4j
class IngenuityImpl implements CopterIF {
    private CurrPosition currentPosition

    @Override
    List<Cell> explore() {
        List<Cell> exploreCells = []

        if(currentPosition != null) {
            [-1,0,1].each {row ->
                [-1,0,1].each {column ->
                    Cell tmpCell = exploreCell(row,column)
                    if(tmpCell != null) {
                        exploreCells.add(tmpCell)
                    }
                }
            }
        }
        if(log.isDebugEnabled()) {
            log.debug("Ingenuity Explore: ")
            log.debug(exploreCells.toString())
            log.debug("Ingenuity Explore Done")
        }
        log.info("IngenuityImpl explore, find " + exploreCells.size() + " cells")
        return exploreCells
    }

    private Cell exploreCell(int rowOffset, int columnOffset) {
        Cell cell = new Cell(currentPosition.row+rowOffset, currentPosition.column+columnOffset)
        if(Region.getInstance().getCellsSet().contains(cell)) {
            return cell
        }
        return null
    }

    @Override
    void launch(CurrPosition launchPosition) {
        currentPosition = launchPosition
    }

    @Override
    void reset() {
        currentPosition = null
    }
}
