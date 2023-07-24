Feature: Brand Management

  Scenario: Create Brand
    When a POST request is made to /brands with the following brand details:
      | name    |
      | Brand A |
    Then the response status code should be 201
    And the response body should contain the created brand details
    
  Scenario: Get Brand by ID
    When a GET request is made to /brands/1
    Then the response status code should be 200
    And the response body should contain the brand details   
    
  Scenario: Update an existing brand
    When I update the brand with id 1
    Then the brand with id 1 should have the updated values
    
  Scenario: Delete an existing brand
    When I delete the brand with id 1
    Then the brand with id 1 should be deleted         