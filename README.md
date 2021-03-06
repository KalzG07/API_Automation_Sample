# API-Automation-Sample #
This README outlines the project and it's elements.

### What is this repository for? ###
This repository contains an API Automation sample that is written in JAVA.

### How do I get set up? ###

* Clone this repo to your local machine, and open the project in Intellij or an IDE of your choice.
* Wait for the dependencies and libraries to auto import before running.

### What is the expected result? ###
Which APIs are used in the sample:
2 POST APIs, namely:
1. Generate Session key -  which will generate a session key to be used as an auth token.
2. Generate Card Identities - Will generate card identifier from certain card details.

2 GET APIs, namely:
1. Get all countries data - which will return data related to all countries.
2. Get COVID Stats by region name - which will take a region name as a parameter and return the COVID stats for that region.

### Tools ###

* The project contains and utilises the following libraries:
1. TestNG 
2. GSON
3. Spring

### How to run the API Tests? ###
Each API test Class can be run from a test xml file under the resources bundle. For instance to run All the POST Request tests, simply run the 'Run_All_POST_Tests.xml' file.

### What is the expected result? ###
Each Test will check for the response code of 201 or 200.

### What is the expected result? ###
Once the tests are run an emailable result page is generate in HTML under the test-output package folder, and can be opened in a browser. Simply right click on the html file and click on 'Open in Browser'.
