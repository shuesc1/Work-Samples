J. Haymaker
CIT 594
HW #5, Milestone 3 -- Movie Recommendation Implementation w/ Modifications
Due 04/19/2017


***********<<<<<<<<<<<<<MILESTONE 1 SUBMISSION>>>>>>>>>>>************************************

1. I think for the initial implementation I am going to specifically make the item a book,
and have the user rate a certain amount of items (perhaps a certain number of books, let's say 10,
in a certain number of genres, let's say at least 5). This will require more active 
participation on the user's end, with the incentive to rate items being that they need to 
complete the requisite number of ratings before they are able to access content (let's assume it's 
ebook/audiobook version of the texts in a hypothetical web app). Where Amazon, Facebook, 
and Goodreads require much more of a passive participation in ratings & suggestions 
(they really just offer suggestions based off of other functionality you the user were 
doing anyway), upon joining Netflix requires (encourages?) you to rate a certain amount of 
items from your favorite movie genres to tailor your experience whenever you interact with their 
product in the future. It is the most user intensive, yet at the same time the most 
fruitful in terms of how it shapes the future user experience (in my opinion). This 
approach is, of course, assuming that the user interface class is the main source of ratings 
information. If further rating information is provided in the data file then it may make sense
to assess the state of the data and let it speak for the user, or have some combination of
already reported data and user supplied ratings.

2a.*****UML CLASS DIAGRAM******  

CLASSES: 

i/ii; iii/iv. Item / Book ; Client / User- Due to the fact that the design can (and will) 
change mid-project I thought making classes as general would be helpful. To this end, the 
Item and Client classes are meant to address the generality of what they may represent; 
the item could be a movie, a person (friend), a book, a playlist, etc. Similarly (though 
somewhat less likely) the client could be a registered shopper, individual one time user, 
group, company, etc. Thus, the idea is that Book would implement item with its specific 
individual characteristics, and User would implement Client for the same reason. UML: Book
implements Item interface; User implements Client interface.

v. FileReader -- takes in a file and parses it for information. Consequently it creates
2 arraylists, one of Book objects and another of User objects. 
UML: FileReader is an aggregate of both Book and User.

vi. SimilarityCalculator -- takes in our user and an arraylist of all users and reviews.
The similarity is calculated between our user and all potential neighbors. The neighbors
are then chosen based off this information. Moreover, the summation of these Pearson
correlations form the denominator (and part of the numerator) of the value(s) calculated 
in the PredictionCalculator class. 
UML: SimilarityCalculator depends on FileReader

vii. NeighborhoodCreator -- this class takes in a (target) User object and a list of users with
corresponding rated Books/Items and uses that information to create the most applicable 
neighborhood by first removing any users that didn't rate the item in question, then 
generate a neighborhood of size 20-50 by getting the Pearson correlation number of all
users who did rate the item and our target user, then take the top scores/users for 
the neighborhood. 
UML: NeighborhoodCreator depends on SimilarityCalculator.

viii. PredictionCalculator -- Calculates the rating prediction of an item using information
from the prior two classes.
UML: PredictionCalculator depends on NeighborhoodCreator

ix. RatingMatrixCreator -- Given the information presented in the handout it made sense
to represent the data as a 3D matrix -- rows = users, columns = items, table entries = 
"weights" (ratings). I was thinking of the data as a bipartite graph with vertices of users
and items, and edges with weights corresponding to the ratings. For this reason this structure
seemed the most logical. 
UML: RatingMatrixCreator depends on UserInterface (and viceversa)

x. UserInterface -- A class that may prompt the user to rate more items/access content, so
in that case RatingMatrixCreator would depend as much on its data as UserInterface relies
on prior class' data.
UML: UserInterface depends on RatingMatrixCreator (and viceversa)

******MAJOR DESIGN DECISIONS*******

Using a matrix: After reading the methodology and talking to Swap about understanding the data as a bipartite graph with weighted edges it made sense to store the edges/weights/ratings in a 2D matrix representation with users for rows and items/books for columns. I am concerned about data access/manipulation with this structure but think it is the best option at this time.

Splitting up SimilarityCalculator, NeighborhoodCreator, and PredictionCalculator: These were originally only two methods in the matrix creator class, but after talking to Swap and more fully understanding how these three elements would interact it seemed more logical to place each one in its own class. 

Use of Item & Client interface: I wanted more practice with interfaces anyway, and it seemed like a good design move from the standpoint of future changes and future reusability of the code for different users and products. 

***********<<<<<<<<<<<<<MILESTONE 1 SUBMISSION 04/07>>>>>>>>>>>************************************


***********<<<<<<<<<<<<<MILESTONE 2 SUBMISSION — 04/14>>>>>>>>>>>************************************

CLASSES: 

i/ii; Item / Book(Movie) - obviously I didn’t have the data during the design phase, so Book object is now effectively
Movie object with really only 1 variable of id number. It still implements the Item interface. IndexLocation variable
was going to be used to create the user/rating matrix but didn’t have time to implement it.There are also other extemporaneous
instance variables that I either didn’t have data for or didn’t have time to add data for.

iii/iv.  Client / User- The model for this is very much the same as above. Instance variables were made public for easy access.
Consequently ratedMovies, ratingAvg, correlation, and deviation were used heavily in the calculation methods.

v. FileReader -- takes in a file and parses it for information. Parses a given file and creates new User/Movie objects or adds 
pointers to existing objects then adds current movie and rating to User’s list of rated movies. countLines() method was 
going to be used for creating a matrix.

vi. SimilarityCalculator -- first used to calculate and set User averages (instance variables), then encapsulates the individual
similarity calculation to get the aggregate calculations of all user similarities. 

vii. NeighborhoodCreator -- Similarities from above are used to create a neighborhood. High correlation values fall in the intervals
from 0.5 - 1.0, and -.05 - 1. This information was used to create a neighborhood of size 30.

viii. PredictionCalculator -- The values from the above 2 methods were used here to get a final calculation of a rating for an item i.

ix. RatingMatrixCreator -- was supposed to be used to store and access all users and all movies/ratings

x. UserInterface -- A class that may prompt the user for a valid file to parse, then 2 options. The first is to generate a 
rating for a (presumably) unrated item and a user of their choosing. The second is given a user u and a size n what 
are the n highest predicted preferences for that user. 

USER INSTRUCTION: run UserInterface.java class and provide a valid text file, then choose one of the two options providing valid input.


***********<<<<<<<<<<<<<MILESTONE 3 SUBMISSION — 04/19>>>>>>>>>>>************************************

CLASS ADDITIONS:

1. Book.java — accommodates new data set.

2. CosineSimilarity.java — calculates the similarity between 2 users

3. fileReader2.java — accounts for the differing formatting in the Book Crossing file

4. BaselinePredictor.java — encapsulated all calculations in calculateBaseline() method, comprised of 3 separate methods to get the overall average (getAggRatingAvg()), user bias(biasUser()), and item bias(biasItem()).



CLASS MODIFICATION:

1. UserInterface.java — now accounts for 2 options: 1. use 10m movielens file with cosine similarity, or 2. use Book Crossing file with Baseline Predictor

2. User.java — changed instance variable HM from seen movies to more general seen items.


Part 2c — which is best: i.Baseline Predictor, ii.kNN with Pearson Correlation, or kNN with Cosine Similarity?:
EquationTester.java creates 5 users and 5 movies and attempts to compare estimated versus actual values (not fully implemented).
