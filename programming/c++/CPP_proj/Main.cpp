/* josepma */
/* Joseph Haymaker */
/* compiled on eniac cluster -- $./main <text.txt> to run */

#include "Sentence.h"
#include "Word.h"
#include <iostream>
#include <string>
#include <cstring>
#include <vector>
#include <stdio.h>
#include <boost/shared_ptr.hpp>
#include <set>
#include <map>
#include <sstream>
using namespace std;
using namespace boost;

/* Declarations of functions declared in Analyzer.cpp */
  //a->b == (*a).b;
vector<Sentence*>* readFile(char*); // Part 1
std::set<Word*>* allWords(vector<Sentence*>&); // Part 2
map<string, double>* calculateScores(set<Word*>&); // Part 3
double calculateSentenceScore(map<string, double>&, string); // Part 4

/* helper methods to delete dynamically allocated memory */
void freeSentenceVector(vector<Sentence*>* finishedVector){
	//vector<int> vec;
	//vector<Sentence*>().swap(*finishedVector);
	vector<Sentence*>::iterator i;
	for(i = finishedVector->begin(); i != finishedVector->end(); ++i){
    	  delete (*i);//delete data that was pointed
    	  *i = 0;
	}
	finishedVector->clear();
	finishedVector->shrink_to_fit();

	delete(finishedVector);
}

void freeWordSet(set<Word*>* finishedSet){
	for (std::set<Word*>::iterator it = finishedSet->begin(); it != finishedSet->end(); ++it){
    	  //cout << ' ' << (*it)->getWord() << endl;
	  delete (*it);
	} 
	delete finishedSet;
	finishedSet->clear();
}

void freeWordScoreMap(map<string, double>* finishedMap){
  //for (std::map<string,double>::iterator map_it = finishedMap->begin(); map_it != finishedMap->end(); ++map_it){
    //std::cout << map_it->first << " => " << map_it->second << '\n';
    //delete map_it;
  //}
  finishedMap->clear() ;//Removes all elements from the map container (which are destroyed), leaving the container with a size of 0.
  delete(finishedMap);
}


/* =========================== MAIN ================== */
int main(int argc, char* argv[]) {

  /* check for valid file argument */
  if (argc < 2) { // catch no filename specificed in command line
        std::cerr << "Usage: " << argv[0] << " <filename.txt>" << std::endl;
        return 1;
  }
  char filename_input[50];
  strcpy(filename_input, argv[1]) ;
  cout << "filename_input: " << filename_input << endl ;

  //TODO filename checking

  /* part 1 - read in file */
  vector<Sentence*>* sentence_vec = readFile(filename_input);

  /* part 2 - create a set of Words with counts and totals (sum of sentence sentiment values they appear in) */
  set<Word*>* words_agg = allWords(*sentence_vec);

  /* part 3 */
  map<string, double>* words_scores = new  map<string, double>;
  words_scores = calculateScores(*words_agg) ;
  // show content
  /*
  for (std::map<string,double>::iterator map_it = words_scores->begin(); map_it != words_scores->end(); ++map_it){
    std::cout << map_it->first << " => " << map_it->second << '\n';
  }
  */

  /* part 4 */
  while(1){
	string line = "";
  	cout << "Enter a line to get it's sentiment value: " << endl ;
 	getline(cin, line);
	if(line == "quit"){
		cout << "exiting..." << endl;
		freeSentenceVector(sentence_vec);
		freeWordSet(words_agg);
		freeWordScoreMap(words_scores);
		break;
  	} else {
		double final_score = calculateSentenceScore(*words_scores, line);
		cout << "Sentiment score of line: " << final_score << endl;
		sleep(2);
	}
  }

	
}
