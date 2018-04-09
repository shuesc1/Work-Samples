/**************************************************************

	The program reads an BMP image file and creates a new
	image that is the negative of the input file.

**************************************************************/
#include "qdbmp.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int pixels_already_processed[500][500];

/* Creates a negative image of the input bitmap file */
int main( int argc, char* argv[] )
{
	int boxes_used = 0;
	int x_prime_min = 0;
	int x_prime_max = 0;			
	int y_prime_min = 0;
	int y_prime_max = 0;
	// int x_start = 0;
	// int y_start = 0;
	UCHAR	r, g, b;
	long	r_sum, g_sum, b_sum, r_avg, g_avg, b_avg;
	UINT	width, height, depth;
	UINT	x, y;
	BMP*	bmp;
	BMP*    bmp_out;

	/* Check arguments */
	if ( argc != 4 )
	{
		fprintf( stderr, "Usage: %s <input file> <output file> <int box size>\n", argv[ 0 ] );
		return 1;
	}

	/* Read an image file */
	bmp = BMP_ReadFile( argv[ 1 ] );
	// bmp_out = NULL;
	BMP_CHECK_ERROR( stdout, -1 );


	int box_size = atoi(argv[3]);
	printf("box size: %d\n", box_size);
	if(box_size<=0){
		printf("error1: box size cannot be negative or 0\n");
		return 1;
	}

	/* Get image's dimensions */
	width = BMP_GetWidth( bmp );
	height = BMP_GetHeight( bmp );
	depth = BMP_GetDepth( bmp ) ;
	//printf("width: %lu, height: %lu\n", width, height);

	// Creating blank bmp to write to
	bmp_out = BMP_Create(height,width,depth);

	/* Iterate through all the image's pixels */
	for ( x = 0 ; x < width ; ++x ){ // process all rows in each column before moving to next column
		for ( y = 0 ; y < height ; ++y ){
			//=========for each pixel =======
			// | x = COL ; 0-width			|
			// | y = ROW ; 0-height			|
			// 1. for a given pixel find bounds given the input box_size
			// range: (x-input) through (x+input); (y-input) through (y+input)
			//BMP_GetPixelRGB(bmp, x, y, &r, &g, &b );
			//BMP_SetPixelRGB(bmp_out, x, y, r-50, g-50, b-50);
			r_sum = 0;
			r_avg = 0;
			g_sum = 0;
			g_avg = 0;
			b_sum = 0;
			b_avg = 0;
			// x_start = 0;
			// y_start = 0;
			boxes_used = 0;
			x_prime_min = x - box_size;
			if(x_prime_min < 0){x_prime_min = 0;} // if extends to beyond box limit set to min limit (0)
			x_prime_max = x + box_size;
			if(x_prime_max > width){x_prime_max = width; } // if extends to beyond max box limit set to max (width)
			y_prime_min = y - box_size;
			if(y_prime_min < 0){y_prime_min = 0;} // if extends to beyond box limit set to min limit (0)
			y_prime_max = y + box_size;
			if(y_prime_max > width){y_prime_max = height; } // if extends to beyond max box limit set to max (height)
			//printf("x_prime_min: %d, x_prime_max: %d, y_prime_min: %d, y_prime_max: %d\n", x_prime_min, x_prime_max, y_prime_min, y_prime_max);
			// iterate through inner square around current pixel defined by 
			for(; x_prime_min <= x_prime_max; x_prime_min++){
				for(; y_prime_min <= y_prime_max; y_prime_min++){
					// Get all surrounding pixels' RGB values 
					//if(x_prime_min == x &&  y_prime_min == y){ continue; } //ignore current pixel
					BMP_GetPixelRGB(bmp, x_prime_min, y_prime_min, &r, &g, &b );
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
			BMP_SetPixelRGB(bmp_out, x, y, r_avg, g_avg, b_avg);
			// |							|
			// |							|
			//===============================
		}
	}

	/* Save result */
	BMP_WriteFile( bmp_out, argv[ 2 ] );
	BMP_CHECK_ERROR( stdout, -2 );


	/* Free all memory allocated for the image */
	BMP_Free( bmp );
	BMP_Free(bmp_out);

	return 0;
}

