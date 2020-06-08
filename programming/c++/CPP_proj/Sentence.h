/* josepma */
/* Joseph Haymaker */
/* compiled on eniac cluster -- $./main <text.txt> to run */

#include <iostream>
#include <string>
using namespace std;

class Sentence {
private:	int score;
		string text;

public: 
		/* public constructor that initializes 2 fields */
 		Sentence(){
		  score = 0;
		  text = "";
 		}

 		/* overloaded constructor that takes in a string arg */
 		Sentence(int new_score, string input_text){
		  this->score = new_score;
		  this->text = input_text; // this == ptr to this obj
 		}

		/* setter method for score */
 		void setScore(int new_score){
		  this->score = new_score;
 		}

 		/* getter method for score */
 		int getScore(){
		  return score;
 		}

 		/* a setter method for text */
 		void setText(string new_text){
		  this->text = new_text;
 		} 

 		/* getter method for text */
 		string getText(){
		  return text;
 		}

};
