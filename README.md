# spark-project
A spark research project to determine fitness for Oauth integration with lightweight REST services.
This is an HTTP sample local server configuration of the normal HTTPS OAUTH2 layered security pattern.
The Authentication server is a temporarily hosted OAUTH 2 compliant server on the EU auth0.com servers to achieve the desired functions of a basic OAUTH pattern.


## Getting started

### Prerequisites
* Java 8 - Java SE Runtime Environment
* A Working GIT Install
* An Internet Connection (Required for OAuth Intergration-testing)

## Two ways to run the project

### A. Locally building and running the project
Clone/pull this project from Github and build it locally in your IDE / development environment.
Once you have the the project to your local environment, you will need to update the Maven dependencies that are necessary to execute this project.
You can do this by running the appropriate maven commands
```
Maven clean
```

```
Maven install
```

Important: Ensure that your build environment is configured to run on Java 8 for the application to build and run correctly.

#### You can now run the class named "RestLayer.java" and if required, point to the method named "main".

### B. Running the precompiled Jar
To run the precompiled executable Java Jar
#### Navigate to the root folder of the cloned GIT project (you should find the file named "pre-compiled-test.jar".

Execute the jar by running the following command in your terminal/console/command prompt:
```
java -jar pre-compiled-test.jar
```
Note: Use Ctrl+c to quit OR escape OR exit the terminal window. This is not a headless instance and will stop when you exit the process.

#### Comment: Either server should end up running the server's only active endpoint on the path http://localhost:8123/chartData/

## Connecting to, and interacting with, the server

## Step 1 - OAuth authentication 
Opening the command prompt (Windows) or terminal (OS X) or console (Linux) and execute the CURL commands
```
curl --request POST \
  --url https://untrace.eu.auth0.com/oauth/token \
  --header 'content-type: application/json' \
  --data '{"client_id":"T15tT9A7HrfEz7U1WlcEIcAy6fWyjZts","client_secret":"UP_8-b9Prwo9U8ebnBFVrt3kdBRuh5bs0cb-1AWSRMZtHpizOUB8f3gz9vATtZJn","audience":"https://untrace.eu.auth0.com/api/v2/","grant_type":"client_credentials"}'
```

## Step 2 - Submit the generated response key to your locally hosted server with a payload
```
  curl --request POST \
    --url http://localhost:8123/chartData/ \
    --header "Content-Type: application/json" \
    --data '{"day":1,"month":12,"year":98}' \
    --header 'authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik9UZzBSVGMwUVRaRU5EUkRRemczTkRnMFJUSXhPVU15TWpkRVFqQkJNak0wTWpjMk5VTkRSUSJ9.eyJpc3MiOiJodHRwczovL3VudHJhY2UuZXUuYXV0aDAuY29tLyIsInN1YiI6IlQxNXRUOUE3SHJmRXo3VTFXbGNFSWNBeTZmV3lqWnRzQGNsaWVudHMiLCJhdWQiOiJodHRwczovL3VudHJhY2UuZXUuYXV0aDAuY29tL2FwaS92Mi8iLCJpYXQiOjE1MjY0ODU3MzEsImV4cCI6MTUyNjU3MjEzMSwiYXpwIjoiVDE1dFQ5QTdIcmZFejdVMVdsY0VJY0F5NmZXeWpadHMiLCJndHkiOiJjbGllbnQtY3JlZGVudGlhbHMifQ.bwb51d1NDS8MhuC2f9TfqJhMnmkiZp2ST_96NgJM6ZqMyKyvLajpqMAZaUwgmsFk_Oz5m_y4CdNT748IP_jsMo1f952O_y4rwmv36XuY3NukExmT7rVj54u1xi2coYt3e6jl5IXHJSd8Sl2Qu1jqsRuxcmMWZaTxinuyhcvAvZN7VNln6-tj43C1AnHnw2InsPyE_tUOfrEhlF-REdj7mqgCsuboJOxFchYEJpe2gJsdyZtZRb7tgaewNesAW2Dd1cnMPA2EpJDaI-W9gWh0sjx1lPUQB86eHyZ8t9MfbsnOo38POX1HSsKucWdXCQfEXWrezyAHqxZHdm3V7hPuMw' 
```
Note: For testing, the second call can be submitted with an empty Authorization command to allow continuous testing. This is not a feature, but simply a gap in my knowledge of the Apache Ignite Spark header extraction of Authorization tokens.

## Input Samples
### JSON Input Samples to perform different functions (REST POST body)

* Where DD = day as an integer (eg. 1, 24)
* Where MM = day as an integer (eg. 3, 12)
* Where YY = day as an integer (eg. 98 for 1998 or 01 for 2001)

#### A - Extract the specific closing rate for a day (unsorted)
```
{"day":DD, "month":MM, "year":YY}
```

#### B - Extract the Daily closing rate for a month (unsorted)
```
{"month":MM, "year":YY}
```

#### C - Extract the Monthly closing rate for a year (unsorted)
```
{"year":YY}
```

#### D - Extract the Yearly closing rate for all time (unsorted)
```
{}
```
Notes: 
Missing dates of the datasets are not included in results, we assume that the average is for active periods only to extract a volume based average.
Decimal points are summed, divided and then rounded down to two decimal points.

## Output Samples
The output from this /chartData/ endpoint is to return a list of objects that contain the following two key parts of the data:
#### date ( String )
#### closingPrice ( Numeric with 2 decimal points )
```
{"datapoints":[{"date":"84-11-1","closingPrice":2.91}]}
```

### The JSON Response corrseponding to the above JSON Input patterns above
* A - "DD/MM/YY" 
* B - "DD/MM/YY"
* C - "MM/YY"
* D - "YY"

## Commentary
### OAUTH Integration
Considering the application will be running client-side, the assumption was to run a lightweight backend OAUTH 2.0 ready authentication pattern.
Looking at several options, the direct route would require developer accounts with the authorization companies with whom we wish to use (Dropbox, Facebook, Google, etc).
I investigated several OAUTH integration options such as:
* www.ory.sh - https://www.ory.sh/run-oauth2-server-open-source-api-security/
* Google Auth Services API - https://developers.google.com/identity/protocols/OAuth2
* https://oauth1.wp-api.org/ - HTTP friendly implementation


## Authors
Untrace
