import interview.models.Cell
import interview.models.CurrPosition
import interview.models.Region
import interview.models.impls.IngenuityImpl
import interview.models.impls.PercyImpl
import interview.services.ControlCenterIF
import interview.services.impls.NASAImpl
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import static org.junit.jupiter.api.Assertions.assertEquals


/**
 * Author: Steve
 * Date: 2021-08-22
 */

@SpringBootTest(classes = [IngenuityImpl.class, PercyImpl.class, NASAImpl.class])
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
@CucumberContextConfiguration
class ControlCenterIntegrationTest {
    CurrPosition currPosition

    @Autowired
    ControlCenterIF controlCenter

    @Before
    void initRegion() {
        Region.getInstance()
        controlCenter.reset()
    }

    @Given("A Rover is Landed")
    void aRoverIsLanded() {
        currPosition = controlCenter.landRover()
    }

    @Then("The Rover should report his position and the position should in the given Region {string}")
    void theRoverShouldReportHisPositionAndThePositionShouldInTheGivenRegion(String inRegion) {
        assertEquals(inRegion, Region.getInstance().getCellsSet().contains(new Cell(currPosition.row, currPosition.column))?"Yes":'No')
    }

    @Given("The rover landed at {int}, {int}")
    void theRoverLandedAt(int row, int column) {
        controlCenter.landRover(new CurrPosition(row, column, CurrPosition.Heading.NORTH))
    }

    @When("NASA send a command {string}")
    void nasaSendACommand(String command) {
        controlCenter.sendCommand(command)
    }

    @Then("The Rover report his new position {string}")
    void theRoverReportHisNewPosition(String positionName) {
        assertEquals(positionName, controlCenter.rover.getCurrentPosition().getPositionName())
    }


    @Then("The explore rate is {float}")
    void theExploreRateIsExplored(float expectedRate) {
        assertEquals(expectedRate, controlCenter.calculateExploreRate())
    }
}
