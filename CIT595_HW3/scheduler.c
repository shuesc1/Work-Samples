#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"
#include "process.h"



linkedlist* prior_qs[10];

/*
 * This function schedules the processes in the linked list and simulates their execution.
 *
 * This implementation uses FCFS; you need to modify it to use Priority Round Robin
 * as described in the assignment description.
 */
void schedule(linkedlist* l, int slice) {
	linkedlist* q = malloc(sizeof(linkedlist));
	q->head = NULL;
	int time = 0; // used to keep track of the time that has passed
	int count = 0; // total time the current process has been running

	// figure out the sum of the execution times
	int total = 0;
	node* n = l->head;
	while (n != NULL) {
		process* p = (process*)n->value;
		total += p->time;
		n = n->next;
	}


	process* curr_proc = malloc(sizeof(process));
	curr_proc = NULL;
	int prior_index = 0;
	int preempted_flag = 0; // 0-N ; 1-Y
	int slice_ctr = 0;
	for(int j=0; j<10; j++){
		prior_qs[j]=malloc(sizeof(linkedlist));
		prior_qs[j]->head = NULL;
	}
	 

	/*
	 * This code simulates each step of time
	 */
	for (; time < total; time++) {
		printf("=========================================\n");
		printf("=============== Time %d - %d ============\n", time, time+1);
		printf("=========================================\n");
		// see if there's anything that just arrived at this time
		node* n = l->head;

		// if at the end of this time period slice is over then set preempted_flag
		if(slice_ctr+1 == slice){ 
			preempted_flag=1;
			printf("INTERRUPT: at end of current interval slice will expire\n");		
		}

		// ================= ADDING ARRIVED PROCESSING/QUEUEING ================
		while (n != NULL) {
			process* p = (process*)n->value;
			if (p->arrival == time) {
				printf("Adding process %s [P%d] at time %d\n", p->name, p->priority, time);
				add_to_tail(p, q);

				// if arriving process is higher priority than current -- move current to end of its queue (interrupted)
				if(curr_proc==NULL || p->priority>curr_proc->priority){
					if(curr_proc!=NULL){
						printf("INTERRUPT: higher priority process beginning");
						prior_index = curr_proc->priority;
						remove_from_front(prior_qs[prior_index]);
						add_to_tail(curr_proc, prior_qs[prior_index]);
						preempted_flag = 1;
					}
					add_to_tail(p, prior_qs[p->priority]);
					// if arriving process has same priority as current process
				} else if(curr_proc->priority == p->priority){
					if(preempted_flag==1){
						add_to_tail(p, prior_qs[p->priority]);
						prior_index = curr_proc->priority;
						remove_from_front(prior_qs[prior_index]);
						add_to_tail(curr_proc, prior_qs[prior_index]);
					} else {
						add_to_tail(p, prior_qs[p->priority]);
					}
				}
			}
			n = n->next;
		}
		preempted_flag = 0; //TODO check logic on this


		//==================== PROCESSING ============================
		// run whatever is at the front of the highest priority queue
		for(int j=9; j>=0; j--){
			if(prior_qs[j]!=NULL){
				process* p = (process*) prior_qs[j]->head->value; //TODO fix this -- causes crash
				if(curr_proc!=NULL && p->priority > curr_proc->priority){
					curr_proc = p ;
					break;
				} else if(curr_proc!=NULL && p->priority < curr_proc->priority){
					break; // curr_proc already a higher priority
				}
			}

		}		

		if (q->head != NULL) {
			process* p = (process*)q->head->value;
			//process* p = (process*) curr_proc->head->value;
			if (count == 0) printf("Running process %s at time %d\n", p->name, time);
			//if (count == 0) printf("Running process %s at time %d\n", curr_proc->name, time);
			p->remaining--;
			//curr_proc->remaining--;
			count++;

			if (p->remaining == 0) {
			//if(curr_proc->remaining==0){
				// this task is done!
				printf("Finished process %s at time %d\n", p->name, time + 1);
				//printf("Finished process %s at time %d\n", curr_proc->name, time + 1);
				// remove it
				remove_from_front(q);
				// reset the counter
				count = 0;
				slice_ctr=0; 
				continue;
			}
			slice_ctr++;
			printf("slice time now: %d\n", slice_ctr);
			if(slice_ctr==slice){
				printf("resetting slice counter\n");
				slice_ctr = 0;
				printf("slice counter now: %d\n", slice_ctr);
			}
		}
		}
	}


