Feature: navigates to admin page
  Background:
    Given user enters valid admin username and password
    And user clicks on login button
    And user navigates to admin page
    @HW
    Scenario: user adds job title from admin page
      And navigates to job title and clicks add button
      And user adds Job Title "AScrumMaster0071" and saves
      Then query the HRMS database for JobTitle
      Then verify job title from frontend and backend


