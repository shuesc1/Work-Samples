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
	// char *filename_mod = NULL ;
	char filename_mod[50];

// =========================
// 1. check argument to main
// =========================
	for(int f = 0; f < strlen(argv[1]); f++){
		printf("%c", argv[1][f]);
		filename_mod[f] = argv[1][f] ;
	}
	// filename_mod = (char*) malloc((strlen(argv[1])+1) * sizeof(char));
	// filename_mod = strcpy(filename_mod,filename);
	// char *stripped_filename = "";
	// stripped_filename = strtok(filename, ".") ;
	// printf("stripped_filename: %s\n", stripped_filename);

	if(argv[1] == 0){
		printf("No string filename provided to main!\n");
		printf("error1: usage: ./assembler <assembly_file.asm>\n");
		return 1;
	} else {
		// char * str = argv[1] ;
		for(int i = 0; i < strlen(argv[1]); i++){
			// printf("%c", str[0]);
			// filename[i] = argv[1][i] ;
			if(!isalnum(argv[1][i])){
				if(argv[1][i] == 46){ //if non alphanumeric char is '.' then check next char
					continue;
				}
				printf("Please provide a valid filename.\n");
				printf("error1: usage: ./assembler <assembly_file.asm>\n");
				return 1;
				break;
			}
		}
	}

 
	filename = &argv[1][0] ;
	// filename_mod = &argv[1][0] ;
	printf("filename if name is valid: %s\n", filename);
	
// =========================
//2. read file
// =========================
	read_asm_file(filename, program) ;

// =========================
//3. parse instructions
// =========================
	for(int row = 0; row < 1; row++){
		printf("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
		printf("<<<<<<<<<<<<<<<<<<<<<<<<<<< ROW %d>>>>>>>>>>>>>>>>>>>>>>>>\n", row);
		printf("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
		parse_instruction(&program[row][0], &program_bin_str[row][0]);
		// program_bin[row] = convert_dec_to_hex(str_to_bin(&program_bin_str[row][0])); //this might also have to be [row][0]
		program_bin[row] = (str_to_bin(&program_bin_str[row][0])); 
	}

// =========================
//4. get name of .obj file 
// =========================
	// char* filename2 = argv[1] ;
	printf("original filename: %s\n", filename) ;
	char *stripped_filename = "";
	stripped_filename = strtok(filename, ".") ;
    printf("stripped_filename: %s\n", stripped_filename);
	int stripped_file_end = strlen(stripped_filename) ;
	char new_ending[4] = ".obj" ;
	for(int q = 0 ; q < 5; q++, stripped_file_end++){
		stripped_filename[stripped_file_end] = new_ending[q] ;
	}
	stripped_filename[stripped_file_end] = '\0' ;
	printf("output file name: %s\n", stripped_filename);

	// write_obj_file(stripped_filename, program_bin) ;
	char* output_file = "output.obj" ;
	write_obj_file(output_file, program_bin) ;

	return 0;
}
