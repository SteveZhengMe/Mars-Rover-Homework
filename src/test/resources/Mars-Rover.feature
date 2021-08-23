Feature: Landed, execute command, report explore coverage
  Rover landed, then send command and Rover reports the explore coverage

  Scenario: Land
    Given A Rover is Landed
    Then The Rover should report his position and the position should in the given Region "Yes"

  Scenario Outline: Execute Command
    Given The rover landed at <row>, <column>
    When NASA send a command <command>
    Then The Rover report his new position <result>
    Then The explore rate is <explored>

    Examples:
      | row | column | command           | result | explored |
      | 17  | 8      | "FFFLFFRBBHFFFFF" | "11F"  | 2.88     |