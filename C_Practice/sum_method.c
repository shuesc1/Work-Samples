#include <stdio.h>

int sumFunc(int x);

int main()
{
  int x;
 
  printf( "Please input a number and I will return the sum of all positive integers up to this number: " );
  scanf( "%d", &x );
  printf( "The sum of all positive numbers up to your num %d is: %d\n", x, sumFunc(x) ); 

  return 0 ;
}

int sumFunc(int x)
{
  int total = 0;
  do{
    total = total + x ;
    x = x - 1 ;
  }  while (x > 0) ;
  return total ;
}
