import sacn
from Bar import Bar

luniverse = 1           # DMX-Universe

# =============================== sACN ===============================

receiver = sacn.sACNreceiver()
receiver.start()

@receiver.listen_on('universe', universe=luniverse)
def callback(packet):  # packet type: sacn.DataPacket
    print(packet.dmxData)

def bartest():
    horizontal = Bar(255, False)
    horizontal.move(300)

# =========================== Main function ===========================

def main():
    #TODO Create Window, etc.
    print("Listening on Universe " + str(luniverse))
    bartest()



if __name__ == "__main__":
    main()
