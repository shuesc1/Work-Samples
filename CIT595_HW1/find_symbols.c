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
//==============================================================

/*
 * This is the function you need to implement for Parts 1 and 3.
 * You must NOT change its signature! 
 */
int parse_function_header(char* header) {
  if (header == NULL) return -1;

  // clean up the global variables - do not remove this line!
  reset();

  // WRITE THE REST OF THIS FUNCTION!








  for(int j = 0; j < 11; j++){
  	if()
  	printf("parameter(s) name(s):", parameter_names[j]) ;
  }
  printf("function name: %s", fun_name) ;
  // Be sure to return the correct value in Part 3.
  return 1;
}

int main(){
  char* null = "0/";
  char all_str[10];
  char str[] ="- This, a sample string.";
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

