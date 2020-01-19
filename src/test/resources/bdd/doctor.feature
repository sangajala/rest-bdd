Feature: doctor apis responds as expected

  @smoke
  Scenario Outline: Check that register

    Given I take the "/AppRegistration.ashx"
    When I register with following details
      | HospitalCode         | <HospitalCode>         |
      | StudyID              | <StudyID>              |
      | RegistrationCode     | <RegistrationCode>     |
      | RegistrationPassword | <RegistrationPassword> |
      | UserPassword         | <UserPassword>         |
    Then the response code should be 200
    And I should see json response with pairs
      | Success    | <Success>    |
      | StatusText | <StatusText> |
    And I should see json response keys
      | Token | Random |


    Examples:
      | Test case                      | HospitalCode | StudyID | RegistrationCode | RegistrationPassword | UserPassword | Success | StatusText              |
      | Client data                    | CHST         | CHST3   | CF78D6FA         | greenmountain%1      | anything     | True    | Registration successful |
      | Number as UserPassword         | CHST         | CHST3   | CF78D6FA         | greenmountain%1      | 124252       | True    | Registration successful |
      | Number as RegistrationPassword | CHST         | CHST3   | CF78D6FA         | greenmountain%1      | anything     | True    | Registration successful |
      | Alpha Numeric                  | CHST         | CHST3   | CF78D6FA         | greenmountain%1      | 1212HKSDHK   | True    | Registration successful |
      | special chars                  | CHST         | CHST3   | CF78D6FA         | greenmountain%1      | &=           | True    | Registration successful |
      | Modified Client data           | CHST         | CHST3   | CF78D6FA         | greenmountain%1      | anything     | True    | Registration successful |

  @smoke_1
  Scenario Outline: Check AppRegistration with negative scenarios

    Given I take the "/AppRegistration.ashx"
    When I register with following details
      | HospitalCode         | <HospitalCode>         |
      | StudyID              | <StudyID>              |
      | RegistrationCode     | <RegistrationCode>     |
      | RegistrationPassword | <RegistrationPassword> |
      | UserPassword         | <UserPassword>         |
    Then the response code should be 200
    And I should see json response with pairs
      | Success    | <Success>    |
      | StatusText | <StatusText> |
    And I should see json response keys
      | Token | Random |


    Examples:
      | Test case                      | HospitalCode | StudyID | RegistrationCode | RegistrationPassword | UserPassword | Success | StatusText          |
#      | Client data                    | WRONG        | CHST3   | CF78D6FA         | greenmountain%1      | anything     | False   | Unable to register. |
      | Number as UserPassword         | chst         | CHST3   | CF78D6FA         | 1221221              | 124252       | False   | Registration failed |
      | Number as RegistrationPassword | CHST         | wrong   | CF78D6FA         | greenmountain%1      | anything     | False   | Registration failed |
      | Alpha Numeric                  | CHST         | CHST3   | wrong            | greenmountain%1      | 1212HKSDHK   | False   | Registration failed |
      | special chars                  | CHST         | CHST3   | CF78D6FA         | wrong                | &=           | False   | Registration failed |
