#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * These are the global variables that you need to populate in the functions below.
 * Do not change these variables' names or types!
 */
char* function_name;
char* parameter_names[10];
char* variable_names[10];

// declaration for helper function to reset global variables
void reset();

// ======== PART I: TO MATCH =========
// int fun0 ( ) {
// int fun1 ( int a ) {
// int fun2 ( int dog , int cat ) {
// int fun3 ( int larry , int moe , int curly ) {
// possible regex: " *[A-Za-z]+ +[A-Za-z0-9_] *\( *[A-Za-z0-9]* *\,* *\) *\{ "
// _int_ : _*[A-Za-z]+ +
// _func_n4m3 (:  " +[A-Za-z0-9_]* *\("
// *(int|char|void|float|double)\**[^ A-Za-z]

//================= HW1 - PART I ====================

/*
 * This is the function you need to implement for Parts 1 and 3.
 * You must NOT change its signature! 
 */
int parse_function_header(char* header) { //takes in pointer to a header
  if (header == NULL) return -1;

  // clean up the global variables - do not remove this line!
  reset();

	// ---------- making 2D array ------------
  	// char strs[NUMBER_OF_STRINGS][STRING_LENGTH+1];
	char arr_char_arrs[20][30] = {""};
	//strcpy(arr_char_arrs[0], aString); // where aString is either an array or pointer to char
	//strcpy(arr_char_arrs[1], "foo");

	// ------------- tokenizing string ------------
	//printf ("Splitting string \"%s\" into tokens:\n", header);

	char * token;
	char * input_copy = malloc(sizeof(char)*strlen(header));
	strcpy(input_copy, header) ; // make copy of immutable input string
	//char * token = malloc(sizeof(char) * 20) ;
	token = strtok (input_copy, " ");
	//char token[20];
	//token[0] = *strtok (header, " ");
	//char* toks[10];
	int x = 0 ;
	//arr_char_arrs[x] = token;
	//printf("%s\n", token);
	strcpy(arr_char_arrs[x], token);
	x++;
	while (token != NULL) {
		// printf ("%s\n", token);
		token = strtok (NULL, " ");
		//printf ("%s\n", token);
		if (token == NULL) {
			break;
		}
		// arr_char_arrs[x] = token ;
		strcpy(arr_char_arrs[x], token);
		// printf("toks[%d]: %s\n", x, toks[x]);
		x++;
	}
	//a[x] = "\0" ;
	strcpy(arr_char_arrs[x], "\0");

	// ------------ TOKEN ARRAY -------------
	// int length = strlen(arr_char_arrs);
	int length = x;
	//printf("Length: %d\n", length);
	//printf("======= Tokens ====== \n");
	//for (int i = 0; i < length; i++) {
	//	printf("arr_char_arrs[%d]: %s\n", i, arr_char_arrs[i]);
	//}

	// ----------- getting parameters ---------
	if (length > 5) {
		//toks[2] will always be '\(' && toks[length - 2] will always be '\)'
		// toks[4] will be the 1st param is there is one, and toks[length - 3] will be the last
		// params (if multiple) will be 3 indices apart
		int a = 4;
		int b = 0;
		int limit = length - 2;
		// for(int a = 0; a < (length-2); a=a+3){
		while (a < limit) {
			//parameter_names[b] = toks[a];
			// printf("arr_char_arrs[%d]: %s\n", a, arr_char_arrs[a]);
			parameter_names[b] = malloc(sizeof(char)*strlen(arr_char_arrs[a]));
			strcpy(parameter_names[b], arr_char_arrs[a]);
			//parameter_names[b] = arr_char_arrs[a] ;
			// printf("parameter_names[%d]: %s\n", b, parameter_names[b]);
			a += 3;
			b++;
		}
	}

	//printf("\n======== OUTPUT: ==========\n") ;
	function_name = malloc(sizeof(char)*strlen(arr_char_arrs[1]));
	strcpy(function_name, arr_char_arrs[1]);

	//function_name = arr_char_arrs[1] ;
	//printf("\nfunction name: %s\n", function_name);
	//for (int v = 0; v < 10; v++) { //length of 10
	// 	printf("parameter_names[%d]: %s\n", v, parameter_names[v]);
	//}

	free(input_copy);
  // Be sure to return the correct value in Part 3.
  return 1;
}

