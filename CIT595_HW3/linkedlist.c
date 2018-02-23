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
//  printf("|%p| ==> ", n->value);
  while (n->next != NULL) {
    //printf("|%s| ==> ", n->value->name);
    //printf("|%p| ==> ", n->value);
    n = n->next;
    //printf("|%p| ==> ", n->value);
  }
  n->next = new;
  //printf("|%p| ==> ", new->value);
  //printf("\n");
  return 1;
}

int remove_from_front(linkedlist* l) {
  if (l == NULL || l->head == NULL) return 0;
  node* old = l->head;
  //printf("removing node: |%p|\n", old->value);
  l->head = l->head->next;
  free(old);
  // not that this does not free the value in the node, in case it needs to be reused
  return 1;
}
