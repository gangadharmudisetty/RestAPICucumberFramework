Feature: Validating place API's
@Addplace
Scenario Outline: verify if place is being sucessfully added using AddPlaceAPI
Given Add place payload "<name>" "<language>" "<address>"
When user calls "addplaceAPI" with "post" http request  
Then the API call got sucess with status code 200
And "status" in response body is equal to "OK"
And "scope" in response body is equal to "APP"
And verify place_id created maps to "<name>" using "getplaceAPI"

Examples:
|name	   |language|address  |
#|gangaHouse|Telugu  |Vaikuntam|
|SivaHouse |Sanskrit|Kailasam |

 
@DeletePlace
Scenario: verify if delete place functionality is working
Given deletePlace payload
When user calls "deleteplaceAPI" with "post" http request  
Then the API call got sucess with status code 200
And "status" in response body is equal to "OK"
