Scenario Outline: Verify if aspect ratio selection option is displayed
Given the resource is avaialable for Preset on share & embed
And user is on Add Preset Page for Single Preset
And advanced option is set on
And user clicks on the lock button to unlock
Then user should  be presented with Aspect Ration dropdown on <brower>
Examples:
|browsers|
|IE|
|Safari|
|Firefox|
|Chrome|

Scenario Outline: Verify the helptip displayed when user hover over the icon
Given the resource is avaialable for Preset on share & embed
And user is on Add Preset Page for Single Preset
And advanced option is set on
And user clicks on the lock button to unlock
Then user should  be presented with the helptip "Click to select a specific Aspect Ratio that will be applied to your Output Dimensions. You will be able to adjust the Pre-Crop Dimensions according to the selection made in the dropdown menu." on <brower>
Examples:
|browsers|
|IE|
|Safari|
|Firefox|
|Chrome|

Scenario Outline: Verify that the options displayed for the Aspect ratio are as per the requirments
Given the resource is avaialable for Preset on share & embed
And user is on Add Preset Page for Single Preset
And advanced option is set on
And user clicks on the lock button to unlock
Then user should see the following <ratio> displayed
Examples:
|ratio|
|1:1|
|2:3|
|4:3|
|16:9|


Scenario Outline: Verify that the output dimension gets updated when user selects an aspect ratio from the list
Given the resource is avaialable for Preset on share & embed
And user is on Add Preset Page for Single Preset
And advanced option is set on
And user clicks on the lock button to unlock
And user selects on of the <ratio> from the dropdown
Examples:
|ratio|
|1:1|
|2:3|
|4:3|
|16:9|
Then output dimension should be updated based on the ratio selected matching the height entered in the custom dimension height field

Scenario Outline: Verify that the aspect ratio is maintained when user alters the custom dimension option
Given the resource is available for Preset on share & embed
And user is on Add Preset Page for Single Preset
And advanced option is set on
And user clicks on the lock button to unlock
And user selects on of the <ratio> from the dropdown
Examples:
|ratio|
|1:1|
|2:3|
|4:3|
|16:9|
Then selected aspect ratio should be maintained


Scenario Outline: Verify that the aspect ratio is maintained when user selects original to close match
Given the resource is available for Preset on share & embed
And user is on Add Preset Page for Single Preset
And advanced option is set on
And user clicks on the lock button to unlock
And user selects on of the <ratio> from the dropdown
Examples:
|ratio|
|1:1|
|2:3|
|4:3|
|16:9|
And user enables original to closest match
Then selected aspect ratio should be maintained







