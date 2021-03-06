from flask import Flask, request, render_template
from flask_admin import Admin
from flask_admin.contrib.sqla import ModelView
from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declarative_base
#from models import User
#from models import Post

app = Flask(__name__)      
#from app import views
#imports the view module
 
admin = Admin(app, name='microblog', template_mode='bootstrap3')
#admin.add_view(ModelView(User, db.session))
#admin.add_view(ModelView(Post, db.session))

#add admin views here

#app.run()

@app.route('/')
def home():
#def home():
#  return render_template('home.html')
#  return render_template('homes.html')
  return render_template('index.html')

@app.route('/about')
def about():
  return render_template('about.html')
 
if __name__ == '__main__':
  app.run(debug=True)
