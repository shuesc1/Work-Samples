#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* function_name;
char* parameter_names[10];
char* variable_names[10];

// declaration for helper function to reset global variables
void reset();

/*
 * This is a solution to Part 1.
 */
int parse_function_header(char* header)
{
  if (header == NULL) return 0;

  reset();

  // we want to use strtok but it modifies the string, so we need a copy of it
  char* copy = malloc(strlen(header) + 1);
  strcpy(copy, header);

  // first token is "int" -- ignore it
  strtok(copy, " ");

  // next is function name
  char* fun_name = strtok(NULL, " ");
  //printf("found function name: %s.\n", fun_name);
  function_name = malloc(strlen(fun_name) + 1);
  strcpy(function_name, fun_name);

  // next is "(" -- ignore it
  strtok(NULL, " ");

  // now read all the parameter names
  int param_count = 0;

  while (1) {

    char* next_tok = strtok(NULL, " ");

    // if it's "int" then we found a parameter
    if (strcmp(next_tok, "int") == 0) {
      char* param_name = strtok(NULL, " ");
      parameter_names[param_count] = malloc(strlen(param_name) + 1);
      strcpy(parameter_names[param_count], param_name);
      param_count++;    

      // now read the next one
      next_tok = strtok(NULL, " ");
    }

    // if the last thing we read is ")" then we're done
    if (strcmp(next_tok, ")") == 0) break;

  }

  free(copy);

  return 1;

}


/*
 * This is a solution to Part 2.
 */
int parse_line(char* line)
{
  if (line == NULL) return 0;

  reset();

  // we want to use strtok but it modifies the string, so we need a copy of it
  char* copy = malloc(strlen(line) + 1);
  strcpy(copy, line);

  char* first_tok = strtok(copy, " ");
  
  // if the first token is "int", then there's at least declaration here
  if (strcmp(first_tok, "int") == 0) {

    // if we got here, we know the next token must be a variable 
    char* var_name = strtok(NULL, " ");
    //printf("found local variable %s\n", var_name);
    variable_names[0] = malloc(strlen(var_name) + 1);
    strcpy(variable_names[0], var_name);

    // read the rest of the line and keep looping until we hit the semicolon
    int variable_counter = 1;
    char* prev_tok = var_name;

    while (1) {
      char* tok = strtok(NULL, " ");
      //printf("considering %s\n", tok);

      // if this starts with a semicolon, then we're done
      if (*tok == ';') break;

      // if this is a + or = or a comma, then obviously it's not a variable
      else if (strcmp(tok, "+") == 0 || strcmp(tok, "=") == 0 || strcmp(tok, ",") == 0) {
	// do nothing
      }
      // if the thing before it is + or =, then it's not a variable being declared
      else if (strcmp(prev_tok, "+") == 0 || strcmp(prev_tok, "=") == 0) {
	// not a variable being declared
      }
      else {
	//printf("found local variable %s\n", tok);
	variable_names[variable_counter] = malloc(strlen(tok) + 1);
	strcpy(variable_names[variable_counter], tok);
	variable_counter++;
      }

      prev_tok = tok;
    }

  }


  free(copy);

  return 1;

  // END

}

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