//================= HW1 - PART I (ABOVE) ====================

/*
 * This is the function you need to implement for Parts 2 and 4.
 * You must NOT change its signature! 
 */
int parse_line(char* line) {
  if (line == NULL) return -1;

  // clean up the global variables - do not remove this line!
  reset();

  // -------------- 2D array to store line tokens -----------
  char arr_char_arrs[20][30] = {""};
  // ------------- tokenizing string ------------
  //printf ("<<<<<<<<<< Splitting line \"%s\" into tokens >>>>>>>>:\n", line);
  char * token;
  char * input_copy = malloc(sizeof(char)*strlen(line));
  strcpy(input_copy, line) ; // make copy of immutable input string
  token = strtok (input_copy, " ");
  int x = 0 ;
  //printf("%s\n", token);
  strcpy(arr_char_arrs[x], token);
  x++;
  while (token != NULL) { //store tokens in array of tokens
	token = strtok (NULL, " ");
        //printf ("%s\n", token);
        if (token == NULL) {
            break;
        }
        strcpy(arr_char_arrs[x], token);
        x++;
  }
  strcpy(arr_char_arrs[x], "\0");

  // ------------ TOKEN ARRAY -------------
  int length = x;
  //printf("Length: %d\n", length);
  // printf("======= Tokens ====== \n");
  // for (int i = 0; i < length; i++) {
  //    printf("arr_char_arrs[%d]: %s\n", i, arr_char_arrs[i]);
  // }
  // ----------- getting variables ---------
  // if first token is 'int'
  if(strcmp(arr_char_arrs[0],"int")==0){
	// arr_char_arrs[0] will always be 'int', and what follows, arr_char_arrs[1] will always be the first declaration
    	//printf("\n****** match! *****\n") ;
    	variable_names[0] = malloc(sizeof(char)*strlen(arr_char_arrs[1])) ; //malloc space for 1st var
    	strcpy(variable_names[0],arr_char_arrs[1]);
    	int var_index = 1;
    	int iter_till_end = length-2;
    	if (iter_till_end > 1){
    		iter_till_end += 2;
    		for(int start = 2; start < iter_till_end; start++){
    			if(strcmp(arr_char_arrs[start], ",") == 0){ //if token value at current index is ","
    				variable_names[var_index] = malloc(sizeof(char)*strlen(arr_char_arrs[start+1])) ;
    				strcpy(variable_names[var_index], arr_char_arrs[start+1]); //copy value right after "," into var array
    				var_index++;
    			} 
    		}
    	}
  }
   
  //printf("\n======== OUTPUT: ==========\n") ;
  // function_name = malloc(sizeof(char)*strlen(arr_char_arrs[1]));
  // strcpy(function_name, arr_char_arrs[1]);
    
  // //function_name = arr_char_arrs[1] ;
  // printf("\nfunction name: %s\n", function_name);
  //for (int v = 0; v < 10; v++) { //length of 10
  //      printf("variable_names[%d]: %s\n", v, variable_names[v]);
  //}
    
  free(input_copy);
  // Be sure to return the correct value in Part 4.
  return 1;
}


/*
 * This helper function resets the global variables so we can 
 * check them when grading your assignment.
 * Do not change this function!
 */
void reset() {
  if (function_name != NULL) free(function_name);
  function_name = NULL;
  int i;
  for (i = 0; i < 10; i++) {
    if (parameter_names[i] != NULL) free(parameter_names[i]);
    parameter_names[i] = NULL;
  }
  for (i = 0; i < 10; i++) {
    if (variable_names[i] != NULL) free(variable_names[i]);
    variable_names[i] = NULL;
  }
}

/**
int main(){
	char* input = "int fun ( int dog , int cat ) {";
	parse_function_header(input);
}
**/

