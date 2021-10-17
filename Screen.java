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
    int height;
    int width;
    int mode = 0;
    
    int[] dmxcache;
    Panel panel;

    Dimension windowSize;

    public Screen(){
        dmxcache  = new int[512];

        setSize(800, 600);
        setTitle("Beaser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        logger.log(Level.INFO, "Screen ready");
        
        panel = new Panel();
        panel.setBackground(Color.black);
        add(panel);
        setVisible(true);

        windowSize = getSize();
        System.out.println(windowSize);
    }

    public void givedata(int[] dmx){
        panel.setwindowsize(getSize().width, getSize().height); // Muss zuerst aufgerufen werden, damit positionen stimmen

        size(dmx[6], dmx[7]);                                   // Muss als zweites aufgerufen werden, damit größen für die position stimmen
        mode(dmx[0]);
        dim(dmx[1], dmx[2], dmx[3]);
        setpos(dmx[4], dmx[5]);
        //logger.log(Level.INFO, String.valueOf(panel.getwidht()) + String.valueOf(panel.getheight()));
        rgb(dmx[8], dmx[9], dmx[10], dmx[11], dmx[12], dmx[13], dmx[14], dmx[15], dmx[16]);
        eoptions(dmx[17]);
        panel.forcerepaint();
    }

    public void mode(int dmx){
        if(dmx >= 0 && dmx <= 9){
            if (mode != 0){
                mode = 0; // OFF
                panel.setmode(0);
                logger.log(Level.INFO, "Mode to off");
            }
        } else if(dmx >= 10 && dmx <= 19){
            if (mode != 1){
                mode = 1; // Horizontal Line
                panel.setmode(1);
                logger.log(Level.INFO, "Mode to Horizontal Line");
            }
        } else if(dmx >= 20 && dmx <= 29){
            if (mode != 2){
                mode = 2; // Vertical Line
                panel.setmode(2);
                logger.log(Level.INFO, "Mode to Vertical Line");
            }
        } else if(dmx >= 30 && dmx <= 39){
            if (mode != 3){
                mode = 3; // Rectangle
                panel.setmode(3);
                logger.log(Level.INFO, "Mode to rectangle");
            }
        } else if(dmx >= 40 && dmx <= 49){
            if (mode != 4){
                mode = 4; // Oval
                panel.setmode(4);
                logger.log(Level.INFO, "Mode to oval");
            }
        } else if(dmx >= 50 && dmx <= 59){
            if (mode != 5){
                mode = 5; // Poly Line
                panel.setmode(5);
                logger.log(Level.INFO, "Mode to Poly Line");
            }
        } else if(dmx >= 60 && dmx <= 69){
            if (mode != 6){
                mode = 6; // NGK - Logo
                panel.setmode(6);
                logger.log(Level.INFO, "Mode to NGK - Logo");
            }
        } else if(dmx >= 70 && dmx <= 79){
            if (mode != 7){
                mode = 7; // Arc
                panel.setmode(7);
                logger.log(Level.INFO, "Mode to Arc");
            }
        } else {
            if (mode != 0){
                panel.setmode(1);
                logger.log(Level.INFO, "Mode not in defined level");
                mode = 0;
            }
        }
    }

    public void dim(int tempdim1, int tempdim2, int tempdim3){
        //TODO: Implement DIM2 and DIM3 for Written NGK
        float ctempdim = tempdim1; // Sonst keine nachkommastellen
        dim = (ctempdim / 255);
    }

    public void setpos(int tempx, int tempy){
        //TODO: 3x Pan / Tilt für Effekte
        windowSize = getSize();
        float cx = tempx; // Sonst keine nachkommastellen
        float cy = tempy; // Gleiches hier
        //panel.setposition(Math.round((cx / 255) * windowSize.width), Math.round((cy / 255) * windowSize.height));   // Hier ist es unmöglich oben raus zu kommen
        panel.setposition(Math.round((cx / 255) * (windowSize.width + width) - width), Math.round((cy / 255) * (windowSize.height + height) - height));
    }

    public void size(int tempwidth, int tempheight){
        windowSize = getSize();
        float cx = tempwidth; // Sonst keine nachkommastellen
        float cy = tempheight; // Gleiches hier
        width = Math.round((cx / 255) * windowSize.width);
        height = Math.round((cy / 255) * windowSize.height);
        panel.setsize(width, height);
    }

    public void rgb(int red1, int green1, int blue1, int red2, int green2, int blue2, int red3, int green3, int blue3){
        //TODO: Implement RGB2 and RGB 3 for Written NGK
        color = new Color(Math.round(red1 * dim), Math.round(green1 * dim), Math.round(blue1 * dim));
        if (color != panel.getcolor()){
            panel.setcolor(color);
        }
    }

    public void eoptions(int tempoption){
        panel.seteffectoption(tempoption);
    }
}
