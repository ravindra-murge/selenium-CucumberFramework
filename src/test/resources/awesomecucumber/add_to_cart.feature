Feature: Add to cart

  Scenario: Add one quantity from store
    Given User is on the store page
    When User add a "Blue Shoes" to the cart
    Then User should see 1 "Blue Shoes" in the cart

  Scenario Outline: Add one quantity from store - examples
    Given User is on the store page
    When User add a <product_name> to the cart
    Then User should see 1 <product_name> in the cart
    Examples:
      | product_name |
      | Blue Shoes  |
      | Anchor Bracelet |