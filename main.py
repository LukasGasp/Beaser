import sacn

luniverse = 1           # DMX-Universe

# =============================== sACN ===============================

receiver = sacn.sACNreceiver()
receiver.start()

@receiver.listen_on('universe', universe=luniverse)
def callback(packet):  # packet type: sacn.DataPacket
    print(packet.dmxData)

# =========================== Main function ===========================

def main():
    #TODO Create Window, etc.
    print("Listening on " + str(ip) + " for Universe " + str(luniverse))

if __name__ == "__main__":
    main()
