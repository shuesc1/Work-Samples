try:
    import statsmodels.api as sm
except ImportError:
    import scikits.statsmodels.api as sm
import matplotlib.pyplot as plt

dta = sm.datasets.co2.load_pandas().data
dta.plot()
plt.title("CO2 Levels")
plt.ylabel("Parts per million")
plt.show()


