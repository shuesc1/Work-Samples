#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "find_symbols_soln.h"

// defining Node struct
typedef struct Node node ;
struct Node {
	void* symbol;
	int* offset;
	node* next;
	node* prev;
};

#define SIZE 10
// declaring global var of hashtable
node* hashtable[SIZE];

// starter code
extern int parse_function_header(char*);
extern int parse_line(char*);
extern char* function_name;
extern char* parameter_names[10];
extern char* variable_names[10];

/*
 * THIS IS THE STARTER CODE FOR PART 1 
 */

// =======================================
// ========= helper funcs ===============

/* adapted from: https://research.cs.vt.edu/AVresearch/hashing/strings.php
* A function to compute the index in the hashtable of a string value
*/
int hash_func(char* value, int hash_table_len){
   int val_len = strlen(value);
   int i, sum;

   // get sum of ASCII values of chars, then take mod to get index
   for (sum=0, i=0; i < val_len; i++)
     sum += value[i];

   return sum % hash_table_len; // return index
}

/*
* A function to check to see if a hashtable contains a given target string 
*/
int contains(node* ht[], char* target){ // 1 - found; 0 - not found
	int hashcode = hash_func(target, SIZE); // get index
	node* head = ht[hashcode]; // head is at certain index in hash array; may contain several linked nodes
	while(head!=NULL){
		if(strcmp(head->symbol, target)==0){
			return 1;
		} else {
			head = head->next;
		}
	}
	return 0;
}

/*
* A helper function for the get_offset primary function that traverses linked nodes in a hashtable
*/
int get_existing_offset(node* ht[], char* sym){ // 1 - found; 0 - not found
        int hashcode = hash_func(sym, SIZE); // get index
	node* head = ht[hashcode]; // head is at certain index in hash array; may contain several linked nodes
        while(head!=NULL){
                if(strcmp(head->symbol, sym)==0){
                        return (int)head->offset;
                } else {
                        head = head->next;
                }
        }
        return 0;
}

/*
* A function to add a symbol string and offset int to a node in a hashtable
*/
int add(node* ht[], char* sym, int offst){ // 0 - error ; 1 - added correctly
	int hashcode = hash_func(sym, SIZE);
	node* head = ht[hashcode]; // get head at certain index in hash array
	node* new = malloc(sizeof(node)); // malloc space for new node
	if(new==NULL) return 0; // check for no space error

	new->symbol = malloc(sizeof(char)*(strlen(sym)+1)); // malloc space for symbol
	if(new->symbol==NULL) return 0;
	new->offset = malloc(sizeof(int));
	if(new->offset==NULL) return 0;

	strcpy(new->symbol, sym);
	//strcpy(new->offset, offst);
	new->offset = offst;

	new->next = head; // new now points to head
	new->prev = NULL; // new points back to NULL
	if(head!=NULL){
		head->prev = new;
	} // head prev ptr now points to new
	ht[hashcode] = new; // promote new to head

	return 1;
}
//|					|
// ========= helper funcs ==============
// =====================================

/*
* A func that attempts to add the symbol & offset to the symbol table
* returns -1 (no mods to the sym table) if symbol param is NULL or empty
* returns 0 (no mods to sym table) if (symbol) param reps a symbol ALREADY present in table (even if mapped to diff offset)
* returns 1 if symbol param not present already, and if (symbol) param properly mapped to (offset) param
*/
int add_symbol(char* symbol, int offset) {
  // inputs not valid - return -1
  if(symbol==NULL | strcmp(symbol, "")==0){
  	return -1;
  } else {
	//int x = hash_func(symbol, SIZE);
	// hashtable already contains symbol param (even if it has a diff offset)
	if(contains(hashtable, symbol)==1){ 
		return 0;
	} else {
		add(hashtable, symbol, offset);
		return 1;
	}
  }
}


