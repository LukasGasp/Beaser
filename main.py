import sacn
from tkinter import *
from tkinter.ttk import *

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

# =========================== Main functions ===========================

def main():
    #TODO Create Window, etc.
    print("Listening on Universe " + str(luniverse))
    createwindow()
    # Infinite loop breaks only by interrupt
    main.mainloop()    

def createwindow():
    # object of class Tk, responsible for creating
    # a tkinter toplevel window
    main = Tk()
    main.attributes('-fullscreen',True)
    main.configure(bg='black')
    

if __name__ == "__main__":
    main()
