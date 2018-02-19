#include <stdlib.h>
#include <stdio.h>
#include "linkedlist.h"

int add_to_front(void* value, linkedlist* l) {
  if (value == NULL || l == NULL) return 0;

  node* new = malloc(sizeof(node));
  if (new == NULL) return 0;

  new->value = value;
  new->next = l->head;

  l->head = new;

  return 1;
}

int add_to_tail(void* value, linkedlist* l) {
  if (value == NULL || l == NULL) return 0;
  if (l->head == NULL) return add_to_front(value, l);

  node* new = malloc(sizeof(node));
  if (new == NULL) return 0;

  new->value = value;
  new->next = NULL;

  node* n = l->head;
  while (n->next != NULL) {
    //printf("|%s| ==> ", n->value->name);
    n = n->next;
  }
  printf("\n");
  n->next = new;
 
  return 1;
}

int remove_from_front(linkedlist* l) {
  if (l == NULL || l->head == NULL) return 0;
  node* old = l->head;
  l->head = l->head->next;
  free(old);
  // not that this does not free the value in the node, in case it needs to be reused
  return 1;
}
