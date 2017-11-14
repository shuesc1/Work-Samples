;; user_text.asm -- a program that practices using I/O with assembly
;; The program prints out my name using the TRAP_PUTS subroutine,
;; inserts a newline, then prompts the user to "Start Typing >"
;; Program then uses TRAP_GETC to get typed character & TRAP_PUTS to display character
;; until a newline character (x0A) is typed
;; since the instructions are very, very vague I'm going to assume for user input
;; TRAP_GETC runs until it get a char then stores it in R5, then that value is stored
;; in the current addr in Data Mem, then TRAP_PUTS uses R5 for its operations and gets the 
;; ASCII value from Data Mem

;;;;;;;;;;;;;;;;;;;;;;;;; Data section - Fill Data Memory ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
.FALIGN
	.DATA
	.ADDR x4000	;start writing in Data Memory at addr 0x4000 	
	;;fill data memory with name
NAME_ARR
	.FILL x4A	; "J"
	.FILL x6F	; "o"
	.FILL x65	; "e"
	.FILL x79	; "y"
	.FILL x20	; " "
	.FILL x22	; "/""
	.FILL x45	; "E"
	.FILL x6C	; "l"
	.FILL x20	; " "
	.FILL x43	; "C"
	.FILL x69	; "i"
	.FILL x64	; "d"
	.FILL x22	; "/""
	.FILL x20	; " "
	.FILL x48	; "H"
	.FILL x2E	; "."
	.FILL x0A	; newline
	;;Output prompt "Start Typing>"
	.FILL x53	;"S"
	.FILL x74	;"t"
	.FILL x61	;"a"
	.FILL x72	;"r"
	.FILL x74	;"t"
	.FILL x20	;" "
	.FILL x54	;"T"
	.FILL x79	;"y"
	.FILL x70	;"p"
	.FILL x69	;"i"
	.FILL x6E	;"n"	
	.FILL x67	;"g"
	.FILL x3E	;">"
	.FILL x00	;"0" - now NULL terminating string/char array
	;;.FILL x0A		; Output a newline character - 37 spaces in data mem
USER_INPUT
;;===================================================================================;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; To run script file: <Script user_text_script.txt> in LC4
;; |_REG._FILE|										    |_DATA_MEMORY_|
;; |_R0(x4###)| -addr in Data Mem|	   <NAME_ARR>(x4000)|_____"J"______|
;; |__R1(   )_| - 			|			  		  x4001 |_____"o"______|
;; |_R2(x0A)__|	- x0A, line feed |  	  		  x4002 |_____"e"______|
;; |___R3(0)__|	- #0							  x4003 |_____"y"______|
;; |_____R4___| - OS_ADDR_ADDR	display data reg.|x4004 |_____" "______| , etc.
;; |_____R5___| - TRAP_GETC values/current char
;; |_____R6___| - OS_KBSR_ADDR keyboard status reg

;;;;;;;;; Code section ;;;;;;;;;;;
.CODE ;; This section contains code.
.ADDR x0000 ;; Specify address to start putting the code
.FALIGN

START
	LEA R0 NAME_ARR		; store x4000 in R0
	CONST R2, x0A		; store x0A (newline) to be used to end the program later
	CONST R3, #0		; store 0 for appending 0 at end of string/char array
	
PRINT_INTRO
	TRAP x08			; TRAP_PUTS, uses addr value in R0 as argument
	ADD	R0, R0 #1		; increment Data Mem addr
	CMPI R1, #0			; instead of using counter break out of loop when you reach end of null terminating string
	BRnp PRINT_INTRO		; if yes jump back to print, else fall through to TRAP_GETC

READ_WRITE
	TRAP x00			; TRAP_GETC
	TRAP x08			; TRAP_PUTS
	ADD	R0, R0 #1		; increment Data Mem addr
	CMP R5 R2			; compare current char (R5) to x0A (line feed, R2)
	BRnp READ_WRITE		;
	STR R3, R0 #0		; append '0' in R3 to string/end of char array (null terminating)
	JMP END
	
STORE_TEMP
	
	RET

LOAD_TEMP	

	RET
	
END