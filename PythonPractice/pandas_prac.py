import pandas as pd
# making data frames from a dictionary
# that maps column names to their values
df = pd.DataFrame({
  "name": ["Bob", "Alex", "Janice"],
  "age": [60, 25, 33]
  })
# reading a DataFrame from a file
# other_df = pd.read_csv("myfile.csv")

#making new columns from old ones is hella easy
df["age_plus_one"] = df["age"] + 1
df["age_times_two"] = 2 * df["age"]
df["age_squared"] = df["age"] * df["age"]
df["over_30"] = (df["age"] > 30 ) # this col is bools
#columns have various built in aggregate functions

total_age = df["age"].sum()
median_age = df["age"].quantile(0.5)
# You can select several rows of the DateFrame and make a new DataFrame out of them
df_below50 = df[df["age"] < 50]
# apply a custom function to a column
df["age_squared"] = df["age"].apply(lambda x: x*x)

# changing index values of rows
df = pd.DataFrame({
  "name": ["Bob", "Alex", "Jane"],
  "age": [60, 25, 33]
  })
print df.index # prints 0-2, the line numbers

# create a DataFrame containing the same  data, but where the name is the index
df_w_name_as_ind = df.set_index("name")
print df_w_name_as_ind.index # prints their names

# get row for Bob
bobs_row = df_w_name_as_ind.ix["Bob"]
print bobs_row["age"] # prints 60



