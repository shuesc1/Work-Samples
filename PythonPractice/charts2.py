from PIL import Image
import pandas as pd
import sklearn.datasets as ds
import matplotlib.pyplot as plt
#import pandas.plotting.scatter_matrix

# make pandas dataframe
bs = ds.load_boston()
df = pd.DataFrame(bs.data, columns=bs.feature_names)
df['MEDV'] = bs.target

# Normal scatterplot
df.plot(x='CRIM',y='MEDV',kind='scatter')
plt.title('Crime rate on normal axis')
plt.savefig('scatter_w_log_axes.png')
df.plot(x='CRIM',y='MEDV',kind='scatter',logx=True)
plt.title('Crime rate on logarithmic axis')
plt.close()

from pandas.tools.plotting import scatter_matrix
scatter_matrix(df)
plt.savefig('scatter_matrix.png')
plt.close()

img = Image.open('scatter_w_log_axes.png')
img.show()

img2 = Image.open('scatter_matrix.png')
img2.show()
