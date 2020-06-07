#! /usr/bin/env Rscript

print("Some data structures practice") ;

# arrays - just multidimensional vectors
# dim=c(rows,columns)
a <- array(c(1,2,3,4,5,6,7,8,9,10,11,12), dim=c(3,4))
a

# to reference a cell
a[2,2]
#again, 1 indexed--2nd row, 2nd column

# defining a vector with the same values as above
v <- c(1,2,3,4,5,6,7,8,9,10,11,12)
v

# a matrix is just a 2D array
print("a 2D array:") ;
m <- matrix(data=c(1,2,3,4,5,6,7,8,9,10,11,12),nrow=3,ncol=4)
m

print("a 3D array:") ;
w <- array(c(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18),dim=c(3,3,2))
w

w[1,1,1]
# stopping point p. 25 of R in a Nutshell (O'Reilly)

