#include <stdio.h>
#include <stdlib.h>

extern int parse_function_header(char*);
extern int parse_line(char*);
extern int add_symbol(char*, int);
extern int get_offset(char*, int*);
extern int populate_symbol_table(char*);

/*
 * THIS IS THE STARTER CODE FOR PART 2
 */

int main(int argc, char* argv[]) {
 
  if (argc < 2) {
    printf("Usage: compiler [input file]\n");
    exit(1);
  }

  char* filename = argv[1];

  // first, try to build the symbol table
  if (populate_symbol_table(filename) == 1) {

    // IMPLEMENT PART 2 HERE

  }

  return 0;
}
