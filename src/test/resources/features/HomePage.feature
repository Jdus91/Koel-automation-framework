Feature: HomePage
# --- SUCCESSFUL LOGIN & NAVIGATION ---

  @Login_Success
  Scenario: Successful Login and Homepage Navigation
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    #AC 1-6 (Verfication of Homepage text, Recently Played songs, icons, Recently Added Songs sections)

  @Homepage_Text_RecentlyPlayed_RecentlyAdded_Verification @AC_1-6
  Scenario: Verification of Homepage Text, Recently Played songs, buttons, Recently Added Songs sections
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    And I select Home tab from the navigation menu
    Then I am on the Homepage
    And I verify that the Homepage displays phrases
    And I verify that Recently Played songs are displayed on the Homepage
    And I verify that the view all button in Homepage is displayed in the Recently Played section
    And I verify that the Recently Played section has added songs
    And I verify that Album names are displayed for the Recently Added songs section on the Homepage
    #And I verify that the Shuffle and Download icons are present for the Recently Added songs section on the Homepage
#     #AC 7 (Search functionality validation)
#   @Search_Functionality_Validation @AC_7
#   Scenario: Search Functionality Validation on Homepage
#     Given I open Login Page
#     When I enter email "jennifer.de.jesus@testpro.io"
#     And I enter password "FCVlLOni12!"
#     And I submit
#     Then I am logged in
#     And I select Home tab from the navigation menu
#     Then I am on the Homepage
#     And I verify that Search field is present on the Homepage
#     When I click on the Search field on the Homepage
#     And I type f in the Search field
#     Then I verify that search results related to "f" are displayed on the Homepage
#     #AC 8-10 (Verification of music panel, playlist panel on Homepage)
#   @Music_and_Playlist_Panels_Verification @AC_8-10
#   Scenario: Verification of Music Panel, Playlist Panel, and Icons on Homepage
#     Given I open Login Page
#     When I enter email "jennifer.de.jesus@testpro.io"
#     And I enter password "FCVlLOni12!"
#     And I submit
#     Then I am logged in
#     And I select Home tab from the navigation menu
#     Then I am on the Homepage
#     And I verify that the Music panel includes Home, Current Queue, All Songs, Albums, and Artists
#     And I verify that the Playlist panel includes Favorites playlist, Recently played playlist, Smart playlists and user created playlists
#     #AC 11 (Verification of Profile, Logout, About Koel links on Homepage)
#   @Profile_Logout_AboutKoel_Icons_Verification @AC_11
#   Scenario: Verification of Profile, Logout, and About Koel Icons on Homepage
#     Given I open Login Page
#     When I enter email "jennifer.de.jesus@testpro.io"
#     And I enter password "FCVlLOni12!"
#     And I submit
#     Then I am logged in
#     And I select Home tab from the navigation menu
#     Then I am on the Homepage
#     And I verify that Profile icon, Logout icon, and About Koel icons are present on the Homepage
#     When profile icon is available
#     And I click profile icon
#     Then I verify that profile and preferences form appears
#     And I select Home tab from the navigation menu
#     Then I am on the Homepage
#     Then Logout option is visible
#     When I click on Logout option
#     Then I am redirected to Login Page
#     When I enter email "jennifer.de.jesus@testpro.io"
#     And I enter password "FCVlLOni12!"
#     And I submit
#     Then I am logged in
#     And I select Home tab from the navigation menu
#     Then I am on the Homepage
#     When I click on About Koel icon
#     Then I verify that About Koel modal appears
