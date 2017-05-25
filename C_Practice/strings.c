#include <stdio.h>
#include <string.h>

int main(){

  /*
  char * name = "Don Fulano";
  pointer to a char array to define simple string -- can only be used for reading
  */

  //This is a string (char array) that can be manipulated--size auto calculated
  char name[] = "John Smith";
  //is the same as: char name[11] = "Don Fulano"; -- all chars plus null termination

  int age = 30;

  for(int i = 0; i < 11; i++){
    printf("%c", name[i]);
  }
  printf(" is %d years old\n", age);

  char * nombre = "Mickali";
  printf("El numero de letras en el nombre 'Mickali' es %lu\n",strlen(nombre));


  /*STRING COMPARISON */
  printf("Input: %s\n", nombre);

  if(strncmp(nombre, "mickali", 7) == 0){
    printf("Hello, Mickali!\n");
  } else {
    printf("You are not Mickali. Access denied\n");
  }

  /*STRING CONCATENATION */
  char beg[20] = "Hola";
  char mdl[10] = ",";
  char end[20] = "mundo!!\n";

  strncat(beg,mdl,1);
  strncat(beg,end,8);
  printf("Concatenated phrase: %s\n", beg);

  /*USING SPRINTF() FUNCTION*/
  char str[20];
  int hours = 16;
  sprintf(str,"%d:0%d",hours,5);
  puts(str);

  return 0;
}
