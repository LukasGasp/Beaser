import java.awt.Color;
import java.util.logging.Level;

import javax.swing.*;

import java.util.logging.Logger;

import java.util.logging.Level;

public class Screen extends JFrame{

    static Logger logger = Logger.getLogger("Logger for screen");
    JFrame frame;

    DRectangle drectangle;

    int x = 100;
    int y = 100;
    int xwidth = 100;
    int ywidht = 100;

    int red = 255;
    int green = 255;
    int blue = 255;
    int mode;
    // 0: OFF
    // 1: D -   (efault)  - Rectangle
    // 2: D -   (efault)   - Circle
    // 3: ICH HABE DOCH SELBST KEINEN PLAN
    
    public Screen(){
        //Create Objects and shit
        createscreen();
        prepareobjects();
        logger.log(Level.INFO, "Screen ready");
    }

    public void createscreen(){
        frame = new JFrame("Beaser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        logger.log(Level.INFO, "Window Created");
    }

    public void prepareobjects(){
        drectangle = new DRectangle(x, y, xwidth, ywidht, red, green, blue);
    }

    public void refresh(){
        //TODO: Machen, falls nötig
    }

    public void setdim(){
        // ÄHM JA. BESTIMMT. Muss mit RGB Kombiniert werden. BESTIMMT
        //TODO: Machen. Kein Bock ~ Lukas
    }

    public void moveit(int tempx, int tempy){
        x = tempx;
        y = tempy;
        drectangle.moveit(x, y);
    }

    public void setcolor(int tempRed, int tempGreen, int tempBlue){
        red = tempRed;
        green = tempGreen;
        blue = tempBlue;
        drectangle.setcolor(red, green, blue);
    }

    // =================================================================== Moduswahl ===================================================================

    public void setmode(int tempmode){
        if(mode == tempmode){
            // Everything stays the same => Nothing to do
            logger.log(Level.WARNING, "Main.java send useless Data. WHY!?");
        } else {
            if(tempmode == 0){
                logger.log(Level.INFO, "Mode set to 0 (OFF)");
                setmode0();
            }
            if(tempmode == 1){
                logger.log(Level.INFO, "Mode set to 1 (D-Rectangle)");
            }
            if(tempmode == 2){
                logger.log(Level.INFO, "Mode set to 2 (D-Oval)");
            }
            if(tempmode == 3){
                logger.log(Level.INFO, "Mode set to 3 (KEINE AHNUNG)");
            }
        }
    }

    public void setmode0(){
        // ÄHM JA
        //TODO: Machen
    }

    public void setmode1(){
        frame.getContentPane().add(drectangle);
    }

    public void setmode2(){
        // ÄHM NEIN
        //TODO: Machen (Oval)
    }
}
