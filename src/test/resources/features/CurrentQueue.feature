Feature: Current Queue
# --- SUCCESSFUL LOGIN & NAVIGATION ---
  @Login_Success
  Scenario: Successful Login and Homepage Navigation
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in

#AC 1 (Current Queue currently played songs validation)
    @Current_Queue_Validation @AC_1
    Scenario: Validate Currently Played Songs in Current Queue
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    And I select Home tab from the navigation menu
    Then I am on the Homepage
    When I play a song from the Most Played section
    And I select Current Queue tab from the navigation menu
    Then I verify that the song "Midnight in Mississippi" is playing in the Current Queue page

