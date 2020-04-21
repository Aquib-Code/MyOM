Feature: User is able to login and submit a call me back

Scenario Outline: Verify product details for mock user
		Given Old Mutual app is launched in mobile device "<TestDataFile>" & Data Set "<DataSetSheet>" & caseId "<TestCaseId>"
		When user log in to OM app
		And verify Available balance of user
		And verify Total investments of user
		And verify Total cover of user
		And verify Bank accounts of user
		And verify Product details of user
		And user logs out of OM app
						
		Examples: 
        | TestDataFile | DataSetSheet | TestCaseId |
        | Mobile_TestData | OldMutualMain | TC_01 |