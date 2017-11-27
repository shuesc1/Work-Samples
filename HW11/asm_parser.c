/************************************************************************/
/* File Name : asm_parser.c 											*/
/* Purpose   : the functions are declared in asm_parser.h 				*/
/*             The intention of this library is to parse a .ASM file 	*/
/*             															*/
/* Author(s) : tjf and you												*/
/************************************************************************/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include "asm_parser.h"

/* to do - implement all the functions in asm_parser.h */

// =========================== READ IN ASM FILE & STORE IN 2D MATRIX ==================================

int read_asm_file (char* filename, char program [ROWS][COLS]) {
	FILE *file = fopen(filename, "r") ;
	if (filename == NULL) {
		printf("error2: read_asm_file() failed\n");
		return 2;
	} else {
		int *byte_read;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 255 ; j++) {
				*byte_read = fgetc(file);
				if (*byte_read == 10) {
					// printf("\n");
					i++;
					continue;
					// } else if(*byte_read == 0 && !isalnum(program[i][j-1]){
					// 	continue;
					// } else {
				} else {
					program[i][j] = *byte_read ;
					// printf("%c", program[i][j]);
				}
				// fscanf(file,"%c\t", &program[i][j]); //read in 1 char at a time (not whole line)
			}
			//trying to use fgets()
			// fgets(input, sizeof(input), file);
			// // program[i][0] = *strtok(input, "\n") ;
			// program[i][0] = *input ;
			// printf("%c\n", program[i][0]);
		}
		// for(int i=0 ; i < 10; i++){
		//    	for(int j = 0; j < 255 ; j++){
		//         printf("%c", program[i][j]);
		//     }
		//         printf("\n");
		// }
		fclose(file) ;
		return 0;
	}
}

// ============================ PARSE ORIGINAL INSTRUCTION ===============================

int parse_instruction 	(char* instr, char* instr_bin_str) {
	const char* delimiter = " ";
	char* opcode = "" ;
	int i = 0;
	char newstring[255] = "";
	char direc_string[255] = "" ;
	printf("newstring this row: ");
	for (int row = 0; row < 100; row++) {
		// for(int column = 0; column < 255; column++, i++){
		if (instr[row] == 0) {
			continue;
		}
		newstring[i] = instr[row] ;
		direc_string[i] = instr[row] ;
		printf("%c", newstring[i]);
		// printf("i: %c\n", (int)i);
		// printf("%c", instr[row]);
		i++ ;
	}
	// printf("\nNewstring: ");
	// for(int x; x < strlen(newstring); x++){
	// 	printf("%c", newstring[x]);
	// }
	// printf("\n");
	opcode = strtok(newstring, delimiter) ;
	printf("\nopcode: %s\n\n", opcode);

	if (opcode != 0) {
		if (strcmp(opcode, "ADD") == 0) {
			printf("Converting 'ADD' to binary...\n");
			parse_add(direc_string, instr_bin_str);
		} else if (strcmp(opcode, "MUL") == 0) {
			parse_add(direc_string, instr_bin_str);
			// parse_mul(newstring, instr_bin_str);
		} else if (strcmp(opcode, "SUB") == 0) {
			parse_add(direc_string, instr_bin_str);
			// parse_sub(newstring, instr_bin_str);
		} else if (strcmp(opcode, "DIV") == 0) {
			// parse_div(newstring, instr_bin_str);
		} else if (strcmp(opcode, "AND") == 0) {
			// parse_and(newstring, instr_bin_str);
		} else if (strcmp(opcode, "OR") == 0) {
			// parse_or(newstring, instr_bin_str);
		} else if (strcmp(opcode, "XOR") == 0) {
			// parse_xor(newstring, instr_bin_str);
		}
	}

	if (*newstring != 0 && opcode == NULL) { //ie if there are contents but the opcode is still null then process failed
		printf("error2: read_asm_file() failed\n");
		return 2;
	} else {
		return 0;
	}
}

// ============================== PARSE ADD DIRECTIVE ===========================
// ===================== (EXTRACT_REG_NUM() && PARSE_REG HELPER FUNCTIONS) =======

