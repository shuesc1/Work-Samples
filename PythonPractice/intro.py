#! /usr/bin/env python

#import used modules
import sys

#code declared in main function
def main():
    print 'Howdy, there', sys.argv[1],'and', sys.argv[2]

#Standard boilderplate to call the main() function to begin
# the program
if __name__ == '__main__':
    main()

a = 2
print(a + 5)
a = 'hi'
print(len(a))
print(a + str(len(a)))
