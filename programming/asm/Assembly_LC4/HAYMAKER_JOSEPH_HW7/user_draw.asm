;; user_draw.asm -- a program that practices using I/O with assembly
;; The program draws a rectangle to the screen in PennSim
;; uses TRAP_DRAW_RECT

;;;  Define colors
;;;
RED   .UCONST x7C00	; 0 11111 00000 00000
GREEN .UCONST x03E0	; 0 00000 11111 00000
BLUE  .UCONST x001F	; 0 00000 00000 11111
WHITE .UCONST x7FFF	; 0 11111 11111 11111
YELLOW .UCONST	x7FE0; 0 11111 11111 00000 
;; YELLOW should be 100% RED, 100% GREEN (shows up kinda goldenrod)
;;===================================================================================;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; To run script file: <Script user_text_script.txt> in LC4
;; |_REG._FILE|										   
;; |R0(#0-127)| -'X coordinate' of upper-left corner of the rectangle
;; |R1(#0-123)| - 'Y coordinate' of upper-left corner of the rectangle	  		  
;; |_R2(#)____|	- length of the rectangle (# of pixels)
;; |_R3(#)____|	- width of side of rectangle (# of pixels)
;; |_R4(x)____| - color of the box  
;; |_R5()_____| - OS_VIDEO_NUM_COLS #128
;; |_____R6___| - OS_VIDEO_NUM_ROWS #124

;;;;;;;;; Code section ;;;;;;;;;;;
.CODE ;; This section contains code.
.ADDR x0000 ;; Specify address to start putting the code
.FALIGN
	
DRAW
	TRAP x05			; TRAP_DRAW_RECT, does all the work

END