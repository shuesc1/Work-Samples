import re

# prob 2.2

text = "I am a text with the words foo and bar"
text2 = "I am a text with only fooish and barish words"
bool = False 

words = ('foo', 'bar')
if set(words).issubset(text.split()):
	bool = True
	#return True
	print("match!")
else:
	#return False
	print("no match!!")

return bool
#print(bool)