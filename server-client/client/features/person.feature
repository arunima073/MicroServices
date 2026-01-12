Feature: Person Service

  Scenario: person service returns correct person details
    When I call the person service
    Then I get the person name "John Doe" and age 30

  Scenario: Updating a person
    When I call the person service to update the person to name "John Doe" and age 25
    Then I get the person name "John Doe" and age 25
