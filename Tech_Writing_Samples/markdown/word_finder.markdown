
### Method - Java

+ __Proposed audience:__ beginning/intermediate Java user
+ __Function:__ reference; explanation, assessment, and proposed feedback for a given Java method
+ __Register:__ informal; assumes some knowledge of Java programming

# wordFinder
```
public static void wordFinder(String filename, String[] keywords); 
```
1. [Definition and Usage](#Definition-and-Usage:)
2. [Parameters](#Parameters)
3. [Method Declaration](#Method-Declaration)
4. [Example 1](#Example-1)
5. [Example 2](#Example-2)
6. [Example 3](#Example-3)
7. [Time Complexity](#Time-Complexity)
8. [Comments & Suggestions](#Comments-&-Suggestions)
9. [Final Proposed Version](#Final-Proposed-Version)

<a id="Definition-and-Usage:"></a>
## Definition and Usage: 
__Find the number of instances of target words in a text file__

Prints all words specified in an array of key search words along with the number of times they appear in a text file.

__Tip__: Related methods: [contractionSplitter()][1], [lineTokenizer()][2], [overUnderThroughParser()][3], [conjunctionJunctionizer()][4] and [OEDchecker()][5].

[1]:http://blog.maketaketeach.com/wp-content/uploads/2014/10/Screen-Shot-2014-10-06-at-6.17.23-PM.png
[2]:https://cdn.auth0.com/blog/refresh-token/tokens.png
[3]:http://www.empirella.com/wp-content/uploads/2013/03/tina-fey-quote3.jpg
[4]: http://fistfuloftalent.com/wp-content/uploads/2010/04/conjuction.jpg
[5]: http://27ldk4j1esh2tto962ds2vk1.wpengine.netdna-cdn.com/wp-content/uploads/PC-oed-full-1222x330.jpg

<a id="Parameters"></a>
## Parameters: 

| Name | Description: |
| ---- | ----------- |
|`String filename`| The name of a valid text file|
|`String keywords`| An array words to search for and find the number of occurrences|

<a id="Method-Declaration"></a>
## Method declaration: 
```java
	/** A method that counts all occurrences of words in a text file
	 * 
	 * @param filename the name of a valid text file
	 * @param keywords an array of words to find and count in a text file
	 * @throws FileNotFoundException
	 */
	public static void wordFinder(String filename, String[] keywords) throws FileNotFoundException{
		File file = new File(filename);
		Scanner in = new Scanner(file);
		int[] count = new int[keywords.length];
		while(in.hasNext()){
			String line = in.nextLine();
			String regex = "[^a-zA-Z0-9']";
			String[] tokens = line.split(regex);
			for(int i = 0; i < keywords.length; i++){
				for(int j = 0; j < tokens.length; j++){
					if(tokens[j].equalsIgnoreCase(keywords[i])){
						count[i]++;
					}
				}
			}
		}
		for (int k = 0; k < keywords.length; k++) {
			System.out.println(keywords[k] + ": " + count[k]);
		}
		in.close();
	}
```
<a id="Example-1"></a>
## Example 1: 

```java
//		FindWords fw = new FindWords();   //No need to create a new class object since the method is static
		String[] keywords = {"rabbit", "bunny", "Peter", "bunnies", "the"};
		wordFinder("peter-rabbit.txt", keywords);
```
Output:

```java
rabbit: 5
bunny: 1
Peter: 24
bunnies: 1
the: 46
```
<a id="Example-2"></a>
## Example 2:<a id="regex"></a>


```java
		String[] keywords2 = {"sun", "moon", "wine", "and", "sultan"};
		wordFinder("rubaiyat-of-omar-khayyam.txt", keywords2);	
```
Output:

```java
sun: 1
moon: 3
wine: 9
and: 129
sultan: 4
```
<a id="Example-3"></a>
## Example 3:

```java
		String[] keywords3 = {"tiger", "Aharsi", "animals", "at", "jungle"};
		wordFinder("aharsi-the-bengal-tiger.txt", keywords3);
```
Output:

```java
tiger: 13
Aharsi: 28
animals: 5
at: 14
jungle: 1
```

<a id="Time-Complexity"></a>
## Time Complexity

`O(nmp)` - where `n` is the number of lines in the text file, `m` is the number of elements in the `keywords` string, and `p` is the number of elements in the `tokens` string. In the worst case all three sets of elements will approximate infinity, leading to an upper time bound of `O(n^3)`. 

<a id="Comments-&-Suggestions"></a>
## Comments & Suggestions:
* **Data structure usage & time complexity:** One approach to still solving the problem of finding certain words and their instances while _also_ trying to improve time complexity includes sorting all words in the text alphabetically. This could be done with a sorting algorithm on an array/arraylist and then a search for each keyword with binary search. Alternatively all words in the text could be stored in and searched for via a binary search tree implementation. However, a much quicker option to implement would include the original algorithm, now using a HashMap data structure to store all text words as _keys_ and incidence number as _values_. This eliminates the need to maintain a `count` array and, consequently, the nested 'for' loop. The reasoning for using a HashMap is based on the fact that _keys_ cannot be repeated, and the fact that the `get()` and `put()` operations take `O(1)` time to complete. 

<a id="Final-Proposed-Version"></a>
## Final proposed version:
* __Improved time complexity:__ `O(np + m)` → `O(np)`→ worst case scenario: `O(n^2)`

```java
	/*
	 * Revised method implementing a HashMap with Keys of words and Values of number of occurrences
	 */
	public static void wordFinder2(String filename, String[] keywords) throws FileNotFoundException{
		File file = new File(filename);
		Scanner in = new Scanner(file);
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		while(in.hasNext()){
			//need to make all keys lowercase to avoid undercounting
			String line = in.nextLine().toLowerCase();
			String regex = "[^a-zA-Z0-9']";
			String[] tokens = line.split(regex);
			for(int i = 0; i < tokens.length; i++){
				//if word is already in HashMap increase value (number of occurrences) by 1
				if(hm.containsKey(tokens[i])){ 
					int count = hm.get(tokens[i]);
					hm.put(tokens[i], count + 1);
					//if first occurrence of word put it in HashMap as a key with a value/occurrence number to 1	
				} else { 
					hm.put(tokens[i], 1);
				}
			}
		}
		for (int j = 0; j < keywords.length; j++) {
			//first need to account for discrepancies in case
			String keyword = keywords[j].toLowerCase();
			System.out.println(keywords[j] + ": " + hm.get(keyword));
		}
		in.close();
	}
	
```