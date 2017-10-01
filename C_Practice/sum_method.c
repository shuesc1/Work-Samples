#include <stdio.h>

int sumFunc(int A);

int main()
{
  int A;
 
  printf( "Please input a number and I will return the sum of all positive integers up to this number: " );
  scanf( "%d", &A );
  printf( "The sum of all positive numbers up to your num %d is: %d\n", A, sumFunc(A) ); 

  return 0 ;
}

int sumFunc(int A)
{
  int total = 0;
  do{
    total += A ;
    A -= 1 ;
  }  while (A > 0) ;
  return total ;
}



