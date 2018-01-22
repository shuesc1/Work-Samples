#include <stdio.h>
#include <string.h>

char* function_name;
char* parameter_names[10];
char* variable_names[10];

int main() {

	char* null = "0/";

	char* all_str[10];
	char str[] = "- This, a sample string.";
	char str1[] = "int fun0 ( ) {";
	char str2[] = "int fun1 ( int a ) {" ;
	char str3[] = "int fun2 ( int dog , int cat ) {" ;
	char str4[] = "int fun3 ( int larry , int moe , int curly ) {" ;
	char str5[] = "int fun3 ( int param1 , int param2 , int param3 , int param4 ) {" ;

	all_str[0] = str;
	printf("all_str[0]: %s\n", all_str[0]);
	all_str[1] = str1;
	all_str[2] = str2;
	all_str[3] = str3;
	all_str[4] = str4;
	all_str[5] = null;









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
	printf ("Splitting string \"%s\" into tokens:\n", str5);
	token = strtok (str5, " ");
	// printf ("Splitting string \"%s\" into tokens:\n", str3);
	// token = strtok (str3, " ");
	char* toks[10];
	int x = 0 ;
	toks[x] = token;
	x++;
	while (token != NULL) {
		// printf ("%s\n", token);
		token = strtok (NULL, " ");
		printf ("%s\n", token);
		if(token == NULL){
			break;
		}
		toks[x] = token ;
		printf("toks[%d]: %s\n", x, toks[x]);
		x++;
	}
	toks[x] = "\0" ;

	// printf("%s\n", toks[0]);
	// printf("%x\n", &toks[0]);
	// printf("%c\n", *toks[0]);
	// printf("%s\n", toks[1]);
	// printf("%s\n", toks[2]);
	// printf("%s\n", toks[3]);
	// printf("%s\n", toks[4]);
	// printf("%s\n", toks[5]);
	// printf("%s\n", toks[6]);


	// ------------ TOKEN ARRAY -------------
	printf("contents of token array: \n") ;
	//char * current_tok;
	int length = x;
	// int length = strlen(toks);
	printf("length: %d\n", length);
	for(int q = 0; q < length; q++){
		printf("toks[%d]: %s\n", q, toks[q]);
		//printf("%c\n", *toks[q]); //that specific char
		// printf("%s\n", &toks[q]); //ptr to ptr
	}



	// ------------- getting parameters ---------
	// if(length == 5){
	// 	continue;
	// } else if(length > 5){
	if(length > 5){
		//toks[2] will always be '\(' && toks[length - 2] will always be '\)'
		// toks[4] will be the 1st param is there is one, and toks[length - 3] will be the last
		// params (if multiple) will be 3 indices apart
		int a = 4;
		int b = 0;
		int limit = length-2;
		// for(int a = 0; a < (length-2); a=a+3){
		while(a < limit){
			parameter_names[b] = toks[a];
			a+=3;
			b++;
		}
	}

	printf("======== OUTPUT: ==========") ;
	function_name = toks[1] ;
	printf("\nfunction name: %s\n", function_name);

	for(int v = 0; v < 11; v++){
		printf("parameter_names[%d]: %s\n", v, parameter_names[v]);
	}






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
