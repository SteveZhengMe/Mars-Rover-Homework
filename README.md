# Mars-Rover-Homework
This project is for a pre-interview homework of Sr. developer.

## I. Project Introduction

### Perseverance Rover Adventure
The latest NASA Mars rover (Percy) had successfully landed on Mars was received on 18 February 2021. Percy has a similar design to its predecessor rover, Curiosity. One significant improvement is about carrying the mini-helicopter, Ingenuity.

You are the software engineer of Percy. Your task is to build a program to control him to explore a 625 square meter area. The area is spitted to 25 * 25 cells.

The rover might land anywhere in the area. After the landing, he will return you a coordinate (Such as t8) and always heading north. Then you can send him a command. There are five types of control (doesn't matter the uppercase or lowercase):

- F: move one step forward
- B: move backward one step
- L: turn left (90 Degree)
- R: turn right (90 Degree)
- H: Launch Ingenuity (When Ingenuity is flying, Percy can not move.)

Because of the bandwidth, the control center can only send the command once a time in a string. For example, if the order is: "FFFLFFRBBHFFFFF." The Roller will:
- Go north (Forward) 3 steps
- Turn left
- Go west (Forward) 2 steps
- Turn right
- Go south (Backward) 2 steps
- Launch mini-helicopter
- (When mini-helicopter is back), go north (Forward) 5 steps

(If Percy landed at h17. After the commands, it is at f11.)

Exception:
If the command causes the Roller to run outside the area, it will ignore it.

### The Task

Implement the Perseverance Roller class. When the roller object is created, return a random landed coordinate and print it out. When the Roller received a command, it returns the final coordinate. 
For example, input “FFFLFFRBBHFFFFF,” return “f11.”

### Bonus Task
Suppose the Roller can only explore the spot he stands and the mini-helicopter can explore the surrounding cells (9 cells). Write another function/method to return the percentage of the investigated area. 

For example, in the above example, the percentage is:18/625 = 2.88%
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