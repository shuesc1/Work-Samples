	;;FIBONACCI SERIES -- store 1st 20 values in Data Memory
	;;RO:current addr in data mem
	;;R1:current addr minus 2 contents
	;;R2:current addr minus 1 contents
	;;R3:current calculated fibonacci series value to be stored in current addr in data memory
	;;R4: i (counter)

	;Data section
.FALIGN
	.DATA
	.ADDR x4000	;start writing in Data Memory at addr 0x4000 (start of data mem)
	
	;;fill data memory with first 2 values in series
fib_val_arr
	.FILL #0
	.FILL #1 ; fill in 1st 2 values that we already know

	;;start of code section
	.CODE
	.ADDR 0x0000	;start code at addr 0x0000
INICIO
	LEA R0, fib_val_arr	;store addr of fib_val_arr in R0
	ADD R0, R0 #2		;advance ptr 2 since we've already stored values in 1st 2 data mem locations
	CONST R4, #2  		;i=2:already on 3rd iteration (index 2) b/c start off with 0 at x4000 and 1 at x4001
	;;CONST R1, #0	;F0 in R1
	;;CONST R2, #1	;F1 in R2
	JMP PRUEBA

CUERPAZO
	;;LDR R3, R0, #0		;;use addr at R0, get content from data mem and store in R3
	LDR R1, R0, #-2		;;use addr at R0-2, get contents from data mem and store in R1
	LDR R2, R0, #-1		;;use addr at R0-1, get contents from data mem and store in R2
	ADD R3, R2, R1		;;get current fibonacci value: F= F-1 + F-2
	STR R3 R0 #0		;;store newly obtain fib value in R3 in current addr register in Data memory w/ offset of 0
	ADD R0, R0, #1		;; increment current addr
	ADD R4, R4, #1		;; increment counter

PRUEBA
	CMPI R4 #20
	BRnz CUERPAZO		; if i - 20 is negative or zero then jump back to beginning of while loop
	
FIN
	JMP FIN

  .OS
  .CODE ;;end of code section
  .ADDR x8200
  .FALIGN ;; jumps to here
    CONST R7, #0
    RTI
