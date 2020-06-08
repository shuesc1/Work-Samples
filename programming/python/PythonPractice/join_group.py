import pandas as pd

df_w_age = pd.DataFrame({
  "name": ["Tom", "Tyrell", "Claire"],
  "age": [60, 25, 33]
  })

df_w_height = pd.DataFrame({
  "name": ["Tom", "Tyrell", "Claire"],
  "height": [6.2, 4.0, 5.5]
  })

joined = df_w_age.set_index("name").join(df_w_height.set_index("name"))
print joined
print joined.reset_index()
df = pd.DataFrame({
  "name": ["Tom", "Tyrell", "Claire"],
  "age": [60, 25, 33],
    "height": [6.2, 4.0, 5.5],
    "gender": ["M", "M", "F"]
  })

print df.groupby("gender").mean()

medians = df.groupby("gender").quantile(0.5)
# use a custom aggregation function
def agg(ddf):
  return pd.Series({
    "name": max(ddf["name"]),
    "oldest": max(ddf["age"]),
    "mean_height": ddf["height"].mean()
    })
print df.groupby("gender").apply(agg)


