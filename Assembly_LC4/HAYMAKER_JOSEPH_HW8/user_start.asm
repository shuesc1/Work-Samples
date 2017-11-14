;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;  Assembly to setup stack pointer ;;;;;;;;;;;;;
;;;;;                and               ;;;;;;;;;;;;;
;;;;;    call C's "main()" function    ;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

.DATA
.ADDR x2000
USER_STACK_ADDR .UCONST x7FFF	; address where stack should start
    
.CODE
.ADDR x0000

.FALIGN
USER_START
    LC R6, USER_STACK_ADDR	; initialize the stack pointer (R6)
    ADD R5, R6, #0		; initialize the frame pointer (R5)
    LEA R7, main
    JSRR R7			; invoke the main routine
END