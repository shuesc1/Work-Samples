;; Implements an algorithm as a SUBROUTINE to compute the factorial of an argument
;; User argument supplied above subroutine
;; |__R0(A)__| - user argument (passed from script or PennSim command line)
;; |__R1(B)__| - output (answer or -1 for error)
;; |___R2____|
;; |___R3____|
;; largest factorial is 8! == #40320 (9 too large) -- also mentioned below in EC part


.CODE ;; This section contains code.
.ADDR x0000 ;; Specify address to start putting the code

.FALIGN

MAIN
	;;CONST R0, #9		;; A = 6 for test purposes -- arg passed in script file
	CMPI R0, #0			;; if(A<0)
	BRn CATCH_ILLEGAL_ARG	;; jump to catch statement
	CMPI R0, #8			;; if(A>8)
						;; 16 bits, 2 bytes, LC4 can represent up to #131071
						;; thus, largest factorial is 8! == #40320 (9 too large)
	BRp CATCH_ILLEGAL_ARG
	
	;; otherwise if no illegal args then call subroutine
	JSR SUB_FACTORIAL	;; B = SUB_FACTORIAL(A)

JMP END

;;;;;;;;;;;;;;;; FACTORIAL as subroutine;;;;;;;;;;;;;;;;
.FALIGN
SUB_FACTORIAL
  ADD R1, R0, #0	;; B=A! when while loop completes - effectively copies contents of R0
WH_LP_START
 CMPI R0, #1		;; while(A>1)
 BRnz END		;; If A-1 is negative or zero jump to the end
 ADD R0, R0, #-1	;; A = A - 1
 MUL R1, R1, R0 	;; B = B * A
 JMP WH_LP_START	;; Jump back to start of while loop
RET
;;;;;;;;;;;;;;;;;;;;;end of subroutine;;;;;;;;;;;;;;;;;;;;

CATCH_ILLEGAL_ARG
  CONST R1, #-1		;; B = #-1, 0xFFFF ==> ERROR
  
END

.OS
.CODE
.ADDR x8200
.FALIGN
	CONST R7, #0
	RTI