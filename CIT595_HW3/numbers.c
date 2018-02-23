#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <ctype.h>

void* fun1(void*);
void* fun2(void*);
float input_vals[100]; // global float array to hold all input values
int index_arr; // index of current location in array
int quit, max, min, total;

/*
// dummy func
int sq(int num){
	int ret = num * num;
	return ret;
}

void* fun1(void* p){
	int k = *(int*)p; // int value at int ptr p
	int* r = malloc(sizeof(int));
	if(r==NULL) printf("error3: malloc error\n") ;

	//*r = do_something(k);
	*r = sq(k);
	printf("fun1 returning value: %d\n", *r);
	pthread_exit(r);
}
*/


void* fun1(void* p){
  char in[100];
  float val_input;
  //while(q != 1){
	printf("Please input a valid floating point number: \n");
	fgets(in, 100, stdin);
	val_input = atof(in);
	if(strcmp(in, "q")==0 || strcmp(in,"Q")==0){
		quit = 1;
		continue;
	} else if(val_input != 0.0){ //input is valid
		input_vals[index_arr] = val_input;
		index_arr++;
	} else { // conversion couldn't happen (chars) OR 0.0 entered

	}

  //}
}

/*
   void* fun2(void* p){
   int k = *(int*)p; // int value at int ptr p
   int* r = malloc(sizeof(int));
   if(r==NULL) printf("error3: malloc error\n") ;

 *r = do_something(k);
 *r = sq(k);
 printf("fun2 returning value: %d\n", *r);
 pthread_exit(r);
 } */

void* fun2(void* p){
	int avg = 0;
	do{
		sleep(10);
		if(p!=NULL){
			int new_entry = *(int*)p;
			if(new_entry > max){
				max = new_entry;
			}
			if(new_entry < min){
				min = new_entry;
			}
		}
		avg = (total+new_entry)/(index_arr+1)
		printf("maximum value input so far: %d\n", max);
		printf("minimum value input so far: %d\n", min);
		printf("average of all values input so far: %d\n", avg);
		int floor = index_arr - 5;
		if(floor<0){ floor=0; }
		printf("last 5 values input:\n");
		for(int ctr=index_arr; ctr>=floor; ctr--){
			printf("%d\n", input_vals[ctr]);
		}
	
		int* r = malloc(sizeof(int));
		if(r==NULL) printf("error3: malloc error\n") ;
		r = 0;
	} while(quit != 1);

	pthread_exit(r);
}

/*
 * ======================= MAIN ======================
 */
int main(){
	index_arr = 0;
	quit = 0;
	max = 0;
	min = 0;
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
