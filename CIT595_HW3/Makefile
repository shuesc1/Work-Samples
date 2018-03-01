CC = clang
ARGS = -Wall
LDFLAGS= -pthread -lpthread

all: scheduler negative numbers blur1

numbers:
	$(CC) -g -o numbers $(ARGS) $(LDFLAGS) numbers.c

scheduler:
	$(CC) -g -o scheduler $(ARGS) driver.c linkedlist.c scheduler.c

negative:
	$(CC) -g -o negative $(ARGS) negative.c qdbmp.c

blur1:
	$(CC) -g -o blur1 $(ARGS) blur1.c qdbmp.c

clean: 
	rm -rf scheduler negative numbers blur1

