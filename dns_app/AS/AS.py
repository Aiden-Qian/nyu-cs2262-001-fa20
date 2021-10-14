#editor: Cheng Qian
#date: 10/08/2021

from socket import *
import json

sock=socket(AF_INET,SOCK_DGRAM)
sock.bind(('',53533))
dict = {}

while True:
    in_data, addr = sock.recvfrom(2048)
    message = json.loads(in_data.decode())
    ip = 'VALUE' in message
    if not ip:
        hostname = message['NAME']
        type = message['TYPE']
        cont = dict[type + ' ' + hostname]
        fs_ip = cont['VALUE']
        response_message=str(fs_ip).encode()
    else:
        hostname = message['NAME']
        ip = message['VALUE']
        type = message['TYPE']
        ttl = message['TTL']
        cont = {'TYPE': type, 'NAME': hostname, 'VALUE': ip, 'TTL': ttl}
        key = type + ' ' + hostname
        dict[key] = cont
        response_message=json.dumps('').encode()
    sock.sendto(response_message, addr) 