int parse_add 			(char* instr, char* instr_bin_str ) {
	char* bin_opcode_0_3 = "0001" ;
	char* bin_opcode_10_12 = "001" ;
	int i = 0;
	for (int j = 0; j < 4; j++) {
		instr_bin_str[j] = bin_opcode_0_3[j] ;
	}
	for (int k = 10; k < 13; k++, i++) {
		instr_bin_str[k] = bin_opcode_10_12[i];
		// printf("instr_bin_str[%c] = %c\n", (int)k, instr_bin_str[k]);
	}

	// printf("Printing instructions to binary string:\n");
	// for(int q = 0; q < strlen(instr_bin_str); q++){
	// 	printf("%c", instr_bin_str[q]);
	// }

	int* R_first, R_second, R_third;

	// printf("\nInstruc to parse: ");
	// for(int x; x < strlen(instr); x++){
	// 	printf("%c", instr[x]);
	// }

	// set up flag to let parse_reg know where to store outcome in string of instr_bin_str
	int Rd_Rs_Rt_indicator[1] ; // 1 - Rd, 2 - Rs, 3 - Rt

	//======  1st register =======
	char* a = strtok(instr, ",") ;
	printf("\nfirst token: %s\n", a);
	*R_first = extract_reg_num(a);
	printf("first reg num: %d\n", *R_first);
	*Rd_Rs_Rt_indicator = 1 ; // 1 indicates Rd
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(*R_first, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//======= 2nd register =======
	a = strtok(NULL, " ") ;
	printf("next token: %s\n", a);
	R_second = extract_reg_num(a);
	printf("second reg num: %d\n", R_second);
	*Rd_Rs_Rt_indicator = 2 ; // 2 indicates Rs
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_second, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//====== 3rd register ==========
	a = strtok(NULL, " ") ;
	printf("next token: %s\n", a);
	R_third = extract_reg_num(a);
	printf("third reg num: %d\n", R_third);
	*Rd_Rs_Rt_indicator = 3 ; // 3 indicates Rt
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_third, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//print out finished bin string
	printf("Printing instructions to binary string:\n");
	for (int q = 0; q < 17; q++) {
		printf("%c", instr_bin_str[q]);
	}
	printf("\n");

	return 0;
}

//===================== HELPER FUNCTION -- EXTRACT REGISTER NUMBER FROM REGISTER TOKEN ============================

int extract_reg_num		(char * tokenized_reg_string) {
	int register_num = '0' ;
	for (int i = 0; i < strlen(tokenized_reg_string); i++) {
		if (!isdigit(tokenized_reg_string[i])) {
			continue;
		} else {
			register_num = (int)tokenized_reg_string[i] ;
			break;
		}
	}
	return register_num ;
}

// char* ret_reg_num_binary(int* register_number){
// 	if(strcmp(register_number, "0")==1){

// 	}
// }

//==================== PARSE REGISTER NUMBER =========================

int parse_reg 			(char reg_num, char* instr_bin_str, int* Rd_Rs_Rt_indicator) {
	if (instr_bin_str == NULL || Rd_Rs_Rt_indicator == NULL) {
		printf("error5: parse_reg() function failed due to null input(s)\n");
		return 5;
	} else {

		char* reg_num_in_binary = "";
		if (reg_num == 48) { // ascii '0'
			reg_num_in_binary = "000" ;
			printf("Reached here-- reg number in binary is %s\n", reg_num_in_binary);
		} else if (reg_num == 49) { // ascii '1'
			reg_num_in_binary = "001" ;
			printf("Reached here-- reg number in binary is %s\n", reg_num_in_binary);
		} else if (reg_num == 50) { // ascii '2'
			reg_num_in_binary = "010" ;
		} else if (reg_num == 51) {
			reg_num_in_binary = "011" ;
		} else if (reg_num == 52) {
			reg_num_in_binary = "100" ;
		} else if (reg_num == 53) {
			reg_num_in_binary = "101" ;
		} else if (reg_num == 54) {
			reg_num_in_binary = "110" ;
		} else if (reg_num == 55) {
			reg_num_in_binary = "111" ;
		}

		int* switch_value = Rd_Rs_Rt_indicator;
		printf("switch value provided: %d\n", *switch_value);
		int i = 0;

		switch (*Rd_Rs_Rt_indicator) //1 - Rd, 2 - Rs, 3 - Rt
		{
		case 1: // Rd is register
			//copy Rd reg_num_in_binary to proper bits in instr_bin_str[4:6]
			printf("Rd input: %s\n", reg_num_in_binary);
			i = 0;
			for (int t = 4; t < 7; t++, i++) {
				instr_bin_str[t] = reg_num_in_binary[i] ;
			}
			break;
		case 2: // Rs is register
			//copy Rs reg_num_in_binary to proper bits in instr_bin_str[7:9]
			printf("Rs input: %s\n", reg_num_in_binary);
			i = 0;
			for (int u = 7; u < 10; u++, i++) {
				instr_bin_str[u] = reg_num_in_binary[i] ;
			}
			break;
		case 3: // Rt is register
			//copy Rt reg_num_in_binary to proper bits in instr_bin_str[13:15]
			printf("Rt input: %s\n", reg_num_in_binary);
			i = 0;
			for (int v = 13; v < 16; v++, i++) {
				instr_bin_str[v] = reg_num_in_binary[i] ;
			}
			break;
		default:
			printf("Default ");
		}
		return 0;
	}
}

// ======================================== PARSE MULTIPLY FUNCTION ==========================

int parse_mul 			(char* instr, char* instr_bin_str ) ;
/* add additional helper functions to support other instructions */


// ===================================== STRING TO BINARY FUNCTION ==========================

unsigned short int str_to_bin (char* instr_bin_str) {


	return 0;
}


//===================================== WRITE OBJECT TO FILE FUNCTION ==========================

int write_obj_file 			  (char* filename, unsigned short int program_bin[ROWS] ) ;

