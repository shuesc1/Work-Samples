#include <stdlib.h>
/*
 This is a simple implementation of a linked list.
*/

typedef struct Node node;
struct Node {
  void* value;
  node* next;
};

typedef struct LinkedList linkedlist;
struct LinkedList {
  node* head;
};

int add_to_front(void* value, linkedlist* l);
int add_to_tail(void* value, linkedlist* l);
int remove_from_front(linkedlist* l);
