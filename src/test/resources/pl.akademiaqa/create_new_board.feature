Feature: Create new board

  @cleanup
  Scenario: I should be able to create new board with valid data
    Given I am authenticated to Trello
    When I create new board
    Then I can read created board details