#!flask/bin/python
#from app import app
#app.run(debug=True)
from flask import Flask, request, render_template
from flask_admin import Admin
from flask_admin.contrib.sqla import ModelView
from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declarative_base

app = Flask(__name__)      
admin = Admin(app, name='microblog', template_mode='bootstrap3')
#admin.add_view(ModelView(User, db.session))
#admin.add_view(ModelView(Post, db.session))
#add admin views here
#app.run()

@app.route('/')
def home():
  return render_template('/templates/index.html')

@app.route('/about')
def about():
  return render_template('about.html')
 
if __name__ == '__main__':
  app.run(debug=True)
