CC = clang
ARGS = -Wall

all: find_symbols_test

find_symbols:
	$(CC) -g -c $(ARGS) find_symbols.c

find_symbols_test: find_symbols.o
	$(CC) -g -o test_find_symbols $(ARGS) find_symbols.o find_symbols_test.c

clean: 
	rm -rf test_find_symbols *.o

