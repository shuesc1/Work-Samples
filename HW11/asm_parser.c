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
	if (file == NULL) {
		printf("error2: read_asm_file() failed\n");
		return 2;
	} else {
		int *byte_read;
		for (int i = 0; i < 100; i++) {
		// while(fgetc(file) != NULL){
			for (int j = 0; j < 255 ; j++) {
				*byte_read = fgetc(file);
				if (*byte_read == 10) {
					printf("\n");
					i++;
					continue;
					// } else if(*byte_read == 0 && !isalnum(program[i][j-1]){
					// 	continue;
					// } else {
				} else if (*byte_read > 0 && *byte_read < 128) {
					program[i][j] = *byte_read ;
					printf("%c", program[i][j]);
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
	printf("instruction this row: ");
	for (int row = 0; row < 100; row++) {
		// for(int column = 0; column < 255; column++, i++){
		if (instr[row] == 0) {
			continue;
		}
		newstring[i] = instr[row] ;
		direc_string[i] = instr[row] ;
		printf("%c", newstring[i]);
		// printf("%c", direc_string[i]);
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
			// parse_add(direc_string, instr_bin_str);
			parse_mul(direc_string, instr_bin_str);
		} else if (strcmp(opcode, "SUB") == 0) {
			// parse_add(direc_string, instr_bin_str);
			parse_sub(direc_string, instr_bin_str);
		} else if (strcmp(opcode, "DIV") == 0) {
			parse_div(direc_string, instr_bin_str);
		} else if (strcmp(opcode, "AND") == 0) {
			parse_and(direc_string, instr_bin_str);
		} else if (strcmp(opcode, "OR") == 0) {
			parse_or(direc_string, instr_bin_str);
		} else if (strcmp(opcode, "XOR") == 0) {
			// parse_xor(newstring, instr_bin_str);
		}
	} else {
		printf("error3: parse_instruction() failed\n");
		return 3;
	}

	if (*newstring != 0 && opcode == NULL) { //ie if there are contents but the opcode is still null then process failed
		printf("error3: parse_instruction() failed\n");
		return 3;
	} else {
		return 0;
	}
}

//==================================================================================================
//==================================================================================================
//=================== INSTRUCTION PARSING (ADD, MUL, SUB, ETC.)=====================================
//==================================================================================================
//==================================================================================================

// ============================== PARSE ADD DIRECTIVE ===========================
// ===================== (EXTRACT_REG_NUM() && PARSE_REG HELPER FUNCTIONS) =======

int parse_add 			(char* instr, char* instr_bin_str ) {
	char* bin_opcode_0_3 = "0001" ;
	char* bin_opcode_10_12 = "000" ;
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

	printf("===========Extracting number from registers============\n");
	//======  1st register =======
	char* a = strtok(instr, ",") ;
	printf("\nfirst token: %s\n", a);
	// if(*a != 0){
		*R_first = extract_reg_num(a);
		printf("first reg num (as ascii char): %c\n", *R_first);
		*Rd_Rs_Rt_indicator = 1 ; // 1 indicates Rd
		printf("Reg position indicator (as asciic char): %d\n", (int)*Rd_Rs_Rt_indicator);
		parse_reg(*R_first, instr_bin_str, Rd_Rs_Rt_indicator) ;
	// } else {
	// 	printf("error4: parse_add() failed\n");
	// 	return 4;
	// }


	//======= 2nd register =======
	a = strtok(NULL, " ") ;
	printf("next token: %s\n", a);
		// if(a == 0){
		// printf("error4: parse_add() failed\n");
		// return 4;
		// }
	R_second = extract_reg_num(a);
	printf("second reg num (as ascii char): %c\n", R_second);
	*Rd_Rs_Rt_indicator = 2 ; // 2 indicates Rs
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_second, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//====== 3rd register ==========
	a = strtok(NULL, " ") ;
	printf("next token: %s\n", a);
	// if(a == 0){
	// 	printf("error4: parse_add() failed\n");
	// 	return 4;
	// }
	R_third = extract_reg_num(a);
	printf("third reg num (as ascii char): %c\n", R_third);
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

// ======================================== PARSE MULTIPLY FUNCTION ==========================
// ===================== (EXTRACT_REG_NUM() && PARSE_REG HELPER FUNCTIONS) ===================
// ======================================= 0001 ddd sss 001 ttt ==============================

int parse_mul 			(char* instr, char* instr_bin_str ) {
	// hardcoding <XXXX> ddd sss <XXX> ttt
	char* bin_opcode_0_3 = "0001" ; //[12:15]
	char* bin_opcode_10_12 = "001" ; //[3:5]
	int i = 0;
	//filling in above two values in instr_bin_str
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
	printf("\nInstruc to parse: ");
	for(int x = 0; x < strlen(instr); x++){
		printf("%c", instr[x]);
	}
	printf("\n");

	// set up flag to let parse_reg know where to store outcome in string of instr_bin_str
	int Rd_Rs_Rt_indicator[1] ; // 1 - Rd, 2 - Rs, 3 - Rt

	printf("===========Extracting number from registers============\n");
	//======  1st register =======
	char* a = strtok(instr, ",") ;
	printf("\nfirst token: %s\n", a);
	*R_first = extract_reg_num(a);
	printf("first reg num (as ascii char): %c\n", *R_first);
	*Rd_Rs_Rt_indicator = 1 ; // 1 indicates Rd
	printf("Reg position indicator (as asciic char): %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(*R_first, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//======= 2nd register =======
	a = strtok(NULL, " ") ;
	printf("2nd token: %s\n", a);
	R_second = extract_reg_num(a);
	printf("second reg num (as ascii char): %c\n", R_second);
	*Rd_Rs_Rt_indicator = 2 ; // 2 indicates Rs
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_second, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//====== 3rd register ==========
	a = strtok(NULL, " ") ;
	printf("3rd token: %s\n", a);
	R_third = extract_reg_num(a);
	printf("third reg num (as ascii char): %c\n", R_third);
	*Rd_Rs_Rt_indicator = 3 ; // 3 indicates Rt
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_third, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//print out finished bin string
	printf("Printing instructions to binary string:\n\n");
	for (int q = 0; q < 17; q++) {
		printf("%c", instr_bin_str[q]);
	}
	printf("\n");
	return 0;
}

// ======================================== PARSE SUBTRACT FUNCTION ==========================
// ===================== (EXTRACT_REG_NUM() && PARSE_REG HELPER FUNCTIONS) ===================
// ======================================= 0001 ddd sss 010 ttt ==============================

int parse_sub 			(char* instr, char* instr_bin_str ){
	// hardcoding <XXXX> ddd sss <XXX> ttt
	char* bin_opcode_0_3 = "0001" ; //[12:15]
	char* bin_opcode_10_12 = "010" ; //[3:5]
	int i = 0;
	//filling in above two values in instr_bin_str
	for (int j = 0; j < 4; j++) {
		instr_bin_str[j] = bin_opcode_0_3[j] ;
	}
	for (int k = 10; k < 13; k++, i++) {
		instr_bin_str[k] = bin_opcode_10_12[i];
		// printf("instr_bin_str[%c] = %c\n", (int)k, instr_bin_str[k]);
	}
	int* R_first, R_second, R_third;
	printf("\nInstruc to parse: ");
	for(int x = 0; x < strlen(instr); x++){
		printf("%c", instr[x]);
	}
	printf("\n");

	// set up flag to let parse_reg know where to store outcome in string of instr_bin_str
	int Rd_Rs_Rt_indicator[1] ; // 1 - Rd, 2 - Rs, 3 - Rt

	printf("===========Extracting number from registers============\n");
	//======  1st register =======
	char* a = strtok(instr, ",") ;
	printf("\nfirst token: %s\n", a);
	*R_first = extract_reg_num(a);
	printf("first reg num (as ascii char): %c\n", *R_first);
	*Rd_Rs_Rt_indicator = 1 ; // 1 indicates Rd
	printf("Reg position indicator (as asciic char): %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(*R_first, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//======= 2nd register =======
	a = strtok(NULL, " ") ;
	printf("2nd token: %s\n", a);
	R_second = extract_reg_num(a);
	printf("second reg num (as ascii char): %c\n", R_second);
	*Rd_Rs_Rt_indicator = 2 ; // 2 indicates Rs
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_second, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//====== 3rd register ==========
	a = strtok(NULL, " ") ;
	printf("3rd token: %s\n", a);
	R_third = extract_reg_num(a);
	printf("third reg num (as ascii char): %c\n", R_third);
	*Rd_Rs_Rt_indicator = 3 ; // 3 indicates Rt
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_third, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//print out finished bin string
	printf("Printing instructions to binary string:\n\n");
	for (int q = 0; q < 17; q++) {
		printf("%c", instr_bin_str[q]);
	}
	printf("\n");
	return 0;
}


// ======================================== PARSE DIVISION FUNCTION ==========================
// ===================== (EXTRACT_REG_NUM() && PARSE_REG HELPER FUNCTIONS) ===================
// ======================================= 0001 ddd sss 011 ttt ==============================

int parse_div 			(char* instr, char* instr_bin_str ){
	// hardcoding <XXXX> ddd sss <XXX> ttt
	char* bin_opcode_0_3 = "0001" ; //[12:15]
	char* bin_opcode_10_12 = "011" ; //[3:5]
	int i = 0;
	//filling in above two values in instr_bin_str
	for (int j = 0; j < 4; j++) {
		instr_bin_str[j] = bin_opcode_0_3[j] ;
	}
	for (int k = 10; k < 13; k++, i++) {
		instr_bin_str[k] = bin_opcode_10_12[i];
		// printf("instr_bin_str[%c] = %c\n", (int)k, instr_bin_str[k]);
	}
	int* R_first, R_second, R_third;
	printf("\nInstruc to parse: ");
	for(int x = 0; x < strlen(instr); x++){
		printf("%c", instr[x]);
	}
	printf("\n");

	// set up flag to let parse_reg know where to store outcome in string of instr_bin_str
	int Rd_Rs_Rt_indicator[1] ; // 1 - Rd, 2 - Rs, 3 - Rt
	printf("===========Extracting number from registers============\n");
	//======  1st register =======
	char* a = strtok(instr, ",") ;
	printf("\nfirst token: %s\n", a);
	*R_first = extract_reg_num(a);
	printf("first reg num (as ascii char): %c\n", *R_first);
	*Rd_Rs_Rt_indicator = 1 ; // 1 indicates Rd
	printf("Reg position indicator (as asciic char): %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(*R_first, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//======= 2nd register =======
	a = strtok(NULL, " ") ;
	printf("2nd token: %s\n", a);
	R_second = extract_reg_num(a);
	printf("second reg num (as ascii char): %c\n", R_second);
	*Rd_Rs_Rt_indicator = 2 ; // 2 indicates Rs
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_second, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//====== 3rd register ==========
	a = strtok(NULL, " ") ;
	printf("3rd token: %s\n", a);
	R_third = extract_reg_num(a);
	printf("third reg num (as ascii char): %c\n", R_third);
	*Rd_Rs_Rt_indicator = 3 ; // 3 indicates Rt
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_third, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//print out finished bin string
	printf("Printing instructions to binary string:\n\n");
	for (int q = 0; q < 17; q++) {
		printf("%c", instr_bin_str[q]);
	}
	printf("\n");
	return 0;
}


// ======================================== PARSE AND FUNCTION ==========================
// ===================== (EXTRACT_REG_NUM() && PARSE_REG HELPER FUNCTIONS) ===================
// ======================================= 0101 ddd sss 000 ttt ==============================

int parse_and			(char* instr, char* instr_bin_str ) {
	// hardcoding <XXXX> ddd sss <XXX> ttt
	char* bin_opcode_0_3 = "0101" ; //[12:15]
	char* bin_opcode_10_12 = "000" ; //[3:5]
	int i = 0;
	//filling in above two values in instr_bin_str
	for (int j = 0; j < 4; j++) {
		instr_bin_str[j] = bin_opcode_0_3[j] ;
	}
	for (int k = 10; k < 13; k++, i++) {
		instr_bin_str[k] = bin_opcode_10_12[i];
		// printf("instr_bin_str[%c] = %c\n", (int)k, instr_bin_str[k]);
	}
	int* R_first, R_second, R_third;
	printf("\nInstruc to parse: ");
	for(int x = 0; x < strlen(instr); x++){
		printf("%c", instr[x]);
	}
	printf("\n");

	// set up flag to let parse_reg know where to store outcome in string of instr_bin_str
	int Rd_Rs_Rt_indicator[1] ; // 1 - Rd, 2 - Rs, 3 - Rt
	printf("===========Extracting number from registers============\n");
	//======  1st register =======
	char* a = strtok(instr, ",") ;
	printf("\nfirst token: %s\n", a);
	*R_first = extract_reg_num(a);
	printf("first reg num (as ascii char): %c\n", *R_first);
	*Rd_Rs_Rt_indicator = 1 ; // 1 indicates Rd
	printf("Reg position indicator (as asciic char): %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(*R_first, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//======= 2nd register =======
	a = strtok(NULL, " ") ;
	printf("2nd token: %s\n", a);
	R_second = extract_reg_num(a);
	printf("second reg num (as ascii char): %c\n", R_second);
	*Rd_Rs_Rt_indicator = 2 ; // 2 indicates Rs
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_second, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//====== 3rd register ==========
	a = strtok(NULL, " ") ;
	printf("3rd token: %s\n", a);
	R_third = extract_reg_num(a);
	printf("third reg num (as ascii char): %c\n", R_third);
	*Rd_Rs_Rt_indicator = 3 ; // 3 indicates Rt
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_third, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//print out finished bin string
	printf("Printing instructions to binary string:\n\n");
	for (int q = 0; q < 17; q++) {
		printf("%c", instr_bin_str[q]);
	}
	printf("\n");
	return 0;
}


// ======================================== PARSE OR FUNCTION ==========================
// ===================== (EXTRACT_REG_NUM() && PARSE_REG HELPER FUNCTIONS) ===================
// ======================================= 0101 ddd sss 010 ttt ==============================

int parse_or 			(char* instr, char* instr_bin_str ) {
	// hardcoding <XXXX> ddd sss <XXX> ttt
	char* bin_opcode_0_3 = "0101" ; //[12:15]
	char* bin_opcode_10_12 = "010" ; //[3:5]
	int i = 0;
	//filling in above two values in instr_bin_str
	for (int j = 0; j < 4; j++) {
		instr_bin_str[j] = bin_opcode_0_3[j] ;
	}
	for (int k = 10; k < 13; k++, i++) {
		instr_bin_str[k] = bin_opcode_10_12[i];
		// printf("instr_bin_str[%c] = %c\n", (int)k, instr_bin_str[k]);
	}
	int* R_first, R_second, R_third;
	printf("\nInstruc to parse: ");
	for(int x = 0; x < strlen(instr); x++){
		printf("%c", instr[x]);
	}
	printf("\n");

	// set up flag to let parse_reg know where to store outcome in string of instr_bin_str
	int Rd_Rs_Rt_indicator[1] ; // 1 - Rd, 2 - Rs, 3 - Rt
	printf("===========Extracting number from registers============\n");
	//======  1st register =======
	char* a = strtok(instr, ",") ;
	printf("\nfirst token: %s\n", a);
	*R_first = extract_reg_num(a);
	printf("first reg num (as ascii char): %c\n", *R_first);
	*Rd_Rs_Rt_indicator = 1 ; // 1 indicates Rd
	printf("Reg position indicator (as asciic char): %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(*R_first, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//======= 2nd register =======
	a = strtok(NULL, " ") ;
	printf("2nd token: %s\n", a);
	R_second = extract_reg_num(a);
	printf("second reg num (as ascii char): %c\n", R_second);
	*Rd_Rs_Rt_indicator = 2 ; // 2 indicates Rs
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_second, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//====== 3rd register ==========
	a = strtok(NULL, " ") ;
	printf("3rd token: %s\n", a);
	R_third = extract_reg_num(a);
	printf("third reg num (as ascii char): %c\n", R_third);
	*Rd_Rs_Rt_indicator = 3 ; // 3 indicates Rt
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_third, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//print out finished bin string
	printf("Printing instructions to binary string:\n\n");
	for (int q = 0; q < 17; q++) {
		printf("%c", instr_bin_str[q]);
	}
	printf("\n");
	return 0;
}

// ======================================== PARSE XOR FUNCTION ==========================
int parse_xor			(char* instr, char* instr_bin_str ) {
	// hardcoding <XXXX> ddd sss <XXX> ttt
	char* bin_opcode_0_3 = "0101" ; //[12:15]
	char* bin_opcode_10_12 = "010" ; //[3:5]
	int i = 0;
	//filling in above two values in instr_bin_str
	for (int j = 0; j < 4; j++) {
		instr_bin_str[j] = bin_opcode_0_3[j] ;
	}
	for (int k = 10; k < 13; k++, i++) {
		instr_bin_str[k] = bin_opcode_10_12[i];
		// printf("instr_bin_str[%c] = %c\n", (int)k, instr_bin_str[k]);
	}
	int* R_first, R_second, R_third;
	printf("\nInstruc to parse: ");
	for(int x = 0; x < strlen(instr); x++){
		printf("%c", instr[x]);
	}
	printf("\n");

	// set up flag to let parse_reg know where to store outcome in string of instr_bin_str
	int Rd_Rs_Rt_indicator[1] ; // 1 - Rd, 2 - Rs, 3 - Rt
	printf("===========Extracting number from registers============\n");
	//======  1st register =======
	char* a = strtok(instr, ",") ;
	printf("\nfirst token: %s\n", a);
	if(a == 0){
		printf("error4: parse_xor() failed\n");
		return 4;
	}
	*R_first = extract_reg_num(a);
	printf("first reg num (as ascii char): %c\n", *R_first);
	*Rd_Rs_Rt_indicator = 1 ; // 1 indicates Rd
	printf("Reg position indicator (as asciic char): %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(*R_first, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//======= 2nd register =======
	a = strtok(NULL, " ") ;
	printf("2nd token: %s\n", a);
	// 	if(a == 0){
	// 	printf("error4: parse_xor() failed\n");
	// 	return 4;
	// }
	R_second = extract_reg_num(a);
	printf("second reg num (as ascii char): %c\n", R_second);
	*Rd_Rs_Rt_indicator = 2 ; // 2 indicates Rs
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_second, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//====== 3rd register ==========
	a = strtok(NULL, " ") ;
	printf("3rd token: %s\n", a);
	// if(a == 0){
	// 	printf("error4: parse_xor() failed\n");
	// 	return 4;
	// }
	R_third = extract_reg_num(a);
	printf("third reg num (as ascii char): %c\n", R_third);
	*Rd_Rs_Rt_indicator = 3 ; // 3 indicates Rt
	printf("Reg position indicator: %d\n", (int)*Rd_Rs_Rt_indicator);
	parse_reg(R_third, instr_bin_str, Rd_Rs_Rt_indicator) ;

	//print out finished bin string
	printf("Printing instructions to binary string:\n\n");
	for (int q = 0; q < 17; q++) {
		printf("%c", instr_bin_str[q]);
	}
	printf("\n");
	return 0;
}

//==================================================================================================
//==================================================================================================
//=================== END - INSTRUCTION PARSING (ADD, MUL, SUB, ETC.)===============================
//==================================================================================================
//==================================================================================================


//===================== HELPER FUNCTION -- EXTRACT REGISTER NUMBER FROM REGISTER TOKEN ============================

int extract_reg_num		(char * tokenized_reg_string) {
	// if(tokenized_reg_string == NULL){
	// 	printf("error4: parse_xor() failed\n");
	// 	return 4;
	// } else {
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
	// }
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
		printf("=============Changing register numbers to binary=============\n");
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
			//copy Rd reg_num_in_binary to proper bits in instr_bin_str[4:6] - ([9:11]) 0000 <DDD> sss 000 ttt
			printf("Rd input: %s\n", reg_num_in_binary);
			i = 0;
			for (int t = 4; t < 7; t++, i++) {
				instr_bin_str[t] = reg_num_in_binary[i] ;
			}
			break;
		case 2: // Rs is register
			//copy Rs reg_num_in_binary to proper bits in instr_bin_str[7:9]- ([6:8]) 0000 ddd <SSS> 000 ttt
			printf("Rs input: %s\n", reg_num_in_binary);
			i = 0;
			for (int u = 7; u < 10; u++, i++) {
				instr_bin_str[u] = reg_num_in_binary[i] ;
			}
			break;
		case 3: // Rt is register
			//copy Rt reg_num_in_binary to proper bits in instr_bin_str[13:15] - ([0:2]) 0000 ddd sss 000 <TTT>
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




// ===================================== STRING TO BINARY FUNCTION ==========================

unsigned short int str_to_bin (char* instr_bin_str) {

	// char str[30] = "2030300 This is test";
	char *ptr = NULL;
	unsigned short int ret;

	ret = strtol(instr_bin_str, &ptr, 2);
	printf("The number(unsigned long integer) is %hu\n", ret);
	printf("The number(hex) is 0x%hx\n\n", ret);
	// printf("String part is |%s|", ptr);

	if (ret == 0) {
		printf("error6: function str_to_bin() failed\n");
		return 6;
	} else {
		return ret;
	}
	// return 0;
}


//===================================== WRITE OBJECT TO FILE FUNCTION ==========================

int write_obj_file 			  (char* filename, unsigned short int program_bin[ROWS] ){

	FILE *output = fopen(filename, "wb"	);
	
	// ---- header (3 groups of 16 bits) ----- CA DE 00 00 00 0_ <== <n>
	unsigned short int header[3];

	unsigned short int CADE = 0xCADE ; //11001010 11011110
	// unsigned short int DE = 0xDE; //11011110
	unsigned short int zeros = 0x0000; // 3 of these

	header[0] = CADE;
	header[1] = zeros;

	// -- count number of rows <n> ----
	unsigned short int n = 0;
	for(int i = 0; i < ROWS; i++){ //getting the number of rows 
		if(program_bin[i] != 0){
			n++;
		} else {
			break;
		}
	}
	printf("num rows in program_bin: %hu\n", n); 	//get the number n of the rows of program_bin[]
	
	// add header number as last part of header
	header[2] = n;

	//iterate over header array
	fwrite(header, 2, 3, output) ;

	// iterate over program_bin[ROWS]
	// fwrite(program_bin, sizeof *program_bin, 2, output) ; //size of each element is 1 byte
	fwrite(program_bin, sizeof(short unsigned int), 2, output) ;
	fclose(output) ;
	return 0;
}


////===================================== CONVERT DECIMAL VALUE TO HEX ==========================
//source: http://scanftree.com/programs/c/c-code-to-convert-decimal-to-hexadecimal/
unsigned short int convert_dec_to_hex(unsigned short int decimal) {
	long int decimalNumber,remainder,quotient;
	int i=1,j,temp;
	unsigned short int hexadecimalNumber[100];
	// printf("Enter any decimal number: ");
	// scanf("%ld",&decimalNumber);
	quotient = decimal;
	while(quotient!=0) {
		temp = quotient % 16;
		//To convert integer into character
		if( temp < 10)
		           temp =temp + 48; else
		         temp = temp + 55;
		hexadecimalNumber[i++]= temp;
		quotient = quotient / 16;
	}
	// printf("Equivalent hexadecimal value of decimal number %d: ",decimalNumber);
	// for (j = i -1 ;j> 0;j--)
	//       printf("%c",hexadecimalNumber[j]);
	return 0;
}