#      | special chars                  |              |         |                  | wrong                | &=           | False   | Unable to register. |

  @smoke
  Scenario Outline: Check that AppHealthScore apis respond as expected

    Given I take the "/AppRegistration.ashx"
    When I register with following details
      | HospitalCode         | CHST            |
      | StudyID              | CHST3           |
      | RegistrationCode     | CF78D6FA        |
      | RegistrationPassword | greenmountain%1 |
      | UserPassword         | anything        |
    Then the response code should be 200
    And I should see json response with pairs
      | Success    | <Success>             |
      | StatusText | <StatusText_register> |
    And I should see json response keys
      | Token | Random |
    Given I take the "/AppHealthScore.ashx"
    When I register with following details
      | HospitalCode | CHST           |
      | HealthScore  | <HealthScore>  |
      | Token        | <Token>        |
      | UserPassword | <UserPassword> |
    Then the response code should be 200
    And I should see json response with pairs
      | Success | <Success> |
    And I should see json response with pairs contains
      | StatusText | <StatusText_health> |

    Examples:
      | Test case            | HealthScore | Token                                | UserPassword | Success | StatusText_health              | StatusText_register     |
      | Modified Client data | 43          | 208e3a35-a092-4e24-ac1a-fc080299eb93 | anything     | True    | Health score of 43 notified at | Registration successful |

  @smoke
  Scenario Outline: Check HIT6

    Given I take the "/AppRegistration.ashx"
    When I register with following details
      | HospitalCode         | CHST            |
      | StudyID              | CHST3           |
      | RegistrationCode     | CF78D6FA        |
      | RegistrationPassword | greenmountain%1 |
      | UserPassword         | anything        |
    Then the response code should be 200
    And I should see json response with pairs
      | Success    | <Success>             |
      | StatusText | <StatusText_register> |
    And I should see json response keys
      | Token | Random |
    Given I take the "/AppHIT6.ashx"
    When I register with following details
      | HospitalCode | CHST           |
      | Severity     | <Severity>     |
      | Activities   | <Activities>   |
      | LieDown      | <LieDown>      |
      | Tiredness    | <Tiredness>    |
      | FedUp        | <FedUp>        |
      | Limit        | <Limit>        |
      | Token        | <Token>        |
      | UserPassword | <UserPassword> |
    Then the response code should be 200
    And I should see json response with pairs
      | Success | <Success> |
    And I should see json response with pairs contains
      | StatusText | <StatusText> |

    Examples:
      | Test case            | Severity | Activities | LieDown | Tiredness | FedUp | Limit | Token                                | UserPassword | Success | StatusText        | StatusText_register     |
      | Modified Client data | 3        | 0          | 1       | 2         | 3     | 5     | 208e3a35-a092-4e24-ac1a-fc080299eb93 | anything     | True    | HIT-6 notified on | Registration successful |

  @smoke
  Scenario Outline: Check ValidatePassword

    Given I take the "/AppRegistration.ashx"
    When I register with following details
      | HospitalCode         | CHST            |
      | StudyID              | CHST3           |
      | RegistrationCode     | CF78D6FA        |
      | RegistrationPassword | greenmountain%1 |
      | UserPassword         | anything        |
    Then the response code should be 200
    And I should see json response with pairs
      | Success    | <Success>             |
      | StatusText | <StatusText_register> |
    And I should see json response keys
      | Token | Random |
    Given I take the "/AppValidatePassword.ashx"
    When I register with following details
      | HospitalCode | CHST           |
      | Token        | <Token>        |
      | UserPassword | <UserPassword> |
    Then the response code should be 200
    And I should see json response with pairs
      | Success | <Success> |
