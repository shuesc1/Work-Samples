CC = clang
ARGS = -Wall

all: scheduler negative

scheduler:
	$(CC) -o scheduler $(ARGS) driver.c linkedlist.c scheduler.c

negative:
	$(CC) -o negative $(ARGS) negative.c qdbmp.c

clean: 
	rm -rf scheduler negative

