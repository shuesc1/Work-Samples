/* josepma */
/* Joseph Haymaker */
/* compiled on eniac cluster -- $./main <text.txt> to run */
#include "Sentence.h"
#include "Word.h"
#include <iostream>
#include <string.h>
#include <vector>
#include <fstream>
#include <cstring>
#include <ctype.h>
#include <stdlib.h>
#include <boost/algorithm/string.hpp>
#include <set>
#include <stdio.h>
#include <ctype.h>
#include <map>
using namespace std;
using namespace boost;

/* ======================== part 1 - readFile() ==================== */
vector<Sentence*>* readFile(char* filename) {
  string line;
  string line_parts[2];
  int index_first_space = 0;
  char space = 32 ;
  vector<Sentence*> *v = new vector<Sentence*>;

  ifstream myfile (filename);
  if (myfile.is_open()){
    string sub1;
    string sub2;
    int is_digit = 0 ;//0 - F, !0 - T
    int valid_sentence = 1; // 0 - F, 1 - T
    while(getline(myfile,line)){
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

      /* convert substring1 to int and set new Sentence obj's fields */
      int i = atoi(sub1.c_str());
      //Sentence* sent = new Sentence(i, sub2);	
      Sentence* sent = new Sentence;
      sent->setScore(i);
      sent->setText(sub2);
      //cout << "score: " << sent->getScore() << endl << "text: " << sent->getText().c_str() << endl ;

      /* push Sentence obj to vector */
      v->push_back(sent);
    }
    myfile.close();
    return v;
  } else { 
    cout << "Unable to open file";  
    return v;
  }

  return 0;
}

/* ========================= wordComparator struct ============================================ */
/*  a helper struct that is used to traverse a set (bin. search tree) and indicate branch to look in/if 2 elements are equal */
struct wordComparator {
  bool operator() (const Word* lhs, const Word* rhs) const
  {return lhs->getWord() < rhs->getWord();}
};


/* ========================= part 2 - allWords() ============================================ */
/* a function that finds all individual string words in a Sentence and creates a Word object for each 
DISTINCT word, keeping track of occurences and total score all sentences in which it appears */
set<Word*, wordComparator>* allWords(vector<Sentence*>& sentences) {
  int sentence_ctr = 0;
  int word_inserted_ctr = 0;
  int all_words = 0 ;

  // create the set using the wordComparator struct like this:
  set<Word*, wordComparator>* words = new set<Word*, wordComparator>;

  /* iterate over all <<SENTENCE>> objects in vector */
  for(std::vector<Sentence*>::iterator it = sentences.begin(); it != sentences.end(); ++it) {
  	//std::cout << "text: " << (*it)->getText() << endl << "score: " << (*it)->getScore() << endl ;
  	string sentence_cpy = (*it)->getText() ;
  	boost::to_lower(sentence_cpy);
  	//std::cout << "text to_lower: " << sentence_cpy << endl ;

  	/* split each Sentence->text on ' ' */	
  	vector <string> fields;
  	split( fields, sentence_cpy, is_any_of( " " ), token_compress_on );

  	/* for each <<STRING WORD>> in Sentence->text process if it's a valid word */	
  	for (size_t n = 0; n < fields.size(); n++){
		char first_letter = fields[n].at(0);
		if(isalpha(first_letter) != 0){
			/* fields[n] is a valid word string */
			string curr_string = fields[n] ;
			Word* current_word = new Word;
			(*current_word) = Word(curr_string);

			/* check if Word is already in set */
			std::set<Word*, wordComparator>::iterator word_it = (*words).find(current_word) ;
			bool is_in = (*words).find(current_word) != (*words).end();
			if(is_in == 0){
				/* if not in Set already increase total/count and insert word */
				word_inserted_ctr++;
				current_word->increaseTotal((*it)->getScore()); // add current sentence's score to Word's total
				(*words).insert(current_word);
			} else {
				/* if in Set increase total/count */
				(*word_it)->increaseTotal((*it)->getScore()); // add current sentence's score to Word's total
				//cout << "using iterator: " << (*word_it)->getWord() << ", count: " << (*word_it)->getCount() << ", total:" << (*word_it)->getTotal() << endl ;
			}
		all_words++;
		}
  	}
  	sentence_ctr++;
  }
/*  cout << "sentences: " << sentence_ctr << ", unique words: " << word_inserted_ctr << ", all words: " << all_words << endl ;

  int iterator_ctr = 0;
  int total_words = 0;
  for (std::set<Word*, wordComparator>::iterator it3 = words->begin(); it3 != words->end(); it3++) {
	cout << "using iterator: " << (*it3)->getWord() << ", count: " << (*it3)->getCount() << ", total:" << (*it3)->getTotal() << endl ;
	iterator_ctr++;
      	total_words += (*it3)->getCount();
  } 
  cout << "iterator ctr: " << iterator_ctr << ", total words: " << total_words <<  endl;
*/
  return words ;
}

/* ======================== part 3 - calculateScores() ======================= */
/* a function that iterates over each Word in the set parameter, use its calculateScore() func to get the average
sentiment score for that Word, and then add the word (as key) and score (as value) to a map. */
map<string, double>* calculateScores(set<Word*>& words) {
	map<string, double>* return_map = new map<string, double>;
	
	int iterator_ctr = 0;
	int total_words = 0;
	for (std::set<Word*>::iterator it = words.begin(); it != words.end(); it++) {
       		//cout << "calcScores: " << (*it)->getWord() << ", count: " << (*it)->getCount() << ", total:" << (*it)->getTotal() << endl ;
		iterator_ctr++;
        	total_words += (*it)->getCount();

		string key_word = (*it)->getWord() ;
		double avg_sentiment = (*it)->calculateScore() ;
	        //cout << avg_sentiment << ", " << key_word << endl ;
		(*return_map).insert(std::make_pair(key_word,avg_sentiment)) ;
  	} 
	//cout << "iterator ctr: " << iterator_ctr << ", total words: " << total_words <<  endl;
	return return_map ;
}

/* ======================== part 4 - calculateSentenceScore() ======================= */
/* A function that uses the map parameter to calculate and return the average score of all the words in the sentences.
If a word in the sentence is not present in the map, assigne it a score of 0 */
double calculateSentenceScore(map<string, double>& scores, string sentence) {
		double sentence_score = 0 ;
		double word_count = 0 ;

	        /* split each Sentence->text on ' ' */
        	boost::to_lower(sentence);
		vector<string> toks;
        	split(toks, sentence, is_any_of( " " ), token_compress_on );
		//cout << "Sentence toks: " << endl ;

		/* iterate over each individual word in sentence */
		for (std::vector<string>::iterator it = toks.begin(); it != toks.end(); it++) {
			//cout << (*it) << endl; // (*it) == word
			std::map<string, double>::iterator loc = scores.find((*it));
			/* if word is in fact in the map */
			if (loc != scores.end()){
    				//cout << loc->second << endl;
				/* add word's score to sentence score */
				sentence_score += loc->second ; 
			}
			word_count++;
		}
		/* finally get sentence average by total / num. of words in sentence */
		//cout << "Sentence total: " << sentence_score << ", num of words in sentence: " << word_count << endl ;
		sentence_score /= word_count ;
		
		return sentence_score;
}









