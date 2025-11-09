Feature: Login Feature

   # --- SUCCESSFUL LOGIN & NAVIGATION ---
  # ACs 1, 2 (Successful Login and Homepage Navigation)
  @Login_Success @AC_1 @AC_2 @AC_3
  Scenario: Successful Login and Homepage Navigation
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni"
    And I submit
    Then I am logged in

    # AC 3 (Navigation)
  @Navigation @AC_3
  Scenario: User can navigate to all pages after login
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni"
    And I submit
    And I navigate to "Favorites"
    Then I am taken to the "Favorites" page

    # AC 4 (Last Visited Page Persistence)
    @Last_Visited @AC_4
    Scenario: Last visited page is remembered after logout and login
    # 1. First, log in and set the application state
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni"
    And I submit
    # 2. Navigate to a non-default page to set the "last visited" cookie/session
    When I navigate to "Favorites"
    And I am taken to the "Favorites" page
    # 3. Log out.
    And I log out
    # 4. Log in again. After logout, you should be back on the Login Page.
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni"
    And I submit
    # 5. This is the key assertion for AC 4.
    # Instead of the homepage, we should land on "Favorites".
    Then I am taken to the "Favorites" page

#  # AC 4 (Last Visited Page Persistence)
#  @Last_Visited @AC_4
#  Scenario: Last visited page is remembered after logout and login
#    Given I open Login Page
#    When I enter email "jennifer.de.jesus@testpro.io"
#    And I enter password "FCVlLOni"
#    And I submit
#    And I navigate to Favorites page
#    And I log out
#    When I enter email "jennifer.de.jesus@testpro.io"
#    And I enter password "FCVlLOni"
#    And I submit
#    Then I am taken to the "Favorites" page

#  # AC 5 & 6 (Account Updates)
#  @Update_Profile @AC_5_6
#  Scenario: Login with updated email and password
#    Given I open Login Page
#    When I enter email "jennifer.de.jesus@testpro.io"
#    And I enter password "FCVlLOni"
#    And I submit
#
#            # AC 5: Update Email
#    When I update my email from "jennifer.de.jesus@testpro.io" to "new.email@testpro.io" using password "newPassword123"
#    And I log out
#    Then I can log in with "new.email@testpro.io" and password "newPassword123"
#    And I cannot log in with "jennifer.de.jesus@testpro.io" and password "newPassword123"
#
#    # AC 6: Update Password
#    When I update my password from "FCVlLOni" to "newPassword123"
#    And I log out
#    Then I can log in with "jennifer.de.jesus@testpro.io" and password "newPassword123"
#    And I cannot log in with "jennifer.de.jesus@testpro.io" and password "FCVlLOni"



    # --- NEGATIVE LOGIN SCENARIOS ---
    #This tag is for all negative login scenarios
  @negative_login @AC_7
  Scenario: Login with incorrect password
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "wrongPassword"
    And I submit
    Then I see an error message

  @negative_login @AC_7
  Scenario: Login with correct email and incorrect password
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "reallybadpass"
    And I submit
    Then I see an error message

  @negative_login @AC_8
  Scenario: Login with a non-existent email
    Given I open Login Page
    When I enter email "nonexistent@testpro.io"
    And I enter password "anyPassword"
    And I submit
    Then I see an error message

  @negative_login @AC_8
  Scenario: Login with incorrect email format and correct password
    Given I open Login Page
    When I enter email "notanemail@testpro.com"
    And I enter password "FCVlLOni"
    And I submit
    Then I see an error message

  @negative_login @AC_9
  Scenario: Login with correct email and blank password
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password ""
    And I submit
    Then I see the password required field validation message

  @negative_login @AC_9
  Scenario: Login with empty email and password
    Given I open Login Page
    When I enter email ""
    And I enter password ""
    And I submit
    Then I see the email required field validation message

  @negative_login @AC_9
  Scenario: Login with blank email and correct password
    Given I open Login Page
    When I enter email ""
    And I enter password "FCVlLOni"
    And I submit
    Then I see the email required field validation message