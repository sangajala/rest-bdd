Feature: Registration


  Scenario Outline: Validations

    Given user is in login page
    And navigate to registration page
    And provide below details
      | Invite code   | <Invite code> |
      | Password      | <Password>    |
      | Surname       | <Surname>     |
      | Email Address | <Surname>     |
    And Submit the data
    Then he should see the "<Error Message>"


    Examples:
      | Test case                  | Invite code | Password       | Surname | Error Message       |
      | Verify Invalid invite code | abc         |                |         | Invalid invite code |
      | Verify invalid password    | b0dc8       | 121212         |         | Invalid Password    |
      | Verify invalid surname     | b0dc8       | Bananaapps123$ |         | Invalid Surename    |
