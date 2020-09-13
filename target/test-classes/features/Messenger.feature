@web
  Feature: Messenger page feature

    Background:
      Given I am on the messenger page

      @messenger
      Scenario: Verify invalid login from the messenger page
        When I enter deneme@gmail.com into username field on the messenger page
        And I enter test123456 into password field on the messenger page
        And I click login button on the messenger page
        Then I verify invalid login the message on the sign in page