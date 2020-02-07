Feature: Testing the functionality of google places
@AddPlace
Scenario: Testing add place api
#define the payload (Define http base url, headers, body and params **Spec builder)
Given Add place payload nimer arabic

#define the resource and http method (endpoint, GET,POST,PUT,DELETE) **bad usage
When User wants to call "AddPlaceAPI" with http method "POST"

#assertion on status code (AssertThat.statusCode,headers,contentType) 
Then Status code should be 200

#internal assertion on response body (Deep assertion on response body)
And "status" is response body should equal OK
And "scope" is response body should equal APP

Scenario Outline:
Given Add place payload <name> <language>
#define the resource and http method (endpoint, GET,POST,PUT,DELETE)
When User wants to call "AddPlaceAPI" with http method "POST"

#assertion on status code (AssertThat.statusCode,headers,contentType) 
Then Status code should be 200

#internal assertion on response body (Deep assertion on response body)
And "status" is response body should equal OK
And "scope" is response body should equal APP
And verify created name is <name> using getPlaceAPI

Examples:
|name|language|
|Rahaf|English|
#|Fayroz|Arabic|

@DeletePlace
Scenario: Delete created place
Given Delete created place payload
When User wants to call "deletePlaceAPI" with http method "DELETE"
Then Status code should be 200