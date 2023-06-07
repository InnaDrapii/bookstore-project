Feature: Bookstore API

  Scenario: Add books to user
    Given create user with username 'testUser76' and password 'testPassword#76'
    And generate token for created user
    And get books with author 'Axel Rauschmayer'
    When add selected books to user
    Then response status code is '201'
    And response contains added books

  #logging