/*
* A func that attempts to find the offset in the symbol table for the specified symbol param
* returns -1 if (symbol) param is NULL or empty, OR if the offset param is NULL
* returns 0 if the (symbol) param is not found in the symbol table
* returns 1 if (symbol) param successfully added to symbol table with value pointed to by offset ptr/param
*/
int get_offset(char* symbol, int* offset) {
   // inputs not valid - return -1
  if(symbol==NULL | strcmp(symbol, "")==0|offset==NULL){
        return -1;
  } else {
 	if(contains(hashtable, symbol)!=1){ // 0 - not contains
		return 0 ;
	} else { // contains 
		*offset = get_existing_offset(hashtable, symbol);
		return 1;
	}
  }		
}
/*
* A function that removes all entries/mappings from the symbol table and frees memory where they are stored as necessary
*/
void clear() {
  printf("**CLEARING** array of nodes:\n");
  for(int k=0; k<11; k++){ 
	node *n1 = hashtable[k]; 
	if(n1==NULL){
		printf("[%d]|NULL|\n", k);
	} else {
		printf("[%d]|symbol: %s, offset: %d|", k, n1->symbol, n1->offset);
		//int l = k;
		while(n1->next != NULL){
			printf(" ===> |symbol: %s, offset: %d| ", n1->next->symbol, n1->next->offset);
			n1 = n1->next;
		}
		printf("\n");
	}
  }
  printf("\n");
  for(int i = 0; i < 11; i++){
	node *n = malloc(sizeof(node));
	n = hashtable[i]; // get head of all LL in hashtable array
	//printf("node at %p with [symbol] %s and [offset] %d\n", n, n->symbol, n->offset);
	if(n != NULL){
		// in case there’s only one node
		// space malloc'd for node, node->symbol, & node->offset
		if (n->next == NULL) {
			printf("freeing node at %p with [symbol] %s and [offset] %d\n", n, n->symbol, n->offset);
			free(n->symbol);
			n->symbol = NULL; // free symbol and set to NULL
			//free(n->offset);
			n->offset = NULL; // free offset and set to NULL
			free(n);
			hashtable[i] = NULL; // free head node and set to NULL
		} else { // at least one other node
			printf("n->next != NULL\n");
			// find the tail
			while (n->next->next!= NULL) { 
				n = n->next;
			}

			// n now at penultimate node
			while(n->prev != NULL){
				// 1) free next's symbol
				free(n->next->symbol);
				n->next->symbol = NULL;
				// 2) free next's offset
                        	free(n->next->offset);
                        	n->next->offset = NULL;
				// 3) free next
                        	free(n->next);
                        	n->next = NULL;		
				// 4) move current node back one
				n = n->prev;	
			}
			// broke out -- means n->prev == NULL (at head)
			if(n->prev == NULL && n->next!= NULL){
                        	printf("freeing node at %p with [symbol] %s and [offset] %d\n", n, n->next->symbol, n->next->offset);
				// 1) free next's symbol
                        	free(n->next->symbol);
                        	n->next->symbol = NULL;
                        	// 2) free next's offset
                        	//free(n->next->offset);
                        	n->next->offset = NULL;
                        	// 3) free next
                        	free(n->next);
                        	n->next = NULL;   	
			} 
			if(n->prev == NULL && n->next == NULL){
				printf("freeing node at %p with [symbol] %s and [offset] %d\n", n, n->symbol, n->offset);
				free(n->symbol);
				n->symbol = NULL;
				//free(n->offset);
				n->offset = NULL;
				free(n);
				n= NULL;
			}
 		}
	}
	//free(n);
	//n=NULL;
  }
}

// ======================== STEP 2 =====================

/*
* A function that that takes in a filename, opens the file, reads in all lines, uses
* parse_function_header and parse_line functions to 
*/
int populate_symbol_table(char* filename) {
	FILE* file;
	char buffer[255];
	file = fopen(filename, "r");

	if(file==NULL){
		printf("File not found\n");
		return -1;
	}
	// ===== ITERATING OVER LINES ====
	// while more lines
	int line = 1;
	int offst = 0;
	while(fgets(buffer, 255, (FILE*) file)) {
    		printf("%s", buffer);
		// line is header
		if(line==1){
			if(parse_function_header(buffer)==1){
				int offset = 4;
				int i =0;
				//if(parameter_names[0]==NULL){
				//	printf("No parameters found during populate_symbol_table\n");
				//	return -1;
				//}
				while(parameter_names[i] != NULL){
					if(add_symbol(parameter_names[i], offset)==1){
						printf("adding symbol %s with offset %d\n", parameter_names[i], offset);
						offset++;
						i++;
					} else {
						printf("error trying to add a symbol to the symbol table that is already present\n");
						return -1;
					}
				}
			} else {
				printf("error using parse_function_header function\n");
				return -1;
			}

		// line is regular variable declaration line
		} else {
			//int offset = 0;
			if(parse_line(buffer)==1){
				//int offset = 0;
				int j = 0;
				while(variable_names[j] != NULL){				
					if(add_symbol(variable_names[j],offst)==1){ // 0 -trying to add repeat value; 1 - added correctly 
						printf("adding symbol %s with offset %d\n", variable_names[j], offst);
						offst=offst-1;
						j++;
					} else {
						printf("error trying to add a symbol to the symbol table that is already present\n");
						return -1;
					}
				}
			} else {
				printf("error using parse_line function\n");
				return -1;
			}
		}
		line++;
	}

	// EOF -- close file
	fclose(file);

  return -1;
}
/*
int main(){

	char* filename = "sample.c";
	populate_symbol_table(filename);

	clear();

	return 1;
}
*/
