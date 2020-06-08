#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "find_symbols_soln.h"

// defining Node struct
typedef struct Node node ;
struct Node {
	void* symbol;
	int offset;
	node* next;
	node* prev;
};

#define SIZE 10
// declaring global var of hashtable
node* hashtable[SIZE];
char arr_char_arrs[20][30] = {""}; //TODO make a reset to null func for this after each line is read in and tokenized
char* oper[4] = {"LDR ", "STR ", "ADD ","AND "}; //array of char ptrs-- 0-LDR, 1-STR, 2-ADD
char* regs[9] = {"R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8"};
char* comma = ", ";
char* wafflefry = "#";
char* fp = "FP, ";
int add_indices[10];
char* multiple_assign_subarr[10];

// starter code
extern int parse_function_header(char*);
extern int parse_line(char*);
extern char* function_name;
extern char* parameter_names[10];
extern char* variable_names[10];

// foo->bar == (*foo).bar

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
int get_existing_offset(node* ht[], char* sym){ // non-zero - found; 0 - not found
	//printf("running get_existing_offset() searching for %s\n",sym);
	int hashcode = hash_func(sym, SIZE); // get index
	node* head = ht[hashcode]; // head is at certain index in hash array; may contain several linked nodes
	while(head!=NULL){
		if(strcmp(head->symbol, sym)==0){
			//printf("symbol found: head->offset: %d\n",head->offset);
			return head->offset;
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
	//new->offset = &offst;
	new->offset = offst;
	//sprintf(new->offset, "%p", &offst); 
	//printf("new->offset: %d\n", new->offset);
        //printf("*new->offset: %d\n", *new->offset);
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
						printf("error <<line %d>> trying to add a symbol [[%s]] to the symbol table that is already present\n", line, parameter_names[i]);
						return 0;
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
						printf("error <<line %d>> trying to add a symbol [[%s]] to the symbol table that is already present\n", line, variable_names[j]);
						return 0;
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

	return 1;
}

// =====================================================================================================
// ========================================== PART 2 C->LC4 ASM ========================================
// =====================================================================================================

// ----------------------------------------------------
// -------------- lil baby helper funcs --------------
// ----------------------------------------------------
// |						      |

/*
 * A helper function to split a line into tokens and store them in a global array
 */
int tokenize_line(char* input_line){
	char* token;
	token = strtok (input_line, " ");
	int x = 0 ;
	//printf("arr_char_arrs[%d]: %s\n", x, token);
	strcpy(arr_char_arrs[x], token);
	x++;
	while (token != NULL) { //store tokens in array of tokens
		token = strtok (NULL, " ");
		//printf ("%s\n", token);
		if (token == NULL) {
			break;
		}
		if (strlen(token) > 29) {
			printf("Token %s too long!\n", token);
			continue;
			//strcpy(arr_char_arrs[x], token, 29);
			//x++;
		} else {
			strcpy(arr_char_arrs[x], token);
			//printf("arr_char_arrs[%d]: %s\n", x, arr_char_arrs[x]);
			x++;
		}
	}
	strcpy(arr_char_arrs[x], "\0");

	// ------------ TOKEN ARRAY -------------
	int length = x;
	return length;
}

/*
 * A helper method that counts the number of '=' in a line (assignments)
 */
int get_num_assignments(int length){
	int assignments = 0;
	for(int i = 0; i < length; i++){
		if(strcmp(arr_char_arrs[i], "=")==0){
			assignments++;
		}
	}
	return assignments;
}

/*
 * A helper method that counts the number of addition operators in a line (instances of '+')
 */
int get_num_additions(int length){
	int additions = 0;
	for(int i = 0; i < length; i++){
		if(strcmp(arr_char_arrs[i], "+")==0){
			additions++;
		}
	}
	return additions;
}

/*
 * A helper method that counts the number of commas in a line (instances of ',')
 */
int get_num_commas(int length){
	int commas = 0;
	for(int i = 0; i < length; i++){
		if(strcmp(arr_char_arrs[i], ",")==0){
			commas++;
		}
	}
	return commas;
}

/*
 * A helper function that returns the index of a certain character
 */
int get_char_index(char* key_char, int length){
	for(int j=0; j<length; j++){
		if(strcmp(arr_char_arrs[j], key_char)==0){
			return j;
		}
	}
	return 0;
}

/*
 * A helper function that iterates over a char array to see if its value is a digit
 */
int is_number(char* str){ // 0 - is a digit, 1 - is NOT a digit (!48-57)
	int length = strlen(str);
	int result = 1;
	for(int i=0; i<length; i++){
		if(str[i]>47 && str[i]<58){
			result=0;
		} else {
			result=1;
			return result;
		}
	}
	return result;
}

/*
 * A helper function to store the indices of all instances of '+' in the add_indices[] global array
 */
void get_add_indices(int length){
	//int end = length -1;
	int i = 0;

	for(int end=(length-1); end >=0; end--){
		if(strcmp(arr_char_arrs[end], "+")==0){
			add_indices[i] = end;
			add_indices[i+1]= '\0';
			i++;
		}
	}
}

/*
 * A helper function to clean the global array
 */
void clear_add_indices(){
	for(int j = 0; j<10; j++){
		add_indices[j]= '\0';
	}
}

/*
 * A helper function that takes in the arr_char_arrs index of the left and right tokens, the string register of the left var (that will be changing), and a flag 
 * for if this is the 1st iteration or not (0-yes, 1-No)
 */
int generate_asm_addition(int tok_index_L, int tok_index_R, char* register_L_var, int first_iter_flag, FILE* dest_file){
	char* left_tok = arr_char_arrs[tok_index_L];
	char* right_tok = arr_char_arrs[tok_index_R]; 
	char* asm_line1 = malloc(sizeof(char)*255);
	char* asm_line2 = malloc(sizeof(char)*255);

	// first iteration or not
	if(first_iter_flag==0){ // double LDR required
		// ---------- FIRST ITERATION ------- R side loaded in as well
		if(is_number(right_tok)==0){ // is 0-9
			//snprintf(prefix, sizeof(prefix), "%s: %s: %s", argv[0], cmd_argv[0], cmd_argv[1]);
			//snprintf(asm_line1, sizeof(asm_line1), "%s%s, %s, #%d\n", oper[3], regs[0], regs[0], 0);// 'AND R0, R0, #0
			snprintf(asm_line1, 100, "AND R0, R0, #%d\n", 0);// 'AND R0, R0, #0
			//printf("asm_line1: %s", asm_line1);
			fputs(asm_line1, dest_file);
			strcpy(asm_line1, ""); // clear line
			snprintf(asm_line1, 100, "ADD R0, R0, #%s\n",right_tok);// 'ADD R0, R0, #RIGHT_TOKEN_NUM'
			fputs(asm_line1, dest_file);
			strcpy(asm_line1, ""); // clear line */
		} else if(is_number(right_tok)!=0){ // !0-9
			snprintf(asm_line1, 100, "LDR R0, FP, #%d\n", get_existing_offset(hashtable, right_tok));// 'LDR R0, FP, #offset_sym
			//printf("right tok: %s, offset: %d\n", left_tok, get_existing_offset(hashtable, right_tok));
			//printf("asm_line1: %s", asm_line1);
			fputs(asm_line1, dest_file);
			strcpy(asm_line1, ""); // clear line
		}
	}  

	// single LDR required for L variable (in all cases)
	// ------------- 1st or 2nd++ iteration --------- only L side loaded   
	if(is_number(left_tok)==0){ // is 0-9
		snprintf(asm_line2, 100, "AND %s, %s, #0\n", register_L_var, register_L_var);
		//printf("asm_line2: %s", asm_line2);
		fputs(asm_line2, dest_file);
		strcpy(asm_line2, "");
		snprintf(asm_line2, 100, "ADD %s, %s, #%s", register_L_var, register_L_var, left_tok);
		fputs(asm_line2, dest_file);
		strcpy(asm_line2, "");
	} else if(is_number(left_tok)!=0){ // !0-9
		snprintf(asm_line2, 100, "LDR %s, FP, #%d\n", register_L_var, get_existing_offset(hashtable, left_tok));// 'LDR R#, FP, #offset_sym
		//printf("left tok: %s, offset: %d\n", left_tok, get_existing_offset(hashtable, left_tok));
		//printf("asm_line2: %s", asm_line2);
		fputs(asm_line2, dest_file);
		strcpy(asm_line2, ""); // clear line
	}

	// ASM for ADDing two values that are now in regs
	snprintf(asm_line2, 100, "ADD R0, R0, %s\n", register_L_var);// 'LDR R#, FP, #offset_sym
	//printf("asm_line2: %s", asm_line2);
	fputs(asm_line2, dest_file);
	strcpy(asm_line2, ""); // clear line

	// freeing vars
	left_tok = NULL;
	right_tok = NULL;
	free(asm_line1);
	asm_line1 = NULL;
	free(asm_line2);
	asm_line2 = NULL;
	return 0;
}

/*
 * A helper function that takin in the arr_char_arrs[] index of left and right (if any) tokens and produces the apppropriate LC4 ASM written out to a file
 */
int generate_asm_assignment(int tok_index_L, int tok_index_R, int return_flag, FILE* dest_file){ // return flag - 1 if Y, 0 if N
	// int x = y OR t = 5 OR t = q + u OR return g OR return y + t +v
	char* asm_line1 = malloc(sizeof(char)*strlen(arr_char_arrs[tok_index_L]));
	char* asm_line2 = malloc(sizeof(char)*strlen(arr_char_arrs[tok_index_R]));
	//char* left_tok = malloc(sizeof(char)*strlen(arr_char_arrs[tok_index_L]));
	char* left_tok = arr_char_arrs[tok_index_L];
	//char* right_tok = malloc(sizeof(char)*strlen(arr_char_arrs[tok_index_R]));
	char* right_tok = arr_char_arrs[tok_index_R];
	if(return_flag==1){
		snprintf(asm_line1, 100, "STR R0, FP, #%d\n", 3);
		//printf("assignment ASM: %s", asm_line1);
		fputs(asm_line1, dest_file);
		strcpy(asm_line1, "");
	} else if(return_flag!=0 && tok_index_R==0){ // int x = .. OR return y (right side already in R0 hypothetically)
		snprintf(asm_line1, 100, "STR R0, FP, #%d\n", get_existing_offset(hashtable, left_tok));
		//printf("assignment ASM:: %s", asm_line1);
		fputs(asm_line1, dest_file);
		strcpy(asm_line1, "");
	} else {
		if(is_number(right_tok)==0){ // int x = 7 OR y = 12
			snprintf(asm_line2, 100, "AND R0, R0, #0\n");
			//printf("asm_line2: %s", asm_line2);
			fputs(asm_line2, dest_file);
			strcpy(asm_line2, "");
			snprintf(asm_line2, 100, "ADD R0, R0, #%s\n", right_tok);
			fputs(asm_line2, dest_file);
			strcpy(asm_line2, "");
		} else if(is_number(right_tok)!=0) { // int x = y OR x = tok
			snprintf(asm_line2, 100, "LDR R0, FP, #%d\n", get_existing_offset(hashtable, right_tok));// 'LDR R#, FP, #offset_sym
			fputs(asm_line2, dest_file);
			strcpy(asm_line2, ""); // clear line
		}
		snprintf(asm_line2, 100, "STR R0, FP, #%d\n", get_existing_offset(hashtable, left_tok));// 'LDR R#, FP, #offset_sym
		fputs(asm_line2, dest_file);
		strcpy(asm_line2, ""); // clear line
	}

	return 0;
}

/*
 * The major function that takes in a current buffer line and generates asm depending on its properties
* break line in to tokens and store tokens in array-- then use token attributes to call 1.) delcaration(), 2.) one_assignment(), 3.) multiple assignments(), 4.)return()
* if line has multiple assignments or commas is split on commas into smaller sublines and then processed accordingly
*/
int process_line(char* buffer, FILE* file_output){
	int num_assignments = 0;// necessary strings & values
	int num_additions = 0;
	int num_commas = 0;
	int add_index = 0;
	char* plus = "+";
	char* equals = "=";

	char* copy = malloc(sizeof(char)*strlen(buffer)+1);
	strcpy(copy, buffer);
	// GET ALL TOKENS IN LINE in arr_char_arrs[]
	int length = tokenize_line(buffer);// length==num tokens in current line of file; all toks stored in global array arr_char_arrs         
	num_assignments = get_num_assignments(length);
	//printf("number of assignments from get_num_operator is: %d\n", num_assignments);
	num_additions = get_num_additions(length);
	//printf("number of additions is (''): %d\n", num_assignments);
	num_commas = get_num_commas(length);

	// filling global array of where those addition symbols are in the token index
	if(num_additions>1){
		get_add_indices(length); // filling in '+' indices in global array add_indices[]                
	} else if(num_additions==1){
		add_index = get_char_index(plus, length);
		//printf("index of addition operator '+' is: %d\n", add_index);
	}

	// ready to assess if line is: 1. simple declaration, 2. 1 assignment, 3. declaration and (multiple), or 4. return statement
	//===============  1. simple declaration -- nothing to do =====================================
	if(strcmp(arr_char_arrs[0],"int")==0 && num_assignments==0){ //1. simple declaration -- nothing to do
		printf("line is type 1.) simple declaration-- no asm generated\n");
		//continue;
		// ================= 2.) simple (1) assignment =================================================
	} else if(num_assignments==1 && num_commas<1){ //2.) simple (1) assignment
		printf("line is type 2). contains 1 simple assignment '='\n");
		//int add_index = get_char_index(plus, length); // arr_char_arrs[add_index - 1] is 1st variable/number, arr_char_arrs[add_index+1] is 2nd variable/number
		//printf("index of addition operator '+' is: %d\n", add_index);

		// int x = 2 OR x = y
		if(num_additions==0){ // + not present in line
			// LDR R0 FP #offset_y
			// STR R0 FP #offset_x
			int equals_index = get_char_index(equals,length);
			//printf("index of equals '=' operator is: %d\n", equals_index);
			generate_asm_assignment(equals_index-1, equals_index+1, 0, file_output);

			// int x = a + b OR x = a + b + 7 OR t = 9 OR int q = 4 + 72, etc.
		} else if(num_additions>0) { // 1 or multiple instances of +
			int j = 1;
			int index_add = 0;
			for(int i=0;i<num_additions;i++){
				index_add = add_indices[i]; // get the location of all '+'s from global array
				generate_asm_addition((index_add-1), (index_add+1), regs[j], i,file_output);// set flag to i, so will do double load on 1st iteration, 
				//the regs for L varalso get incremented this way
				j++;
			}
			int equals_index = get_char_index(equals,length);
			generate_asm_assignment((equals_index-1),0,0,file_output);
		}
		// ======================= 3.) (possible) declaration and 2 or more assignments ===================
	} else if(num_assignments>1 || num_commas>0){ // 3.) (possible) declaration and 2 or more assignments
		printf("line is type 3). multiple assignments '=' in line AND/OR contains multiple line types\n");
		//break string into smaller tokens on the ',' and store in global arr multiple_assign_subarr[10];
		int x = 0;
		//char* copy = malloc(sizeof(char)*strlen(buffer)+1);
		//strcpy(copy, buffer);
		char* copy3 = malloc(sizeof(char)*strlen(buffer)+1);
		copy3 = strtok (copy,",");
		while (copy3 != NULL){
			//printf ("%s\n", copy3);
			//strcpy(multiple_assign_subarr[x],copy3);
			multiple_assign_subarr[x] = copy3;
			x++;
			copy3 = strtok (NULL, ",");
		}
		//printf("finished adding all substrings to global array multiple_assign_subarr[]\n");
		// x is last index reached -- start at line at index x and go backwards (processing right to left)
		for(int t=x-1; t>=0; t--){
			//copy3 = multiple_assign_subarr[t];
			printf("line at multiple_assign_subarr[%d]: %s\n", t, multiple_assign_subarr[t]);
			process_line(multiple_assign_subarr[t],file_output);
		}
		// ========================== 4.) 'return' statement =============================================
	} else if(strcmp(arr_char_arrs[0],"return")==0){
		printf("line is type 4). return line\n");
		// return x OR return 7 OR return x + 7 OR return x + y + z + 10, etc.

		int num_additions = get_num_additions(length);
		//printf("number of additions in line: %d\n", num_additions);
		if(num_additions==0){
			generate_asm_assignment(1, 0, 1, file_output);
		} else { // 1 or more additions
			int j = 1;
			int index_add = 0;
			for(int i=0;i<num_additions;i++){
				index_add = add_indices[i]; // get the location of all '+'s from global array
				generate_asm_addition((index_add-1), (index_add+1), regs[j], i,file_output);// set flag to i, so will do double load on 1st iteration, 
				//the regs for L varalso get incremented this way
				j++;
			}
			generate_asm_assignment(1, 0, 1, file_output);
		}
	}

	return 0;
}

// |							|
// |							|
// -------------- helper funcs (above) ------------------
// ------------------------------------------------------

// TODO do part 2, 2nd part -- error handling
//TODO global-- free all mallocs

/*
 * A helper function that parses a file and generates LC4 assembly for each line (used in compiler.c)
 */
int generate_asm(char* orig_filename, char* lc4_filename){
	// === file I/O ===
	FILE* file;
	FILE* file_output;
	char buffer[255];
	file = fopen(orig_filename, "r");// open for reading
	file_output = fopen(lc4_filename, "a"); // open/create for appending
	if(file==NULL){
		printf("File not found\n");
		return -1;
	}

	// ===== ITERATING OVER LINES ====
	// while more lines
	int line = 1;
	while(fgets(buffer, 255, (FILE*) file)) {
		printf("************ buffer line:******** %s\n", buffer);
		//arr_char_arrs = "";
		char* copy = malloc(sizeof(char)*strlen(buffer)+4);	
		char dest[50] = ";; ";

		//== write out line to file==
		if(line!=1 && strlen(buffer)>2){
			//fwrite(buffer, sizeof(buffer), 1, file_output);
			strcpy(copy, buffer);
			strcat(dest, copy);
			//fputs(buffer, file_output);
			fputs(dest, file_output);
		}

		// line is header
		if(line==1){
			line++;
			continue;
		} else { //no longer at header
			// break line in to tokens and store tokens in array-- then use token attributes to call delcaration(), one_assignment(), multiple assignments(), return()
			// all above encapsulated in below function
			process_line(buffer, file_output);
		} 
		clear_add_indices();// wipe this after each line
	}
	fclose(file);
	fclose(file_output);

	return 0;
}



/*
   int main(){

   char* filename = "sample.c";
   populate_symbol_table(filename);

   clear();

   return 1;
   }
 */
