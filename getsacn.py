import socket
import time
import sacn

HOST = "localhost"
PORT = 1994
duniverse = 1           # DMX-Universe

receiver = sacn.sACNreceiver("192.168.178.3")
 
def sendmessage(message):
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((HOST, PORT))
    sock.sendall(message.encode('utf-8'))

@receiver.listen_on('universe', universe=1)
def callback(packet):  # packet type: sacn.DataPacket)
    data = "start"
    for i in range(0, 10):
        data = data + ":" + str(packet.dmxData[i])
    sendmessage(data)

print("Start receiver")
receiver.start()

#data = sock.recv(1024)
#print ("1)"+ data)