#include <stdio.h>

//function declaration
void print_big(int arg);

int main(){
  //function called
  print_big(1);
  print_big(20);
  print_big(5);
  return 0;
}

//function fully implemented
void print_big(int arg){
  if(arg <= 10){
    printf("%d is small!\n", arg);
  } else {
    printf("%d is big!\n", arg);
  }
}
