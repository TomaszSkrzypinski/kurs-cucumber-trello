Feature: Update board name

  @cleanup
  Scenario: I should be able to update board name
    Given I am authenticated to Trello
    And the board already exist
    When I update board name "UPDATE BOARD NAME"
    Then I see new board name "UPDATE BOARD NAME"

