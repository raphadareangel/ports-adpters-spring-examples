Feature: Get Price List by Date
  As a user
  I want to get the price list based on a specific date, product, and brand
  So that I can retrieve the applicable pricing information
    
  Scenario: Create a new brand, product and pricing and then get list of pricing based on inputs.
    Given a POST request is made to /brands with the following brand details:
      | name    |
      | Brand A |
    And a POST request is made to /products with the following product details:
      | name    |
      | Product A |  
    And the following pricing request:
      | startDate           | endDate             | priceList | priority | price | currency |productId  |brandId  |
      | 2022-01-01T00:00:00 | 2022-12-31T23:59:59 | List A    | 1        | 10.0  | USD      | 1         | 1       |
      | 2023-01-01T00:00:00 | 2023-12-31T23:59:59 | List B    | 2        | 15.0  | USD      | 2         | 1       |
      | 2023-01-01T00:00:00 | 2023-02-01T00:00:00 | List C    | 3        | 20.0  | USD      | 3         | 1       |  
    When I send a GET request to /pricing/list with the parameters:
      | applicationDate   | productId   | brandId |
      | 2023-01-15T00:00:00 | 3         | 1       |
    Then the response status code for pricing should be 200
    And the response body should contain the pricing details:
      | startDate           | endDate             | priceList | priority | price | currency | productId | brandId |
      | 2023-01-01T00:00:00 | 2023-02-01T00:00:00 | List C    | 3        | 20.0  | USD      | 3         | 1       |