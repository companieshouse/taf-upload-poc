#IN PROGRESS
Feature: Removing File

  Scenario: Successfully remove uploaded attachment
    Given a limited company is applying to be secure
    When I upload a PDF file
    And I remove the file by the cross button
    Then the following removed file message should be displayed