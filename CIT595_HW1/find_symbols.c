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
    printf ("Splitting string \"%s\" into tokens:\n", header);
    
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
    //    printf("arr_char_arrs[%d]: %s\n", i, arr_char_arrs[i]);
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
    
    printf("\n======== OUTPUT: ==========\n") ;
    function_name = malloc(sizeof(char)*strlen(arr_char_arrs[1]));
    strcpy(function_name, arr_char_arrs[1]);
    
    //function_name = arr_char_arrs[1] ;
    printf("\nfunction name: %s\n", function_name);
    for (int v = 0; v < 10; v++) { //length of 10
        printf("parameter_names[%d]: %s\n", v, parameter_names[v]);
    }
    
    free(input_copy);
    // Be sure to return the correct value in Part 3.
    return 1;
}
//================= HW1 - PART I (ABOVE) ====================




////==================INCORRECT VERSION==================================
///*
// * This is the function you need to implement for Parts 1 and 3.
// * You must NOT change its signature!
// */
//int parse_function_header(char* header) {
//    if (header == NULL) return -1;
//    // clean up the global variables - do not remove this line!
//    reset();
//    // char strs[NUMBER_OF_STRINGS][STRING_LENGTH+1];
//    char arr_char_arrs[20][30] = {""};
//    //strcpy(arr_char_arrs[0], aString); // where aString is either an array or pointer to char
//    //strcpy(arr_char_arrs[1], "foo");
//    printf ("Splitting string \"%s\" into tokens:\n", str4);
//    char * token;
//    token = strtok (str4, " ");
//    //char* toks[10];
//    int x = 0 ;
//    //arr_char_arrs[x] = token;
//    printf("%s\n", token);
//    strcpy(arr_char_arrs[x], token);
//    x++;
//    while (token != NULL) {
//        // printf ("%s\n", token);
//        token = strtok (NULL, " ");
//        // printf ("%s\n", token);
//        if (token == NULL) {
//            break;
//        }
//        // arr_char_arrs[x] = token ;
//        strcpy(arr_char_arrs[x], token);
//        // printf("toks[%d]: %s\n", x, toks[x]);
//        x++;
//    }
//    //a[x] = "\0" ;
//    strcpy(arr_char_arrs[x], "\0");
//    // ------------ TOKEN ARRAY -------------
//    // int length = strlen(arr_char_arrs);
//    int length = x;
//    printf("Length: %d\n", length);
//    printf("======= Tokens ====== \n");
//    for (int i = 0; i < length; i++) {
//        printf("arr_char_arrs[%d]: %s\n", i, arr_char_arrs[i]);
//    }
//    // ----------- getting parameters ---------
//    if (length > 5) {
//        //toks[2] will always be '\(' && toks[length - 2] will always be '\)'
//        // toks[4] will be the 1st param is there is one, and toks[length - 3] will be the last
//        // params (if multiple) will be 3 indices apart
//        int a = 4;
//        int b = 0;
//        int limit = length - 2;
//        // for(int a = 0; a < (length-2); a=a+3){
//        while (a < limit) {
//            //parameter_names[b] = toks[a];
//            // printf("arr_char_arrs[%d]: %s\n", a, arr_char_arrs[a]);
//            parameter_names[b] = arr_char_arrs[a] ;
//            // printf("parameter_names[%d]: %s\n", b, parameter_names[b]);
//            a += 3;
//            b++;
//        }
//    }
//    // printf("\n======== OUTPUT: ==========\n") ;
//    function_name = arr_char_arrs[1] ;
//    // printf("\nfunction name: %s\n", function_name);
//    // for (int v = 0; v < 10; v++) { //length of 10
//    //     printf("parameter_names[%d]: %s\n", v, parameter_names[v]);
//    // }
//    // Be sure to return the correct value in Part 3.
//    return 1;
//}


int main() {
	char* null = "0/";
	char all_str[10];
	char str[] = "- This, a sample string.";
	char str1[] = "int fun0 ( ) {";
	char str2[] = "int fun1 ( int a ) {" ;
	char str3[] = "int fun2 ( int dog , int cat ) {" ;
	char str4[] = "int fun3 ( int larry , int moe , int curly ) {" ;

	return 0;
}










//=====================================================

/*
 * This is the function you need to implement for Parts 2 and 4.
 * You must NOT change its signature!
 */
int parse_line(char* line) {
	if (line == NULL) return -1;

	// clean up the global variables - do not remove this line!
	reset();


	// WRITE THE REST OF THIS FUNCTION!


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

