Feature: Adding new job positive

  @AddJob
  Scenario Outline: Adding job for user <Email> and password <Password>

    Given User is signing into App with email: <Email> and password: <Password>
    When User is on dashboard page and wants to add a job
    Then Creates  customer with FirstName: <FirstName> , LastName: <LastName>
    And Add Item with Item Name: <ItemName> and Price: <ItemPrice> and schedule
    And Fill private note with text: <PrivateNote>
    Then User saves job and checks Activity Feed


    Examples:
      | Email                | Password  | FirstName | LastName | ItemName | ItemPrice | PrivateNote |
      | pawel.ozog@yahoo.com | #N!u7kCeY | John      | Doe      | TestItem | $12.00    | CheckNote   |