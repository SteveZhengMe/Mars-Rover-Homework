## Move Forward Sequence Diagram

```plantuml 
autonumber
  activate NASA
  NASA -> NASA: Check the rover's current position
alt No current position is returned
  NASA -> LogService: Log an error and throw exception
else Has position
  loop each command letter
    alt "F"
      NASA -> Percy: moveForward()
      Percy -> CurrentPosition: Where am I heading?
      return heading
      activate Percy
      alt "N"
        Percy -> Percy: new CurrentPostion(row-1)
      else "W"
        Percy -> Percy: new CurrentPostion(row+1)
      else "S"
        Percy -> Percy: new CurrentPostion(col-1)
      else "E"
        Percy -> Percy: new CurrentPostion(col+1)
      end
      return CurrentPosition
      CurrentPosition -> CurrentPosition: is the new position still in the area 
      alt "Yes"
        Percy -> Percy: Update position to the new position
      else "NO"
        Percy -> Percy: Stay still
        Percy -> LogService: Log a warning message
      end 
      deactivate Percy
    else "B"
      NASA -> Percy: moveBackward()
      note right of NASA
        Same process as Move Forward
      end note
    else "L"
      NASA -> Percy: turnLeft()
      activate Percy
      alt "N"
        Percy -> Percy: heading to "W"
      else "W"
        Percy -> Percy: heading to "S"
      else "S"
        Percy -> Percy: heading to "E"
      else "E"
        Percy -> Percy: heading to "N"
      end
      deactivate Percy
    else "R"
      NASA -> Percy: turnRight()
      note right of NASA
        Same process as Turn Left
      end note
    else "H"
      NASA -> Percy: launch()
      Percy -> Ingenuity: explore()
      return List<Cell>
      Percy --> NASA: List<Cell> (from Copter)
    end
      Percy -> Percy: explore()
      Percy -> NASA: Cell (from Rover)
  end
  Percy -> NASA: CurrentPosition
  deactivate NASA
end
  
```