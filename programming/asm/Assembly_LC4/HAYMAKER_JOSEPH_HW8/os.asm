;;; adapted from code by Amir Roth; CJT Oct 17, 2010
;;; modified 10/2017 - tjf

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;; OS Code ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	.OS
	.CODE
	.ADDR x8000
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;; TRAP VECTOR TABLE ;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	JMP TRAP_GETC		; x00
	JMP TRAP_PUTC		; x01
	JMP TRAP_GETS		; x02
	JMP TRAP_PUTS		; x03
	JMP TRAP_GETC_TIMER ; x04
	JMP TRAP_DRAW_RECT	; x05
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;; OS MEMORY ADDRESS CONSTANTS ;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
USER_CODE_ADDR 	.UCONST x0000
OS_CODE_ADDR 	.UCONST x8000
OS_VIDEO_ADDR 	.UCONST xC000
				
OS_KBSR_ADDR	.UCONST xFE00	; keyboard status register
OS_KBDR_ADDR	.UCONST xFE02	; keyboard data register
OS_ADSR_ADDR	.UCONST xFE04	; display status register
OS_ADDR_ADDR	.UCONST xFE06	; display data register
OS_TSR_ADDR	.UCONST xFE08	; timer register
OS_TIR_ADDR	.UCONST xFE0A	; timer interval register
OS_VDCR_ADDR	.UCONST xFE0C	; video display control register
	
TIM_INIT 	.UCONST #0
TEMPS .UCONST x4200
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;; OS START  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;; operating system entry point (always starts at x8200) ;;;;
	.CODE
	.ADDR x8200
OS_START
	;; R7 <- User code address (x0000)
	LC R7, USER_CODE_ADDR 
	RTI			; RTI removes the privilege bit
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;; OS VIDEO MEMORY ;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	.DATA
	.ADDR xC000	
OS_VIDEO_MEM .BLKW x3E00
	; this merely reserves 3E00 rows of memory for video
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;; OS GLOBALS ;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	.ADDR xA000
OS_GLOBALS_MEM	.BLKW x1000	

OS_VIDEO_NUM_COLS .UCONST #128
OS_VIDEO_NUM_ROWS .UCONST #124

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;; TRAP_GETC ;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; TRAP_GETC - Check for a character from the keyboard
;;; Inputs    - none
;;; Outputs   - R0 = value of character read from the keyboard

	.CODE
TRAP_GETC
	LC R0, OS_KBSR_ADDR
	LDR R0, R0, #0
	BRzp TRAP_GETC		; loop while the MSB is zero

	LC R0, OS_KBDR_ADDR
	LDR R0, R0, #0		; read in the character
	
	RTI	
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;; TRAP_PUTC ;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; TRAP_PUTC - Put a character on the console
;;; Inputs    - R0 = ASCII character value to output to display
;;; Outputs   - none

	.CODE
TRAP_PUTC
	LC R1, OS_ADSR_ADDR
	LDR R1, R1, #0
	BRzp TRAP_PUTC		; loop while the MSB is zero

	LC R1, OS_ADDR_ADDR
	STR R0, R1, #0		; write out the character
	
	RTI
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;; TRAP_GETC_TIMER ;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; TRAP_GETC_TIME - Check for a character from the keyboard and time out after 2 sec
;;; Inputs    - none
;;; Outputs   - R0 = value of character read from the keyboard
;;OS_TSR_ADDR	.UCONST xFE08	; timer register
;; OS_TIR_ADDR	.UCONST xFE0A	; timer interval register
;;(from manual) 
;;xFE08 - TMR - Timer Register: TMR[15] is 1 if the timer has gone off, and 0 otherwise.
;;xFE0A - TMI - Timer Interval Register: the number of milliseconds between timer ticks. Setting this to 0 
;;disables the timer, and setting it to 1 sets the timer to generate "ticks" from "." (period) 
;;characters read from the current Text I/O Device (either user input or a file)
;; <<logic:>> set TMI to 2000ms and keep checking TMR to see if it == x8000, aka it went off. Keep looping till it goes off.

	.CODE
TRAP_GETC_TIMER
		;; <<initialize timer>>
	LC R2, OS_TIR_ADDR		; load value at timer interval into R6 (out of curiosity)
	STR R1, R2 #0			; store #2000 (2000 milliseconds = 2 sec) from .asm file
START_EC_LOOP
	LC R3, OS_TSR_ADDR		; load addr of timer reg
	LDR R3, R3 #0			; load value at timer reg addr -- 1 if timer has gone off
	LC R4, OS_TIR_ADDR	
	LDR R4, R4 #0			; load value in timer interval reg
	CMP R3 R6				; value at timer reg - x8000 = outcome
	BRz TIMER_BREAK			; if 1 then break from loop -- 2 seconds have passed w/out input
	;; <<end timer>>
	
	LC R0, OS_KBSR_ADDR
	LDR R0, R0, #0
	BRzp START_EC_LOOP		; loop while the MSB is zero

	LC R0, OS_KBDR_ADDR
	LDR R0, R0, #0		; read in the character
	
