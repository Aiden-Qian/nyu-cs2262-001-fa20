#editor: Cheng Qian
#date: 10/08/2021

from flask import Flask,request,Response,jsonify
from socket import *
import json
app = Flask(__name__)

def Fibo(n):
    if n == 2:
        return 1
    elif n == 1:
        return 0
    else:
        return Fibo(n - 1) + Fibo(n - 2)

@app.route('/')
def hello_world():
    return 'Hello world!'


@app.route('/fibonacci', methods=['GET'])
def fibonacci():
    x = request.args.get('number')
    num=int(x)
    res = Fibo(num)
    return Response("the fibo for "+str(num)+" is: "+str(res), status = 200)




@app.route('/register', methods=['PUT'])
def Registration():
    in_data = request.get_json() 
    hostname= in_data.get('hostname')
    ip=in_data.get('ip')
    as_ip=in_data.get('as_ip')
    as_port=in_data.get('as_port')
    server_name= as_ip
    server_port =int(as_port)
    fs_socket = socket(AF_INET, SOCK_DGRAM) 
    dns_request = {'TYPE': 'A','NAME': hostname,'VALUE': ip,'TTL': 10}
    message = json.dumps(dns_request)
    fs_socket.sendto(message.encode(), (server_name, server_port))
    message, _ = fs_socket.recvfrom(2048)

    fs_socket.close()

    return  jsonify(success=True, code=201)
   

app.run(host='0.0.0.0',
        port=9090,
        debug=True)