;; Implements an algorithm as a SUBROUTINE to compute the factorial of an argument
;; User argument supplied above subroutine
;; |___R0____| - A = #5
;; |___R1____| - previously output
;; |___R2____|
;; |___R3____|
;; |___R4____|
;; |___R5____| - frame ptr FP, x7FFC
;; |___R6____| - stack ptr SP, x7FF9
;; |___R7____| - # 5 from storing at top of stack

;; x7FF6|_______| - 
;; x7FF7|_______| - 
;; x7FF8|_______| - 
;; x7FF9|_______|_-___A=#5____arg_for_subroutine_____________
;; x7FFA|_______| - B = #0
;; x7FFB|_______| - A = #5
;; x7FFC|_______|____x7FFF_(R5)-caller's_FP________________
;; x7FFD|_______| - xMAIN return address (x0068 or whatever)
;; x7FFE|_______| - 
;; x7FFF|_______| - 

;; so starting here MAIN FramePointer-x7FFC, SP-x7FF9 / SUB_FACTORIAL FP
;;;;;;;;;;;;;;;; FACTORIAL as subroutine ;;;;;;;;;;;;;;;;
.FALIGN
SUB_FACTORIAL
;; ================ PROLOGUE ==============
  ;; prologue
  STR R7, R6, #-2	;; save return address (return address), x0069 stored at x7FF7 (x7FF9-2)
  STR R5, R6, #-3	;; save base pointer (calling function's FP), x7FFC (FP) stored at x7FF6 (x7FF9-3)
  ADD R6, R6, #-3 ;; updated to point to the top of the stack, x7FF6 (x7FF9-3) stored in R6
  ADD R5, R6, #0 ;; store updated pointer to top of the stack in R5, x7FF6 stored in R5
  
  ;; setup local variable
  ADD R6, R6, #-1	;; allocate stack space for local variable(only 1 -- B), x7FF5 in R6-- top of SUBROUTINE's frame
  LDR R0, R6, #4	;; store in R0 the #5 (A) --> R0 = dmem[x7FF5 + 4 = x7FF9]
  STR R0, R5, #-1	;; store #5 (A) at x7FF5 (x7FF6) --> aka B = A and is stored at x7FF5
;; =============== PROLOGUE END ==========

  CMPI R0, #0			;; if(A<0)
  BRn CATCH_ILLEGAL_ARG	;; jump to catch statement
  CMPI R0, #8			;; if(A>8)
						;; 16 bits, 2 bytes, LC4 can represent up to #131071
						;; thus, largest factorial is 8! == #40320 (9 too large)
  BRp CATCH_ILLEGAL_ARG
	;; otherwise if no illegal args then call subroutine
  ADD R1, R0, #0	;; B=A! when while loop completes - effectively copies contents of R0
  ;;B now in x7FF5 and R1
WH_LP_START
  STR R1, R6, #3	;; each iteration store return value at R1 (B == return value RV) out to Data Mem at x7FF8 (x7FF5 + 3)
  CMPI R0, #1		;; while(A>1)
  BRnz FINISH		;; If A-1 is negative or zero jump to the end
  ADD R0, R0, #-1	;; A = A - 1
  MUL R1, R1, R0 	;; B = B * A
  JMP WH_LP_START	;; Jump back to start of while loop
CATCH_ILLEGAL_ARG
  CONST R1, #-1		;; B = #-1, 0xFFFF ==> ERROR
  STR R1, R6, #3	;; if there is an error store return value at R1 and also out to Data Mem at x7FF8 (x7FF5 + 3)
FINISH

  ;; epilogue
  LDR R7, R6, #3	;; store in R7 addr the return value (B) at x7FF8
  ADD R6, R6, #4	;; restore SP stack pointer to top of main stack -- R6 now holds addr of main stack ptr R6|__x7FF9__|
  STR R7, R5, #-1	;; update local B = R7 == return value --> R7|__updated B____|
  ;; problem though -- R7 now has value x78, NOT the addr to return to (which is stored in x7FF7)
  LDR R7, R5, #1	;; store Return Address RA (x003D) from x7FF7 into R7 for RET

RET
;;;;;;;;;;;;;;;;;;;;;end of subroutine;;;;;;;;;;;;;;;;;;;;