;; Implements an algorithm as a SUBROUTINE to compute the factorial of an argument
;; Arguments are retrieved and then stored back in Data Memory
;; |_REG. FILE|										    |_DATA MEMORY_|
;; |__R0(A)___| -value in addr R2|<FACTORIAL_ARR>(x4020)|_____#6______|
;; |__R1(B)___| -output (or -1 error) |			  x4021 |_____#5______|
;; |_R2(x4020)|	-start addr in Data Mem			  x4022 |_____#8______|
;; |__R3(#5)__|	-COUNTER = 5					  x4023 |_____#10_____|
;; |_____R4___| 								  x4024 |_____#-5_____|

;;;;;;;;;;;;;;;;;;;;;;;;; Data section - Fill Data Memory ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
.FALIGN
	.DATA
	.ADDR x4020	;start writing in Data Memory at addr 0x4020 (near start of data mem)	
	;;fill data memory with arguments
FACTORIAL_ARR
	.FILL #6
	.FILL #5
	.FILL #8
	.FILL #10
	.FILL #-5
;;===================================================================================;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;; Code section ;;;;;;;;;;;
.CODE ;; This section contains code.
.ADDR x0000 ;; Specify address to start putting the code
.FALIGN
START
	LEA R2, FACTORIAL_ARR ;; load first address of FACTORIAL_ARR into a register file R2
	CONST R3, #5		;; COUNTER = 5
	JMP OUTER_WHILE_LOOP ;; while(COUNTER > 0)

BODY	;; retrieves int from current addr, checks if it's valid, if T call subroutine, 
		;; store output in same register, advance register addr 1, advance counter #-1
	LDR R0, R2, #0		;; load contents of addr at R2 into R0 (A)
MAIN
	CMPI R0, #0			;; if(A<0)
	BRn CATCH_ILLEGAL_ARG	;; jump to catch statement
	CMPI R0, #8			;; if(A>8)
						;; 16 bits, 2 bytes, LC4 can represent up to #131071
						;; thus, largest factorial is 8! == #40320 (9 too large)
	BRp CATCH_ILLEGAL_ARG
	;; otherwise if no illegal args then call subroutine
	JSR SUB_FACTORIAL	;; B = SUB_FACTORIAL(A)
BODY_2
	STR R1, R2, #0		;; dmem[R2 + 0] = R1(B)
	CONST R1, #0		;; clear out R0
	ADD R2, R2, #1		;; after storing B in data mem addr increment address
    ADD R3, R3, #-1		;; decrement COUNTER before jumping to outer while loop
JMP OUTER_WHILE_LOOP
;;;;;;;; FACTORIAL as subroutine;;;;;;;;;
.FALIGN
SUB_FACTORIAL
  ADD R1, R0, #0	;; B=A! when while loop completes - effectively copies contents of R0
WH_LP_START
 CMPI R0, #1		;; while(A>1)
 BRnz BODY_2		;; If A-1 is negative or zero jump to the end
 ADD R0, R0, #-1	;; A = A - 1
 MUL R1, R1, R0 	;; B = B * A
 JMP WH_LP_START	;; Jump back to start of while loop
RET
;;;;;;;;;;;end of subroutine;;;;;;;;;;;;;;;;
CATCH_ILLEGAL_ARG
  CONST R1, #-1		;; B = #-1, 0xFFFF ==> ERROR
  STR R1, R2, #0	;; dmem[R2 + 0] = R1(#-1)
  ADD R2, R2, #1	;; after storing -1 in data mem addr increment address
  ADD R3, R3, #-1	;; decrement COUNTER before falling into outer while loop

OUTER_WHILE_LOOP	;; while(COUNTER > 0)
  CMPI R3, #0		;; COUNTER - 0
  BRp BODY			;; if COUNTER-0 is positive then jump to body 
  
END

.OS
.CODE
.ADDR x8200
.FALIGN
	CONST R7, #0
	RTI