Feature: User is able to login and submit a call me back

  Scenario Outline: Login, Logout and feedback
    Given Old Mutual app is launched in mobile device "<TestDataFile>" & Data Set "<DataSetSheet>" & caseId "<TestCaseId>"
    When user log in to OM app
    And user logs out of OM app
    And user gives feedback to OM app

    Examples: 
      | TestDataFile   | DataSetSheet  |
      | MobileTestData | OldMutualMain |