TIMER_BREAK
	RTI	

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;; TRAP_GETS ;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; TRAP_GETS - Check for a string from the keyboard
;;; Inputs    - R0 = address of where string should be written to
;;; Outputs   - R1 = length of string that has been read in
;;; TRAP_GETS - loops on self checking for leading '1' in keyboard status reg.
;;; OS_KBSR_ADDR	.UCONST xFE00	; keyboard status register
;;; OS_KBDR_ADDR	.UCONST xFE02	; keyboard data register
;;; This trap should continually check the Keyboard Status Register until it detects that a character has been typed.
;;; When the trap returns, register R5 should contain the typed character.
;;; |___R5____| - addr keyboard status reg --> value at that addr --> addr keyboard DATA reg --> value of ASCII char at that reg

	.CODE
TRAP_GETS

CHK_ADDR
  CMP R0 R2				; user addr - smallest addr in user memory -- should be greater than or equal
  BRn GET_BREAK 			;
  CMP R0 R3				; user addr - largest addr in user memory -- should be less than or equal
  BRp GET_BREAK 
GET_CHAR
  LC R5, OS_KBSR_ADDR		;; keyboard status reg-- will have leading 1 if there is input  
  LDR R5, R5 #0				;; load value into same reg.
  BRzp GET_CHAR 			;; loop on self until Status Reg has leading 1 (neg) value
  ;; when keyboard status reg detects input - load ASCII char into R5
  LC R5, OS_KBDR_ADDR		;; R5 = addr of keyboard data reg
  LDR R5, R5, #0			;; R5 = value of keyboard data reg.
CHECK_VALID
  CMPI R5 x0A				;; check if char is line feed (return)
  BRz GET_BREAK 				;; if it is then break
STORE
  STR R5, R0 #0				;; store value of ASCII char in addr at R0 (in data memory)
  ADD R1, R1 #1				;; increment letter count
  ADD R0, R0 #1				;; increment addr in Data Memory
  BRnzp TRAP_GETS			;; jump back to beginning to get next char
  
GET_BREAK  
  RTI 

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;; TRAP_PUTS ;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; TRAP_PUTS - Check for a string from the keyboard
;;; Inputs    - R0 = address of where string is located
;;; Outputs   - no output

	.CODE
TRAP_PUTS

;;CHK_VALID_ADDR
;;	CMP R0 R2				; user addr - smallest addr in user memory -- should be greater than or equal
;;	BRn BREAK				;
;;	CMP R0 R3				; user addr - largest addr in user memory -- should be less than or equal
;;	BRp BREAK
PRINT_CHAR
	LC R4, OS_ADDR_ADDR		; load addr of data register for ASCII display into R4
	LDR R1 R0 #0			; load contents of addr in Data Mem at R0 into R1 - ASCII char
	CMPI R1 #0				; if current char 0 break	
	BRz BREAK
	STR R1, R4 #0			; now that we have addr in R4 put ASCII char there to display it
	ADD R0, R0 #1			; advance addr in data mem at R0 by 1
	LDR R1, R0 #0			; load char at current addr into R1
CHK_ZERO
	CMPI R1 #0				; compare this char to zero
	BRnp TRAP_PUTS			; if char isn't zero jump back to beginning, else return
BREAK

	RTI

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;; TRAP_DRAW_RECT ;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; TRAP_DRAW_RECT - draws a colored rectangle on the screen
;;; Inputs    - R0 = x coordinate, upper-left corner of rectangle (0-127)
;;; 		  - R1 = y coordinate, upper-left corner of rectangle (0-123)
;;; 		  - R2 = length of the side of the rectangle -- (0-127)
;;; 		  - R3 – width of the side of the rectangle (0-123)
;;;			 -  R4 = color of rectangle 
;;; Outputs	  - none

	.CODE
TRAP_DRAW_RECT
;;===== <<STEP 1a>>: check that inputs are valid. if not, swap them to min/max values===;;;
  ;; if(R0(x) < 0) set to 0
  CMPI R0 #0		; R0 - 0 -- should be zero or positive
  BRzp NO_SWAP_X_MIN	; if x coord is valid skip over next part
  CONST R0 x00
  HICONST R0 x00
