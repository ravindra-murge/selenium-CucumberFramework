Feature: Place an Order
  Scenario: Using default payment option
    Given User is a guest User
    And billing details are
      | firstname | lastname  | country | address_line1  | city  | state | zip | email |
      | demo  | user  | United States (US)  | 6300 spring Greek | Plano | Texas | 75024 | askomdch@gmail.com  |
    And User has a product in the cart
    And User on the Checkout page
    When User provide billing details

    And User place an order
    Then the order should be placed successfully
