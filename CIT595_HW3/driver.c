#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"
#include "process.h"

void schedule(linkedlist*, int);


int main() {

  process* p0 = malloc(sizeof(process));
  p0->name = "sleepy";
  p0->arrival = 0;
  p0->time = 15;
  p0->remaining = 15;
  p0->priority = 1;

  process* p1 = malloc(sizeof(process));
  p1->name = "dopey";
  p1->arrival = 2;
  p1->time = 5;
  p1->remaining = 5;
  p1->priority = 9;

  process* p2 = malloc(sizeof(process));
  p2->name = "doc";
  p2->arrival = 0;
  p2->time = 6;
  p2->remaining = 6;
  p2->priority = 8;

  process* p3 = malloc(sizeof(process));
  p3->name = "grumpy";
  p3->arrival = 17;
  p3->time = 7;
  p3->remaining = 7;
  p3->priority = 5;

  process* p4 = malloc(sizeof(process));
  p4->name = "happy";
  p4->arrival = 8;
  p4->time = 3;
  p4->remaining = 3;
  p4->priority = 8;

  process* p5 = malloc(sizeof(process));
  p5->name = "bashful";
  p5->arrival = 16;
  p5->time = 9;
  p5->remaining = 9;
  p5->priority = 4;

  process* p6 = malloc(sizeof(process));
  p6->name = "sneezy";
  p6->arrival = 17;
  p6->time = 5;
  p6->remaining = 5;
  p6->priority = 4;


  linkedlist* l = malloc(sizeof(linkedlist));
  l->head = NULL;
  add_to_tail(p0, l);
  add_to_tail(p1, l);
  add_to_tail(p2, l);
  add_to_tail(p3, l);
  add_to_tail(p4, l);
  add_to_tail(p5, l);
  add_to_tail(p6, l);

  schedule(l, 4);

  return 0;

}



