Feature: Adding employee and testing DataBase
  Background:
    And user is logged in with valid admin credentials
    When user clicks on PIM option
    And user clicks on Add employee button
  @DB
  Scenario: Add the employee and testing it from the backend
    When user enters first name "Lord Bacon" middle name "MF" and last name "Richard"
    And capture the employeeId
    And user clicks on save button option
    Then query the HRMS database
    And verify the data from frontend and backend


