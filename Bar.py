from tkinter import Canvas


class Bar():
    "Makes a Bar"

    zoom = 127 # Half of 255 (DMX - Max)
    horizontal = False
    screensize = 1920, 1080

    def __init__(self, canvas, pos, xscreensize, temphorizontal):
        # Initialisiert Klasse
        horizontal = temphorizontal
        if horizontal:
            # TODO Wagerechten Balken machen - Sonst Senkrecht
            pass
        self.canvas = canvas
        self.canvas_id = self.canvas.create_rectangle(0, pos, xscreensize, pos + 100, fill = "white")
        # TODO Outline?
    
    def visibility(self, state):
        print("Changing visibility to " + state)
        print("TODO")
        # TODO Change Visibility

    def move(self, postemp):
        self.canvas.moveto(self.canvas_id, 0, postemp)
        print("TODO")

    def getpos(self):
        return(self.pos)

    def zoom(self, zoomtemp):
        zoom = zoomtemp
        # TODO Zoom