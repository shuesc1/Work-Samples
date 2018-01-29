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

//===================================================================
//===================================================================
//======================== PART II - PARSE HEADER ===================
//===================================================================
//===================================================================


// a helper function to assess an identifier if it's legal or not
int eval_identifier(char* identifier){
        //64-90 ==> A-Z ASCII ; 97-122 ==> a-z ASCII
        int result = 1;
        if(identifier[0] > 63 && identifier[0] < 91 | identifier[0] > 96 && identifier[0] < 123){
                result = 0 ;
        } else {
                result = 1;
        }
        return result;
}

// ======== PART I: TO MATCH =========
// int fun0 ( ) {
// int fun1 ( int a ) {
// int fun2 ( int dog , int cat ) {
// int fun3 ( int larry , int moe , int curly ) {
// possible regex: " *[A-Za-z]+ +[A-Za-z0-9_] *\( *[A-Za-z0-9]* *\,* *\) *\{ "
// _int_ : _*[A-Za-z]+ +
// _func_n4m3 (:  " +[A-Za-z0-9_]* *\("
// *(int|char|void|float|double)\**[^ A-Za-z]

//================= HW1 - PART II ====================

/*
 * This is the function you need to implement for Parts 1 and 3.
 * You must NOT change its signature! 
 */
int parse_function_header(char* header) { //takes in pointer to a header
  if (header == NULL) return -1;

  // clean up the global variables - do not remove this line!
  reset();

  	int valid = 1; //1 - True, 0 - False

	// ---------- making 2D array ------------
	char arr_char_arrs[20][30] = {""};

	// ------------- tokenizing string ------------
	printf ("\n===================[ \"%s\"]================ \n", header);
	char * token;
	char * input_copy = malloc(sizeof(char)*strlen(header));
	strcpy(input_copy, header) ; // make copy of immutable input string
	token = strtok (input_copy, " ") ;
	int x = 0 ;
	//printf("%s\n", token) ;
	strcpy(arr_char_arrs[x], token);
	x++;
	while (token != NULL) {
		token = strtok (NULL, " ");
		//printf ("%s\n", token);
		if (token == NULL) {
			break;
		}
		strcpy(arr_char_arrs[x], token);
		// printf("toks[%d]: %s\n", x, toks[x]);
		x++;
	}
	strcpy(arr_char_arrs[x], "\0");

	// ------------ TOKEN ARRAY -------------
	int length = x;
/*	printf("Length: %d\n", length);
	printf("======= Tokens ====== \n");
	for (int i = 0; i < length; i++) {
		printf("arr_char_arrs[%d]: %s\n", i, arr_char_arrs[i]);
	}
*/
	//================= PART II - START ===============
	// <<<<A)>>>>
	// arr_char_arrs[0] can be 'func' & arr_char_arrs[1] can be '\(' OR arr_char_arrs[0] is 'int' or 'void', arr_char_arrs[1] is 'func_name', and arr_char_arrs[2] is '\('
	// '\(' can either be at index 1 OR 2; it cannot be any greater than that or less than that
	
	/*
	// CASE: arr_char_arrs[0] ===========> '(' -- invalid --- taken care of below
	if(strcmp(arr_char_arrs[0], "(")==0){
		valid = 0 ;
	}
	*/

	if(valid==1){ //only check if still valid
		// CASE: arr_char_arrs[1] = '(' OR arr_char_arrs[2] = '(' -- only 2 valid cases
		//=================>  "(...."
		if(strcmp(arr_char_arrs[1], "(")==0 |strcmp(arr_char_arrs[2], "(")==0){ // opening '(' must be at one of these two locations, otherwise illegal
			valid = 1;
			if(strcmp(arr_char_arrs[1], "(")==0){ // "func_name (..."
				if(eval_identifier(arr_char_arrs[0])!=0){
					valid = 0;
					printf("ILLEGAL: func name starts with a non-letter\n");
				}	
			} else if(strcmp(arr_char_arrs[2], "(")==0){ // "int func_name (...."
				if(eval_identifier(arr_char_arrs[1])!=0){
                                        valid = 0;
                                        printf("ILLEGAL: func name starts with a non-letter\n");
                                }    
			}
		} else {
			valid = 0;
			printf("ILLEGAL: '(',is not at index 1 or 2; thus string is invalid\n");
		}
	}

	if(valid==1){
		// CASE: last or second to last character must be ')'
		//=================> ".....)" or ".....) {"	 
		//printf("second to last char is: [%s] ; last char is [%s]\n", arr_char_arrs[x-2], arr_char_arrs[x-1]);
		if(strcmp(arr_char_arrs[x-2], ")")==0 |strcmp(arr_char_arrs[x-1], ")")==0){
                        valid = 1;
			if(strcmp(arr_char_arrs[x-2], ")")==0 && strcmp(arr_char_arrs[x-1], "{")!=0){
				valid = 0;
				printf("ILLEGAL due to fact that 2nd to last char is ')' but last char is not '{'\n");
			}
                } else {
                        valid = 0;
                        printf("ILLEGAL: ')',is not at the ultimate or penultimate index; thus string is invalid\n");
                }   

	}

	if(valid==1){ //only check while statement still valid-- input doesn't start with '(', the '(' is in index [1] or [2]
		// CASE: null return type, arr_char_arrs[0] = 'a_string' & arr_char_arrs[1] = '('
		// "valid_fun_name (..."
		if(strcmp(arr_char_arrs[1], "(")==0){
			if(strcmp(arr_char_arrs[0], "int")==0 | strcmp(arr_char_arrs[0],"void")==0){
				valid = 0;
				printf("ILLEGAL: '(' at second index and func name is a reserved work 'INT' or 'VOID'\n");
			}
		// CASE: 'int' or 'void' return type, arr_char_arrs[1] 'func_name', arr_char_arrs[2] = ')'
		// "int/void valid_fun_name (...."
		} else if(strcmp(arr_char_arrs[2], "(")==0){
			if(strcmp(arr_char_arrs[0], "int") != 0 && strcmp(arr_char_arrs[0], "void") != 0){
				valid = 0;
				printf("ILLEGAL: first token '%s' is not either 'INT' or 'VOID'\n", arr_char_arrs[0]);
			}
			//printf("*arr_char_arrs[1]: %c\n", *arr_char_arrs[1]);
			//printf("*arr_char_arrs[1]: %d\n", *arr_char_arrs[1]);
		}
	}

	// start and end have now been verified --> "int valid_name ( ... ) {" OR "int valid_name ( .... )" OR " valid_name ( ... )", etc.
	int start = 0;
	int end = 0 ;
	if(valid == 1){
		for(int i = 0; i < x-1; i++){
			if(strcmp(arr_char_arrs[i],"(")==0){
				start = i;
			}
			if(strcmp(arr_char_arrs[i],")")==0){
                                end = i;
                        }
			if(strcmp(arr_char_arrs[i],"(")==0 && strcmp(arr_char_arrs[i+1],")") !=0){
				if(strcmp(arr_char_arrs[i+1],"int")!=0 | eval_identifier(arr_char_arrs[i+2])!=0){
					valid = 0;
					printf("ILLEGAL: invalid syntax - not 'int func_name'\n");
					//break;
				}
			} else if(strcmp(arr_char_arrs[i],",")==0){
		                if(strcmp(arr_char_arrs[i+1],"int")!=0 | eval_identifier(arr_char_arrs[i+2])!=0){
                                        valid = 0;
                                        printf("ILLEGAL: invalid syntax - not 'int func_name'\n");
					//break;
                                }
			}
		}
	}

	if(valid==1){
		//printf("start: %d\n", start);
		//printf("end: %d\n", end);
		if((end-start)%3 != 0 && (start+1 != end) && end > start){
			valid = 0;
			printf("ILLEGAL: incorrect number of args between parens\n");
		}
	}

  // once string has been checked and deemed fully valid
  if(valid == 1){
	// ----------- getting parameters ---------
	if (length > 5) {
		//toks[2] will always be '\(' && toks[length - 2] will always be '\)'
		// toks[4] will be the 1st param is there is one, and toks[length - 3] will be the last
		// params (if multiple) will be 3 indices apart
		//int a = 4;
		int a = start + 2;
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
	printf("\nfunction name: %s\n", function_name);
	for (int v = 0; v < 10; v++) { //length of 10
	 	printf("parameter_names[%d]: %s\n", v, parameter_names[v]);
	}
  }

	free(input_copy);
  // Be sure to return the correct value in Part 3.
  return valid; //1 if T, 0 if F
}

//================= HW1 - PART II (ABOVE) ====================


//===================================================================
//===================================================================
//======================== PART II - PARSE LINE =====================
//===================================================================
//===================================================================


// ======================================================
// ============== LINE SYNTAX HELPER FUNCS ==============
// ======================================================
// |							|
// |							|

// ==================== VALID NAME???? ================
/*
* A function that takes in a string ptr and iterates over it to check all letters to see if they are valid [A-Za-z_], not 'VOID' or 'INT'
*/
int valid_name(char* var_name){ // 0 - valid ; 1 - invalid
  int equal = 0;
  int length = strlen(var_name);
  //printf("length: %d\n", length);

  if(strcmp(var_name,"int")==0|strcmp(var_name,"void")==0){ // is 'INT' or 'VOID'
        equal = 1;
  } else {
        for(int i = 0; i < length; i++){
          if((var_name[i] == 95) | (var_name[i]>64 && var_name[i]<91) | (var_name[i]>96 && var_name[i]<123)){ // is '_', or 'A-Z', or 'a-z'
                equal = 0;
          } else {
                equal = 1;
                //printf("ILLEGAL: identifier/variable contains illegal character\n");
          }
        }
  }
  return equal;
}
// ====================== VALID NUMBER??? ==================
/*
* A function that takes in a string ptr and iterates over it to check all chars to see if they are valid [0-9]*
*/
int valid_num(char* num_string){ // 0 - valid ; 1 - invalid
  int equal = 0;
  int length = strlen(num_string);
  //printf("length: %d\n", length);
  for(int i = 0; i < length; i++){
	if((num_string[i] < 48) | (num_string[i] > 57)){
		equal = 1;
		break;
	}
  }
  return equal;
}
// ===================== VALID OPERATOR +-*DIV%?? ================
/*
* A function that takes in a char and checks it to see if it is a valid operator [+-*%/]
*/
int valid_operator(char op_char){ // 0 - valid ; 1 - invalid
  int equal = 0;
  if(op_char==43|op_char==45|op_char==47|op_char==42|op_char==35){ // +==43 ; -==45 ; /==47 ; *==42 ; %==35
        equal = 0;
  } else {
        equal = 1;
        //printf("ILLEGAL: operator '%c' is not a valid operator (+, -, *, /, mod ) \n", op_char);
  }
  return equal;
}

//===================== PARSE -- 'INT' ===============
// A func that parses the line if it starts with 'INT'
//  " int ..... ; "
int parse_int_line(char tok_arr[20][30], int len){ // 0 - ILLEGAL ; 1 - legal
  int result = 1;
  int string_len = len ;
  //printf("length: %d\n", string_len);

  // ~~~~~~~ String needs to satisfy 6 conditions:~~~~~~~~~~
  // 1) min length is 3 tokens; any less automatically is elminated
  if(len < 3){
	result = 0;
  } else {
    // 2) 'int' needs to be directly followed by a valid variable name
    if(valid_name(tok_arr[1])!=0){
	result = 0;
	printf("ILLEGAL: 'int' not directly followed by a valid variable name\n");
    } else { // legal so far
    	//printf("array of toks passed to 'parse_int_line': ");
    	for(int i = 0; i < len; i++){
		// 3) '=' must be preceded and followed by valid variable names OR followed by a valid number
		if(strcmp(tok_arr[i],"=")==0){
			if((valid_name(tok_arr[i-1])!=0) | (valid_name(tok_arr[i+1])!=0 && valid_num(tok_arr[i+1])!=0)){
				result=0;
				printf("ILLEGAL: '=' not following a valid variable name and/or not before a valid variable or number\n");
				break;
			}
		}
		// 4) ',' must be preceded by a valid variable name OR valid number
		if(strcmp(tok_arr[i],",")==0){
                        if(valid_name(tok_arr[i-1])!=0 && valid_num(tok_arr[i-1])!=0){
                                result=0;
                                printf("ILLEGAL: ',' not following a valid variable name or valid number\n");
                        	break;
			}
                }
		// 5) a valid variable name must not be preceded by another variable name (anything other than 'INT')
		if(valid_name(tok_arr[i])==0){
                        if(valid_name(tok_arr[i-1])==0){
                                result=0;
             			printf("ILLEGAL: two variable names in a row\n");
                        	break;
			}
                }	
		// 6) any operator + - / * % must be preceded & followed by a valid variable name OR valid number
                if(valid_operator(*tok_arr[i])==0){
                        if((valid_name(tok_arr[i-1])!=0 && valid_num(tok_arr[i-1])!=0) | (valid_name(tok_arr[i+1])!=0 && valid_num(tok_arr[i+1])!=0)){
                                result=0;
                                printf("ILLEGAL: operator (+,-,etc.) not between 2 variables and/or 2 numbers\n");
                        	break;
			}
                }
		// 7) any number should not be preceded for followed by a variable name
		if(valid_num(tok_arr[i])==0){
			if((valid_name(tok_arr[i-1])==0) | (valid_name(tok_arr[i+1])==0)){
				result = 0;
				printf("ILLEGAL: number followed or preceded by a variable\n");
				break;
			}	
		}
    	}
    }
  } 
  return result;
}
// ================= PARSE -- 'RETURN.....;'=============
/*
* a func that parses a line if it starts with 'RETURN' ;  " return .... ; "
*/
int parse_return_line(char tok_arr[20][30], int len){
  int result = 1;
  int string_len = len ;
  // ~~~~~~~ String needs to satisfy 4 conditions:~~~~~~~~~~
  for(int i = 0; i < len; i++){
 	// 1) tok[0] is 'return', tok[last] is ';'
	// 2) 'return' must be followed by valid variable names OR a valid number OR ';'
  	if(strcmp(tok_arr[i],"return")==0){
        	if((valid_name(tok_arr[i+1])!=0)&&(valid_num(tok_arr[i+1])!=0)&&(strcmp(tok_arr[i+1],";")!=0)){
                	result=0;
                        printf("ILLEGAL: 'return' not followed by a valid variable/number/;\n");
                        break;
                }
        }
        // 3) a valid variable name must not be preceded by another variable name
        if(valid_name(tok_arr[i])==0 && i!=0){
        	if(valid_name(tok_arr[i-1])==0){
                        result=0;
                        printf("ILLEGAL: two variable names in a row\n");
                	break;
               	}
        }
        // 4) any operator + - / * % must be preceded & followed by a valid variable name OR valid number
        if(valid_operator(*tok_arr[i])==0){
        	if((valid_name(tok_arr[i-1])!=0 && valid_num(tok_arr[i-1])!=0) | (valid_name(tok_arr[i+1])!=0 && valid_num(tok_arr[i+1])!=0)){
                	result=0;
                        printf("ILLEGAL: operator (+,-,etc.) not between 2 variables and/or 2 numbers\n");
                        break;
                }
        }
   }
  return result;
}
// ==================== PARSE --'X = ....' ===============
/*
* a func that parses an assign line that takes the format 'var = .... ; ' ;  " a = ..... ; "
*/ 
int parse_assign_line(char tok_arr[20][30], int len){
  int result = 1;
  int string_len = len ;
 
  // 1) if doesn't start with a valid variable then illegal
  if(valid_name(tok_arr[0])!=0){
	result = 0;
	printf("ILLEGAL: doesn't start with a valid variable\n");
  }
  if(result==1){
	// 2) if 2nd token isn't '=' then illegal
  	if(strcmp(tok_arr[1], "=")!=0){
		result = 0;
		printf("ILLEGAL: second token not assignment operator =\n");
  	}
  }
  if(result==1){
  	for(int i = 0; i < len; i++){
		// 3) any operator +-/*% must be in between variables/numbers
		if(valid_operator(*tok_arr[i])==0){
		        if((valid_name(tok_arr[i-1])!=0 && valid_num(tok_arr[i-1])!=0) | (valid_name(tok_arr[i+1])!=0 && valid_num(tok_arr[i+1])!=0)){
                        result=0;
                        printf("ILLEGAL: operator (+,-,etc.) not between 2 variables and/or 2 numbers\n");
                        break;
                	}
		} 
                // 4) any number should not be preceded for followed by a variable name
                if(valid_num(tok_arr[i])==0){
                        if((valid_name(tok_arr[i-1])==0 && strcmp(tok_arr[i-1],"return")!=0) | (valid_name(tok_arr[i+1])==0)){
                                result = 0;
                                printf("ILLEGAL: number followed or preceded by a variable\n");
                                break;
                        }
                }
	}
  }
  return result;
}
// |							|
// |							|
// ======================================================
// ======================================================

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
  printf ("<<<<<<<< %s >>>>>>>>>>\n", line);
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
 
  // ----------- SYNTAX CHECKING ------------

  int valid = 1;

  // first check -- ends in ';'
  if(strcmp(arr_char_arrs[x-1],";") != 0){ // if line doesn't end in ';' automatically invalid
	valid = 0;
	printf("ILLEGAL: line does not end with ';'\n");
  }
  // 2nd check -- depends on first token
  if(valid ==1){
	if(strcmp(arr_char_arrs[0], "int")==0){ // " int ...... ; "
		valid = parse_int_line(arr_char_arrs, length);
	} else if (strcmp(arr_char_arrs[0], "return")==0){ // "return ..... ; "
		valid = parse_return_line(arr_char_arrs, length);
	} else {
		valid = parse_assign_line(arr_char_arrs, length); // " z = .... ; "
	}
  }  



  // ----------- SYNTAX CHECKING (ABOVE) ------------

 // ----------- getting variables ---------
  // if first token is 'int'
  if(strcmp(arr_char_arrs[0],"int")==0 && valid == 1){
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

  /*
  printf("\n======== OUTPUT: ==========\n") ;
  for (int v = 0; v < 10; v++) { //length of 10
        printf("variable_names[%d]: %s\n", v, variable_names[v]);
  }
  */

  free(input_copy);
  // Be sure to return the correct value in Part 4.
  return 1;
}


// ============ MAIN METHOD ============
//=====================================

int main(){
        //char* input = "int fun ( int dog , int cat ) {";
        //parse_function_header(input);
/*
        // TESTING ---- PARSE_FUNC_HEADER
        char in1_val[] = "int fun ( )";
        parse_function_header(in1_val);

        char in2_val[] = "void fun ( int a ) {" ;
        parse_function_header(in2_val);

        char in3_val[] = "fun ( int dog , int cat ) {" ;
        parse_function_header(in3_val);

        char in4[] = "cat fun ( )" ;
        parse_function_header(in4);     

        char in5[] = "int ( int a ) {" ;
        parse_function_header(in5);  

        char in6[] = "int fun ( a , int b ) {" ;
        parse_function_header(in6);  

        char in7[] = "int fun ( int dog cat ) {" ;
        parse_function_header(in7);  

        char in8[] = "int fun ( int dog , )" ;
        parse_function_header(in8);  

        char in9[] = "int fun ( int 8cow )" ;
        parse_function_header(in9);  

        char in10[] = "int fun ( int cow, int dog ) chicken";
        parse_function_header(in10);

        char in11[] = "int 8fun ( int cow , int dog ) {";
        parse_function_header(in11);

        char in12[] = "int fun fun ( int cow, int dog )";
        parse_function_header(in12);

        char in13[] = "int fun ( int cow , dog ) {";
        parse_function_header(in13);

        char in14[] = "int fun8 ( int cow , int8 dog )";
        parse_function_header(in14);

        char in15[] = "int fun ( int cow , dog dog ) {";
        parse_function_header(in15);
*/
    char line1[] = "int x ;";
    parse_line(line1);
    char line2[] = "int j , k ;" ;
    parse_line(line2);
    char line3[] = "int y = 2 ;" ;
    parse_line(line3);
    char line4[] = "int a = x + 5 ;";
    parse_line(line4);
    char line5[] = " int b = a + y + 8 ;" ;
    parse_line(line5);
    char line6[] = "int c = a + x , d , e = b ;" ;
    parse_line(line6);
    char line7[] = "int x";
    parse_line(line7);
    char line8[] = "int j k ;";
    parse_line(line8);
    char line9[] = "int y = ;";
    parse_line(line9);
    char line10[] = "int a = x 5 ;" ;
    parse_line(line10);
    char line11[] = "int void ;" ;
    parse_line(line11);
    char line12[] = "= 9 ;" ;
    parse_line(line12);
    char line13[] = "x = 8" ;
    parse_line(line13);
    char line14[] = "a = b + ;" ;
    parse_line(line14);
    char line15[] = "y = + d ;" ;
    parse_line(line15);
    char line16[] = "m = a + b + ;" ;
    parse_line(line16);
    char line17[] = "int x = + 2 ;";
    parse_line(line17);
    char line18[]="int b = x + - 5 ;";
    parse_line(line18);
    char line19[]="cat x = 11 ;";
    parse_line(line19);
    char line20[]="return x";
    parse_line(line20);
    char line21[]="return 3 + ;";
    parse_line(line21);

}


//================ RESET ==================================
//=========================================================
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

