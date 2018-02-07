CC = clang
ARGS = -Wall

all: symbol_table_test populate_symbol_table_test compiler

symbol_table: 
	$(CC) -c $(ARGS) symbol_table.c find_symbols_soln.c

symbol_table.o: symbol_table.c
	$(CC) -c symbol_table.c

find_symbols_soln.o: find_symbols_soln.c find_symbols_soln.h
	$(CC) -g -c find_symbols_soln.c

symbol_table_test: symbol_table
	$(CC) -o test_symbol_table $(ARGS) symbol_table.o find_symbols_soln.o symbol_table_test.c	

populate_symbol_table_test: symbol_table
	$(CC) -o test_populate_symbol_table $(ARGS) symbol_table.o populate_symbol_table_test.c	find_symbols_soln.c

compiler: symbol_table
	$(CC) -o compiler $(ARGS) symbol_table.o compiler.c find_symbols_soln.c

clean: 
	rm -rf test_symbol_table test_populate_symbol_table compiler *.o


