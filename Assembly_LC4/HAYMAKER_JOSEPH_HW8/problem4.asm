		.DATA
		.ADDR x4000
L2_problem4 		.FILL #73
		.FILL #32
		.FILL #76
		.FILL #79
		.FILL #86
		.FILL #69
		.FILL #32
		.FILL #67
		.FILL #73
		.FILL #84
		.FILL #53
		.FILL #57
		.FILL #51
		.FILL #0
;;;;;;;;;;;;;;;;;;;;;;;;;;;;main;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		.CODE
		.FALIGN
main
	;; prologue
	STR R7, R6, #-2	;; save return address
	STR R5, R6, #-3	;; save base pointer
	ADD R6, R6, #-3
	ADD R5, R6, #0
	ADD R6, R6, #-15	;; allocate stack space for local variables
	
	;; function body
	ADD R1, R5, #-14
	LEA R0, L2_problem4
;ASGNB
	ADD R6, R6, #-1
	STR R2, R6, #0
	ADD R6, R6, #-1
	STR R3, R6, #0
;blkloop!!!!
	AND R3, R3, #0
	ADD R3, R3, #14
L3
	LDR R2, R0, #0
	STR R2, R1, #0
	ADD R0, R0, #1
	ADD R1, R1, #1
	ADD R3, R3, #-1
BRnp L3
	LDR R3, R6, #0
	ADD R6, R6, #1
	LDR R2, R6, #0
	ADD R6, R6, #1
	CONST R7, #0
	STR R7, R5, #-15
	ADD R7, R5, #-14
	ADD R6, R6, #-1
	STR R7, R6, #0
	
	;;;;;;; TRAP_PUTS;;;;;;;;;;;
	JSR lc4_puts
	
	LDR R7, R6, #-1	;; grab return value
	ADD R6, R6, #1	;; free space for arguments
	CONST R7, #0
L1_problem4
	;; epilogue
	ADD R6, R5, #0	;; pop locals off stack
	ADD R6, R6, #3	;; free space for return address, base pointer, and return value
	STR R7, R6, #-1	;; store return value
	LDR R5, R6, #-3	;; restore base pointer
	LDR R7, R6, #-2	;; restore return address
	RET

