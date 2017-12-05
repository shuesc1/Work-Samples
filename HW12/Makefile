all: lc4 

lc4: asm_parser.o lc4.o
	clang asm_parser.o assembler.o -g -o assembler

lc4.o: lc4.c
	clang -c lc4.c

asm_parser.o: asm_parser.c asm_parser.h
	clang -g -c asm_parser.c

clean:
	rm -rf *.o

clobber: clean
	rm -rf lc4
