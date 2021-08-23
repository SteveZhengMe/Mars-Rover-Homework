import interview.models.Cell
import interview.models.CurrPosition
import interview.models.Region
import interview.models.RoverIF
import interview.models.impls.IngenuityImpl
import interview.models.impls.PercyImpl
import interview.services.impls.NASAImpl
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import static org.junit.jupiter.api.Assertions.assertEquals

@SpringBootTest(classes = [IngenuityImpl.class, PercyImpl.class, NASAImpl.class])
class RoverUnitTest {

    @Autowired
    RoverIF roverIF

    @BeforeAll
    static void "Prepare Region"() {
        Region.getInstance()
    }

    @BeforeEach
    void "Renew Rover"() {
        roverIF.reset()
    }

    @Test
    void "Move Forward"() {
        roverIF.setCurrentPosition(new CurrPosition(10,10,CurrPosition.Heading.NORTH))
        roverIF.moveForward()
        assertEquals("9J", roverIF.getCurrentPosition().getPositionName())
        assertEquals(CurrPosition.Heading.NORTH, roverIF.getCurrentPosition().currentHeading)

        roverIF.setCurrentPosition(new CurrPosition(10,10,CurrPosition.Heading.WEST))
        roverIF.moveForward()
        assertEquals("10I", roverIF.getCurrentPosition().getPositionName())
        assertEquals(CurrPosition.Heading.WEST, roverIF.getCurrentPosition().currentHeading)
    }

    @Test
    void "Move Backward"() {
        roverIF.setCurrentPosition(new CurrPosition(10,10,CurrPosition.Heading.NORTH))
        roverIF.moveBackward()
        assertEquals("11J",roverIF.getCurrentPosition().getPositionName())
        assertEquals(CurrPosition.Heading.NORTH, roverIF.getCurrentPosition().currentHeading)

        roverIF.setCurrentPosition(new CurrPosition(10,10,CurrPosition.Heading.WEST))
        roverIF.moveBackward()
        assertEquals("10K", roverIF.getCurrentPosition().getPositionName())
        assertEquals(CurrPosition.Heading.WEST, roverIF.getCurrentPosition().currentHeading)
    }

    @Test
    void "Turn Left"() {
        assertEquals(CurrPosition.Heading.WEST, turnTest("left",CurrPosition.Heading.NORTH).currentHeading)
        assertEquals(CurrPosition.Heading.SOUTH, turnTest("left",CurrPosition.Heading.WEST).currentHeading)
        assertEquals(CurrPosition.Heading.EAST, turnTest("left",CurrPosition.Heading.SOUTH).currentHeading)
        assertEquals(CurrPosition.Heading.NORTH, turnTest("left",CurrPosition.Heading.EAST).currentHeading)
    }

    @Test
    void "Turn Right"() {
        assertEquals(CurrPosition.Heading.EAST, turnTest("right",CurrPosition.Heading.NORTH).currentHeading)
        assertEquals(CurrPosition.Heading.NORTH, turnTest("right",CurrPosition.Heading.WEST).currentHeading)
        assertEquals(CurrPosition.Heading.WEST, turnTest("right",CurrPosition.Heading.SOUTH).currentHeading)
        assertEquals(CurrPosition.Heading.SOUTH, turnTest("right",CurrPosition.Heading.EAST).currentHeading)
    }

    @Test
    void "Rover Explore"() {
        roverIF.setCurrentPosition(new CurrPosition(10,10,CurrPosition.Heading.NORTH))
        assertEquals(new Cell(10,10), roverIF.explore())
    }

    @Test
    void "Copter Explore"() {
        roverIF.setCurrentPosition(new CurrPosition(10,10,CurrPosition.Heading.NORTH))
        List<Cell> expected = []
        [9,10,11].each {row ->
            [9,10,11].each {column ->
                expected.add(new Cell(row, column))
            }
        }
        assertEquals(expected, roverIF.launch().explore())
    }

    @Test
    void "Move Boundary Test"() {
        roverIF.setCurrentPosition(new CurrPosition(1,1,CurrPosition.Heading.NORTH))
        roverIF.moveForward()
        assertEquals("1A", roverIF.getCurrentPosition().getPositionName())
        roverIF.turnLeft()
        roverIF.moveForward()
        assertEquals("1A", roverIF.getCurrentPosition().getPositionName())

        roverIF.setCurrentPosition(new CurrPosition(25,25,CurrPosition.Heading.SOUTH))
        roverIF.moveForward()
        assertEquals("25Y", roverIF.getCurrentPosition().getPositionName())
        roverIF.turnLeft()
        roverIF.moveForward()
        assertEquals("25Y", roverIF.getCurrentPosition().getPositionName())

    }

    @Test
    void "Copter Explore Boundary Test"() {
        roverIF.setCurrentPosition(new CurrPosition(1,1,CurrPosition.Heading.NORTH))
        List<Cell> upperLeftExpected = []
        [1,2].each {row ->
            [1,2].each {column ->
                upperLeftExpected.add(new Cell(row, column))
            }
        }
        assertEquals(upperLeftExpected, roverIF.launch().explore())

        roverIF.setCurrentPosition(new CurrPosition(25,25,CurrPosition.Heading.NORTH))
        List<Cell> bottomRightExpected = []
        [24,25].each {row ->
            [24,25].each {column ->
                bottomRightExpected.add(new Cell(row, column))
            }
        }
        assertEquals(bottomRightExpected, roverIF.launch().explore())
    }

    private CurrPosition turnTest(String turn, CurrPosition.Heading currentHeading) {
        roverIF.setCurrentPosition(new CurrPosition(10,10,currentHeading))
        if("left".equalsIgnoreCase(turn)) {
            roverIF.turnLeft()
        } else if("right".equalsIgnoreCase(turn)) {
            roverIF.turnRight()
        }

        assertEquals(roverIF.getCurrentPosition().getPositionName(),"10J")

        return roverIF.getCurrentPosition()
    }
}
