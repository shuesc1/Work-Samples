/* josepma */
/* Joseph Haymaker */

#include "Sentence.h"
#include "Word.h"
#include <iostream>
#include <string.h>
#include <vector>
#include <fstream>
#include <cstring>
#include <ctype.h>
using namespace std;

/*
  Add other "include" and "using namespace" lines here as necessary
*/

vector<Sentence*>* readFile(char* filename) {
  string line;
  string line_parts[2];
  int index_first_space = 0;
  char space = 32 ;

  ifstream myfile (filename);
  if (myfile.is_open())
  {
    string sub1;
    string sub2;
    int is_digit = 0 ;//any non-zero value means T, 0 - F
    int valid_sentence = 1; // 0 - F, 1 - T
    while ( getline (myfile,line) )
    {
      /* find where the first space character is located */
      index_first_space = line.find_first_of(" ") ; 
      /* get subtring before and after first space */
      string sub1 = line.substr(0,index_first_space);
      string sub2 = line.substr(index_first_space+1, line.length()-1);
      /* check number before space and substring after space to see if they are valid */	 
      if(isalpha(sub2[0]) != 0 || sub2[0] == 32){
	valid_sentence = 1; // T
      } else {
        valid_sentence = 0 ; // F
      }
      is_digit = isdigit(sub1[index_first_space-1]); // check if char before 1st space is a digit
      
      /* don't process if either is invalid */
      if(valid_sentence == 0 || is_digit == 0){ // if either is F - 0
       continue;
      }      
    }
    myfile.close();
  }
  else cout << "Unable to open file";  

  return 0;
}



/*
// Uncomment this for Part 2
struct wordComparator {
  bool operator() (const Word* lhs, const Word* rhs) const
  {return lhs->getWord() < rhs->getWord();}
};
*/


/*
set<Word*, wordComparator>* allWords(vector<Sentence*>& sentences) {

  // create the set using the wordComparator struct like this:
  set<Word*, wordComparator>* words = new set<Word*, wordComparator>;

  // UNCOMMENT AND IMPLEMENT THIS FOR PART 2


}
*/

/*
map<string, double>* calculateScores(set<Word*>& words) {
  
  // UNCOMMENT AND IMPLEMENT THIS FOR PART 3

}
*/


/*
double calculateSentenceScore(map<string, double>& scores, string sentence) {

  // UNCOMMENT AND IMPLEMENT THIS FOR PART 4

}
*/
