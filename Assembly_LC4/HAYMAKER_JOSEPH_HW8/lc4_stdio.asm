;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;  Assembly file to serve as bridge ;;;;;;;;;;;;
;;;;;  between subroutines and TRAPs	;;;;;;;;;;;;
;;;;;  for programs we will write in C  ;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;; WRAPPER SUBROUTINES FOLLOW ;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    
.CODE
.ADDR x0010    ;; we start after line 10, to preserve USER_START

;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; TRAP_GETC Wrapper ;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;

.FALIGN
lc4_getc

	;; prologue
	STR R7, R6, #-2	;; save caller's (main's) return addr--> store x002B (PC+1) in x7FF7 (x7FF9-2)
	STR R5, R6, #-3	;; save caller's (main's) frame pointer FP --> x7FFC stored at xx7FF6
	ADD R6, R6, #-3	;; updates Stack Pointer SP --> R6 updated to x7FF6
	ADD R5, R6, #0	;; updates R5's value to x7FF6
		
	;; function body 
	TRAP x00        ; 
	
	;; epilogue
	; TRAP_PUTC has return value R0 the char to be writter, so needs to be copied back to stack
	;; |__x7FF6__| - R5	
	;; |__x7FF6__| - R6
	STR R0, R6, #-1	;; write out return value at R0 to addr at R6-1, x7FF5 (top of stack)
	STR R0, R6, #2	;; write RV to x7FF8 as well

	ADD R6, R6, #3	;; update SP to x7FF9 (used right after we return to problem3.asm)
	;; |__x7FF6__| - R5	
	;; |__x7FF9__| - R6
	LDR	R7, R5, #1	;; make sure that return address (correct PC+1) is in R7 before returning from JSR
	RET

;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; TRAP_PUTC Wrapper ;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;

.FALIGN
lc4_putc

	;; prologue
	STR R7, R6, #-2	;; save caller's (main's) return addr--> store x003D (PC+1) in x7FF8 (x7FFA-2)
	STR R5, R6, #-3	;; save caller's (main's) frame pointer FP --> x7FFC stored at x7FF7
	ADD R6, R6, #-3	;; updates Stack Pointer SP --> R6 updated to x7FF7
	ADD R5, R6, #0	;; updates R5's value to x7FF7

	;; setup local var (c)
	ADD R6, R6, #-1	;; update R6 to x7FF6
	LDR R0, R6, #4	;; load arg (c) at x7FFA into R0 for the trap to use
	STR R0, R5, #-1	;; store that same value out to the top of the stack
		
	;; function body 
	;; arg already stored in R0 from data mem
	TRAP x01        ; R0 must be set before TRAP_PUTC is called
	
	;; epilogue
		; TRAP_PUTC has no return value, so nothing to copy back to stack
	LDR	R7, R5, #1	;; make sure that return address (correct PC+1) is in R7 before returning from JSR
	RET

;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; TRAP_PUTS Wrapper ;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;

.FALIGN
lc4_puts

	;; prologue
	STR R7, R6, #-2	;; save caller's (main's) return addr--> store x003D (PC+1) in x7FF8 (x7FFA-2)
	STR R5, R6, #-3	;; save caller's (main's) frame pointer FP --> x7FFC stored at x7FF7
	ADD R6, R6, #-3	;; updates Stack Pointer SP --> R6 updated to x7FF7
	ADD R5, R6, #0	;; updates R5's value to x7FF7

	;; setup local var (c)
	ADD R6, R6, #-1	;; update R6 to x7FF6
	;;LDR R0, R6, #4	;; load arg (c) at x7FFA into R0 for the trap to use
	ADD R0, R0 #-14		;; R0 currently 400E, need to get it back to start of char array
	STR R0, R5, #-1	;; store that same value out to the top of the stack
		
	;; function body 
	;; arg already stored in R0 from data mem
	TRAP x03        ; R0 must be set before TRAP_PUTC is called
	
	;; epilogue
		; TRAP_PUTC has no return value, so nothing to copy back to stack
	LDR	R7, R5, #1	;; make sure that return address (correct PC+1) is in R7 before returning from JSR
	RET

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; TRAP_DRAW_RECT Wrapper ;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


.FALIGN
lc4_draw_rect
	TEMPS2 .UCONST x4300 ;;-- will be needed to store the important values given the TRAP uses ALL regs
	;; <<<<<<<<prologue>>>>>>>>
	STR R7, R6, #-2	;; save caller's (main's) return addr--> store x0070 (PC+1) in x7FF0 
	STR R5, R6, #-3	;; save caller's (main's) frame pointer FP --> x7FFC stored at x7FEF
	ADD R6, R6, #-3	;; updates Stack Pointer SP --> R6 updated to x7FEF
	ADD R5, R6, #0	;; updates R5's value to x7FEF

	;; setup local var (c)
	ADD R6, R6, #-1	;; update R6 to x7FEE

	;; at this point R5 (x7FEF) & R6 (x7FEE) need to be stored out to TEMPS in Data Mem, cause ish bout to
	;; get cray
	LC R7, TEMPS2	;; x4300 now in R7
	STR R5, R7, #0
	STR R6, R7, #1

	;; R0,R1,R2,R3 & R4 must be set before TRAP_DRAW_RECT is called
	LDR R0, R6, #4	;; R0 - x_coord (x64/#100)
	LDR R1, R6, #5	;; R1 - y_coord (x3C/#60)
	LDR R2, R6, #6  ;; R2 - length (x1E/#30)
	LDR R3, R6, #7	;; R3 - width (x14/#20)
	LDR R4, R6, #8  ;; R4 - color x7C00 (red)
		
	;; <<<<<<<<function body >>>>>>>
	TRAP x05        
	
	;; need to restore R5 & R6 to their prior states
	LC R7, TEMPS2
	LDR R5, R7, #0
	LDR R6, R7, #1

	;; <<<<<<<<<< epilogue >>>>>>>>>>>
	; TRAP_DRAW_RECT has no return value, so nothing to copy back to stack
	LDR	R7, R5, #1	;; make sure that return address (correct PC+1) is in R7 before returning from JSR
	RET