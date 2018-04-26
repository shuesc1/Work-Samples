/* josepma */
/* Joseph Haymaker */

#include <iostream>
#include <string>
using namespace std;
/*
  Add "include" and "using namespace" lines here as necessary
*/

class Sentence {
  /*
    Implement the Sentence class here for Part 1
  */
 private:	int score;
		string text;

 public: 

 //public constructor that initializes 2 fields
 Sentence(){
	score = 0;
	text = "";
 }

 // overloaded constructor that takes in a string arg
 Sentence(string input_text){
	score = 0;
	this->text = input_text; // this == ptr to this obj
 }

 /* getter method for score */
 int getScore(){
	return score;
 }
 
 /* getter method for text */
 string getText(){
	return text;
 }


};
