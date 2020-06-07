#include <stdio.h>

int main(){

  int n = 0;
  while(n < 10){
    n++;

    if(n%2 == 1){
      continue;
      //go back to the start of the while loop
    }

    printf("The number %d is even.\n", n);
  }

  int i = 0;

  while(i < 100){
    i++;
    if(i<=5){
      continue;
    } else if(i>10){
      break;
    } else {
      printf("The current number is %d\n",i);
    }
  }

  return 0;
}
