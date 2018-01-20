#include <stdio.h>
#include <string.h>

char* function_name;
char* parameter_names[10];
char* variable_names[10];

int main() {

	char* null = "0/";

	char all_str[10];
	char str[] = "- This, a sample string.";
	char str1[] = "int fun0 ( ) {";
	char str2[] = "int fun1 ( int a ) {" ;
	char str3[] = "int fun2 ( int dog , int cat ) {" ;
	char str4[] = "int fun3 ( int larry , int moe , int curly ) {" ;

	all_str[0] = str[0];
	all_str[1] = str1[0];
	all_str[2] = str2[0];
	all_str[3] = str3[0];
	all_str[4] = str4[0];
	all_str[5] = *null;

	char * token;
// char * current;
	// printf ("Splitting string \"%s\" into tokens:\n", str1);
	// token = strtok (str1, " ");
	// while (token != NULL) {
	// 	printf ("%s\n", token);
	// 	token = strtok (NULL, " ");
	// }
//=======================================================
	// printf ("Splitting string \"%s\" into tokens:\n", str1);
	// token = strtok (str1, " ");
	printf ("Splitting string \"%s\" into tokens:\n", str2);
	token = strtok (str2, " ");
	// printf ("Splitting string \"%s\" into tokens:\n", str3);
	// token = strtok (str3, " ");
	char* toks[10];
	int x = 0 ;
	toks[x] = token;
	x++;
	while (token != NULL) {
		printf ("%s\n", token);
		token = strtok (NULL, " ");
		if(token == NULL){
			break;
		}
		toks[x] = token ;
		x++;
	}
	toks[x] = "\0" ;

	printf("contents of token array: \n") ;
	//char * current_tok;
	int length = x;
	// int length = strlen(toks);
	printf("length: %d\n", length);
	for(int q = 0; q < length; q++){
		printf("%s\n", toks[q]);
		//printf("%c\n", *toks[q]); //that specific char
		// printf("%s\n", &toks[q]); //ptr to ptr
	}

	function_name = toks[1] ;
	printf("\nfunction name: %s\n", function_name);

//======================================
	// printf ("Splitting string \"%s\" into tokens:\n",str3);
	// token = strtok (str3," ");
	// while (token != NULL){
	//         printf ("%s\n",token);
	//         token = strtok (NULL, " ");
	// }

	// printf ("Splitting string \"%s\" into tokens:\n",str4);
	// token = strtok (str4," ");
	// while (token != NULL){
	//         printf ("%s\n",token);
	//         token = strtok (NULL, " ");
	// }
	return 0;
}
