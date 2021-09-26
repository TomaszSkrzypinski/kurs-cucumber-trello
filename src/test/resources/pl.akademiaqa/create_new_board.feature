Feature: Create new board

  Scenario: I should be able to create new board with valid data
    Given I am authenticated to Trello
    When I create new board
    Then I see created board on the list