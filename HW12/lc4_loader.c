/************************************************************************/
/* File Name : lc4_loader.c		 										*/
/* Purpose   : This file implements the loader (ld) from PennSim		*/
/*             It will be called by main()								*/
/*             															*/
/* Author(s) : tjf and you												*/
/************************************************************************/

#include <stdio.h>
#include "lc4_memory.h"

/* declarations of functions that must defined in lc4_loader.c */

FILE* open_file(char* file_name)
{
	return NULL ;
}

int parse_file (FILE* my_obj_file, row_of_memory** memory)
{
	return 0 ;
}
