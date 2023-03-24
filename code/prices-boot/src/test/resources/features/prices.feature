Feature: Check price to be applied via API REST

  Background:
    Given a range of dates with some product prices and priority

  Scenario: Request for Test 1
    When user asks for the price of product id 35455 of brand id 1 at "2020-06-14T10:00:00"
    Then product id 35455, brand id 1, price list 1, price 35.50 and range dates are answered

  Scenario: Request for Test 2
    When user asks for the price of product id 35455 of brand id 1 at "2020-06-14T16:00:00"
    Then product id 35455, brand id 1, price list 2, price 25.45 and range dates are answered

  Scenario: Request for Test 3
    When user asks for the price of product id 35455 of brand id 1 at "2020-06-14T21:00:00"
    Then product id 35455, brand id 1, price list 1, price 35.50 and range dates are answered

  Scenario: Request for Test 4
    When user asks for the price of product id 35455 of brand id 1 at "2020-06-15T10:00:00"
    Then product id 35455, brand id 1, price list 3, price 30.50 and range dates are answered

  Scenario: Request for Test 5
    When user asks for the price of product id 35455 of brand id 1 at "2020-06-16T21:00:00"
    Then product id 35455, brand id 1, price list 4, price 38.95 and range dates are answered
