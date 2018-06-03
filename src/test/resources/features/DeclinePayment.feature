Feature: Decline payment
  Decline payment for a booking up

  Scenario Outline: Booking up with invalid credit card
    Given I search for flights from <origin> to <destination> on 25/08/2018 for <adults> adults and <children> children
    And I select the first flight for <adults> adults and <children> children
    When I log in
    And I insert valid data for <adults> adults and <children> children
    And I try to pay for booking with card details <card number>, 10/2018 and <ccv code>
    And I insert billing address data
    And I accept terms and conditions
    Then I get payment declined message

    Examples:
    |adults|children|origin  |destination  |card number        |ccv code|
    |2     |1       |Dublin  |Madrid       |5555 5555 5555 5557   |265     |

