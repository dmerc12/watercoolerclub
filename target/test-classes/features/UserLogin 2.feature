Feature: I want to login to the website with my already made account
  Scenario: I should be able to be taken from the landing page to the user page to enter my information
    Given Account is already created and I am on the landing page for user
    When I click on the sign in to an existing user account
    When I am redirected to the login page login
    When I enter my username user login
    When I enter my password user login
    When I click on the Login button user login
    Then I am taken to the jobs page user login