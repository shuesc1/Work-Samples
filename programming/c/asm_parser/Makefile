all: assembler 

assembler: asm_parser.o assembler.o
	clang asm_parser.o assembler.o -g -o assembler

assembler.o: assembler.c
	clang -c assembler.c

asm_parser.o: asm_parser.c asm_parser.h
	clang -g -c asm_parser.c

clean:
	rm -rf *.o

clobber: clean
	rm -rf assembler
