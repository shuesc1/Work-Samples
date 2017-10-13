;; prime number problem
;; tells if a given int is prime 
;;R0=A, R1=0/1 given result(PRIME_FLAG), R2=B, #1 IMM, #0 IMM

  .CODE		; This is a code segment, instructions in prog memory
  .ADDR  0x0000	; Start filling in instructions at address 0x00

  ;;CONST R0, #7  ; Initialize A to 7 (number to be check, previously provided from user)

  CMPI R0, #1	; if (A <= 1)
  BRp IF1_END	; if A-0== pos jump to end of if statement, else enter if statement
  CONST R1, #0  ; set flag to 0
  JMP END
IF1_END
  CONST R2, #2	; B = 2
  CONST R1, #1	; PRIME_FLAG = 1

WHILE_START
  MUL R3 R2 R2	; B * B = B^2 stored in R3
  CMP R0 R3	; while(A >= B^2), R0(A), R3(B^2)
  BRn WHILE_END	; if T (A-B^2 = neg) jump to end of while loop
  ;; enter while loop	
  MOD R4 R0 R2	; A%B, store answer in R4
  ;; if statement #2
  CMPI R4 #0	;if(A%B == 0) 
  BRnp IF2_END	;if A%B-0 = neg or pos (true) then skip block
  CONST R1 #0	;else enter if statement, PRIME_FLAG=0
  JMP END
IF2_END		;end of 2nd if statement
  ADD R2 R2 #1 ; B = B + 1, increment B
WHILE_END

END
  JMP END	;loop forever on END

  .CODE
