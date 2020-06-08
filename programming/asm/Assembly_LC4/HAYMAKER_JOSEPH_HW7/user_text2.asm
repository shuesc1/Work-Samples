;; user_text2.asm -- a program that practices using I/O with assembly
;; The program stores a char array in user Data Memory at a user specified location
;; uses TRAP_GETS 


;;===================================================================================;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; To run script file: <Script user_text_script.txt> in LC4
;; |_REG._FILE|										   
;; |_R0(x4###)| -addr in Data Mem  
;; |_R1(#)____| - letter COUNTER		  		  
;; |_R2(x2000)|	- smallest addr in Data mem 	 
;; |_R3(x7FFF)|	- largest addr in Data mem	
;; |_R4()_____| - 	  
;; |_R5(ASCII)| - addr kbrd stat reg-->value at addr-->addr kbrd DATA reg-->ASCII char at reg
;; |_____R6___| - 

;;;;;;;;; Code section ;;;;;;;;;;;
.CODE ;; This section contains code.
.ADDR x0000 ;; Specify address to start putting the code
.FALIGN

START
	CONST R0 x20 		; setting argument in R0 to x2020
	HICONST R0 x20
	CONST R1, #0		; set COUNTER to zero
	CONST R2, x00		; store x00 
	HICONST R2 x20		; store x20 of x2000 to check if R0 input is valid addr in user Data mem
	CONST R3 xFF		; store xFF of x7FFF
	HICONST R3 x7F		; store x7F of x7FFF to check if R0 input is valid addr in user Data mem
	
GET_CHARS
	TRAP x02			; TRAP_GETS, uses addr value in R0 as argument
	
APPEND_ZERO
	CONST R4, #0
	STR R4 R0 #1		; store 0 in R4 in addr at R0 + 1

PRINT
	CONST R0 x20
	HICONST R0 x20
	TRAP x03

END