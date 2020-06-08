/* josepma */
/* Joseph Haymaker */
/* compiled on eniac cluster -- $./main <text.txt> to run */

#include <iostream>
#include <string>

//using namespace std ;


class Word {

private:	std::string word ;
		int count ;
		int total ;

public:		/* default constructor */
		Word(){
		  count = 0;
		  total = 0;
		  word = "";
		}

		/* overloaded constructor */
		Word(string word_provided){
			this->word = word_provided ;
			count = 0;
			total = 0;
		}

		/* a method that takes an int and increases the total field by that value as well as increments the count */
		void increaseTotal(int val_to_add){
			total += val_to_add ;
			count++;
		}

		/* calculate score */
		double calculateScore(){
			double avg = 0;
			if(count == 0){
				return avg ;
			} else {
				avg = (double)this->total / (double)this->count ;
				return avg ;
			}
		}

		/* a getter method for count */
		int getCount(){
			return this->count;
		}
	
		/* a getter method for total */
		int getTotal(){
			return this->total;
		}

		/* a getter method for word */		
		string getWord() const{ 
			return this->word;
		}		
};


