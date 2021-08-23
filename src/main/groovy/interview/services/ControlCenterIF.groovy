package interview.services

import interview.models.Cell
import interview.models.CurrPosition
import interview.models.RoverIF

interface ControlCenterIF {
    CurrPosition sendCommand(String command)
    float calculateExploreRate()

    CurrPosition landRover()
    CurrPosition landRover(CurrPosition testPosition)

    RoverIF getRover()
    Set<Cell> getExplored()

    void reset()
}