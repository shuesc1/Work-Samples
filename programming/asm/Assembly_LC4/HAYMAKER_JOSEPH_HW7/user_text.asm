;; user_text.asm -- a program that practices using I/O with assembly
;; The program prints out the char array stored in user Data Memory
;; "I love CIT 593/0" using the TRAP_PUTS subroutine

;;;;;;;;;;;;;;;;;;;;;;;;; Data section - Fill Data Memory ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
.FALIGN
	.DATA
	.ADDR x4000	;start writing in Data Memory at addr 0x4000 	
CHAR_ARR
	.FILL x49	; "I"
	.FILL x20	; " "
	.FILL x6C	; "l"
	.FILL x6F	; "o"
	.FILL x76	; "v"
	.FILL x65	; "e""
	.FILL x20	; " "
	.FILL x43	; "C"
	.FILL x49	; "I"
	.FILL x54	; "T"
	.FILL x20	; " "
	.FILL x35	; "5"
	.FILL x39	; "9"
	.FILL x33	; "3"
	.FILL x00	; "/0"
;;USER_INPUT
;;===================================================================================;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; To run script file: <Script user_text_script.txt> in LC4
;; |_REG._FILE|										    |_DATA_MEMORY_|
;; |_R0(x4###)| -addr in Data Mem|	   <CHAR_ARR>(x4000)|_____"I"______|
;; |_R1(ASCII)| - char/ASCII value at R0|  		  x4001 |_____" "______|
;; |_R2(x2000)|	- smallest addr in Data mem| 	  x4002 |_____"l"______|
;; |_R3(x7FFF)|	- largest addr in Data mem	|  	  x4003 |_____"o"______|
;; |_____R4___| - 					|			  x4004 |_____"v"______| , etc.
;; |_____R5___| - 
;; |_____R6___| - OS_ADDR_ADDR display data reg.

;;;;;;;;; Code section ;;;;;;;;;;;
.CODE ;; This section contains code.
.ADDR x0000 ;; Specify address to start putting the code
.FALIGN

START
	CONST R2, x00		; store x00 
	HICONST R2 x20		; store x20 of x2000 to check if R0 input is valid addr in user Data mem
	CONST R3 xFF		; store xFF of x7FFF
	HICONST R3 x7F		; store x7F of x7FFF to check if R0 input is valid addr in user Data mem
	
PRINT
	TRAP x03			    ; TRAP_PUTS, uses addr value in R0 as argument
	
END