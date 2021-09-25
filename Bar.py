class Bar():
    "Makes a Bar"

    pos = 1
    zoom = 127 # Half of 255 (DMX - Max)
    horizontal = False

    def __init__(self, postemp, horizontal):
        # Initialisiert Klasse
        pos = postemp
        if horizontal:
            # TODO Wagerechten Balken machen - Sonst Senkrecht
            pass

    def visibility(self, state):
        print("Changing visibility to " + state)
        print("TODO")
        # TODO Change Visibility

    def move(self, postemp):
        pos = postemp
        # TODO Move
        print("TODO")

    def getpos(self):
        return(self.pos)

    def zoom(self, zoomtemp):
        zoom = zoomtemp
        # TODO Zoom