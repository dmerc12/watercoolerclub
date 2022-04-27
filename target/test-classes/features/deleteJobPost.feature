Feature: As a company I want to delete a job post so that I stop receiving applications.
  Scenario: As a company, I want to delete jobs I have posted so that I do not get applications for jobs that are no longer open.
    Given I am on the landing page
    When  I click the manage posted jobs button
    When  I click the delete job post button
    Then  I am on the landing page