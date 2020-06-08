#include <stdio.h>


int main(){	

const int ROWS = 100;
const int COLS = 255;
char  program [100][255];

	

	FILE *file = fopen("test1.asm", "r") ;
	if (file == NULL) {
		printf("error2: read_asm_file() failed\n");
		return 2;
	} else {
		int *byte_read;

// int i = 0
//todo maybe while (fgetc(file) != 0) { fill 1 row, i++}

//		while ((c = fgetc(fp)) != EOF)

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 255 ; j++) {
				*byte_read = fgetc(file);
				if (*byte_read == 10) {
					// printf("\n");
					i++;
					continue;
					// } else if(*byte_read == 0 && !isalnum(program[i][j-1]){
					// 	continue;
					// } else {
				} else {
					program[i][j] = *byte_read ;
					// printf("%c", program[i][j]);
				}
				// fscanf(file,"%c\t", &program[i][j]); //read in 1 char at a time (not whole line)
			}
			//trying to use fgets()
			// fgets(input, sizeof(input), file);
			// // program[i][0] = *strtok(input, "\n") ;
			// program[i][0] = *input ;
			// printf("%c\n", program[i][0]);
		}
		// for(int i=0 ; i < 10; i++){
		//    	for(int j = 0; j < 255 ; j++){
		//         printf("%c", program[i][j]);
		//     }
		//         printf("\n");
		// }
		fclose(file) ;
		return 0;
	}
}