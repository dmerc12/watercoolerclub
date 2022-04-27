Feature: I should be able to create a user account and then login to that account
  Scenario: As a user I should be able to create a user account on the job website
    Given I am on the landing page user create
    When I click on the create new user button create
    When I am on the create page
    When I fill out my username user create
    When I fill out my password user create
    When I click on the Create User Account button
    When I click on an alert about successful account creation
    Then My user account should be created