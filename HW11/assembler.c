/************************************************************************/
/* File Name : assembler.c 												*/
/* Purpose   : This program will assemble a .ASM file into a .OBJ file  */
/*             This program will use the "asm_parser" library to achieve*/
/*			   its functionality.										*/ 
/* Author(s) : tjf and you												*/
/************************************************************************/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include "asm_parser.h"

int main(int argc, char** argv) {

	char* filename = NULL ;					// name of ASM file-- ptr to string valid filename, main() arg
	char  program [ROWS][COLS] ; 			// ASM file line-by-line, 2D matrix 100 rows x 255 columns
	char  program_bin_str [ROWS][17] ; 		// instructions converted to a binary string
	unsigned short int program_bin [ROWS] ; // instructions in binary (HEX) -- 2 bytes

// =========================
// 1. check argument to main
// =========================
	if(argv[1] == 0){
		printf("No string filename provided to main!\n");
		printf("error1: usage: ./assembler <assembly_file.asm>\n");
		return 1;
	} else {
		char * str = argv[1] ;
		for(int i = 0; i < strlen(argv[1]); i++){
			if(!isalnum(str[i])){
				if(str[i] == 46){ //if non alphanumeric char is . then check next char
					continue;
				}
				printf("Please provide a valid filename.\n");
				printf("error1: usage: ./assembler <assembly_file.asm>\n");
				return 1;
				break;
			}
		}
	}

 
	filename = argv[1] ;
	
// =========================
//2. read file
// =========================
	read_asm_file(filename, program) ;

// =========================
//3. parse instructions
// =========================
	for(int row = 0; row < 3; row++){
		printf("========= Row %d=========\n", row);
		parse_instruction(&program[row][0], &program_bin_str[row][0]);
		str_to_bin(&program_bin_str[row][0]); //this might also have to be [row][0]
	}


	return 0;
}