#    And I should see json response with pairs contains
#      | StatusText | <StatusText> |

    Examples:
      | Test case            | Severity | Activities | LieDown | Tiredness | FedUp | Limit | Token                                | UserPassword | Success | StatusText       | StatusText_register     |
      | Modified Client data | 3        | 0          | 1       | 2         | 3     | 5     | 208e3a35-a092-4e24-ac1a-fc080299eb93 | anything     | True    | HIT6 notified at | Registration successful |

  @smoke
  Scenario Outline: Check Deregister

    Given I take the "/AppRegistration.ashx"
    When I register with following details
      | HospitalCode         | CHST            |
      | StudyID              | CHST3           |
      | RegistrationCode     | CF78D6FA        |
      | RegistrationPassword | greenmountain%1 |
      | UserPassword         | anything        |
    Then the response code should be 200
    And I should see json response with pairs
      | Success    | <Success>             |
      | StatusText | <StatusText_register> |
    And I should see json response keys
      | Token | Random |
    Given I take the "/AppDeregister.ashx"
    When I register with following details
      | HospitalCode | CHST           |
      | Token        | <Token>        |
      | UserPassword | <UserPassword> |
    Then the response code should be 200
    And I should see json response with pairs
      | Success | <Success> |
    And I should see json response with pairs contains
      | StatusText | <StatusText> |

    Examples:
      | Test case            | Severity | Activities | LieDown | Tiredness | FedUp | Limit | Token                                | UserPassword | Success | StatusText               | StatusText_register     |
      | Modified Client data | 3        | 0          | 1       | 2         | 3     | 5     | 208e3a35-a092-4e24-ac1a-fc080299eb93 | anything     | True    | Deregistration succeeded | Registration successful |

  @smoke
  Scenario Outline: Check Reminders

    Given I take the "/AppRegistration.ashx"
    When I register with following details
      | HospitalCode         | CHST            |
      | StudyID              | CHST3           |
      | RegistrationCode     | CF78D6FA        |
      | RegistrationPassword | greenmountain%1 |
      | UserPassword         | anything        |
    Then the response code should be 200
    And I should see json response with pairs
      | Success    | <Success>             |
      | StatusText | <StatusText_register> |
    And I should see json response keys
      | Token | Random |
    Given I take the "/AppReminders.ashx"
    When I register with following details
      | HospitalCode | CHST           |
      | Token        | <Token>        |
      | UserPassword | <UserPassword> |
    Then the response code should be 200
    And I should see json response with pairs
      | Success                    | <Success>                    |
      | QOLReminderFrequencyDays   | <QOLReminderFrequencyDays>   |
      | HIT6ReminderFrequencyDays  | <HIT6ReminderFrequencyDays>  |
      | VisitReminderFrequencyDays | <VisitReminderFrequencyDays> |
    And I should see json response with pairs contains
      | Success | <Success> |

    Examples:
      | Test case            | QOLReminderFrequencyDays | HIT6ReminderFrequencyDays | VisitReminderFrequencyDays | Tiredness                            | FedUp    | Limit | Token                 | UserPassword | Success | StatusText_register     |
      | Modified Client data | 31                       | 31                        | 31                         | 208e3a35-a092-4e24-ac1a-fc080299eb93 | anything | True  | Deregistration failed | anything     | True    | Registration successful |

  @smoke
  Scenario Outline: Check Error report

    Given I take the "/AppRegistration.ashx"
    When I register with following details
      | HospitalCode         | CHST            |
      | StudyID              | CHST3           |
      | RegistrationCode     | CF78D6FA        |
      | RegistrationPassword | greenmountain%1 |
      | UserPassword         | anything        |
    Then the response code should be 200
    And I should see json response with pairs
      | Success    | <Success>             |
      | StatusText | <StatusText_register> |
    And I should see json response keys
      | Token | Random |
    Given I take the "/AppReportError.ashx"
    When I register with following details
      | HospitalCode | CHST           |
      | Token        | <Token>        |
      | UserPassword | <UserPassword> |
      | StackTrace   | <StackTrace>   |
    Then the response code should be 200
#    And I should see json response with pairs
#      | Success                    | <Success>                    |
#      | QOLReminderFrequencyDays   | <QOLReminderFrequencyDays>   |
#      | HIT6ReminderFrequencyDays  | <HIT6ReminderFrequencyDays>  |
#      | VisitReminderFrequencyDays | <VisitReminderFrequencyDays> |
    And I should see json response with pairs contains
      | Success | <StatusText> |

    Examples:
      | Test case            | StatusText | UserPassword | Success | StatusText_register     | StackTrace                                                                                                                                                                                                                                                                                                                                                            |
      | Modified Client data | True       | anything     | True    | Registration successful | at Microsoft.VisualBasic.ErrObject.Raise(Int32 Number, Object Source, Object Description, Object HelpFile, Object HelpContext) at HEIDIServices.AppReportError.AppResponse..ctor(String pStackTrace, String pToken, String pUserPassword) in C:\Users\Sheridan Lloyd\Documents\Visual Studio 2013\Projects\HEIDIServices\HEIDIServices\AppReportError.ashx.vb:line 20 |


#  @afterSales@smoke@MYB-164
#  Scenario Outline: Check that after sales apis respond as expected for any PNR
#
#    When the PNR <PNR> and last name <lastName>
#    And I hit the after sales apis
#    Then the response code should be 200
#    And the contact details should match as email <email> firstName <firstName> lastName <contact_lastName> phoneNumber <phoneNumber>
#
#    Examples:
#      | PNR    | lastName | email                                              | firstName     | contact_lastName     | phoneNumber  |
#      | QWMTCI | test     | TEST@TEST.COM                                      | DSFSDFSD      | DSDFFD               | 447455252562 |
#      | QFQWAW | angajala | abcdefghijklmnopqrstuvwxyzabcdefghijklmn@gmail.com | ABCDEFGHIJKLM | ABCDEFGHIJKLMNOPQRST | 441234567890 |
#      | QEPBQV | angajala | SRIRAM.ANGAJALA@GMAIL.COM                          | SRIRAMANGAJAL | ANGAJALALAALALALJALA | 917453289655 |





