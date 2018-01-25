#include <stdio.h>
#include <string.h>
#include <stdarg.h>

/*
 * Use this file for writing tests for your two functions.
 * 
 * Please do not add a "main" function to find_symbols.c; you should only define it here.
 * 
 * Although this file will not be considered for grading, please submit it so 
 * that we know how you tested your code.
 */


/*
 * The "extern" keyword tells the compiler that we're just declaring these here
 * but we'll define them in a different file.
 */
extern int parse_function_header(char*);
extern int parse_line(char*);
extern char* function_name;
extern char* parameter_names[10];
extern char* variable_names[10];

/*
 * These are prototypes/declarations for functions being defined below.
 */
void test_parse_function_header(char*, char*, int, ...);
void test_parse_line(char*, int, ...);
void check_function_name(char*);
void check_parameter(int, char*);
void check_variable(int, char*);

/*
 * Modify this function as you test out your code.
 */
int main() {

  printf("Calling parse_function_header with input \"int fun ( int dog , int cat ) {\"\n");
  /*
   * Here's an example of a test for parse_function_header
   * The first argument is the input to the parse_function_header function
   * The second argument is the correct name of the function in the input line
   * The third argument is the correct number of parameters in the input line
   * Arguments after that are the correct names of the parameters in the order in which they appear
   */
  test_parse_function_header("int fun ( int dog , int cat ) {", "fun", 2, "dog", "cat");


  printf("Calling parse_line with input \"int c , e = b ;\"\n");
  /*
   * Here's an example of a test for parse_line
   * The first argument is the input to the parse_line function
   * The second argument is the correct number of variables being declared in the input line
   * Arguments after that are the correct names of the variables in the order in which they are being declared
   */
  test_parse_line("int c , e = b ;", 2, "c", "e");

  return 0;

}

/*
 * These are the functions that test that the output is correct.
 */

void test_parse_function_header(char* input, char* function_name, int num_params, ...) {
  parse_function_header(input);

  check_function_name(function_name);

  if (num_params > 0) {
    va_list list;
    va_start(list, num_params);
    int i;
    for (i = 0; i < num_params; i++) {
      char* param_name = va_arg(list, char*);
      check_parameter(i, param_name);
    }
  }
}

void test_parse_line(char* input, int num_vars, ...) { 
  parse_line(input);

  if (num_vars > 0) {
    va_list list;
    va_start(list, num_vars);
    int i;
    for (i = 0; i < num_vars; i++) {
      char* var_name = va_arg(list, char*);
      check_variable(i, var_name);
    }
  }
}



/* 
 * These are helper functions to check the value and print out appropriate messages.
 */

void check_function_name(char* name) {
  if (function_name != NULL) {
    if (strcmp(function_name, name) == 0) {
      printf("function_name %s is correct.\n", function_name);
    }
    else {
      printf("ERROR! function_name should be %s but is %s.\n", name, function_name);
    }
  }
  else {
    printf("ERROR! function_name should be %s but is NULL.\n", name);
  }
}

void check_parameter(int index, char* name) {
  if (parameter_names[index] != NULL) {
    if (strcmp(parameter_names[index], name) == 0) {
      printf("parameter[%d] %s is correct.\n", index, parameter_names[index]);
    }
    else {
      printf("ERROR! parameter[%d] should be %s but is %s.\n", index, name, parameter_names[index]);
    }
  }
  else {
    printf("ERROR! parameter[%d] should be %s but is NULL.\n", index, name);
  }
}

void check_variable(int index, char* name) {
  if (variable_names[index] != NULL) {
    if (strcmp(variable_names[index], name) == 0) {
      printf("variable[%d] %s is correct.\n", index, variable_names[index]);
    }
    else {
      printf("ERROR! variable[%d] should be %s but is %s.\n", index, name, variable_names[index]);
    }
  }
  else {
    printf("ERROR! variable[%d] should be %s but is NULL.\n", index, name);
  }
}
    
