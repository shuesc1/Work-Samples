#include <stdio.h>

int main(){

  int numbers[10];

  /* populate array */
  numbers[0] = 10;
  numbers[1] = 20;
  numbers[2] = 30;
  numbers[3] = 40;
  numbers[4] = 50;
  numbers[5] = 60;
  numbers[6] = 70;

  /* print the 5th number from the array, at index 4 */
  printf("The number at the 4th index is %d\n", numbers[4]);

  char vowels[] = {'a', 'e', 'i', 'o', 'u'};
  printf("The third vowel is %c\n", vowels[2]);

  char vowel_matrix[][5] = {
    {'A', 'E', 'I', 'O', 'U'},
    {'a', 'e', 'i', 'o', 'u'}
  };

  int i, j;

  for(i = 0; i < 2; i++){
    for(j = 0; j < 5; j++){
      printf("Address of vowels[%d][%d]: %s\n", i, j, &vowel_matrix[i][j]);
    }
    printf("\n");
  }

  return 0;
}
