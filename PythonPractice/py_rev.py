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
same_as_pairs = as_dict.items()
print same_as_pairs


