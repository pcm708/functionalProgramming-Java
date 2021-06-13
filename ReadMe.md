## Java_DBOperationsUsingLambdaAndStreams

This repository contains code on performing basic SQL commands on a web table using [Java 8 Lambda and Streams](https://www.w3schools.com/java/java_lambda.asp)

**_The idea behind is to fetch the data from a webtable and perform operations (similar to hitting SQL Queries on a database) using Java Streams API. 
This can significantly help in reducing the LOC. It will also increase the efficiency_**

#### Webtable link: https://datatables.net/extensions/select/examples/initialisation/checkbox.html
##### Attaching the SS of the table below:
 
![image](https://user-images.githubusercontent.com/52307892/121800165-d7ccbf80-cc4d-11eb-93e4-1f2551b39936.png)
##### Some of the examples include: 
##### 1. Get the data from the webtable
##### 2. Get the name of the employee whose age is less than 22
##### 3. Get the 3rd highest salary

### Pre-Requisites:
1. Java 8 Stream APIs
2. TestNG or Junit
3. Selenium(Optional)

### Requirements
1. Java version 8 or higher
2. Maven version compatible to JRE 1.8
3. IDE of your choice: IntelliJ, Eclipse, Netbeans

### Dependencies Used:
1. Selenium WebDriver
2. TestNG
3. WebDriverManager (bonigarcia)

### Packages & Classes: 
The code is self explanatory and contains the following packages:
1. src/main/java object
    Contains an Object class to hold the data record,here (an Employee)
2. src/test/java dbOperationsOnWebtable
    Contains two test classes: 
    1. Parent.java : Base Class for invoking selenium webdriver and performing the prerequisites and later terminating the browser session.
    2. WebTableOperations.java : Test Class which extends Parent and contains only tests that relate to some database operations

### WorkFlow 
(If not from Selenium background, refer to point 6)
1. Code execution begins with running the class WebTableOperations.java with the help of TestNG
2. As being a superclass of WebTableOperations.java and following the TestNG annotation order,
   Parent.java class launchs the webdriver instance([headless](https://www.toolsqa.com/selenium-webdriver/selenium-headless-browser-testing/)) and selects the entry dropdown to be 100 entries per page.(This is done to display the whole table data in a single page, #NoPagination)
3. The WebTableOperations.java contains @Test [annotations](https://www.javatpoint.com/testng-annotations) which in turn generates the results.
4. @AfterTest under Parent class terminates the driver session.
5. To run the code, run any of the @Test annotation from WebTableOperations.java and the results will be displayed right in the console window.
6. This project contains a table to operate upon. The data values are stored in an ArrayList Object(say Employee). You can go through the code written in the @Test annotation of the class WebTableOperations.java, continue further with step 4.

### NOTE:
1. This is just a boilerplate code to help you get familiar with the best possible use case of Lambda and Streams.
2. The code complexity might stretch from Big Omega(Ω) to Big(O). [See here](https://www.geeksforgeeks.org/difference-between-big-oh-big-omega-and-big-theta/)
3. TestNG [annotations](https://www.javatpoint.com/testng-annotations) are used just for running the individual tests rather than performing any assertions,
   Verification can be done manually after getting the results.
4. Queries? Feel free to discuss in the Issues section: https://github.com/pcm708/Java_DBOperationsUsingLambdaAndStreams/issues
