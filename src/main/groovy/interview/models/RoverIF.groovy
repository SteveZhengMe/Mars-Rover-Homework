package interview.models

interface RoverIF {
    CurrPosition land(Region region)
    Cell explore()
    void moveForward()
    void moveBackward()
    void turnLeft()
    void turnRight()
    CopterIF launch()
    CurrPosition getCurrentPosition()
    void setCurrentPosition(CurrPosition currPosition)
    void reset()
}