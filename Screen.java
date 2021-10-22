import java.awt.Color;
import java.util.logging.Level;

import java.awt.Dimension;

import javax.swing.*;

import java.util.logging.Logger;

public class Screen extends JFrame{

    static Logger logger = Logger.getLogger("Logger for screen");
    JFrame frame;

    float dim = 1;
    Color color = new Color(255, 255, 255);

    
    int[] dmxcache;

    int amount;

    Panel panels;

    Dimension windowSize;
    int datapointcache; // Stores which data of dmx has been used

    public Screen(){
        amount = 5;
        dmxcache  = new int[512];

        // Configuring Screen
        setSize(800, 600);
        setTitle("Beaser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        logger.log(Level.INFO, "Screen ready");

        getContentPane().setBackground(Color.BLACK);

        panels = new Panel(amount);
        panels.setOpaque(false);
        add(panels);

        logger.log(Level.INFO, "Configured " + String.valueOf(amount) + " panels.");

        setVisible(true);

        windowSize = getSize();
    }

    public void givedata(int[] dmx){
        windowSize = getSize(); // For right sizing of object sizes
        dmxcache = dmx;
        Color bg = new Color(dmxcache[0], dmxcache[1], dmxcache[2]);
        getContentPane().setBackground(bg);
        datapointcache = 3;
        for(int i = 0; i < amount; i++){
            panels.setwindowsize(i, getSize().width, getSize().height); // Muss zuerst aufgerufen werden, damit positionen stimmen
            setmode(i, datapointcache, i);                              // Rel Channel : 1
            dim(i, datapointcache + 1);                                 // Rel Channel : 2
            setpos(i, datapointcache + 2);                              // Rel Channel : 3 + 4
            size(i, datapointcache + 4);                                // Rel Channel : 5 + 6
            rgb(i, datapointcache + 6);                                 // Rel Channel : 7 + 8 + 9
            eoptions(i, datapointcache + 9);                            // Rel Channel : 10
            panels.forcerepaint();
            datapointcache = datapointcache + 10;
        }
    }

    public void eoptions(int tempobject, int datapoint){
        panels.seteffectoption(tempobject, dmxcache[datapoint]);
    }

    public void macro(int tempmacro){
        // TODO: Make Macros
    }

    public void rgb(int tempobject, int datapoint){
        panels.setcolor(tempobject, dmxcache[datapoint], dmxcache[datapoint + 1], dmxcache[datapoint + 2]);
    }

    public void setpos(int tempobject, int datapoint){
        float cx = dmxcache[datapoint]; // Sonst keine nachkommastellen
        float cy = dmxcache[datapoint + 1]; // Gleiches hier
        //temppanel.setposition(Math.round((cx / 255) * windowSize.width), Math.round((cy / 255) * windowSize.height));   // Hier ist es unmöglich oben raus zu kommen
        panels.setposition(tempobject, Math.round((cx / 255) * (windowSize.width + panels.getwidht(tempobject)) - panels.getwidht(tempobject)), Math.round((cy / 255) * (windowSize.height + panels.getheight(tempobject)) - panels.getheight(tempobject)));
    }

    public void size(int tempobject, int datapoint){
        float cx = dmxcache[datapoint]; // Sonst keine nachkommastellen
        float cy = dmxcache[datapoint + 1]; // Gleiches hier
        //TODO: Smooth Zoom (x+y) => Wahrscheinlich floats statt int
        panels.setsize(tempobject, Math.round((cx / 255) * windowSize.width), Math.round((cy / 255) * windowSize.height));
    }

    public void dim(int tempobject, int datapoint){
        panels.setdim(tempobject, dmxcache[datapoint]); // Sonst keine nachkommastellen
    }

    public void setmode(int tempobject, int datapoint, int index){
        if(dmxcache[datapoint] >= 0 && dmxcache[datapoint] <= 9){
            if (panels.getmode(tempobject) != 0){
                panels.setmode(tempobject, 0); // OFF
                logger.log(Level.INFO, String.valueOf(tempobject));
                logger.log(Level.INFO, "Mode to off");
            }
        } else if(dmxcache[datapoint] >= 10 && dmxcache[datapoint] <= 19){
            if (panels.getmode(tempobject) != 1){
                panels.setmode(tempobject, 1); // Horizontal Line
                logger.log(Level.INFO, String.valueOf(tempobject));
                logger.log(Level.INFO, "Mode to Horizontal Line");
            }
        } else if(dmxcache[datapoint] >= 20 && dmxcache[datapoint] <= 29){
            if (panels.getmode(tempobject) != 2){ 
                panels.setmode(tempobject, 2); // Vertical Line
                logger.log(Level.INFO, String.valueOf(tempobject));
                logger.log(Level.INFO, "Mode to Vertical Line");
            }
        } else if(dmxcache[datapoint] >= 30 && dmxcache[datapoint] <= 39){
            if (panels.getmode(tempobject) != 3){
                panels.setmode(tempobject, 3); // Rectangle
                logger.log(Level.INFO, String.valueOf(tempobject));
                logger.log(Level.INFO, "Mode to rectangle");
            }
        } else if(dmxcache[datapoint] >= 40 && dmxcache[datapoint] <= 49){
            if (panels.getmode(tempobject) != 4){
                panels.setmode(tempobject, 4); // Oval
                logger.log(Level.INFO, String.valueOf(tempobject));
                logger.log(Level.INFO, "Mode to oval");
            }
        } else if(dmxcache[datapoint] >= 50 && dmxcache[datapoint] <= 59){
            if (panels.getmode(tempobject) != 5){
                panels.setmode(tempobject, 5); // Poly Line
                logger.log(Level.INFO, String.valueOf(tempobject));
                logger.log(Level.INFO, "Mode to Poly Line");
            }
        } else if(dmxcache[datapoint] >= 60 && dmxcache[datapoint] <= 69){
            if (panels.getmode(tempobject) != 6){
                panels.setmode(tempobject, 6); // Image
                logger.log(Level.INFO, String.valueOf(tempobject));
                logger.log(Level.INFO, "Mode to Image");
            }
        } else if(dmxcache[datapoint] >= 70 && dmxcache[datapoint] <= 79){
            if (panels.getmode(tempobject) != 7){
                panels.setmode(tempobject, 7); // Arc
                logger.log(Level.INFO, String.valueOf(tempobject));
                logger.log(Level.INFO, "Mode to Arc");
            }
        } else {
            if (panels.getmode(tempobject) != 0){
                panels.setmode(tempobject, 0);
                logger.log(Level.INFO, String.valueOf(tempobject));
                logger.log(Level.INFO, "Mode not in defined level");
            }
        }
    }
}
