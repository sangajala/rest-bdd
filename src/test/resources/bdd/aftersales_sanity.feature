Feature: Check After sales services for sanity checks


  Scenario: Open the valid PNR last name and check its response

    Given passenger looks for his pnr with PNR "ABCDE" and last name "smith"
    Then he should get a response with response code 200