import urllib
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

# Get raw CSV data from the web
#URL = ("ichart.finance.yahoo.com/" + "table.csv?s=GOOG&c=2000")
#dat = urllib.urlopen(URL).read()
#dat = ['GOOG.csv'].read()
#open('foo.csv', 'w').write(dat)

# Make DataFrame, w timestamp as the index
df = pd.read_csv('GOOG.csv')
df.index = df['Date'].astype('datetime64')
df['LogClose'] = np.log(df['Close'])
df['Close'].plot()
plt.title("Normal Axis")
plt.show()
df['Close'].plot(logy=True)
plt.title("Logarithmic Axis")
plt.show()


