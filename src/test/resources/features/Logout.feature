Feature: Log out

   # --- SUCCESSFUL LOGIN & NAVIGATION ---
  # AC 1, (Successful Login and Homepage Navigation)
  @Login_Success @AC_1
  Scenario: Successful Login and Homepage Navigation
   Given I open Login Page
   When I enter email "jennifer.de.bademail@testpro.io"
    And I enter password "badpassword!1"
   And I submit
   Then I am logged in
   Then I am on the Homepage
   Then Logout option is visible 

  # AC 2, (Successful Logout)
  @Logout_Success @AC_2
  Scenario: Successful Logout
    Given I open Login Page
    When I enter email "jennifer.de.bademail@testpro.io"
    And I enter password "badpassword!1"
    And I submit
    Then I am logged in
    Then I am on the Homepage
    Then Logout option is visible    
    When I click on Logout option 
    Then I am logged out

    # AC 3, (Post-Logout Navigation)
  @Post_Logout_Navigation @AC_3
  Scenario: Post-Logout Navigation
    Given I open Login Page
    When I enter email "jennifer.de.bademail@testpro.io"
    And I enter password "badpassword!1"
    And I submit
    Then I am logged in
    Then I am on the Homepage
    Then Logout option is visible    
    When I click on Logout option
    Then I am redirected to Login Page

    # AC 4, (Log out After updating email and password)
  @Logout_After_Update @AC_4
  Scenario: Log out After updating email and password
    Given I open Login Page
    When I enter email "jennifer.de.bademail@testpro.io"
    And I enter password "badpassword!1"
    And I submit
    Then I am logged in
    When profile icon is available
    And I click profile icon
    When profile and preferences form appears
    And I enter new email in profile and preferences form "jennifer.de.jesus@testpro.io"
    And I enter new password in profile and preferences form "FCVlLOni12!"
    And I enter current password in profile and preferences form "badpassword!1"
    When I click save on profile and preferences form a "Profile updated." message appears
    Then Logout option is visible    
    When I click on Logout option
    Then I am logged out