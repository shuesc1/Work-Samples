#include<stdio.h>
#include <string.h>
int main() {

	long int decimalNumber,remainder,quotient;
	int i=1,j,temp;
	char hexadecimalNumber[100];
	printf("Enter any decimal number: ");
	scanf("%ld",&decimalNumber);
	quotient = decimalNumber;
	while(quotient!=0) {
		temp = quotient % 16;
		//To convert integer into character
		if( temp < 10)
		           temp =temp + 48; else
		         temp = temp + 55;
		hexadecimalNumber[i++]= temp;
		quotient = quotient / 16;
	}
	printf("Equivalent hexadecimal value of decimal number %ld:",decimalNumber);
	for (j = i -1 ;j> 0;j--){
	      printf("%c",hexadecimalNumber[j]);
	}
	printf("\n");


    // stripping filename to just before the '.'
	char named_file[50] = "file_name.asm" ;
    char* stripped_filename = strtok(named_file, ".") ;
    printf("stripped_filename: %s\n", stripped_filename);



	return 0;
}