;;;   "How to call TRAPS"
;;;   --1st outputs ASCII characters to the ASCII display
;;;   --reads ASCII characters from the keyboard
;;;
;;;  Define some colors - useful for problem #3
;;;
RED   .UCONST x7C00	; 0 11111 00000 00000
GREEN .UCONST x03E0	; 0 00000 11111 00000
BLUE  .UCONST x001F	; 0 00000 00000 11111
WHITE .UCONST x7FFF	; 0 11111 11111 11111
	
	
	.CODE
	.ADDR x0000

;;;  Output "Type Here>" to the console, then read input from user

	CONST R0, x54	; ASCII code for T
	TRAP x01		

	CONST R0, x79   ; ASCII code for y
	TRAP x01

	CONST R0, x70   ; ASCII code for p
	TRAP x01

	CONST R0, x65   ; ASCII code for e
	TRAP x01

	CONST R0, x20   ; ASCII code for space
	TRAP x01

	CONST R0, x48
	TRAP x01

	CONST R0, x65
	TRAP x01

	CONST R0, x72
	TRAP x01

	CONST R0, x65
	TRAP x01

	CONST R0, x3E
	TRAP x01
	
	;;x07D0 = #2000 for 2000 milliseconds
	CONST R1 xD0
	HICONST R1 x07		; store x07D0 in R1 to set the amount of time before timer goes off
	
	CONST R6 x00
	HICONST R6 x80		; set R6 to x8000 for the timer reg comparison -- after 2000ms
	;; should have a leading 1
	
	TRAP x04	; READ from keyboard into R0 -- TRAP_GETC_TIMER -- time out after 2s
	TRAP x01	; WRITE R0 to display
	

END