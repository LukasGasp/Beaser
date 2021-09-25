import sacn
from tkinter import *
from tkinter.ttk import *

from Bar import Bar

luniverse = 1           # DMX-Universe
dmx = 0, 0, 0
pos = 1
global coj # Current Object
global mode
mode = 0

# Ranges of Mode:

offrange = range(0, 10)
ybarrange = range(10, 20)

# =============================== sACN ===============================

receiver = sacn.sACNreceiver("192.168.178.3")
receiver.start()

@receiver.listen_on('universe', universe=luniverse)
def callback(packet):  # packet type: sacn.DataPacket
    global dmx
    dmx = packet.dmxData
    getmode(packet.dmxData[0])

# ============================== Managing ==============================

def getmode(packet):
    global mode
    if packet in offrange: # Nichts
        mode = 0
        # TODO
    if packet in ybarrange: # yScanner
        ybar()
        mode = 1

def ybar():
    global mode
    global coj
    global main
    global dmx
    print(dmx[2])
    if mode == 1:
        pos = int((dmx[2]/255)*main.winfo_screenheight())
        coj.move(pos)
    else:
        pos = int((dmx[2]/255)*main.winfo_screenheight())
        print(dmx[2])
        coj = Bar(canvas, pos, main.winfo_screenwidth(), False)

# =========================== Main functions ===========================

def main():
    global coj
    global canvas
    print("Listening on Universe " + str(luniverse))
    createwindow()
    canvas = Canvas(main, bg="black", highlightthickness=0)
    canvas.pack(side="top", fill="both", expand=True)
    coj = Bar(canvas, pos, main.winfo_screenwidth(), False)
    canvas.delete("all")
    # Infinite loop breaks only by interrupt
    main.mainloop()    

def createwindow():
    # object of class Tk, responsible for creating
    # a tkinter toplevel window
    global main
    main = Tk()
    main.attributes('-fullscreen',True)
    main.configure(bg='black')
    

if __name__ == "__main__":
    main()
