Feature: As a company I want to view the applicants to my job so that I can see their applications
  Scenario: As a company, I want to view applications I have received so that I can select someone for the job.
    Given I am on the landing page
    When  I click the manage posted jobs button
    When  I click the view applications button on the posted job
    When  I click continue after I see a the list of applicants for my job
    Then  I am on the landing page