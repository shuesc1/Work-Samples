;;;;;;;;;;;;;;;;;;;;;;;;;;;;main;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		.CODE
		.FALIGN
main
	;; <<<<<<<<<<<<<<<<<<prologue>>>>>>>>>>>>>>>>>>>>>>
	STR R7, R6, #-2	;; save return address (return address), x<MAIN>
	STR R5, R6, #-3	;; save base pointer (calling function's FP), x7FFF
	ADD R6, R6, #-3 ;; updated to point to the top of the stack, x7FFC
	ADD R5, R6, #0 ;; store updated pointer to top of the stack in R5, x7FFC
	ADD R6, R6, #-2	;; allocate stack space for local variables, x7FFA -- top of MAIN's frame
	
	;; function body
	CONST R7, #5	;; store A = 5 on stack
	STR R7, R5, #-1
	CONST R7, #0	;; store B = 0 on stack
	STR R7, R5, #-2
	LDR R7, R5, #-1	;; load A = 5 into R7
	
	;; top of MAIN's frame, now setting up stack for SUB_FACTORIAL subroutine
	ADD R6, R6, #-1	;;
	STR R7, R6, #0	;; save A to top of stack
	;; arg stored for subroutine on top of stack

	JSR SUB_FACTORIAL
	LDR R7, R6, #-1	;; grab return value
	ADD R6, R6, #1	;; free space for arguments
	STR R7, R5, #-2	;; x0078 written to x7FF4 (?)
	CONST R7, #0	;; R7 now holds 0
L1_problem1
	;; <<<<<<<<<<<<<<<<<<<epilogue>>>>>>>>>>>>>>>>>>>>>>>>>>
	ADD R6, R5, #0	;; pop locals off stack
	;; |__x7FF9->x7FF6__| - R6
	
	ADD R6, R6, #3	;; free space for return address, base pointer, and return value
	;; |__x7FF6+3=x7FF9__| - R6 (x7FF9 is bottom of subroutine frame)

	STR R7, R6, #-1	;; store return value
 	;; |_ret. val B at x7FF8__| - R7 now holds x0078

	LDR R5, R6, #-3	;; restore base pointer which in fact was x7FFC -- base pointer for MAIN frame
	;; |__addr at x7FF6==x7FFC__| - R5

	LDR R7, R6, #-2	;; restore return address (whatever is right after MAIN -- x003D)
	;; |__x003D___| - R7 

	RET

