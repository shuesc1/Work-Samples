
def main():
  s = 'howdy'
  print s[0] ##h
  print s[4] ##y
  print len(s) ##5
  print s + ' partner' ##howdy partner

  pi = 3.14159
  ##text = 'The value of pi is ' + pi ##won't work ==> need double to string
  text = 'The value of pi is ' + str(pi) ##si se puede
  print(text)

  ## ++ operator not available

  raw = r'this\t\n and that'
  print raw   ##this\t\n and that
  
  multi_line = """It was the best of times.
  It was the worst of times."""

  print(multi_line)

  t = ' s '
  t.upper()
  print(t)
  t.lower()
  t.strip() ##s.trim in Java
  print(t)
  print(t.isalpha()) ##T
  print(t.isdigit()) ##F
  print(t.isspace()) ##F
  print(t.startswith('s')) ##T
  print(t.endswith('x')) ##F
  t.find('art') ##searches for given other string and returns 1st index or -1 if not found
  t.replace('s','5')
  print(t)
  p = 'jack,jill,amy'
  print(p.split(','))

main()
