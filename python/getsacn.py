import socket
import json
import sacn

HOST = "localhost"
PORT = 1994
duniverse = 98           # DMX-Universe

receiver = sacn.sACNreceiver("192.168.178.3")
 
def sendmessage(message):
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((HOST, PORT))
    sock.sendall(message.encode('utf-8'))

@receiver.listen_on('universe', universe=duniverse)
def callback(packet):  # packet type: sacn.DataPacket)
    #print (duniverse)
    data = "start"
    for i in range(0, 200):
        data = data + ":" + str(packet.dmxData[i])
    sendmessage(data)

with open("config.json", "r") as read_file:
    config = json.load(read_file)
HOST = config["python"]["host"]
PORT = int(config["python"]["port"])
duniverse = int(config["python"]["duniverse"])
receiver.start()