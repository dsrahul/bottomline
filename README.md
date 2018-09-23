# Coding Challenge

# Problem
For a supplied total amount and number of payments, please calculate the recurring regular payment amount and if required a different last amount that includes any remainder.

## Input 
Command line parameters in the order <Total Amount> <No of payments>

## Output
Print to console. Always print regular amount, only print last amount if it is different

# Solution
Based on separation of concerns there are two services required.
* calculate the payment schedule (PaymentScheduleService / SchedulePrintService)
* print the schedule (PrintService)

I have used a factory method to inject the print service into the schedule print service. Based on the assumption that there would be a requirement for other types of payment schedule printing (e.g. full schedule printing). 

I started off by using double for calculating the schedule however the precision calculation got too messy and I had to switch to BigDecimal

# About the Application
* Developed using JDK 1.8
* Maven for managing the build
* App.java has got a main method and is the window to running the command line program. 

## How to Build and Run

* Use git to clone the repository for a local copy using the command
```bash
    git clone
```
* The Project can be built and tests run by opening a terminal window and using the command at the root of the project ```mvn clean install```
* The project should successfully and a jar file ``cc-1.0-SNAPSHOT.jar`` will be created within the ``target`` folder
* Once successfully built, the application can be run with either of the following command at the root of the project:
```bash
    java -cp target/cc-1.0-SNAPSHOT.jar com.bottomline.App 10.00 3.00
```
or

```bash
    mvn -q exec:java -Dexec.mainClass=com.bottomline.App -Dexec.args="10.00 3.00"
```
* The expected result will be output to the console.
* There should be no errors in the stdout


## UNIT & Integration Tests.

Junit 4 included as a maven dependency has been used for testing.
There is a single Integration test ```AppIntegrationTest.java```and can be run using the command at the root of the project
```bash
    mvn clean test
```