;; Implements an algorithm to compute the factorial of an argument
;; User argument supplied via corresponding script-- factorial_script.txt
;; |__R0(A)__| - user argument
;; |__R1(B)__|
;; |___R2____|
;; |___R3____|

.CODE ;; This section contains code.
.ADDR x0000 ;; Specify address to start putting the code

.FALIGN
  ;;CONST R0, #5 	;; Setting argument to 5 for 1st example--otherwise set in script
  ADD R1, R0, #0	;; B=A! when while loop completes - effectively copies contents of R0
  
WH_LP_START
 CMPI R0, #1		;; while(A>1)
 BRnz END		;; If A-1 is negative or zero jump to the end
 ADD R0, R0, #-1	;; A = A - 1
 MUL R1, R1, R0 	;; B = B * A
 JMP WH_LP_START	;; Jump back to start of while loop

END			;; End of program, loop back on itself
  JMP END

.OS
.CODE
.ADDR x8200
.FALIGN
	CONST R7, #0
	RTI