#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <ctype.h>
#include <string.h>

void* fun1(void*);
void* fun2(void*);
float input_vals[100]; // global float array to hold all input values
int index_arr; // index of current location in array
int quit, max, min, total, current_ind;


/*
// dummy func
int sq(int num){
int ret = num * num;
return ret;
}
 */

void* fun1(void* p){
	char in[100];
	float val_input;

	while(quit != 1){
		printf("Please input a valid floating point number: \n");
		fgets(in, 100, stdin);
		if(strcmp(in,"q\n")==0 || strcmp(in,"Q\n")==0){
			quit = 1;
			printf("quit entered\n");
			break;
		}
		val_input = atof(in);
		if(val_input != 0.0){ //input is valid float/double
			input_vals[index_arr] = val_input;
			index_arr++; // increase index of global array -- should only be modified here
		} else { // conversion couldn't happen (chars) OR 0.0 entered
			printf("Input not a valid float!!\n");
			//TODO account for if input is 0/0.0/0.0000000
		}
	}
	void* v = NULL;
	//*(int*)v = NULL;
	pthread_exit(v);
}

void* fun2(void* p){
	int avg = 0;
	do{
		sleep(10);
		if(current_ind < index_arr){ // a new value will have been added -- if not no need to recompute all values
			//int new_entry = *(int*)p;
			//int new_entry = input_vals[index_arr];
			//printf("new_entry: %d\n", new_entry);
			//printf("current max: %d\n", max);
			int new_entry = 0;
			int total = 0;
			for(int i=0; i<index_arr; i++){
				new_entry = input_vals[i];
				total+=input_vals[i];
				if(new_entry > max){
					max = new_entry;
					printf("new max set to %d\n", max);
				}
				if(new_entry < min){
					min = new_entry;
					printf("new min set to %d\n", min);
				}
			}
			//new_entry = input_vals[index_arr];
			avg = (total)/(index_arr);	
			current_ind = index_arr;
		}
		printf("maximum value input so far: %d\n", max);
		printf("minimum value input so far: %d\n", min);
		printf("average of all values input so far: %d\n", avg);
		int floor = index_arr - 5;
		if(floor<0){ floor=0; }
		printf("last 5 values input:\n");
		for(int ctr=index_arr-1; ctr>=floor; ctr--){
			printf("%f\n", input_vals[ctr]);
		}

		//int* r = malloc(sizeof(int));
		//if(r==NULL) printf("error3: malloc error\n") ;
		//r = 0;
	} while(quit != 1);

	void* v = NULL;
	//*(int*)v = 1;

	pthread_exit(v);
}

/*
 * ======================= MAIN ======================
 */
int main(){
	index_arr = 0;
	quit = 0;
	max = 0;
	min = 0;
	current_ind = 0;
	int ret_val;
	pthread_t t1, t2 ;
	int a1 = 2, a2 = 4; 

	// starting threads
	ret_val = pthread_create(&t1, NULL, &fun1, &a1); // tbd if 4th input arg needs to go here
	if(ret_val != 0){
		printf("error1: thread creation failed. Exiting...\n");
		return 1;
	}
	ret_val = pthread_create(&t2, NULL, &fun2, &a2); // tbd if 4th input arg needs to go here
	if(ret_val != 0){
		printf("error1: thread creation failed. Exiting...\n");
		return 1;
	}

	// threads now running simultaneously
	void* r1;
	void* r2;
	ret_val = pthread_join(t1, &r1); // waits till pthread_exit() is called; &r1 is ptr to ptr that holds ret value of fun1
	if(ret_val != 0){
		printf("error2: pthread_join() error. Exiting...\n");
		return 2;
	}
	// block/wait here till above thread finished
	ret_val = pthread_join(t2, &r2); // waits till pthread_exit() is called; &r1 is ptr to ptr that holds ret value of fun1
	if(ret_val != 0){
		printf("error2: pthread_join() error. Exiting...\n");
		return 2;
	}

	return 0;
}
