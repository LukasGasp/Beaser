import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class Panel extends JLayeredPane{
    
    int[] x;
    int[] y;
    int[] width;
    int[] height;

    Color[] maincolors;

    int[] mode;

    float[] dim;

    int[] wxsize;
    int[] wysize;

    int[] rotation;

    int[] eoption;

    int macro;

    int[] id; // ID is for helping programming

    public Panel(int tempamount){
        id = new int[tempamount];
        x = new int[tempamount];
        y = new int[tempamount];
        width = new int[tempamount];
        height = new int[tempamount];
        rotation = new int[tempamount];
        maincolors = new Color[tempamount];
        mode = new int[tempamount];
        dim = new float[tempamount];
        wxsize = new int[tempamount];
        wysize = new int[tempamount];
        eoption = new int[tempamount];
        for(int i = 0; i < tempamount; i++){
            id[i] = i;
            x[i] = 0;
            y[i] = 0;
            width[i] = 0;
            height[i] = 0;
            rotation[i] = 0;
            maincolors[i] = new Color(0, 0, 0);
            dim[i] = 0;
            mode[i] = 0;
            wxsize[i] = 0;
            wysize[i] = 0;
            eoption[i] = 0;
        }
    }

    // ==================================== Getter & Setter ==================================== \\

    // ============ Mode ============ \\

    public void setmode(int temppanel, int tempmode){
        mode[temppanel] = tempmode;
    }

    public int getmode(int temppanel){
        return mode[temppanel];
    }

    // ========== Dim ========== \\

    public void setdim(int temppanel, int tempdim){
        float ttempdim = tempdim;
        dim[temppanel] = (ttempdim/255);
    }

    public float getdim(int temppanel){
        return dim[temppanel];
    }

    // ========== Postition ========== \\

    public void setposition(int temppanel, int tempx, int tempy){
        x[temppanel] = tempx;
        y[temppanel] = tempy;
    }

    public int getx(int temppanel){
        return x[temppanel];
    }

    public int gety(int temppanel){
        return y[temppanel];
    }

    // ============ Size ============ \\

    public void setsize(int temppanel, int tempwidth, int tempheight){
        width[temppanel] = tempwidth;
        height[temppanel] = tempheight;
    }

    public int getwidht(int temppanel){
        return width[temppanel];
    }

    public int getheight(int temppanel){
        return height[temppanel];
    }

    // ============ Rotation ============ \\

    public void setrotation(int temppanel, int tangle){
        rotation[temppanel] = tangle;
    }

    public int setrotation(int temppanel){
        return rotation[temppanel];
    }

    // ============ Color ============ \\
    
    public void setcolor(int temppanel, int tred, int tgreen, int tblue){
        float red = tred;
        float green = tgreen;
        float blue = tblue;
        maincolors[temppanel] = new Color((red/255), (green/255), (blue/255), dim[temppanel]);
    }

    public Color getcolor(int temppanel){
        return maincolors[temppanel];
    }

    // ============ Options ============ \\

    public void seteffectoption(int temppanel, int tempoption){
        eoption[temppanel] = tempoption;
    }

    public void setmarco(int tempmacro){
        macro = tempmacro;
    }

    public void setwindowsize(int temppanel, int twxsize, int twysize){
        wxsize[temppanel] = twxsize;
        wysize[temppanel] = twysize;
    }

    // ==================================== Painting ==================================== \\

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(macro != 0){
            System.out.println("Macro not done yet"); //TODO: Macros
        } else{
            for(int i = 0; i < id.length; i++){
                g2d.setColor(maincolors[i]);
                g2d.rotate(Math.toRadians(rotation[i]), x[i] + width[i]/2, y[i] + height[i]/2);
                switch(mode[i]) {
                    case 0 :
                    // Off
                    break;
                    case 1 :
                        // Horizontal Line
                        g2d.fillRect(0, y[i], wxsize[i], height[i]);
                        System.out.println("Mode to horizontal line");
                        break;
                    case 2 :
                        // Vertical Line
                        g2d.fillRect(x[i], 0, width[i], wysize[i]);
                        break;
                    case 3 :
                        // Rectang2dle
                        g2d.fillRect(x[i], y[i], width[i], height[i]);
                        break;
                    case 4 :
                        // Oval
                        g2d.fillOval(x[i], y[i], width[i], height[i]);
                        break;
                    case 5 : 
                        // Poly Line (?)
                        int tx[] = {100, 200, 300};
                        int ty[] = {400, 300, 400};
                        int n = 3;
                
                        g2d.drawPolyline(tx, ty, n);
                        break;
                        //TODO: Position / Size
                    case 6 :
                    if(eoption[i] >= 0 && eoption[i] <= 9){
                        break;
                    } else if(eoption[i] >= 10 && eoption[i] <= 19){ 
                        Image image = new ImageIcon("assets/ngklogo.png").getImage();
                        g2d.drawImage(image, x[i], y[i], width[i], height[i], null);
                        break;
                    } else if(eoption[i] >= 20 && eoption[i] <= 29){   
                        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, width[i]));
                        g2d.drawString("N", x[i], y[i]); 
                        break;
                    } else if(eoption[i] >= 30 && eoption[i] <= 39){   
                        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, width[i]));
                        g2d.drawString("G", x[i], y[i]); 
                        break;
                    } else if(eoption[i] >= 40 && eoption[i] <= 49){   
                        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, width[i]));
                        g2d.drawString("K", x[i], y[i]); 
                        break;
                        //TODO: Test Written NGK
                    } else if(eoption[i] >= 50 && eoption[i] <= 59){   
                        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, width[i]));
                        g2d.drawString("K", x[i], y[i]); 
                        break;
                        //TODO: FMT
                    }
                    case 7 :
                        // Arc
                        g2d.fillArc(400, 200, 100, 100, 0, 90);
                        break;
                    default :
                        //g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
                        //g.drawString("Beaser", 50, 50);
                        break;
                }
                g2d.setTransform(new AffineTransform()); // Zurück auf 0 drehen, da weitere Objekte gezeichnet werden
            }
            g2d.dispose();
        }
    }

    public void forcerepaint(){
        repaint();
    }
}
