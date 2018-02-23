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
	//linkedlist* q = malloc(sizeof(linkedlist));
	//q->head = NULL;
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

	// **** HW3 mods ***
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
		//printf("=========================================\n");
		//printf("=============== Time %d - %d ============\n", time, time+1);
		//printf("=========================================\n");
		/*
		   if(time!=0){ 
		   if(curr_proc==NULL){
		   printf("prevous curr_proc at time %d-%d was: [NULL]\n", time-1, time);
		   } else {
		   printf("prevous curr_proc at time %d-%d was: %s\n", time-1, time, curr_proc->name); 
		   }
		   }
		 */
		// see if there's anything that just arrived at this time
		node* n = l->head;

		// if at the end of this time period slice is over then set preempted_flag
		if(slice_ctr+1 == slice){ 
			preempted_flag=1;
			//printf("INTERRUPT: at end of current interval slice will expire\n");		
		}

		// ================= ADDING ARRIVED PROCESSING/QUEUEING ================
		while (n != NULL) {
			process* p = (process*)n->value;
			if (p->arrival == time) {
				printf("%d: Adding process %s\n", time, p->name);
				//add_to_tail(p, q);

				// if arriving process is higher priority than current -- move current to end of its queue (interrupted)
				/*				if(curr_proc!=NULL){
								printf("current process priority: [%d]; arriving process priority[%d]\n", curr_proc->priority, p->priority);
								}
				 */
				if(curr_proc==NULL || curr_proc->priority > p->priority){
					add_to_tail(p, prior_qs[p->priority]);
				} else if(p->priority > curr_proc->priority){
					if(curr_proc!=NULL){
						printf("%d: Pre-empting process %s\n", time, curr_proc->name);
						prior_index = curr_proc->priority;
						remove_from_front(prior_qs[prior_index]);
						add_to_tail(curr_proc, prior_qs[prior_index]);
						preempted_flag = 1;
						//printf("%d: Adding process %s\n", time, p->name);
						//printf("adding process [[%s]] to priority queue [[%d]]\n", p->name, p->priority);
						add_to_tail(p, prior_qs[p->priority]);
					} else {
						//printf("adding process [[%s]] to priority queue [[%d]]\n", p->name, p->priority);
						add_to_tail(p, prior_qs[p->priority]);
					}
					// if arriving process has same priority as current process
				} else if(curr_proc->priority == p->priority){
					if(preempted_flag==1 && curr_proc->priority==p->priority){
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
		/*		for(int k=9; k>=0; k--){
				printf("prior_qs[%d] = %p\n", k, prior_qs[k]);
				if(prior_qs[k] != NULL && prior_qs[k]->head != NULL){
				process* p = (process*) prior_qs[k]->head->value;
				printf("priority [%d] process(es): |%s| ==> ",p->priority,  p->name);	
				node* n = prior_qs[k]->head;
				while(n->next!=NULL){
				n = n->next;
				process* p2 = (process*) n->value;
				printf("|%s| ==> ", p2->name);
		//n = n->next;
		}
		printf("\n");
		}
		}
		 */

		for(int j=9; j>=0; j--){
			//printf("prior_qs[%d] = %p\n", j, prior_qs[j]);
			if(prior_qs[j] != NULL && prior_qs[j]->head != NULL){
				process* p = (process*) prior_qs[j]->head->value;
				//printf("highest priority process to be run is: %s\n", p->name);
				if(curr_proc==NULL){ // no competing higher process -- make this highest process the one to be processed
					curr_proc = p;
					//printf("%d: Running process %s\n", time, curr_proc->name);
					break;
				} else if(curr_proc!=NULL && p->priority > curr_proc->priority){ // if there is another process ongoing but another has higher priority
					curr_proc = p ;
					//printf("%d: Running process %s\n", time, curr_proc->name);
					slice_ctr = 0 ;
					break;
				} else if(curr_proc!=NULL && p->priority==curr_proc->priority && p!=curr_proc){ // if there is another process ongoing but another has higher priority
					curr_proc = p ;
					//printf("%d: Running process %s\n", time, curr_proc->name);				
					slice_ctr = 0 ;
					break;
				} else if(curr_proc!=NULL && p->priority < curr_proc->priority){ // if you get the highest priority process but it's still lower than the current one (should never happen)
					break; // curr_proc already a higher priority
				}
				break;
			}

		}		

		//if (q->head != NULL) {
		//process* p = (process*)q->head->value;
		//process* p = (process*) curr_proc->head->value;
		//if (count == 0) printf("Running process %s at time %d\n", p->name, time);
		if (slice_ctr == 0) printf("%d: Running process %s\n", time, curr_proc->name);
		//if (count == 0) printf("Running process %s at time %d\n", curr_proc->name, time); //TODO fix this causes crash
		//p->remaining--;
		curr_proc->remaining--;
		//printf("[[%s's]] time remaining: %d\n", curr_proc->name, curr_proc->remaining);
		count++;


		if(slice_ctr+1==slice){ printf("%d: Time slice done for process %s\n", time+1, curr_proc->name); }
		//if (p->remaining == 0) {
		if(curr_proc->remaining==0){
			// ** this task is done! **
			//printf("Finished process %s at time %d\n", p->name, time + 1);
			printf("%d: Finished process %s\n", time+1, curr_proc->name);

			// ** remove it **
			//remove_from_front(q);
			//printf("calling remove_from_front on [%s] in prior_qs[%d]\n", curr_proc->name, curr_proc->priority);
			remove_from_front(prior_qs[curr_proc->priority]);			

			// ** reset the counter **
			count = 0;
			slice_ctr=0; 
			curr_proc = NULL;
			if(time+1==total){ printf("%d: All processes finished\n", time+1) ;}
			continue;
		}
		slice_ctr++; // increment slice counter
		//printf("slice time now: %d\n", slice_ctr);
		if(slice_ctr==slice){
			slice_ctr = 0; // reset slice counter
			//printf("slice counter now: %d\n", slice_ctr);
			if(curr_proc->remaining > 0){ // if process has time left
				remove_from_front(prior_qs[curr_proc->priority]); // remove from front of its queue
				add_to_tail(curr_proc, prior_qs[curr_proc->priority]); // add to tail of its queue
			} 
			curr_proc = NULL; // set current process to null
		}
	}
	}


