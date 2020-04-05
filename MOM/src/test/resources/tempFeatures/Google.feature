Feature: OmkAdvisorFormSubmission

  @TC_01
  Scenario: Advisor creates application for customer between twenty to thirty years of age
    Given omkonnect is launched using Test Data File "<TestDataFile>" & Data Set "<DataSetSheet>"
    Then set username and password
    Then click on login
    Then click on plans and sales
    Then search by client ID number
    Then Enter client details
    Then Enter Affordability Details
    Then Enter Goals and Plans between twenty to thirty years
    Then Enter Benefit Selections
    Then Enter Benefit Details
    Then Enter Beneficiaries Details
    Then Enter Employment Details
    Then Enter Payment Details
    Then Enter Supporting forms Details
    Then Review Summary
    Then click on logout

  @TC_02
  Scenario: Advisor creates application for customer between thirty to fourty years of age
    Given omkonnect is launched using Test Data File "<TestDataFile>" & Data Set "<DataSetSheet>"
    Then set username and password
    Then click on login
    Then click on plans and sales
    Then search by client ID number
    Then Enter client details
    Then Enter Affordability Details
    Then Enter Goals and Plans between and thirty to forty years
    Then Enter Benefit Selections
    Then Enter Benefit Details
    Then Enter Beneficiaries Details
    Then Enter Employment Details
    Then Enter Payment Details
    Then Enter Supporting forms Details
    Then Review Summary
    Then click on logout

  @TC_03
  Scenario: Advisor creates application for customer between fourty to fifty years of age
    Given omkonnect is launched using Test Data File "<TestDataFile>" & Data Set "<DataSetSheet>"
    Then set username and password
    Then click on login
    Then click on plans and sales
    Then search by client ID number
    Then Enter client details
    Then Enter Affordability Details
    Then Enter Goals and Plans between forty to fifty years
    Then Enter Benefit Selections
    Then Enter Benefit Details
    Then Enter Beneficiaries Details
    Then Enter Employment Details
    Then Enter Payment Details
    Then Enter Supporting forms Details
    Then Review Summary
    Then click on logout

  @TC_04
  Scenario: Validate RSAIDNumber field in Client Details
    Given omkonnect is launched using Test Data File "<TestDataFile>" & Data Set "<DataSetSheet>"
    Then set username and password
    Then click on login
    Then click on plans and sales
    Then search by client ID number
    Then Validate ID Number in Client Details

  @TC_05
  Scenario: Validate lifestage based on DOB
    Given omkonnect is launched using Test Data File "<TestDataFile>" & Data Set "<DataSetSheet>"
    Then set username and password
    Then click on login
    Then click on plans and sales
    Then search by client ID number
    Then Enter client details
    Then Enter Affordability Details
    Then Validate lifestage in Goals and Plans
