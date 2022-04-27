Feature: I should be able to create a Company account and then login to that account
  Scenario: As a Company I should be able to create a Company account on the job website
    Given I am on the landing page create account
    When I click on the create new company button
    When I am on the create company page
    When I fill out my username create account
    When I fill out my password create account
    When I click on the Create Company Account button
    When I click on an alert about successful account creation create account
    Then My Company account should be created