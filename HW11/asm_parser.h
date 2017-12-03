/************************************************************************/
/* File Name : asm_parser.h 											*/
/* Purpose   : this header file declares the functions for those		*/
/*             in the "asm_parser.c" library 							*/
/*             standard "string.h" C-library.							*/
/* Author(s) : tjf and you												*/
/************************************************************************/

#define ROWS 100
#define COLS 255

int read_asm_file 		(char* filename, char program [ROWS][COLS] ) ;
int parse_instruction 	(char* instr, char* instr_bin_str) ;
int parse_reg 			(char reg_num, char* instr_bin_str, int* Rd_Rs_Rt_indicator) ;

int parse_add 			(char* instr, char* instr_bin_str ) ;
int parse_mul 			(char* instr, char* instr_bin_str ) ;
int parse_sub 			(char* instr, char* instr_bin_str ) ;
int parse_div 			(char* instr, char* instr_bin_str ) ;
int parse_and			(char* instr, char* instr_bin_str ) ;
int parse_or 			(char* instr, char* instr_bin_str ) ;
int parse_xor			(char* instr, char* instr_bin_str ) ;

/* add additional helper functions to support other instructions */
unsigned short int str_to_bin (char* instr_bin_str) ;
int write_obj_file 			  (char* filename, unsigned short int program_bin[ROWS] ) ;

int extract_reg_num		(char * tokenized_reg_string) ;
unsigned short int convert_dec_to_hex(unsigned short int decimal) ;
