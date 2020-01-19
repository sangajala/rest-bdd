Feature: Apis for city movies


#  @cm
  Scenario: Register new user - Happy path

    Given the apis are up and running for "http://cmapi.bananaappscenter.com/"
    When a user posts a request to "api/User/Register"
    And flowing body parameters
      | User_Type | 3                                  |
      | Email     | sriram.ajngajala24h55789@gmail.com |
      | Password  | Krishh12                           |
    Then the response code should be 200
    And I should see json response with pairs
      | Message    | User Details Registred Successfully and activaton link send to your registred mailid |
      | StatusCode | 200                                                                                  |
      | isError    | false                                                                                |
      | isSuccess  | true                                                                                 |

  @cm
  Scenario: Register new user - Happy path


    Given the apis are up and running for "http://cmapi.bananaappscenter.com/"
    When a user posts a request to "api/User/Register"
    And flowing body parameters
      | User_Type | 3                                  |
      | Email     | sriram.ajngajala24h55789@gmail.com |
      | Password  | Krishh12                           |
    Then the response code should be 502
    And I should see json response with pairs
      | Message    | Email alreday Registred |
      | StatusCode | 500                                                                                  |
      | isError    | false                                                                                |
      | isSuccess  | true                                                                                 |