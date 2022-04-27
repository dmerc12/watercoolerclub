Feature: As an applicant, I want to complete skill tests provided by companies so that I can prove I have the necessary skill set.
  Scenario Outline: As an applicant, I want to complete skill tests provided by companies so that I can prove I have the necessary skill set.
    Given I am on the landing page
    When I input my "<individualId>"
    When I click on the Take Skill Tests button
    When I am on the skill tests page
    When I click on test "<skillTestId>" button
    When I am on that skill test's page with "<questionOne>"
    When I fill out the "<firstAnswer>" 1A
    When I fill out the "<secondAnswer>" 2A
    When I fill out the "<thirdAnswer>" 3A
    When I click the submit button
    When I get an alert that tells me that I passed
    Then I am on the skill tests page 2

    Examples:
    | individualId | skillTestId | questionOne | firstAnswer | secondAnswer | thirdAnswer |
    | -1           | -1          | test        | test        | test         | test        |


  Scenario Outline: As an applicant, I want to complete skill tests but I answer one of the questions incorrectly.
    Given I am on the landing page
    When I input my "<individualId>"
    When I click on the Take Skill Tests button
    When I am on the skill tests page
    When I click on test "<skillTestId>" button
    When I am on that skill test's page with "<questionOne>"
    When I fill out the first answer incorrectly
    When I fill out the "<secondAnswer>" 2A
    When I fill out the "<thirdAnswer>" 3A
    When I click the submit button
    When I get an alert that tells me that I failed
    Then I am on the skill tests page 2

    Examples:
    | individualId | skillTestId | questionOne | secondAnswer | thirdAnswer |
    | -1           | -1          | test        | test         | test        |