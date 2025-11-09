@logout
Feature: Logout

  Background:
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni"
    And I submit
    Then I am logged in
    And I am on the Home page

  @ac1
  Scenario: "Log student out" is present next to "Profile"
    Then I should see the "Log student out" control
    And the "Log student out" control is next to the "Profile" control

  @ac2 @ac3
  Scenario: User can log out and is navigated to Login page
    When I log out
    Then I am on the Login page

  @ac4
  Scenario: User can log out after updating email and password
    When I update my password from "FCVlLOni" to "newPassword123"
    And I update my email from "jennifer.de.jesus@testpro.io" to "new.email@testpro.io" using password "newPassword123"
    And I log out
    Then I am on the Login page