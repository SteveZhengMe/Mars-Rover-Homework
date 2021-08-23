package interview.services.impls

import groovy.util.logging.Slf4j
import interview.exceptions.InvalidateCommandException
import interview.exceptions.RoverException
import interview.models.Cell
import interview.models.CurrPosition
import interview.models.Region
import interview.models.RoverIF
import interview.services.ControlCenterIF
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Slf4j
class NASAImpl implements ControlCenterIF {

    @Autowired
    RoverIF rover
    Set<Cell> explored = []

    @Override
    CurrPosition sendCommand(String command) throws InvalidateCommandException, RoverException {
        log.info("NASAImpl sent '" + command + "'")
        if(rover.currentPosition != null) {
            command.each {
                switch (it.toLowerCase()) {
                    case "f":
                        rover.moveForward()
                        break
                    case "b":
                        rover.moveBackward()
                        break
                    case "l":
                        rover.turnLeft()
                        break
                    case "r":
                        rover.turnRight()
                        break
                    case "h":
                        explored.addAll(rover.launch().explore())
                        break
                    default:
                        log.error("NASAImpl sent invalidate command '" + it + "', cannot be recognized")
                        throw new InvalidateCommandException(it)
                        break
                }
                explored.add(rover.explore())
            }
        } else {
            log.error("NASAImpl's command cannot be executed because Rover has no current position.")
            throw new RoverException("Current Position is null")
        }
        return rover.getCurrentPosition()
    }

    @Override
    float calculateExploreRate() {
        if(log.isDebugEnabled()) {
            log.debug("calculateExploreRate, explored list:")
            log.debug(explored.toString())
        }
        return Math.round(10000 * explored.size() / Region.getInstance().getCellsSet().size())/100
    }

    @Override
    CurrPosition landRover() {
        CurrPosition currPosition = rover.land(Region.getInstance())
        explored.add(rover.explore())
        return currPosition
    }

    @Override
    CurrPosition landRover(CurrPosition testPosition) {
        rover.land(Region.getInstance())
        rover.setCurrentPosition(testPosition)
        explored.add(rover.explore())
        return rover.getCurrentPosition()
    }

    @Override
    void reset() {
        log.info("Rover Reset")
        explored = []
        rover.reset()
    }
}
