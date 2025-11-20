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
    #AC 2_3_4 (Current Queue songs details validation)

  @Current_Queue_Songs_Details_Validation @AC_2_3_4
  Scenario: Validate Songs Details in Current Queue
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
    And I verify the total number of songs in the Current Queue page is accurate
    And I verify the total duration of songs in the Current Queue page is accurate
    And I verify that ID, Title, Artist, Album, and time details are displayed in the Current Queue page
    #AC 5_6_7 (Current Queue actions, shuffle and clear queue validation)

  @Current_Queue_Actions_Validation @AC_5_6_7
  Scenario: Validate Current Queue Actions - Shuffle and Clear Queue
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    And I select Home tab from the navigation menu
    Then I am on the Homepage
    When I select Album tab from the navigation menu
    Then I am on the Album page
    And I double click to play a song from the Album page
    Then I verify that I am navigated to the Current Queue page
    When I select shuffle all songs button
    Then I verify that the songs in the Current Queue page are shuffled
    When I select clear queue button
    Then I verify that the Current Queue page is cleared
    And I verify that a No songs queued. How about shuffling all songs?. message appears
    # When I select "Shuffle all songs" link from the message #code to be implemented in Current Queue
    # Then I verify that all songs appears in the Current Queue page #code to be implemented in Current Queue
