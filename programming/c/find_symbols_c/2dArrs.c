#include <stdio.h>
#include <string.h>

char* function_name;
char* parameter_names[10];
char* variable_names[10];

int main() {

	char str[] = "- This, a sample string.";
	char str1[] = "int fun0 ( ) {";
	char str2[] = "int fun1 ( int a ) {" ;
	char str3[] = "int fun2 ( int dog , int cat ) {" ;
	char str4[] = "int fun3 ( int larry , int moe , int curly ) {" ;
	char str5[] = "int fun3 ( int param1 , int param2 , int param3 , int param4 ) {" ;

	// ========== example 1 =========
	char* arrays[] = {"string one", "string two", "string three", "\0"} ;
	//printf("%d", strlen(arrays)) ;
	for (int h = 0; h < 3; h++) {
		printf("arrays[%d]: %s\n", h, arrays[h]);
	}

	// ========== example 2 =========
	// char strs[NUMBER_OF_STRINGS][STRING_LENGTH+1];
	char arr_char_arrs[20][30] = {""};
	//strcpy(arr_char_arrs[0], aString); // where aString is either an array or pointer to char
	//strcpy(arr_char_arrs[1], "foo");

	printf ("Splitting string \"%s\" into tokens:\n", str4);
	char * token;
	token = strtok (str4, " ");
	//char* toks[10];
	int x = 0 ;
	//arr_char_arrs[x] = token;
	printf("%s\n", token);
	strcpy(arr_char_arrs[x], token);
	x++;
	while (token != NULL) {
		// printf ("%s\n", token);
		token = strtok (NULL, " ");
		// printf ("%s\n", token);
		if (token == NULL) {
			break;
		}
		// arr_char_arrs[x] = token ;
		strcpy(arr_char_arrs[x], token);
		// printf("toks[%d]: %s\n", x, toks[x]);
		x++;
	}
	//a[x] = "\0" ;
	strcpy(arr_char_arrs[x], "\0");


	// ------------ TOKEN ARRAY -------------
	// int length = strlen(arr_char_arrs);
	int length = x;
	printf("Length: %d\n", length);
	printf("======= Tokens ====== \n");
	for (int i = 0; i < length; i++) {
		printf("arr_char_arrs[%d]: %s\n", i, arr_char_arrs[i]);
	}

	// ----------- getting parameters ---------
	if (length > 5) {
		//toks[2] will always be '\(' && toks[length - 2] will always be '\)'
		// toks[4] will be the 1st param is there is one, and toks[length - 3] will be the last
		// params (if multiple) will be 3 indices apart
		int a = 4;
		int b = 0;
		int limit = length - 2;
		// for(int a = 0; a < (length-2); a=a+3){
		while (a < limit) {
			//parameter_names[b] = toks[a];
			// printf("arr_char_arrs[%d]: %s\n", a, arr_char_arrs[a]);
			parameter_names[b] = arr_char_arrs[a] ;
			// strcpy(parameter_names[b], arr_char_arrs[a]);
			printf("parameter_names[%d]: %s\n", b, parameter_names[b]);
			a += 3;
			b++;
		}
	}

	printf("\n======== OUTPUT: ==========\n") ;
	// strcpy(function_name, arr_char_arrs[1]);
	function_name = arr_char_arrs[1] ;
	printf("\nfunction name: %s\n", function_name);

	for (int v = 0; v < 10; v++) { //length of 10
		printf("parameter_names[%d]: %s\n", v, parameter_names[v]);
	}

}