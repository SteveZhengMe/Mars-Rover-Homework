package interview.models.impls

import groovy.util.logging.Slf4j
import interview.models.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Percy Implementation of the Rover
 * Author: Steve
 * Date: 2021-08-19
 */

@Component
@Slf4j
class PercyImpl implements RoverIF {
    private CurrPosition currentPosition

    @Autowired
    CopterIF copter

    @Override
    CurrPosition land(Region region) {
        log.info("PercyImpl landing")
        currentPosition = new CurrPosition((Cell)region.getCellsSet().getAt(Math.round(Math.random()*region.cells.size()).intValue()))
        return currentPosition
    }

    @Override
    Cell explore() {
        log.info("PercyImpl exploring, 1 cell is found")
        return new Cell(currentPosition.row, currentPosition.column)
    }

    @Override
    void moveForward() {
        log.info("PercyImpl moving forward")
        CurrPosition newPosition
        if(currentPosition.currentHeading == CurrPosition.Heading.NORTH) {
            newPosition = new CurrPosition(currentPosition.row-1, currentPosition.column, currentPosition.currentHeading)
        } else if(currentPosition.currentHeading == CurrPosition.Heading.SOUTH) {
            newPosition = new CurrPosition(currentPosition.row+1, currentPosition.column, currentPosition.currentHeading)
        } else if(currentPosition.currentHeading == CurrPosition.Heading.WEST) {
            newPosition = new CurrPosition(currentPosition.row, currentPosition.column-1, currentPosition.currentHeading)
        } else if(currentPosition.currentHeading == CurrPosition.Heading.EAST) {
            newPosition = new CurrPosition(currentPosition.row, currentPosition.column+1, currentPosition.currentHeading)
        }
        if(newPosition!= null && Region.getInstance().getCellsSet().contains(new Cell(newPosition.row, newPosition.column))) {
            currentPosition = newPosition
        } else {
            log.warn("PercyImpl cannot move forward at " + currentPosition.getPositionName())
        }
    }

    @Override
    void moveBackward() {
        log.info("PercyImpl moving backward")
        CurrPosition newPosition
        if(currentPosition.currentHeading == CurrPosition.Heading.NORTH) {
            newPosition = new CurrPosition(currentPosition.row+1, currentPosition.column, currentPosition.currentHeading)
        } else if(currentPosition.currentHeading == CurrPosition.Heading.SOUTH) {
            newPosition = new CurrPosition(currentPosition.row-1, currentPosition.column, currentPosition.currentHeading)
        } else if(currentPosition.currentHeading == CurrPosition.Heading.WEST) {
            newPosition = new CurrPosition(currentPosition.row, currentPosition.column+1, currentPosition.currentHeading)
        } else if(currentPosition.currentHeading == CurrPosition.Heading.EAST) {
            newPosition = new CurrPosition(currentPosition.row, currentPosition.column-1, currentPosition.currentHeading)
        }
        if(newPosition!= null && Region.getInstance().getCellsSet().contains(new Cell(newPosition.row, newPosition.column))) {
            currentPosition = newPosition
        } else {
            log.warn("PercyImpl cannot move backward at " + currentPosition.getPositionName())
        }
    }

    @Override
    void turnLeft() {
        log.info("PercyImpl turning left")
        CurrPosition.Heading newHeading
        if(currentPosition.currentHeading == CurrPosition.Heading.NORTH) {
            newHeading = CurrPosition.Heading.WEST
        } else if(currentPosition.currentHeading == CurrPosition.Heading.SOUTH) {
            newHeading = CurrPosition.Heading.EAST
        } else if(currentPosition.currentHeading == CurrPosition.Heading.WEST) {
            newHeading = CurrPosition.Heading.SOUTH
        } else if(currentPosition.currentHeading == CurrPosition.Heading.EAST) {
            newHeading = CurrPosition.Heading.NORTH
        }

        if(newHeading != null) {
            currentPosition = new CurrPosition(currentPosition, newHeading)
        }
    }

    @Override
    void turnRight() {
        log.info("PercyImpl turning right")
        CurrPosition.Heading newHeading
        if(currentPosition.currentHeading == CurrPosition.Heading.NORTH) {
            newHeading = CurrPosition.Heading.EAST
        } else if(currentPosition.currentHeading == CurrPosition.Heading.SOUTH) {
            newHeading = CurrPosition.Heading.WEST
        } else if(currentPosition.currentHeading == CurrPosition.Heading.WEST) {
            newHeading = CurrPosition.Heading.NORTH
        } else if(currentPosition.currentHeading == CurrPosition.Heading.EAST) {
            newHeading = CurrPosition.Heading.SOUTH
        }

        if(newHeading != null) {
            currentPosition = new CurrPosition(currentPosition, newHeading)
        }
    }

    @Override
    CopterIF launch() {
        log.info("PercyImpl launching copter")
        copter.launch(currentPosition)
        return copter
    }

    @Override
    CurrPosition getCurrentPosition() {
        return currentPosition
    }

    @Override
    void setCurrentPosition(CurrPosition currPosition) {
        this.currentPosition = currPosition
    }

    @Override
    void reset() {
        currentPosition = null
        copter.reset()
    }
}
