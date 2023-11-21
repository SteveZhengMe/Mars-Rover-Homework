# Mars-Rover-Homework
This project is for a pre-interview homework for Sr. developer.

## I. Project Introduction

### Perseverance Rover Adventure
The latest Mars rover from NASA, nicknamed Percy, successfully landed on Mars on February 18th, 2021. Percy has a similar design to its predecessor, Curiosity, but carries an additional mini-helicopter called Ingenuity.

You are a software engineer for Percy, tasked with building a program to explore a 625 square meter area divided into 25x25 cells.

It's important to note that the rover could potentially land in any part of the designated area. Once it has landed, it will provide you with a coordinate, such as "t8," and will always be heading north. At this point, you can issue commands to the rover using one of the five types of control available (note that the case of the commands does not matter).

- F: move one step forward
- B: move backward one step
- L: turn left (90 Degrees)
- R: turn right (90 Degrees)
- H: Launch Ingenuity (Percy can not move when Ingenuity is flying.)

Due to limited bandwidth, the Roller can only receive commands one at a time in a string format. For instance, if the command is "FFFLFFRBBHFFFFF," the Roller will:- Go north (Forward) 3 steps
- Turn left
- Go west (Forward) 2 steps
- Turn right
- Go south (Backward) 2 steps
- Launch mini-helicopter
- (When the mini-helicopter is back), go north (Forward) 5 steps

|   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| 1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 2 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 3 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 4 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 5 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 6 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 7 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 8 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 9 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 10 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 11 |   |   |   |   | E |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 12 |   |   |   |   | X |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 13 |   |   |   |   | X |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 14 |   |   |   |   | X | X | X |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 15 |   |   |   |   | X |   | X |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 16 |   |   |   |   | X |   | X |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 17 |   |   |   |   |   |   | S |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 18 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 19 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 20 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 21 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 22 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 23 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 24 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 25 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |


(If Percy landed at h17. After the commands, it is at f11.)

Exception:
If the Roller exceeds the designated area, it will disregard the commands and stopped at the edge.

### Your Task

Implement a class for the Perseverance Roller. The roller object returns a random landed coordinate upon creation and the final coordinate upon receiving a command. For example, input “FFFLFFRBBHFFFFF,” returns “f11.”

### Bonus Task

Write a function to calculate the percentage of the area explored by a Roller and a mini-helicopter. The Roller can only explore the spot it stands on, while the mini-helicopter can explore the surrounding nine cells.

|   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| 1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 2 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 3 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 4 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 5 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 6 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 7 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 8 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 9 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 10 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 11 |   |   |   |   | E |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 12 |   |   |   |   | X |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 13 |   |   |   |   | X |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 14 |   |   |   |   | X | X | X |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 15 |   |   |   | H | XH | H | X |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 16 |   |   |   | H | XH | H | X |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 17 |   |   |   | H | H | H | S |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 18 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 19 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 20 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 21 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 22 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 23 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 24 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| 25 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |

For example, in the above example (FFFLFFRBBHFFFFF), the percentage is:18/625 = 2.88%
- The yellow cell is from the Roller
- The blue cells are from the mini-helicopter (launched at spot "X")



## II. Compile and run the application

### 1. Preparation

**Step one**: pull the code: 

"`git clone https://github.com/SteveZhengMe/Mars-Rover-Homework.git` "

**Step Two**: Install Gradle

It would help if you had gradle to run the command. You can refer to [This page to install](https://gradle.org/install/)

### 2. Run

- Using the command to run:
    - ```gradle bootRun --args [Your command to Rover]``` 
        - Ex. ```gradle bootRun --args FFFFFLFFBBBHFFFF```
    - The application will compile, build and run.
- Other commands
    - ```gradle build```: Create jar file in build/libs
    - ```gradle test```: Run unit testing (TDD)
    - ```gradle inteTest```: Run Integration testing (BDD)

### 3. Folders
- src/main/groovy/interview: The source code
- src/main/groovy/resources
    - ```logback-spring.xml```: Define the log level (Default level is Debug)
- src/test/groovy/
    - ```ControlCenterIntegrationTest.groovy```: Integration Testing
    - ```ControlCenterUnitTest.groovy```: Unit Testing
    -``` RoverUnitTest.groovy```: Unit Testing
    - src/test/groovy/resources
        - ```Mars-Rover.feature```: BDD description
