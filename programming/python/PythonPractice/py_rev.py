# ints
my_integer = 2
other_int = 2 +3
print other_int
my_float = 2.0 * 4.5
print my_float

# booleans
true_bool = True
print true_bool
false_bool = False
print false_bool
this_is_true = (0 < 100)
print this_is_true
this_is_false = (100 < 50)
print this_is_false

# strings
a_string = "hullo"
same_string = 'hullo'
empty_strang = ""
print "empty strang" + empty_strang
w_single_quote = "hobbit'ses's"
print w_single_quote

# block quotes
multi_line_string = """ line 1
line2"""
print multi_line_string

# substrings
print "ABCD" [0]
print "ABCD" [0:2]
print "ABCD" [1:3]
# zero indexing
# negative (!) indexing from end
print "ABCD" [1:]
print "ABCD" [:-1]

def sqr(x):
# print line 
#print "This function just squares it's input-- " + str(x)  + " squared is " + str(x * x)
# best practice descriptive line:
	"This function just squares its input "
	return (x * x)

print sqr(5)

# data containers: lists, tuples, & dicts
my_list = ["a ", "b ", "c "]
my_set = set(my_list)
my_tuple = tuple(my_list)

list = ["a ", "b ", "c ", "d "] # basically an array
print list[1] # prints 'b'
list[0] = 'q' # changes element
print list
list.append("e ") #adds new element to end of list
# can mix data types --- MADNESS!!!!
cray_cray_list = ["A", 5.4, "b", [1,2,3]]
print cray_cray_list

#list transformation
orig_list = [1,2,3,4,5,6,7,8]
print "Original list: " + str(orig_list)
squares = [x*x for x in orig_list]
sq_of_evens = [x*x for x in orig_list if x%2==0]
print "Squares of list: " + str(squares)
print "Square of evens in list: " + str(sq_of_evens)

# subsets
otra_lista = ['a', 'b', 'c']
primeros_dos_elementos = otra_lista[0:3]
print primeros_dos_elementos
all_but_last_element = otra_lista[:-1]
print all_but_last_element

#string manipulations -- splits
print "ABC DEF".split() #split on white space
print "ABC /tDEF".split() #same as above
print "ABC /tDEF".split(' ') #splitting explicitly on white space here
print "ABCABD".split("AB")

# inverse of split() -- join()!
print ",".join(['A', 'b', 'C'])

#slicing
start, end, count_by = 1, 7, 2
print "ABCDEFG" [start: end: count_by]

#tuples = array in that immutable, no changing elements, no adding/removing
my_tuple = (1, 2, "hello world")
print my_tuple[0] # prints 1
# my_tuple[1] = 5 # this would throw an error

#multiple assignments
zeroth_field, first_field, second_field = my_tuple
print zeroth_field
print first_field
print second_field

#dicts/dictionaries-- structs that take in a 'key' and returns a 'value'
# use {} with a colon separating the key and its value
my_dict = {"January": 1, "February":2}
print my_dict["January"] #prints value 1 for key 'January'
my_dict["March"] = 3 # add new element
my_dict["January"] = "Start of the new year" # overwrite old value

print my_dict

#create a dict by passing a list of tuples
pairs = [("one", 1), ("two", 2)]
as_dict = dict(pairs)
# can create a list of tuples by calling the items() method on a dictionary
same_as_pairs = as_dict.items()
print same_as_pairs

# sets -- like a dict with only 'keys' and NO 'values'
# stores a collection of uniqeu objects that are of atomic types
# mutable -- can add elements to set
s = set()
print 5 in s
s.add(5)
print 5 in s
s.add(5) # does nothing-- 5 already present
print s

#defining functions
def func(x):
  y = x+1
  x_sqrd = y*y
  return x_sqrd

five_plus_one_sqrd = func(5)
print "5 plus 1 squared is " + str(five_plus_one_sqrd)

# function with optional arguments
def raises(x, n=2):
  return pow(x,n)

two_sqrd = raises(2)
print two_sqrd
two_cubed = raises(2, n=3)
print two_cubed

# func definition with lambda expression
sqr = lambda x : x*x
five_sqrd = sqr(5)

#another example
def apply_to_evens(a_list, a_func):
  return [a_func(x) for x in a_list if x%2==0]
my_list = [1,2,3,4,5]
sqrs_of_evens = apply_to_evens(my_list, lambda x:x*x)
print sqrs_of_evens

# 'for' loops
for x in my_list:
  print "the number is ", x

for key, value in my_dict.items():
  print "the value for ", key, " is ", value

# conditionals
i = 0
if i < 3:
 print "i is less than three"
elif i < 5: print "i is between 3 and 5"
else: print "i is greater than 5"


while i < 5:
  print "i is still less than 5"
  i = i + 1

# exception handling
try:
  lines = input_text.split("\n")
  print "tenth line was: ", lines[9]
except:
  print "There were < 10 lines"

#importing existing libraries
# from my_lib import f1, f2 # f1 & f2 in namespace
# import other_lib as ol # ol.f1 is the f1 func
# from other_lib import * #f1 is in namespace

# creating your own classes
class Dog:
  def __init__(self, name): #a special function that gets called when an instance of the class is created
    self.name = name
  def respond_to_command(self, command):
    if command == self.name: self.speak()
  def speak(self):
    print "bark bark!!"

fido = Dog("fido")
fido.respond_to_command("spot") #does nothing
fido.respond_to_command("fido") #prints bark bark

# hashable types -- low level data types, instances of them are immutable; ints, floats, strings, tuples, etc.
# unhashable types -- lists, dicts, libraries; generally for larger, more complex objs; have internal struct that can be modified

#ex:
a = 5 # a is a hashable int
b = a # b points to a COPY of a
a = a + 1
print b # b hasn't bee incremented
A = [] # A is an UNhashable list
B = A # B points to the same list as A NOT a copy
A.append(5)
print B # should be [5]

# to make a true copy of A
# B = [x for x in A]
Q = [{}, {}] # list of dicts
R = [x for x in Q]
Q[0]["name"] = "bob"
print R[0]["name"] # should be 'bob'



