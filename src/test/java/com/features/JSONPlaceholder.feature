Feature: JSONPlaceholder rest api

Scenario: get list of posts
Given I set the API Endpoint
When I make a get request to "posts"
Then I receive a valid response code 200
