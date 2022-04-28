Feature: As a company, I want to create a job post so that people may apply to my job.
  Scenario Outline: As a company, I want to post jobs so that I can attract candidates for the job.
    Given I am on the landing page
    When  I click on the Post Job button
    When  I input the <jobId>
    When  I input the <title>
    When  I click the job type drop down
    When  I choose the type
    When  I input the <description>
    When  I input the <companyId>
    When  I click the location drop down
    When  I choose the location
    When  I input the <companyName>
    When  I click the Create Job Post button
    When  I get an alert telling me the job post was created successfully
    Then  I am on the landing page

    Examples:
    |jobId | title | description | companyId | companyName |
    | 1    | title | description | -1        | companyName |

  Scenario Outline: As a company, I should not be able to post jobs with input too long.
    Given I am on the landing page
    When  I click on the Post Job button
    When  I input the <jobId>
    When  I input the <title> too long
    When  I click the job type drop down
    When  I choose the type
    When  I input the <description>
    When  I input the <companyId>
    When  I click the location drop down
    When  I choose the location
    When  I input the <companyName>
    When  I click the Create Job Post button
    When  I get an alert telling me the job post was created successfully
    Then  I am on the landing page

    Examples:
      |jobId | title | description | companyId | companyName |
      | 1    | thisiswaytoolongtopassthroughtheteststhatIneedTtoperform | description | -1 | companyName |

  Scenario Outline: As a company, I should not be able to post jobs with input too long.
    Given I am on the landing page
    When  I click on the Post Job button
    When  I input the <jobId>
    When  I input the <title>
    When  I click the job type drop down
    When  I choose the type
    When  I input the <description> too long
    When  I input the <companyId>
    When  I click the location drop down
    When  I choose the location
    When  I input the <companyName>
    When  I click the Create Job Post button
    When  I get an alert telling me the job post was created successfully
    Then  I am on the landing page

    Examples:
      |jobId | title | description | companyId | companyName |
      | 1 | title | thisiswaytoolongtopassthroughtheteststhatIneedTtoperformthisiswaytoolongtopassthroughtheteststhatIneedTtoperformthisiswaytoolongtopassthroughtheteststhatIneedTtoperformthisiswaytoolongtopassthroughtheteststhatIneedTtoperformthisiswaytoolongtopassthroughtheteststhatIneedTtoperformthisiswaytoolongtopassthroughtheteststhatIneedTtoperform | -1 | companyName |

  Scenario Outline: As a company, I should not be able to post jobs with input too long.
    Given I am on the landing page
    When  I click on the Post Job button
    When  I input the <jobId>
    When  I input the <title>
    When  I click the job type drop down
    When  I choose the type
    When  I input the <description>
    When  I input the <companyId>
    When  I click the location drop down
    When  I choose the location
    When  I input the <companyName> too long
    When  I click the Create Job Post button
    When  I get an alert telling me the job post was created successfully
    Then  I am on the landing page

    Examples:
      |jobId | title | description | companyId | companyName |
      | 1 | title | description | -1 | thisiswaytoolongtopassthroughtheteststhatIneedTtoperform |
