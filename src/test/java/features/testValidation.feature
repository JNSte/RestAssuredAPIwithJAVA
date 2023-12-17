Feature: Validation place API

@AddPlace@Regression
Scenario Outline: Verify whether new place added through POST method
	Given Add place payload with "<Address>" "<Accuracy>" "<Language>"
	When user calls "AddPlace" with "POST" http request
	Then API returns statuscode as 200
	And "status" in the response is "OK"
	And "scope" in the response is "APP"
	And verify "<Language>" entered for the new PlaceID same as received from "GetPlace"
	
Examples: 
	|Address   |Accuracy |Language |
	|Stockholm |52       |Swedish   |
#	|Oslo		|53      |Norwegian  |
#	|Helsinki |54    |Finnish   |

@DeletePlace@Regression
Scenario: Verify whether the place is deleted by Delete place API
	Given there is payload for Delete place API
	When user calls "DeletePlace" with "POST" http request
	Then API returns statuscode as 200
	And "status" in the response is "OK"