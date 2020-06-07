#! /usr/bin/env Rscript

print("I is doing it!") ;

#all three commands below print out integers- print(int one), print({addition}), and just {addition}
one = 1 + 2 + 3 ;
print(one) ;

print(1 + 2 * 3) ;

# c = combine
c(0,1,1,2,3,5,8)

# operations performed on 2 vectors--matches elements of 2 vectors pairwise and returns a vector

c(1,2,3,4) + c(10,20,30,40)
c(1,2,3,4) * c(10,20,30,40)
c(1,2,3,4) - c(1,1,1,1)

# vectors of different sizes
c(1,2,3,4) + 1
1 / c(1,2,3,4,5)
c(1,2,3,4) + c(10,100)
# produces and error b/c longer obj length isn't a multiple of shorter vector length
#c(1,2,3,4,5) + c(10,100)

# char array/string
"Hola, mundo!"

# character vector
c("Hola, mundo!", "Hola interprete de R")

# I am an example of a code comment. You can't see meeeeeee!!!!
1 + 2 + #or me tbh
+ 3

