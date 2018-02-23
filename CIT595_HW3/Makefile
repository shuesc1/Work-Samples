CC = clang
ARGS = -Wall
LDFLAGS= -pthread -lpthread

all: scheduler negative

numbers:
	$(CC) -g -o numbers $(ARGS) $(LDFLAGS) numbers.c

scheduler:
	$(CC) -g -o scheduler $(ARGS) driver.c linkedlist.c scheduler.c

negative:
	$(CC) -g -o negative $(ARGS) negative.c qdbmp.c

clean: 
	rm -rf scheduler negative numbers

