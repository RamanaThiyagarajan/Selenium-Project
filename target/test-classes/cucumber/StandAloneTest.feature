@tag
Feature: Purchase the order for Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the Order
    Given Logged in with username <name> and password <password>
    When I add <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | name                 | password     | productName  |
      | vramana791@gmail.com | Ramana@2397  | ZARA COAT 3  |
     