NO_SWAP_X_MIN
  ;; if(R0(x) > 127) set to 127
  LC R5, OS_VIDEO_NUM_COLS		; #128
  ADD R5, R5 #-1				; #127
  CMP R0 R5						; x coord - 127 -- should be neg or zero
  BRnz NO_SWAP_X_MAX
  ADD R0 R5 #0					; otherwise set x coord to 127
NO_SWAP_X_MAX
  ;; if(R1(y) < 0) set to 0
  CMPI R1 #0		; R1 - 0 -- should be zero or positive
  BRzp NO_SWAP_Y_MIN	; if x coord is valid skip over next part
  CONST R1 x00
  HICONST R1 x00
NO_SWAP_Y_MIN
  ;; if(R1(y) > 123) set to 123
  LC R6, OS_VIDEO_NUM_ROWS		; #124
  ADD R6, R6 #-1				; #123
  CMP R1 R6						; x coord - 123 -- should be neg or zero
  BRnz NO_SWAP_Y_MAX
  ADD R1 R6 #0					; otherwise set y coord to 123
NO_SWAP_Y_MAX
;;;;;; <<STEP 1b>>: check that length/width are valid. if not, swap them to min/max values;;;;;;
  ;; if(length + R0(x start) > 127) set to : 127 - x = length
  ADD R2 R2 R0		; length + x start = length_end
  CMP R2 R5			; length_end - x limit -- should be negative or zero
  BRnz NO_SHORTEN_LEN
  ;;SUB R2 R2 R0		; return to orig length
  SUB R2 R5 R0		; R2 (new len) = max x (127) - x start (R0)
  ADD R2 R2 R0
NO_SHORTEN_LEN
  SUB R2 R2 R0		; return length_end to just length
  ;; if(width + R1(y start) > 123) set to : 123 - y = length
  ADD R3 R3 R1		; width + y start = width_end
  CMP R3 R6			; width_end - 123 -- should be negative or zero
  BRnz NO_SHORTEN_WID
  SUB R3 R6 R1		; R3 (new width) = max y (123) - y start 
  ADD R3 R3 R1
NO_SHORTEN_WID 
  SUB R3 R3 R1		; return max_width to just width
;;;;;;;;;;;;;;; NOW ALL INPUTS ADJUSTED CORRECTLY ;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;; <<STEP 2>>: writing out to display ;;;;;;;;;;;;;;;;;;;
SETUP
  ADD R5 R5 #1		; #128 again
  ;; 1st operation -- vid_mem_location = xC000 + (y*128) + x
  ;; first free up R4 & R6 to use for operations
  LC R6, TEMPS		; addr x4200
  STR R4, R6 #0		; store color in data mem--> x4200|__xCOLOR__|
  CONST R4 x00
  HICONST R4 xC0	; xC000 now in R4  --> |__R4(xC000)__|
  MUL R1 R1 R5		; R1 = (y*128)
  ADD R0 R0 R1		; R0 = R1 + x
  ADD R0 R0 R4		; R0 = R0 + xC000 ==> |_R0_| = **addr in vid mem to start at**
  LDR R6 R6 #0		; now load COLOR value back into R6
  ;; currently: ;;
  ;;|__R0__| - addr in vid mem to start drawing at
  ;;|__R1__| - <<FREE>> (y*128)
  ;;|__R2__| - length
  ;;|__R3__| - width
  ;;|__R4__| - <<FREE>> xC000 --> TEMPS addr
  ;;|__R5__| - #128 (could be used)
  ;;|__R6__| - COLOR value

WIDTH_WH_LOOP
  ;; while(width >= 0)
  LC R4, TEMPS		; load TEMPS addr into R4
  STR R2, R4 #0		; now store original LENGTH value in TEMPS in Data Mem
  					; redundant, but needed for 1st iteration
  STR R0, R4 #1		; store out current row starting x point in video data mem
  CMPI R3 #0		; R3 (width) - 0 -- should be pos or zero to enter loop
  BRn RECT_BREAK 	; otherwise break out
  
LENGTH_WH_LOOP
  STR R6, R0 #0 	; store color at R6 in video mem addr at R0
  ADD R0 R0 #1		; increment addr in video data mem
  ADD R2 R2 #-1		; decrement length counter
  CMPI R2 #0		; length counter - 0 -- should be pos or zero while looping
  BRzp LENGTH_WH_LOOP ; this loop should fill a whole row up to the given length

LWR_WIDTH_WH_LOOP
  ADD R3 R3 #-1		; decrement WIDTH
  LC R4, TEMPS		; make sure addr still correct in R4
  LDR R2 R4	#0		; reset LENGTH value for next row fill
  LDR R0 R4 #1		; restore current row start address to R0
  ADD R0 R0 R5		; video mem addr_next_row = current row start addr + 128
  JMP WIDTH_WH_LOOP

RECT_BREAK
  
  RTI


