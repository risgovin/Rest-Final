Feature: Reqres rest API

  Background: 
    Given the user sets the endpoint to "https://reqres.in/api"

  Scenario Outline: get a list of objects
    When the user wants to get page <page>
    And the user makes a get request to "<location>"
    Then the response should have status code <code>
    And the response should contain "<field>" field as "<value>"

    Examples: 
      | page | location | code | field | value |
      |    2 | /users   |  200 | page  |     2 |
      |    1 | /unknown |  200 | page  |     1 |

  Scenario Outline: get specific object
    When the user has an id of <id>
    And the user makes a get request to "<location>"
    Then the response should have status code <code>
    And the response should contain "<field>" field as "<value>"

    Examples: 
      | id | location      | code | field           | value |
      |  2 | /users/{id}   |  200 | data.first_name | Janet |
      | 25 | /users/{id}   |  404 |                 |       |
      |  3 | /unknown/{id} |  200 | data.id         |     3 |
      | 25 | /unknown/{id} |  404 |                 |       |

  Scenario: create a new user
    When the user has the following data:
      | name | Rishabh |
      | job  | QA      |
    And the user makes a post request to "/users"
    Then the response should have status code 201
    And show the response

