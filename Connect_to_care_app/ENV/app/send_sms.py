# /usr/bin/env python
# Download the twilio-python library from http://twilio.com/docs/libraries
import os
from twilio.rest import Client

# Find these values at https://twilio.com/user/account
account_sid = "ACe4e43683af664305fcc2094c6f181eea"
auth_token = "cfc35a637ea16d3ed060c412cd34a7c6"
client = Client(account_sid, auth_token)

message = client.api.account.messages.create(to="+17345484922",
                                             from_="+17344362074",
                                             body="OFF WITH YOUR HEAAAAD!")
