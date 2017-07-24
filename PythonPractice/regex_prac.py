import re
# This matches '1600 Pennsylvania Ave.'
# It doesn't match '5 Stony Brook St.'
# b/c there is a space in 'Stony Brook'
street_pattern = r"^[0-9]\s[A-Z][a-z]*" + \
  r"(Street|St|Rd|Road|Ave|Avenue|Blvd|Way|Wy)\.?$"
# pattern assumes no space in town name
city_pattern = r"^[A-Z][a-z]*, \s[A-Z]{2},[0-9]{5}$"
address_pattern = street_pattern + r"\n" + city_pattern
# compile string into a regex object
address_re = re.compile(address_pattern)
text = open("addresses.txt", "r").read()
matches = re.findall(address_re, text)
# list of all strings that match
open("addreses_w_space_between.txt", 
  "w").write("\n\n".join(matches))

