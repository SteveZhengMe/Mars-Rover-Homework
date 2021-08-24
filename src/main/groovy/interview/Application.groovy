package interview

import interview.exceptions.InvalidateCommandException
import interview.exceptions.RoverException
import interview.services.ControlCenterIF
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import java.text.DecimalFormat

/**
 *
 * Author: Steve
 * Date: 2021-08-20
 *
 */

@SpringBootApplication
class Application implements CommandLineRunner {

    @Autowired
    ControlCenterIF controlCenter

    static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args)
    }

    // @Override
    // void run(String... args) {
    //     println("Rover is landed at " + controlCenter.landRover().getPositionName())
    //     print("Enter your command: ")
    //     Scanner scanner = new Scanner(System.in)
    //     String line = scanner.nextLine()
    //     try {
    //         println("Rover is stopped at " + controlCenter.sendCommand(line).getPositionName())
    //         DecimalFormat formatter = new DecimalFormat("##.00")
    //         println("Total explored rate is " + formatter.format(controlCenter.calculateExploreRate()) + "%")
    //     } catch (InvalidateCommandException invalidateCommandException) {
    //         println("Cannot recognize the command: " + invalidateCommandException.getMessage())
    //     } catch (RoverException roverException) {
    //         println("Rover has problem: " + roverException.getMessage())
    //     }
    // }

    @Override
    void run(String... args) {
        println("Rover is landed at " + controlCenter.landRover().getPositionName())
        if(args.length == 1) {
            try {
                println("Rover is stopped at " + controlCenter.sendCommand(args[0]).getPositionName())
                DecimalFormat formatter = new DecimalFormat("##.00")
                println("Total explored rate is " + formatter.format(controlCenter.calculateExploreRate()) + "%")
            } catch (InvalidateCommandException invalidateCommandException) {
                println("Cannot recognize the command: " + invalidateCommandException.getMessage())
            } catch (RoverException roverException) {
                println("Rover has problem: " + roverException.getMessage())
            }
        } else {
            println("Please enter your command")
        }
    }
}
