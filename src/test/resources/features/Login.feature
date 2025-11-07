Feature: Login Feature

  #This tag is for the successful login scenario
  Scenario:
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "FCVlLOni"
    And I submit
    Then I am logged in

    #This tag is for all negative login scenarios
  @negative_login
  Scenario: Login with incorrect password
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "wrongPassword"
    And I submit
    Then I see an error message

  @negative_login
  Scenario: Login with a non-existent email
    Given I open Login Page
    When I enter email "nonexistent@testpro.io"
    And I enter password "anyPassword"
    And I submit
    Then I see an error message

  @negative_login
  Scenario: Login with correct email and incorrect password
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password "reallybadpass"
    And I submit
    Then I see an error message

  @negative_login
  Scenario: Login with incorrect email format and correct password
    Given I open Login Page
    When I enter email "notanemail@testpro.com"
    And I enter password "FCVlLOni"
    And I submit
    Then I see an error message

  @negative_login
  Scenario: Login with correct email and blank password
    Given I open Login Page
    When I enter email "jennifer.de.jesus@testpro.io"
    And I enter password ""
    And I submit
    Then I see the password required field validation message

  @negative_login
  Scenario: Login with empty email and password
    Given I open Login Page
    When I enter email ""
    And I enter password ""
    And I submit
    Then I see the email required field validation message

  @negative_login
  Scenario: Login with blank email and correct password
    Given I open Login Page
    When I enter email ""
    And I enter password "FCVlLOni"
    And I submit
    Then I see the email required field validation message