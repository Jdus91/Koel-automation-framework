Feature: Profile and Preferences
# --- SUCCESSFUL LOGIN & NAVIGATION ---
  @Login_Success
  Scenario: Successful Login and Homepage Navigation
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in

    #AC 1_2 (Name update validation)
    @Name_Update_Validation @AC_1_2
    Scenario: Update Name in Profile and Preferences
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    When profile icon is available
    And I click profile icon
    When profile and preferences form appears
    And I enter new name in profile and preferences form "Jennifer"
    And I enter current password in profile and preferences form "FCVlLOni12!"
    When I click save on profile and preferences form a "Profile updated." message appears
    And I select Home tab from the navigation menu #code to be completed in HomePage.java?
    Then I am on the Homepage
    And I verify that the updated name "Jennifer" is displayed on the Homepage #code to be completed in HomePage.java

    #AC 3_4 (Email and Password update validation)
    @Email_and_Password_Update_Validation @AC_3_4
    Scenario: Update Email and Password in Profile and Preferences
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    When profile icon is available
    And I click profile icon
    When profile and preferences form appears
    And I enter new email in profile and preferences form "jennifer.de.bademail@testpro.io"
    And I enter new password in profile and preferences form "badpassword!1"
    And I enter current password in profile and preferences form "FCVlLOni12!"
    When I click save on profile and preferences form a "Profile updated." message appears
    And I enter new email in profile and preferences form "jennifer.de.jesus@testpro.io"
    And I enter new password in profile and preferences form "FCVlLOni12!"
    And I enter current password in profile and preferences form "badpassword!1"
    When I click save on profile and preferences form a "Profile updated." message appears

    #AC 6 (Theme update validation - Classic to Oak)
    @Theme_Update_Validation @AC_6
    Scenario: Update Theme from Classic to Oak in Profile and Preferences
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    When profile icon is available
    And I click profile icon
    When profile and preferences form appears
    And I select "Oak" theme in profile and preferences form #code to be completed in HomePage.java
    And I select Home tab from the navigation menu
    Then I am on the Homepage
    And I verify that the "Oak" theme is applied on the Homepage #code to be completed in HomePage.java

    #AC 7 ("Show Now Playing" checkbox checked and notification verification)
    @Show_Now_Playing_Notification_Verification @AC_7
    Scenario: Enable "Show Now Playing" and verify notification
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    When profile icon is available
    And I click profile icon
    When profile and preferences form appears
    And I check the "Show Now Playing" checkbox in profile and preferences form #code to be completed in HomePage.java
    And I select Home tab from the navigation menu
    Then I am on the Homepage
    When I play a song
    Then I verify that the "Now Playing" notification is displayed on the Homepage #code to be completed in HomePage.java

    #AC 8 ("Confirm before closing Koel checkbox checked and confirmation window verification)
    @Confirm_Before_Closing_Verification @AC_8
    Scenario: Enable "Confirm before closing Koel" and verify confirmation window
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged in
    When profile icon is available
    And I click profile icon
    When profile and preferences form appears
    And I check the "Confirm before closing Koel" checkbox in profile and preferences form #code to be completed in HomePage.java
    And I log out
    Then I verify that the confirmation window is displayed #code to be completed in HomePage.java

    #AC 9 ("Show a translucent, blurred overlay of the current album’s art checkbox checked and overlay verification)
    @Album_Art_Overlay_Verification @AC_9
    Scenario: Enable "Show a translucent, blurred overlay of the current albums art" and verify overlay
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni12!"
    And I submit
    Then I am logged ins
    When profile icon is available
    And I click profile icon
    When profile and preferences form appears
    And I check the "Show a translucent, blurred overlay of the current albums art" checkbox in profile and preferences form #code to be completed in HomePage.java
    And I select Home tab from the navigation menu
    Then I am on the Homepage
    When I play a song
    Then I verify that the translucent, blurred overlay of the current albums art is displayed on the Homepage #code to be completed in HomePage.java