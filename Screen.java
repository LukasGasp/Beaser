import java.util.logging.Level;

import javax.swing.*;

import java.util.logging.Logger;

public class Screen extends JFrame{

    static Logger logger = Logger.getLogger("Logger for screen");
    JFrame frame;

    DRectangle drectangle;

    int x = 100;
    int y = 100;
    int xwidth = 50;
    int ywidht = 50;
    int dim = 255;

    int red = 255;
    int green = 0;
    int blue = 0;
    int mode;

    // 0: OFF
    // 1: D -   (efault)  - Rectangle
    // 2: D -   (efault)   - Circle
    // 3: ICH HABE DOCH SELBST KEINEN PLAN
    
    public Screen(){
        //Create Objects and shit
        prepareobjects();
        createscreen();
        frame.getContentPane().add(drectangle);
        frame.getContentPane().remove(drectangle);
        logger.log(Level.INFO, "Screen ready");
    }

    public void createscreen(){
        frame = new JFrame("Beaser");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        logger.log(Level.INFO, "Window Created");
    }

    public void prepareobjects(){
        drectangle = new DRectangle(x, y, xwidth, ywidht, red, green, blue);

    }

    public void refresh(){
        //TODO: Machen, falls nötig
    }

    public void setdim(int tempdim){
        // ÄHM JA. BESTIMMT. Muss mit RGB Kombiniert werden. BESTIMMT
        dim = tempdim;
        drectangle.setcolor(red * (dim/255), green * (dim/255), blue * (dim/255));
        // TODO: Dim < 0 = AUS?
        frame.getContentPane().repaint();
    }

    public void moveit(int tempx, int tempy){
        if(!(x == tempx && y == tempy)){
            x = tempx;
            y = tempy;
            drectangle.moveit(x, y);
            frame.getContentPane().repaint();
        }
    }

    public void setcolor(int tempRed, int tempGreen, int tempBlue){
        red = tempRed;
        green = tempGreen;
        blue = tempBlue;
        drectangle.setcolor(red * (dim/255), green * (dim/255), blue * (dim/255));
        frame.getContentPane().repaint();
    }

    // =================================================================== Moduswahl ===================================================================

    public void setmode(int tempmode){
        if(mode == tempmode){
            // Everything stays the same => Nothing to do
            return;
        } else {
            if(tempmode == 1){
                logger.log(Level.INFO, "Mode set to 0 (OFF)");
                setmode0();
            }
            if(tempmode == 2){
                logger.log(Level.INFO, "Mode set to 1 (D-Rectangle)");
                setmode1();
            }
            if(tempmode == 3){
                logger.log(Level.INFO, "Mode set to 2 (D-Oval)");
            }
            if(tempmode == 4){
                logger.log(Level.INFO, "Mode set to 3 (KEINE AHNUNG)");
            }
        }
        mode = tempmode;
    }

    public void setmode0(){
        // ÄHM JA
        //TODO: Machen
        frame.getContentPane().remove(drectangle);
    }

    public void setmode1(){
        frame.getContentPane().add(drectangle);
    }

    public void setmode2(){
        // ÄHM NEIN
        //TODO: Machen (Oval)
    }
}
