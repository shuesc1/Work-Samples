#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/*
 * Contains test code for Step 2 of Part 1
 */

extern int get_offset(char*, int*);
extern int populate_symbol_table(char*);
extern void clear();

void test_offset(char*, int);

int main() {

  char* filename = "sample.c";

  printf("Calling populate_symbol_table(\"%s\")\n", filename);
  populate_symbol_table(filename);
  test_offset("t", -3);
  test_offset("z", -2);
  test_offset("y", -1);
  test_offset("x", -0);
  test_offset("b", 5);
  test_offset("a", 4);
  clear();

  char* filename2 = "sample2.c";
  printf("Calling populate_symbol_table(\"%s\")\n", filename2);
  populate_symbol_table(filename2);
  test_offset("delfin",7);
  test_offset("cocodrilo", 6);
  test_offset("burro", 5);
  test_offset("ave", 4);
  test_offset("xochimilco",0);
  test_offset("capybara",-1);
  test_offset("zebra",-2);
  test_offset("tigre",-3);
  test_offset("culebra",-4);
  clear();

  char* filename3 = "sample3.c";
  printf("Calling populate_symbol_table(\"%s\")\n", filename3);
  populate_symbol_table(filename3);  
  test_offset("chunches", 6);
  test_offset("baloncesto", 5);
  test_offset("avion", 4);
  test_offset("duro",0);
  test_offset("estres",-1);
  test_offset("familia",-2);
  test_offset("gemelos",-3);
  test_offset("huracan",-4);
  test_offset("iguana",-5);
  test_offset("joven",-6);
  test_offset("kimono",-7);
  test_offset("liebre",-8);
  test_offset("muralla",-9);
  test_offset("nopales",-10);
  test_offset("obelisco",-11);
  //clear();

  return 0;
}

void test_offset(char* symbol, int expected_offset) {
  int offset = -1000;
  get_offset(symbol, &offset);

  if (offset == expected_offset) printf("Correctly read offset of %d for %s.\n", expected_offset, symbol);
  else if (offset == -1000) printf("ERROR! get_offset should read offset of %d for %s but did not return a value.\n", expected_offset, symbol);
  else printf("ERROR! get_offset should have read offset of %d for %s but read it as %d\n", expected_offset, symbol, offset);

}

