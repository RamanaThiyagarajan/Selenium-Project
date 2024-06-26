@tag
Feature: Error Validations
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Positive test of submitting the Order
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed 
    
    Examples: 
      | name                 | password     |
      | vramana791@gmail.com | Ramana@239   |
     
