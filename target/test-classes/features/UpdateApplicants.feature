Feature: As an applicant, I want to update my profile so that I can search for a job.
  Scenario: As an applicant, I want to start looking for a job.
    Given: I am on the main job board page
    When: I click on the profile button
    When: I input my first name and last name
    when: I input the phone number
    when: I input email address
    when: I input work history
    when: I input reference
    when: i input education
    when: I input desired job
    when: I click on the update button
    Then: I redirected to the main job board page


