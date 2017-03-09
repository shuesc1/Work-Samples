Joseph Haymaker
CIT 594 - HW #3
Due 03/03/2017

********************
********************
03/10 re-submission:
-fixes faulty methods: isAncestor(), isDescendant(), height(), preorderTraversal(), postorderTraversal(), & isOnlyChild().
-implements all above methods recursively (save isOnlyChild()) in original FamTree.java class.
-adds FamTreeEC.java extra credit class
-fixes serious error in parseMembers() method-- now accounts for repeat parents and adds children to existing parent nodes (previously unaccounted for)
-eliminates method implementation of methods using ArrayList.size() & ArrayList.toString() due to innacurate reporting.
-adds further helper methods such as getNode().

********************
********************


////////////CLASSES\\\\\\\\\\\\\\\\\

*****Implementation classes*****

1. GenericNode - provides a generic node structure

2. FamTree -implements all methods required (12) and implements other helper methods

3. FamilyTreeSimulator - contains the main method and tests all methods

<<ADDED 03/10>>: 4. FamTreeEC - same as FamTree, but implements following methods differently: isAncestor(), isDescendant(), 
				displayStatistics() / height() / size(), preorderTraversal(), postorderTraversal().

<<ADDED 03/10>>: 5. FamilyTreeSimulatorEC - same as FamilyTreeSimulator, now with FamTreeEC object.

******Additional files**********

1. 594_HW3.jpg - a visual representation of the main family tree used

2. family_tree.txt - the text file that establishes family relationships; the root node/
					patriarchal figure is on the first line; the format "person1 , person2"
					denotes that person1 is the parent of person2.

*******General testing classes(not needed for project):*********

1. GeneralTesting - tests parsing of .txt file to create children and parent nodes

2. PaternityTest - tests the "isParent" method; constructs a simple tree of 
					"Adam"->"Sara"->"Abraham" 
 
3. Tree - an initial interface design based on structure in "Data Structures & Algorithms 
			in Java" by Goodrich, Tamassia, & Goldwater

4. FamilyTree - attempts to implement above interface

5. MyGenericNode - another implementation based on designs in "Data Structures & Algorithms";
					applies to a binary tree with pointers to parent and sibling nodes


/////////////DESIGN:\\\\\\\\\\\\\\\

1. GenericNode - this originally had a left and right pointer for the children until I realized
				this limited the structure to a binary tree, and then added an arraylist
				to contain all possible children. I specifically chose an arraylist (of nodes)
				knowing that it would be dynamically sizable and have useful methods such
				as .get, .add, and .size. 
				
				<<ADDED 03/10>>: parent genericNode to structure so as to 
				facilitate data access.

2. FamTree -implements all methods required (12) and implements other helper methods"

	#1 isParent -- uses a helper method to get the name of b's parent. If is is the same as a's name, then return true.

	#2 isChild -- uses above method; if b is a parent of a return true, else return false. 

	#3 isAncestor -- find the node that corresponds to a in list of all nodes. If the size of its children arraylist insn't 0,
				check all its children's names to see if they match. Needs to run recursively but currently doesn't.

	#4 isDescendant -- uses above method; if b is an ancestor of a, then a is a descendant of b.

	#5 isSibling -- get parent's name for a and for b. If they are equal, then return true.

	#6 isCousin -- get a's parent's name, get b's parents name; if isSibling == true for parents, then return true.

	#7 isUncle -- get b's parent's name. If isSibling is true for a and this name (b's parent), return true.

	#8 displayStatistics -- height is a separate method that ideally should be 1 + height of "tallest" child ; size -- 
						store all nodes in Hashmap and set size to size of HashMap, exploiting no repeat objects 
						characteristic of Hashmap; root node - root node set when file read in and kept as instance variable,
						so name is readily accessible. 

	#9 preorderTraversal (N,L,R) --while node has children print out node's name and then all children's names; supposed
									to act recursively but fails to do so. 

	#10 postorderTraversal (L,R,N)-- same as above, except print out children's names first then parent node's name. Also supposed
									to run recursively.

	#11 isFemale -- uses a .csv file of ~4000 most common US female names according to census bureau. If name appears in list,
					return true.

	#12 isOnlyChild -- if the size of the parent's children arraylist is only 1 return true, else return false.


3. FamilyTreeSimulator - contains the main method and tests all methods; I tried to provide
						a true and then a false statement for the more important boolean methods.
						The included 594_HW3.jpg and family_tree.txt files will help with 
						confirming these relationships. Instead of prompting for user input
						I thought it would be easier to pose a question and then simply answer it
						to show the efficacy of the implemented methods. 


///////////USER NOTES:\\\\\\\\\\\\\\\\\

To run the program open the "FamilyTreeSimulator.java" class and simply run the program. 
The console should display all questions and answers as reported by the implemented methods.