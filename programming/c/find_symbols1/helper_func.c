#include <stdio.h>
#include <string.h>

char* function_name;
char* parameter_names[10];
char* variable_names[10];


// a helper function to assess an identifier if it's legal or not
int eval_identifier(char* identifier){
        //64-90 ==> A-Z ASCII ; 97-122 ==> a-z ASCII
        int result = 1;
        if(identifier[0] > 63 && identifier[0] < 91 | identifier[0] > 96 && identifier[0] < 123){
                result = 0 ;
        } else {
                result = 1;
        }
        return result;
}

int main(){

	printf("input: 'a', output: %d\n", eval_identifier("a"));
	printf("input: '_', output: %d\n", eval_identifier("_"));
	printf("input: 'Q', output: %d\n", eval_identifier("Q"));
	printf("input: '8', output: %d\n", eval_identifier("8"));

    printf("line: );
           
    char line1[] = "int x ;";
    parse_line(line1);
    char line2[] = "int j , k ;" ;
    parse_line(line1);
    char line3[] = "int y = 2 ;" ;
    parse_line(line1);
    char line4[] = "int a = x + 5 ;";
    parse_line(line1);
    char line5[] = " int b = a + y + 8 ;" ;
    parse_line(line1);
    char line6[] = "int c = a + x , d , e = b ;" ;
    parse_line(line1);
    char line7[] = "int x";
    parse_line(line1);
    char line8[] = "int j k ;"
    parse_line(line1);
    char line9[] = "int y = ;";
    parse_line(line1);
    char line10[] = "int a = x 5 ;" ;
    parse_line(line1);
    char line11[] = "int void ;" ;
    parse_line(line1);
    char line12[] = "= 9 ;" ;
    parse_line(line1);
    char line13[] = "x = 8" ;
    parse_line(line1);
    char line14[] = "a = b + ;" ;
    parse_line(line1);
    char line15[] = "y = + d ;" ;
    parse_line(line1);
    char line16[] = "m = a + b + ;" ;
    parse_line(line1);
           
	return 1;
}
