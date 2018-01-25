CC = clang
ARGS = -Wall

all: find_symbols_test

practice: practice.c
	clang -o practice practice.c

2dArrs: 2dArrs.c
	clang -o 2dArrs 2dArrs.c

find_symbols:
	$(CC) -c $(ARGS) find_symbols.c

find_symbols_test: find_symbols.o
	$(CC) -o test_find_symbols $(ARGS) find_symbols.o find_symbols_test.c

clean: 
	rm -rf test_find_symbols *.o

