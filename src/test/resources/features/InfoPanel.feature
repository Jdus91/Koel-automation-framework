Feature: Info Panel Feature

   # --- SUCCESSFUL LOGIN & NAVIGATION ---
  @Login_Success
  Scenario: Successful Login and Homepage Navigation
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in

    #AC 1_2_3 (Verify Album name, cover name, lyrics, and artist name is displayed for a playing song)
  @Album_Cover_Lyrics_Artist_Displayed @AC_1_2_3
    Scenario: Verify Album name, cover name, lyrics, and artist name is displayed for a playing song
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    When I play a song
    Then I verify Album name is displayed
    And I verify Cover name is displayed
    And I verify Lyrics is displayed in Progress Pane
    And I verify Artist name is displayed

    #AC 4_5 (Verify Info Panel can be opened and closed)
    @Info_Panel_Open_Close @AC_4_5
    Scenario: Verify Info Panel can be opened and closed
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    When I play a song
    And I open Info Panel
    Then I verify Info Panel is opened
    When I close Info Panel
    Then I verify Info Panel is closed

 #AC 6 (Verify Suffle order of playing songs on the Info panel by album and artist)
    @Shuffle_Order_Verification @AC_6
    Scenario: Verify Shuffle order of playing songs on the Info panel by album and artist
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    When I play a song
    And I open Info Panel
    Then I verify Info Panel is opened
    When I select Album tab in Info Panel
    Then I select and verify that Shuffle button from Album tab was clicked
    When I select Artist tab from the Info Panel
    Then I select and verify that Shuffle button from Artist tab was clicked





