J. Haymaker
CIT 594 - HW #2
Due 02/20/2017

\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//////TASK 1 – Doubly Linked Lists (15 points) and Black Box Testing (20 points):\\\\\\\
////////////////////////////////////////////////////////////////////////////////////////

<<Describe in your readme.txt file which tests you chose and why (in the context of Equivalence
Partitioning and Boundary Value Analysis).>>

******DoublyLinkedList.java:********

METHODS:

1. boolean add(E e) - Logic: if the head is null (empty linked list), then set the head equal to the new tail. Otherwise, set a dummy node equal to head and use it to iterate to the end of the linked list. Once you reach the last node, set the old tail next pointer to the newly created node’s previous pointer equal to the old tail. Return true if successful.

2. void add(int index, E element)- Logic: creates the new node with the parameter, sets currentNode equal to head node. Index parameter - 1 is set to integer targetIndex (due to zero indexing). targetIndex will be used to find the correct location in the linked list. While the currentIndex is not equal to the target index and while currentNode.next doesn’t equal null, advance the node by setting currentNode equal to currentNode.next and increase currentIndex by 1. Break out of this when one of the conditions is satisfied. Step 1: set previous node’s pointer to newNode. Step 2: Set newNode’s next pointer to currentNode. Step 3: set newNode’s previous pointer to currentNode’s previous. Step 4: Set currentNode’s previous to point to new node.

3. void clear() - Logic: if linked list isn’t already empty, advance head node by one, then set previous node to null. If you get to the end and are pointing to null, set head node to null.

4. boolean isEmpty() - Logic: if the head is equal to null return true, else return false.

5. E remove(int index) - Logic: use the same logic as in method #2: while indexTracker doesn’t equal targetIndex (parameter index - 1), increase the dummy head node and increase indexTracker value by 1. When you reach desired index set previous node’s next pointer to the current node’s next. Set the current node’s next node’s previous pointer equal to the current node’s previous node. Now all pointers to the current node have been removed, and we can set the currentNode to null.

6. boolean remove(Object o) - Logic: while the current node isn’t equal to the desired object and the current node isn’t pointing to null (isn’t the tail node), increase the currentNode and increase the index. We break out of this loop when either the currentNode is equal to the target value or we have reached the tail. If we have found the object call remove on its index and return true, otherwise return false.

7. int size() - Logic: if head is null return a size of 0, else increase head and increase size variable until you reach the tail node, then return the size.


*****DoublyLinkedListJUnitTester.java:*****

TESTS: given that there weren't many ways to partition values as input it made more sense
to test based on boundary values (primarily 0, 1, etc). 

I wanted to perform tests on all methods provided and so tested all the above methods.

Most tests consist of adding or removing a small value and asserting that the result would be true/false or a certain value.

\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
///////TASK 2 – Stacks (10 points) and White Box Testing (5 points)\\\\\\\\\\\\\\\
//////////////////////////////////////////////////////////////////////////////////

*****MyStack.java:*****

METHODS:

1. boolean empty() - Logic: call isEmpty() on doubly linked list used to implement stack and return its boolean value.2. E peek() - Logic: if linked list isEmpty() is true, return null. Else, get the tail of the linked list and return it.3. E pop() - Logic: if linked list isEmpty() is true, call getTail(), store the value, and call deleteLast(). Return the stored value
4. E push(E item) - Logic: call add(item) on linked list. 

*****MyStackTester.java:*****

Tested all methods until achieved 100% coverage per Eclemma

\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
////////TASK 3 – HTML Validator (20 points) and Testing (10 points)\\\\\\\\\\\\\\
/////////////////////////////////////////////////////////////////////////////////


*****HTMLValidator.java:*****

This class creates a doubly linked list for the opening tags and a stack for the closing tags. It also has a scanner and boolean instance variables. The constructor for the class takes in a filename (.html file) and initializes Scanner.

isValid() method- uses scanner to read in file. Then uses the Pattern/ Matcher objects to get all values within angle brackets (the html tags). While there are matches, while the match doesn’t contain brackets (we only want the tags within the brackets), if the match contains a backslash(/) add it to the closingTags stack, otherwise add it to the openingTag linked list. 

Logic: there should be a corresponding closing tag for every opening tag, so the linked list size should be the same as the stack size. 

Also, the values should match. This can be checked by iterating through the linked list, checking the value at the top of the stack, popping if off if they match, then advance a node and repeat the process. This works given the fact that comes first will always have a corresponding closing tag at the end, which is why using a stack makes sense in this case. This part of my code kept running and running, and thus I commented it out for testing purposes. 

*****HTMLValidatorTester.java:*****

Used 5 file: The first three are simple html examples from the internet (html1.html, html2.html, html3.html), while the fourth (html4.html) is the one provided in the assignment description, as is the last (html5_INVALID.html). 


