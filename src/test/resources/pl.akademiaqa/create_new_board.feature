Feature: Create new board

  @cleanup
  Scenario: I should be able to create new board with valid data
    Given I am authenticated to Trello
    When I create new board "THIS IS NEW BOARD"
    Then I can read created board details

  @cleanup
  Scenario Outline: I should be unable to create new board without authentication
    Given I am not authenticated to Trello
    When I create new board without authentication
    Then I got a <Exception>

    Examples: Empty names
      | Exception                                                                                 |
      | "io.restassured.path.json.exception.JsonPathException: Failed to parse the JSON document" |

  @cleanup
  Scenario Outline: I should be able to create new board with strange name
    Given I am authenticated to Trello
    When I create new board with name was started at <char>
    Then Created board name started at <char>

    Examples: Strange names
      | char |
      | "!"  |
      | "@"  |
      | "#"  |
      | "$"  |
      | "%"  |
      | "^"  |
      | "&"  |
      | "*"  |

  @cleanup
  Scenario Outline: I should be able to create new board with empty name
    Given I am authenticated to Trello
    When I create new board with empty <name>
    Then I got a <Exception>

    Examples: Empty names
      | name | Exception                                                                                 |
      | ""   | "io.restassured.path.json.exception.JsonPathException: Failed to parse the JSON document" |