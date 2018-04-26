/* josepma */
/* Joseph Haymaker */

#include "Sentence.h"
#include "Word.h"
#include <iostream>
#include <string>
#include <cstring>
#include <vector>
#include <stdio.h>
/*
  Add other "include" and "using namespace" lines here as necessary
*/


/*
 Declarations of functions declared in Analyzer.cpp
 Uncomment these as needed, but do not otherwise change these declarations!
*/
vector<Sentence*>* readFile(char*); // Part 1
//set<Word*>* allWords(vector<Sentence*>&); // Part 2
//map<string, double>* calculateScores(set<Word*>&); // Part 3
//double calculateSentenceScore(map<string, double>&, string); // Part 4

int main(int argc, char* argv[]) {
  /*
    Implement this function for testing.
  */
  if (argc < 2) { // catch no filename specificed in command line
        std::cerr << "Usage: " << argv[0] << " <filename.txt>" << std::endl;
        return 1;
  }

  //cout << "argv[1]: " << argv[1] << std::endl;
  char filename_input[50];
  //std::strcpy(filename_input, argv[1]);
  strcpy(filename_input, argv[1]) ;
  cout << "filename_input: " << filename_input << endl ;
  readFile(filename_input);

  /* TODO make sure input filename is valid
  char *token = std::strtok(filename_input, ".");
  char* input_components[2];
  int i = 0;
  while (token != NULL) {
    	std::cout << token << '\n';
	input_components[i] = token ;
	//strcpy(input_components[i], token);
	cout << input_components[i] << endl;
        token = std::strtok(NULL, " ");
	i++;
  }
  cout << "filename_input: " << filename_input << endl ;
  */
}
