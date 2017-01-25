*************************
HW 4
Philadelphia Bike Share Data
J. Haymaker

Classes
In the Philadelphia Bike Share Data project, I've designed the following 
classes:

	1. FileReader - Taken from in-class work-- class takes in a file and stores lines in 
	an ArrayList.
	
	2. Trip - creates an object that represents a single trip, using file data as variables
	
	3. Station - creates an object that represents a single Station, using file date as variables
	
	4. IndegoObjectCreator - uses FileReader and stored lines to get variable data for 
	each Station and Trip instance, all of which are stored in their own respective ArrayLists.
	
	5. IndegoAnswerGenerator - Its constructor creates new IOC object and suppies it with
	the appropriate file names. 10 different methods then are used to obtain answers to 
	corresponding questions. 
	
	6. IndegoUserInterface - contains main method, prints out a list of possible questions
	to user and allows them to see answers based on question they choose.

************************
Design

Contained in the folder is a jpg of my initial CRC design, which changed significantly over the 
course of the project. This was due to the nature of how data was obtained and stored, so 
the final CRCs (also in jpg in folder) is a representation of the final project.

The general flow of information was intended to go:

FileReader -> Trip/Station -> IndegoObjectCreator -> IndegoAnswerGenerator -> IndegoUserInterface

Design decisions:

	1. FileReader - given that this class was already at our disposal it seemed logical to
	use it to read the target files and then store their contents line by line in 
	ArrayLists.
	
	2. Trip / Station -- identical classes essentially. When I realized I was going to have
	to store all the eventually parsed data somewhere, it made more sense to eventually
	have an ArrayList of objects as opposed to an ArrayList for each separate variable.
	Thus, these classes are purposefully simple in design, as they are only to represent
	all the data that is accounted for in the 2 files. Thus, each column header was made an
	instance variable, which were then taken in by the constructor. Lastly, I created simple
	getter methods for all variables so as to facilitate future data access. 
	
	3. IndegoObjectCreator - Since the two objects were so similar it made most sense to 
	me to create and store them in the same method in the same class. The constructor takes 
	in the file names and then uses instances of the FileReader class to read both files.
	The resulting stores lines are then stored in another ArrayList in THIS class ("stationLines"
	and "tripLines"), so I could access them (redundant, I know, but I couldn't think of a 
	better way to do this). I also removed the first lines of both the files, so as to 
	avoid errors from reading in the column headings. The constructor then initializes the 
	"trips" and "stations" ArrayLists, which store the filled Trip and Station objects. Lastly,
	the constructor calls the loadData() method, which fills the two aforementioned ArrayLists.
	For the loadData method it seemed logical to split the data on the comma, then store each chunk
	in an array, which were then immediately accessed and used to populate the Station/Trip
	objects which were then in turn stored themselves in "trips" and "stations" from the constructor.
	Lastly, I added getter methods for the "trips" and "stations" ArrayLists in case I needed
	to easily access them in other classes. 
	
	
	4. IndegoAnswerGenerator - while long it seemed most logical to answer all the questions
	in one class, as the work was essentially all the same in nature. The constructor merely
	provides the names of the files to a new instance of the above IOC class. 
	Approach to answering each question:
	
	Q1: Iterate over all Trip objects, if passholder type is "walk up" increase a counter
	by 1. Return counter as answer.
	
	Q2: Almost identical to above, now for Station objects. Now just checking the go live date
	for the substring "/2015" as well as station status for string "active". If both conditions
	were met a counter increased by 1. Return that counter as answer.
	
	Q3: Since this question required data from both files, I first iterated over the Station
	objects to get 1 piece of data -- the Rittenhouse station ID, which I then stored in a
	variable. I then iterated over the Trip objects. The first thing was adding an incrementing counter
	that would return the total number of trips. I then added an if statement comparing each
	start station ID to the already obtained Rittenhouse station ID. If that were true the 
	Rittenhouse usage counter would go up. Outside of that for loop I calculated the ratio
	of rittenhouse trips to total trips, then multiplied it by 100 so it would make sense
	when printed out to the user.
	
	Q4: Iterate over Trip objects. Using separate if statements, increase counter for any ride
	that was by an Indego30 user, then increment a separate counter if the object satisfied 
	the condition that the passholder type was "indego30" && trip route category was "round
	trip". I then calculated the percentage outside the for loop and then multiplied it by
	100 to get a user-understandable value for the question.
	
	Q5: Iterate over Trip objs, get bike ID and trip Duration for each Trip object. If the
	duration is greater than the standing greatest duration, then I made this current duration
	the new greatest duration, and the current bike ID the new greatest duration bike ID. Then
	returned the bike ID when the for loop is over.
	
	Q6: iterated over Trip objects, used Date, SimpleDateFormat, & Calendar to use the start
	and end time data as bookends for an interval, then query whether the current trip time
	fell in that interval, as well as satisfied the condition of occurring on 8/3/16. If 
	conditions were met the bike use counter was increased, and that number was returned.
	
	Q7: Using the formula for distance between 2 sets of x,y coordinates on a plane, I 
	calculated this figure with the start & end latitude/longitude for each Trip object.
	If the current distance was greater than the greatest that was stored, then the current
	distance was made the new greatest distance. The trip object that distance corresponded to
	was also stored at that point, so it's other data points would be accessible. After exiting
	that for loop with the longest trip object, I then used the getter methods to print out
	all data for the trip. It made more sense to me to print out all data at this point while
	it was already at hand, as opposed to going through all the work later. 
	
	Q8: iterating over station objects, I stored go live dates in 'unique' and 'dup' hashsets (used
	because the elements can't be repeated). I then removed all duplicate values from the 'unique'
	set, leaving only the appropriate values. I then iterated over Station objects again (not
	a good practice, but I couldn't think of another way), this time using the established
	unique dates to get unique date station IDs which I stored in an ArrayList. Now with
	this list of key station IDs I was able to iterate over the Trip objects, and within each
	Trip object compare its beginning and end station ID with all station IDs stored in 
	the ArrayList (a terrible waste of time/memory, I realize), then print out the Trip ID
	(in the moment since the data was at hand) if either Station ID were a match.
	
	Q9: Shortest trip according to duration -- essentially the same as Q5.
	
	Q10: Highest usage day -- Iterated over Trips to get just the date substring, then
	in a nested for loop went over Trips again to count the incidences of this date. If
	this number were higher than the highest on record then it became the new highest.
	
	5. IndegoUserInterface -  I used a switch list to create a simple user interface, something
	that I saw as the best option with my limited skill set. Using the IAG getter methods
	I pulled the applicable data into this class and stored it in variables, which then 
	were immediately used for printing out the answers.
	
************************
Program Instruction

To use this program:

Make sure all .java files are saved in the same project in Ecclipse. Similarly, make sure
the two corresponding data files ("Q3_2016_trips.csv" & "Station_table.txt") are saved in 
the same project file in Ecclipse, in the appropriate location in the folder (drag into top
folder, should then appear below "JRE System Library", and NOT in same "src" folder .java
files are stored in). With all files correctly saved in the same project folder in Ecclipse,
all the user need do is open the "IndegoUserInterface.java" file and run the file. The user 
then just needs to wait until the prompt appears to enter the number of the question they
would like the answer to, then press enter to obtain said response.




















