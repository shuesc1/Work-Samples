from matplotlib import pyplot as plt
import sklearn
from sklearn.metrics import roc_curve, auc
from sklearn.cross_validation import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.naive_bayes import GaussianNB
import pandas as pd
import sklearn.datasets

def get_iris_df():
  ds = sklearn.datasets.load_iris()
  df = pd.DataFrame(ds['data'],
    columns = ds['feature_names'])
  code_species_map = dict(zip(range(3), ds['target_names']))
  df['species'] = [code_species_map[c]
    for c in ds['target']]
  return df
df = get_iris_df()

# name -> (line format, classifier)
CLASS_MAP = {
    'LogisticRegression':
      ('-', LogisticRegression()),
    'Naive Bayes': ('--', GaussianNB()),
    'Decision Tree':
      ('.-', DecisionTreeClassifier(max_depth=5)),
    'Random Forest':
      (':', RandomForestClassifier(
        max_depth=5, n_estimators=10,
        max_features=1)),
    }

# Divide cols by independent/dependent, rows by test/ train
X, Y = df[df.columns[:3]], (df['species']=='virginica')
X_train, X_test, Y_train, Y_test = \
  train_test_split(X, Y, test_size=.8)

for name, (line_fmt, model) in CLASS_MAP.items():
  model.fit(X_train, Y_train)
  # array w/ one column per label
  preds = model.predict_proba(X_test)
  pred = pd.Series(preds[:,1])
  fpr, tpr, thresholds = roc_curve(Y_test, pred)
  auc_score = auc(fpr, tpr)
  label ='%s: auc=%f' % (name, auc_score)
  plt.plot(fpr, tpr, line_fmt, linewidth=5, label=label)

plt.legend(loc="lower right")
plt.title('Comparing Classifiers')
plt.plot([0,1], [0,1], 'k--') #x=y line. Visual aid
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.show()


# Decision tree classifier
#  from sklearn.tree import DecisionTreeClassifier
#  clf = DecisionTreeClassifier(max_depth=5)
#  clf.fit(train[indep_cols], train.breed)
#  predictions = clf.predict(test[indep_cols])

# Random Forest classifier
#  from sklearn.tree import RandomForestClassifier
#  clf = RandomForestClassifier(max_depth=5, n_estimators=10, max_features=1))
#  clf.fit(train[indep_cols], train.breed)
#  predictions = clf.predict(test[indep_cols])


