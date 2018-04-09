/**************************************************************

	The program reads an BMP image file and creates a new
	image that is the negative of the input file.

**************************************************************/
#include "qdbmp.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>

UINT	width, height, depth; // make global so all threads have access to this info
int num_pixels_processed, total_pixels_per_thread;
int pixels_already_processed[500][500];
void* fun1(void*);
pthread_mutex_t lock;
BMP*	bmp;
BMP*    bmp_out;

void* fun1(void* p){
	int box_size = *(int*)p; // number of boxes will be passed in
	//int start = *(int*)p; // take in the 
	//int end = start + total_pixels_per_thread ;
	int boxes_used = 0;
	int num_to_process = total_pixels_per_thread; // make local copy of the amount of pixels to process (1/N)
	int x_prime_min = 0;
	int x_prime_max = 0;			
	int y_prime_min = 0;
	int y_prime_max = 0;
	int a = 0;
	UCHAR	r, g, b ; 
	long r_sum, g_sum, b_sum, r_avg, g_avg, b_avg;
	UINT	x, y;
	int processed = 0;
	/* Iterate through all the image's pixels */
	for ( x = 0 ; x < width ; ++x ){ // process all rows in each column before moving to next column
		for ( y = 0 ; y < height ; ++y ){
			if(num_to_process>0){
			//=========for each pixel =======
			// first check that pixel hasn't been processed
				a = pthread_mutex_lock(&lock);
				if(a!=0){ printf("pthread_mutex_lock error!\n");}
				processed = pixels_already_processed[x][y] ;
				pthread_mutex_unlock(&lock);
				if(processed==1){ // pixel has already been processed
					continue;
				} else {
					a = pthread_mutex_lock(&lock);
					if(a!=0){ printf("pthread_mutex_lock error!\n");}
					pixels_already_processed[x][y]=1 ; //mark current pixel as processed
					pthread_mutex_unlock(&lock);
					r_sum = 0;
					r_avg = 0;
					g_sum = 0;
					g_avg = 0;
					b_sum = 0;
					b_avg = 0;
					boxes_used = 0;
					x_prime_min = x - box_size;
					if(x_prime_min < 0){x_prime_min = 0;} // if extends to beyond box limit set to min limit (0)
					x_prime_max = x + box_size;
					if(x_prime_max > width){x_prime_max = width; } // if extends to beyond max box limit set to max (width)
					y_prime_min = y - box_size;
					if(y_prime_min < 0){y_prime_min = 0;} // if extends to beyond box limit set to min limit (0)
					y_prime_max = y + box_size;
					if(y_prime_max > width){y_prime_max = height; } // if extends to beyond max box limit set to max (height)
					// iterate through inner square around current pixel defined by 
					for(; x_prime_min <= x_prime_max; x_prime_min++){
						for(; y_prime_min <= y_prime_max; y_prime_min++){
						// Get all surrounding pixels' RGB values 
						//a = pthread_mutex_lock(&lock);
						//if(a!=0){ printf("pthread_mutex_lock error!\n");}
						BMP_GetPixelRGB(bmp, x_prime_min, y_prime_min, &r, &g, &b ); //TODO race condition
						//pthread_mutex_unlock(&lock);
						r_sum = r_sum + r;	
						g_sum = g_sum + g;
						b_sum = b_sum + b;
						boxes_used++;
						}
					}
					r_avg = r_sum / boxes_used ;
					g_avg = g_sum / boxes_used ;
					b_avg = b_sum / boxes_used ;
					// Set current pixel's new RGB values 
					//a = pthread_mutex_lock(&lock);
					//if(a!=0){ printf("pthread_mutex_lock error!\n");}
					BMP_SetPixelRGB(bmp_out, x, y, r_avg, g_avg, b_avg); //TODO race condition
					//pthread_mutex_unlock(&lock);
					num_to_process--;
				}
			} else {
				break; // this thread has processed 1/N of the pixels
			}
		}
	}
void* v = NULL;
pthread_exit(v);
}


/* Creates a negative image of the input bitmap file */
int main( int argc, char* argv[] ){
	num_pixels_processed = 0;
	int total_pixels = 0;
	int ret_val;
	total_pixels_per_thread = 0;
	pthread_t t1 ;

	/* Check arguments */
	if ( argc != 5 ){
		fprintf( stderr, "Usage: %s <input file> <output file> <int box size> <int number of threads to run>\n", argv[ 0 ] );
		return 1;
	}

	/* Read an image file */
	bmp = BMP_ReadFile( argv[ 1 ] );
	BMP_CHECK_ERROR( stdout, -1 );
	// void* box_size = NULL;
	// *(int*)box_size = atoi(argv[3]);
	int box_size = atoi(argv[3]) ;
	//printf("box size: %d\n", box_size);
	int num_threads = atoi(argv[4]);

	/* Get image's dimensions */
	width = BMP_GetWidth( bmp );
	height = BMP_GetHeight( bmp );
	depth = BMP_GetDepth( bmp ) ;
	if(width > 500 || height > 500){
		printf("error0: bmp size too many pixels!\n");
		return 1;
	}
	total_pixels = width * height; // size of 2D array
	total_pixels_per_thread = total_pixels / num_threads; // all pixels divided over number of threads specified

	// fill reference 2D matrix with 0s (ie corresponding pixel hasn't been processed yet)
	for(int l=0; l<width; l++){
		for(int m=0; m<width; m++){
			pixels_already_processed[l][m] = 0;
		}
	}

	// Creating blank bmp to write to
	bmp_out = BMP_Create(height,width,depth);

	// error checking
	if(box_size<=0){
		printf("error1: box size cannot be negative or 0\n");
		return 1 ;
	}

	if(num_threads<=0){
		printf("error2: thread size cannot be negative or 0\n");
		return 2 ;
	}

	// ====================== THREADS ==========================
	//void* box_input;
	//*(int*) box_input = box_size;

	// create the specified number of threads
	for(int i=0; i<=num_threads; i++){
		// starting threads
		ret_val = pthread_create(&t1, NULL, &fun1, &box_size); // tbd if 4th input arg needs to go here
		if(ret_val != 0){
			printf("error3: thread creation failed. Exiting...\n");
			return 3;
		}
	}

	// join threads
	//for(int i=0; i<=num_threads; i++){
		void* r1;
	    ret_val = pthread_join(t1, &r1); // waits till pthread_exit() is called; &r1 is ptr to ptr that holds ret value of fun1
	    if(ret_val != 0){
	    	printf("error4: pthread_join() error. Exiting...\n");
	    	return 4;
	    }
	//}

	// ===========================================================

	/* Save result */
	BMP_WriteFile( bmp_out, argv[ 2 ] );
	BMP_CHECK_ERROR( stdout, -2 );


	/* Free all memory allocated for the image */
	BMP_Free( bmp );
	BMP_Free(bmp_out);

	return 0;
}
