import pandas as pd
df = pd.DataFrame({
  "name": ["Bob", "Alex", "Jane"],
  "age": [60, 25, 33]
  })

df_w_name_as_ind = df.set_index("name")
print df_w_name_as_ind.index


s = pd.Series([1,2,3]) # make Series from list

# display the values in s
# note index is to the far left
print s
s+2 # adds a number to each element of s
print s.index # you can access the index directly 
# adding two series will add corresponding element to each other
s + pd.Series([4,4,5])

bobs_row = df_w_name_as_ind.ix["Bob"]
type(bobs_row)
print bobs_row
