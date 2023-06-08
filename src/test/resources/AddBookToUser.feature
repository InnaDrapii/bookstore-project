Feature: Bookstore API

  Scenario: Add books to user
    Given create user with username 'testUser1' and password 'testPassword#01'
    And generate token for created user
    And get books with author 'Axel Rauschmayer'
    When add selected books to user
    Then response status code is '201'
    And response contains added books
    When user opens 'https://demoqa.com/books' page
    And user goes to Login page
    And user logs in as created user
    And user goes to Profile page
    Then the list of user's books contains added books
