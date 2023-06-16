import java.awt.Color;
import java.util.logging.Level;

import java.awt.Dimension;

import javax.swing.*;

import java.util.logging.Logger;

public class Screen extends JFrame{

    static Logger logger = Logger.getLogger("beaser");
    JFrame frame;

    float dim = 1;
    Color color = new Color(255, 255, 255);

    
    int[] dmxcache;

    int amount;

    Panel panels;

    Dimension windowSize;
    int datapointcache; // Stores which data of dmx has been used

    public Screen(int panelamount){
        amount = panelamount;
        dmxcache  = new int[512];

        // Configuring Screen
        setSize(800, 600);
        setTitle("Beaser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        logger.log(Level.INFO, "[screen] Started Screen Task Manager and window");

        JButton pauseButton = new JButton("Start Beaser");
        pauseButton.setBackground(new Color(0, 0, 0));
        pauseButton.setForeground(new Color(255, 255, 255));
        add(pauseButton);
        setVisible(true);

        for (int i = 0; i < 100; i++) {
            if(pauseButton.getModel().isPressed()){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            pauseButton.setText("Press to start Beaser (" + String.valueOf((100 - i)/10) + "s)");
            pauseButton.setBorderPainted(false);
        }
       
        remove(pauseButton);
        dispose();

        // Fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);;

        getContentPane().setBackground(Color.BLACK);

        logger.log(Level.INFO, "[screen] Started fullscreen"); 

        panels = new Panel(amount);
        panels.setOpaque(false);
        add(panels);
        
        logger.log(Level.INFO, "[screen] Configured " + String.valueOf(amount) + " panels.");

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
            size(i, datapointcache + 4);                                // Rel Channel : 5 + 6              // Muss vor setpos aufgerufen werden
            setpos(i, datapointcache + 2);                              // Rel Channel : 3 + 4
            rotation(i, datapointcache + 6);                            // Rel Channel : 7
            rgb(i, datapointcache + 7);                                 // Rel Channel : 7 + 8 + 9
            eoptions(i, datapointcache + 10);                           // Rel Channel : 10
            panels.forcerepaint();
            datapointcache = datapointcache + 11;
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

        // Die folgende Methode, hat in beiden Fällen * width, damit bei gleichen Werten eine Recheckige Figur entsteht, statt eine entsprechend des Screens gestreckte Figur
        panels.setsize(tempobject, Math.round((cx / 255) * windowSize.width), Math.round((cy / 255) * windowSize.width)); //TODO: Setting machen, ob sich am Screen orientiert werden soll
    }

    public void rotation(int tempobject, int datapoint){
        float percent = dmxcache[datapoint];
        panels.setrotation(tempobject, (int)((percent/255)*360));
    }

    public void dim(int tempobject, int datapoint){
        panels.setdim(tempobject, dmxcache[datapoint]); // Sonst keine nachkommastellen
    }

    public void setmode(int tempobject, int datapoint, int index){
        if(dmxcache[datapoint] >= 0 && dmxcache[datapoint] <= 9){
            if (panels.getmode(tempobject) != 0){
                panels.setmode(tempobject, 0); // OFF
                logger.log(Level.FINE, "[Object " + String.valueOf(tempobject) + "] Mode off"); 
            }
        } else if(dmxcache[datapoint] >= 10 && dmxcache[datapoint] <= 19){
            if (panels.getmode(tempobject) != 1){
                panels.setmode(tempobject, 1); // Horizontal Line
                logger.log(Level.FINE, "[Object " + String.valueOf(tempobject) + "] Mode Horizontal Line"); 
            }
        } else if(dmxcache[datapoint] >= 20 && dmxcache[datapoint] <= 29){
            if (panels.getmode(tempobject) != 2){ 
                logger.log(Level.FINE, "[Object " + String.valueOf(tempobject) + "] Mode Vertical Line"); 
            }
        } else if(dmxcache[datapoint] >= 30 && dmxcache[datapoint] <= 39){
            if (panels.getmode(tempobject) != 3){
                panels.setmode(tempobject, 3); // Rectangle
                logger.log(Level.FINE, "[Object " + String.valueOf(tempobject) + "] Mode Rectangle"); 
            }
        } else if(dmxcache[datapoint] >= 40 && dmxcache[datapoint] <= 49){
            if (panels.getmode(tempobject) != 4){
                panels.setmode(tempobject, 4); // Oval
                logger.log(Level.FINE, "[Object " + String.valueOf(tempobject) + "] Mode Oval"); 
            }
        } else if(dmxcache[datapoint] >= 50 && dmxcache[datapoint] <= 59){
            if (panels.getmode(tempobject) != 5){
                panels.setmode(tempobject, 5); // Poly Line
                logger.log(Level.FINE, "[Object " + String.valueOf(tempobject) + "] Mode Poly Line"); 
            }
        } else if(dmxcache[datapoint] >= 60 && dmxcache[datapoint] <= 69){
            if (panels.getmode(tempobject) != 6){
                panels.setmode(tempobject, 6); // Image
                logger.log(Level.FINE, "[Object " + String.valueOf(tempobject) + "] Mode Image"); 
            }
        } else if(dmxcache[datapoint] >= 70 && dmxcache[datapoint] <= 79){
            if (panels.getmode(tempobject) != 7){
                panels.setmode(tempobject, 7); // Arc
                logger.log(Level.FINE, "[Object " + String.valueOf(tempobject) + "] Mode Arc"); 
            }
        } else {
            if (panels.getmode(tempobject) != 0){
                panels.setmode(tempobject, 0);
                logger.log(Level.FINE, "[Object " + String.valueOf(tempobject) + "] Mode off"); 
            }
        }
    }
}