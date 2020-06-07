#! /usr/bin/env Rscript

print("now for some variable practice") ;

# assignment operator is <-
#x <- y == "x gets y"
x <- 1
y <- 2
z <- c(x,y)
z

# refering to vector elements by location
b <- c(1,2,3,4,5,6,7,8,9,10,11,12)
b
# to get the 7th item in the vector
b[7]
# to get 1st - 6th item
b[1:6]
#1 indexed, not 0 indexed
# get members that are congruent to 0 (mod 3)
# members that are multiples of 3
b[b %% 3 == 0]
#get items 1 - 6
b[1:6]
# get 1, 6, 11
b[c(1,6,11)]
# get items out of order -- ok!
b[c(8,4,9)]
print("Are elements divisible by 3?") ;
b %% 3 == 0
print("The elements divisible by 3 are: ") ;
b[b %% 3 == 0]

# assigning variables can also be done this way:
one <- 1
two <- 2
print("Assigning the value of '2' to variable 'one'") ;
one = two
print("Printing variable 'one':") ;
one 

#this can also be done this way
3 -> three
three

f <- function(x,y) {c(x+1, y+1)}
f(1,2)
print("This is function f:") ;
f



