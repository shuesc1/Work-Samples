#include <stdio.h>
#include <stdlib.h>
#include <string.h>

extern int parse_function_header(char*);
extern int parse_line(char*);
extern int add_symbol(char*, int);
extern int get_offset(char*, int*);
extern int populate_symbol_table(char*);
extern int generate_asm(char*,char*);

/*
 * THIS IS THE STARTER CODE FOR PART 2
 */

int main(int argc, char* argv[]) {
 
  if (argc < 2) {
    printf("Usage: compiler [input file]\n");
    exit(1);
  }

  char* filename = argv[1];
  char* copy = malloc(sizeof(char)* strlen(filename)+1);
  strcpy(copy, filename);
  char* new_filename = malloc(sizeof(char)* strlen(filename)+3); // to go from .c to .lc4
  if(new_filename== NULL){ return 0;}  

  /* get the first token */
  new_filename = strtok(copy, ".");
  char ext[] = ".lc4";

  strcat(new_filename, ext);
  printf("new filename is: %s\n", new_filename);

  // first, try to build the symbol table
  if (populate_symbol_table(filename) == 1) {

    printf("running generate_asm...\n");
    // IMPLEMENT PART 2 HERE
    generate_asm(filename, new_filename);

  }

  return 0;
}
