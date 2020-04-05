Feature: Google Search test

  @TC_01
  @Counter
  Scenario Outline: Search Old Mutual on google using Test Data File "<TestDataFile>" & Data Set "<DataSetSheet>"
    Given google is launched "<TestDataFile>" & "<DataSetSheet>"
    Then set text in search box
    Then click on google search
    Then verify title

    Examples: 
        | TestDataFile | DataSet |
        | TestData | Google |