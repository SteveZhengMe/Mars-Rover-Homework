import interview.models.Cell
import interview.models.CurrPosition
import interview.models.Region
import interview.models.impls.IngenuityImpl
import interview.models.impls.PercyImpl
import interview.services.ControlCenterIF
import interview.services.impls.NASAImpl
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

/**
 * Test ControlCenter and Region, Cell
 * Author: Steve
 * Date: 2021-08-19
 */

@SpringBootTest(classes = [IngenuityImpl.class, PercyImpl.class, NASAImpl.class])
class ControlCenterUnitTest {

    @Autowired
    ControlCenterIF controlCenter

    @BeforeAll
    static void "Prepare Region"() {
        Region.getInstance()
    }

    @BeforeEach
    void "Reset Control Center"() {
        controlCenter.reset()
    }

    @Test
    void "Cell Name Test"() {
        Cell cell = new Cell(3,3)
        assertEquals("3C",cell.getPositionName())
    }

    @Test
    void "Test Command From Control Center"() {
        CurrPosition landingPosition = controlCenter.landRover()

        assertTrue(Region.getInstance().getCellsSet().contains(new Cell(landingPosition.row, landingPosition.column)))
    }

    @Test
    void "Test Send Command To Rover"() {
        controlCenter.landRover(new CurrPosition(17,8, CurrPosition.Heading.NORTH))
        assertEquals(new CurrPosition(11,6, CurrPosition.Heading.NORTH),controlCenter.sendCommand("FFFLFFRBBHFFFFF"))
    }

    @Test
    void "Test Explore Rate"() {
        controlCenter.landRover(new CurrPosition(17,8, CurrPosition.Heading.NORTH))
        controlCenter.sendCommand("FFFLFFRBBHFFFFF")

        //println(controlCenter.explored)
        assertEquals(2.88f,controlCenter.calculateExploreRate())

        Set<Cell> expected = []

        [15,16,17].each {row ->
            [5,6,7,8].each {column ->
                expected.add(new Cell(row, column))
            }
        }

        [11,12,13,14].each {
            expected.add(new Cell(it, 6))
        }

        expected.add(new Cell(14, 7))
        expected.add(new Cell(14, 8))

        assertEquals(expected, controlCenter.explored)
    }

    @Test
    void "Move Boundary Test"() {
        controlCenter.landRover(new CurrPosition(3,3, CurrPosition.Heading.NORTH))
        controlCenter.sendCommand("FFFFFF")
        assertEquals("1C", controlCenter.rover.getCurrentPosition().getPositionName())
        controlCenter.sendCommand("LF")
        assertEquals("1B", controlCenter.rover.getCurrentPosition().getPositionName())
        controlCenter.sendCommand("FFFFFFFF")
        assertEquals("1A", controlCenter.rover.getCurrentPosition().getPositionName())
    }

    @Test
    void "Explore Boundary Test Conor"() {
        controlCenter.landRover(new CurrPosition(3,3, CurrPosition.Heading.NORTH))
        controlCenter.sendCommand("FFFFFFLFFFFFFH")
        assertEquals(1.12f,controlCenter.calculateExploreRate())

        Set<Cell> expected = []
        [1,2].each {row ->
            [1,2,3].each {column ->
                expected.add(new Cell(row, column))
            }
        }
        expected.add(new Cell(3, 3))

        assertEquals(expected, controlCenter.explored)
    }

    @Test
    void "Explore Boundary Test Edge"() {
        controlCenter.landRover(new CurrPosition(2,9, CurrPosition.Heading.NORTH))
        controlCenter.sendCommand("FFFFFFLFFFFFFH")
        assertEquals(1.92f,controlCenter.calculateExploreRate())

//        println(controlCenter.explored)
        Set<Cell> expected = []
        [2,3,4,5,6,7,8,9].each {
            expected.add(new Cell(1, it))
        }
        expected.add(new Cell(2, 9))
        expected.add(new Cell(1, 2))
        expected.add(new Cell(2, 2))
        expected.add(new Cell(2, 3))
        expected.add(new Cell(2, 4))

        assertEquals(expected, controlCenter.explored)
    }
}
