J. Haymaker
CIT 594 - HW #4: Facebook friend data, graphs, DFS & BFS
Due 03/31/2017

///////////CLASSES\\\\\\\\\\\\\\\\\\\\
Classes used for implementation:

1. Node.java - creates a node object with a value of the use/Users/josephhaymaker/Desktop/README_HW4.txtr number (stored as a string)

2. GraphCreator.java - reads and parses the .txt file

3. BFS.java - runs breadth first search on a provided graph representation

4. DFS.java - runs depth first search on a graph.

5. GraphRunner.java - takes in a .txt file and then prompts user to choose the appropriate choice


////////////DESIGN\\\\\\\\\\\\\\\\\\\\

1. Node.java - creates a node object with a value of the user number (stored as a string).
Since I knew this node would be involved with DFS and BFS I added other characteristics to
it that could easily be accessed, namely start (time), finish (time), color, and 
predecessor node.

2. GraphCreator.java - After trying unsuccessfully to store the data in various structures
I settled on 2 distinct hashmaps, 1 storing the name of the node as a key and value of a linked
list with all adjacent nodes, and the other a hashmap also storing the name of the node as 
a key and the node itself as the value. I thought this would be the easiest way to access 
nodes, given that hashmap.get() operation guarantees O(1) execution. I also wanted chose
to have the second hashmap of all nodes represented because you could hypothetically have a 
node that was represented as a neighbor/friend, but not as a user themself. Moreover, I
chose and adjacency list as opposed to matrix because it seemed logical that this would be a
sparse graph, as it was highly unlikely that many people would be friends with upwards of 
4000 people. Thus, an adjacency list made more sense and would hypothetically waste less space
in memory.

In this class the most significant challenge was correctly storing the data and not creating
duplicate nodes for the same values. After much trial and error I accounted for if the 
user node had been created or not, and also if each of their friend nodes had been created 
or not.

The testing section at the bottom prints out all of node 1's friends.


3. BFS.java - One of the more complicated aspects of this class was passing the list
of all nodes seen and adjacencies to the class to be able to act on that data. With respect
to the BFS algorithm, I was basing my implementation on an "Elementary Graph Algorithms"
chapter seen in CIT 596 (either Introduction to Algorithms by Cormen, Leiserson, Rivest 
and Stein (CLRS), or Introduction to Algorithms by Vazirani et al..). 

Line 61 was added to see all node distances, as the largest distance I saw was 2, which
I assume is incorrect. The main method at the bottom of the class tests the class for 
various files (lines 114-117). It seems there is an error with the predecessor node not 
being updated, which is causing the max distance to be recorded as 2.

PROBLEM RESOLVED -- nullpointer error and predecessor issue fixed at lines 55 & 56.

4. DFS.java - The algorithm implemented was similarly taken from one seen in a reading
from CIT 596 (CLRS I'd imagine). The tricky part of implementing this was the recursive
call. I also kept getting another nullpointer exception which was later resolved in line
82. Commented out code in lines 94-100 were replaced by lines 82-92, the only difference
being the way the linked list of adjacent nodes was iterated over. 

The main method at the bottom for testing can be done with any of the 4 graphs (lines 150
-153), and allows you to see the start and finish time of any of the nodes.

5. GraphRunner.java - I started by creating new instances of the GraphCreator, DFS and 
BFS classes, then used a switch list to prompt the user for the questions. 

QUESTIONS:

a. distance between 75 and 2190? Distance automatically implies BFS, so this question
runs BFS from the start point of 75, then gets the distance characteristic from the 
2190 node to answer the question.

b. is the graph connected and why? If the graph is connected and there is a path from
every node to every other node, then running DFS should uncovered/reach all nodes. This
can be checked by searching over all nodes for any single node that wasn't changed color
from its original white coloring. 

c. I used Random to generate a random int between 0 and 4030. As you can see the number of
frontiers needed does vary. This is due to the amount of adjacent nodes and their adjacent
nodes, etc., amounts and structures that differ from node to node.

d. The start and finish times do not change, though hypothetically I think they could
based on situations when you have multiple neighbors and the order in which they are visited
varies/is random. 

e. It appears that all nodes are of distance 3 or less from 1912 when 1912 is the start
node of BFS.

f. i. as mentioned since it would take exponential time BFS isn't feasible to search for
	cliques, though theoretically it makes sense. 
	
	ii. N/A
	
	iii. After a review of literature it seems the best possible running time for finding
	the max clique is polynomial. This can be achieved by the Bron-Kerbosch algorithm. 


////////////USER INSTRUCTIONS\\\\\\\\\\\
1. The user interface class is "graphRunner.java". To run the program, open the class,
run the project as a Java application. The user is then prompted to provide a valid .txt
file. Valid text files provided are: facebook_combined.txt (the full data set), 
facebook_combined2.txt (only data for the 1st 100 nodes), dag.txt (a simple DAG, values 0-
10), and simple_graph.txt (a representation of the graph used in class on 03/15). You will
then be prompted to make a selection from a list of 7 options. It's worth noting that
questions requiring multiple running of DFS or BFS are sometimes slow in printing out answers.