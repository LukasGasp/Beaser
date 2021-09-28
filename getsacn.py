import sacn

luniverse = 1           # DMX-Universe

receiver = sacn.sACNreceiver("192.168.178.3")

@receiver.listen_on('universe', universe=1)
def callback(packet):  # packet type: sacn.DataPacket)
    print("start")
    for i in packet.dmxData:
        print(i)

receiver.start()