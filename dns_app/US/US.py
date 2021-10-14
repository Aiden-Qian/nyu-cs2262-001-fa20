#editor: Cheng Qian
#date: 10/08/2021

from flask import Flask,request
from socket import *
import requests
import logging
import json
app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello world!'

@app.route('/fibonacci', methods=['GET'])
def fibo():
    hostname=request.args.get('hostname')
    fs_port=request.args.get('fs_port')
    as_ip=request.args.get('as_ip')
    as_port=request.args.get('as_port')
    number=request.args.get('number')

    if  hostname and fs_port and number and as_ip and as_port:
        
        client = socket(AF_INET, SOCK_DGRAM)
        query_json = {'TYPE': 'A', 'NAME': hostname}
        client.sendto(json.dumps(query_json).encode(), (as_ip, 53533))
        ip_address, server_address = client.recvfrom(2048)
        message= json.loads(ip_address.decode())
        value=message['VALUE']
        client .close()
        result = requests.get("http://{}:{}fibonacci?number={}".format(value,fs_port,number))
        return request(result.json(), 200)
    else:
        return 'Error 404~~~~~~~'
        
    
    


app.run(host='0.0.0.0',
        port=8080,
        debug=True)