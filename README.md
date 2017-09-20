## BookSearch
A Java implementation of BookSearch, a tool that can find all the books in a library that contain a given series of words.  

Each query has a performance of O(1) as the design behind the scene is a hashtable. A HashMap of all words from all books with HashSets of associated books under the "key" words.

***

### Use requirements:
1. Java 7+
2. Gradle

***

### Use:
* Tests:
  * Option 1: Within an IDE, test as JUnit tests.
  * *PENDING* Option 2: With Gradle installed, run the build with:
  ```Gradle clean test```  

***

By Austin Kincaid
