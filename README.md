
## Orders Scheduler Application
This is a Java implementation of an algorithm to assign orders to pickers in a store based on their picking times and the time they need to complete them.

How it works
The program takes two JSON files as input, orders.json and store.json. The orders.json file contains information about each order, such as the order ID, order value, and the time it takes to pick the order. The store.json file contains information about the store, such as the picking start and end times and the list of pickers.

The algorithm works as follows:

Parse the data from the input JSON files
Sort the orders by their picking times and completion times
Assign each order to a picker based on the picker's availability and the order's picking time
Print the assignments
The algorithm assigns orders to pickers, and assigns the next available picker to the order that takes the least amount of time to pick.
## Authors and acknowledgment
Vadzim Sudaranka
## Setup information
### Java

- At least [Java 17](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) is required
- Set **JAVA_HOME** environment variable pointing to the folder where java is installed

### Apache Maven
- Download [Apache Maven](https://maven.apache.org/download.cgi)
- Set **MAVEN_HOME** environment variable pointing to the folder where maven is installed


### Libraries Used
Json-simple:
- To parse JSON data;

Lombok: 
- To generate getters and setters

Junit-jupiter-api:
- for testing

## How to use
To use this program you have additional json files:orders,json,store,json added to project folder(ordersScheduler)
if you want to try other cases you can provide your own Json files by replacing the existing ones or,
using compiling path java -jar PATH_TO_JAR\ordersScheduler-1.0-SNAPSHOT.jar to execute jar and eding 
path to your json files in the end of the path.
### Attention
PATH_TO_JAR should be replaced with absolute or relative path to back-end part artefact.

