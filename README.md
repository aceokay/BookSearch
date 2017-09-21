## BookSearch
A Java implementation of BookSearch, a tool that can find all the books in a library that contain a given series of words.  

Each query has a performance of O(1) as the design behind the scene is a hashtable. A HashMap of all words from all books with HashSets of associated books under the "key" words.

***

### Use requirements:
1. Java 7+
2. Gradle _(Optional if only running tests. See below)_

***

### Use:
* Tests:
  * Option 1: Within an IDE, test as JUnit tests.
  * Option 2: Without Gradle installed, run the build with the Gradle Wrapper:
    * Unix: ```./gradlew clean test```
    * Windows: ```gradlew clean test```

***

By Austin Kincaid
