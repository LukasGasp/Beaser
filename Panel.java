import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class Panel extends JPanel{
    
    int x;
    int y;
    int width;
    int height;

    Color maincolor = new Color(255, 255, 255);

    int mode = 0;

    // ==================================== Getter & Setter ==================================== \\

    // ============ Mode ============ \\

    public void setmode(int tempmode){
        mode = tempmode;
    }

    public int getmode(){
        return mode;
    }

    // ========== Postition ========== \\

    public void setpos(int tempx, int tempy){
        x = tempx;
        y = tempy;
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }

    // ============ Color ============ \\
    
    public void setcolor(Color color){
        maincolor = color;
    }

    public Color getcolor(){
        return maincolor;
    }

    // ==================================== Painting ==================================== \\

    @Override
    public void paint (Graphics g){
        super.paint(g);
        g.setColor(maincolor);
        switch(mode) {
            case 0 :
               // Off
               break;

            case 1 :
               // Horizontal Line
               g.fillRect(0, y, width, height);
               break;
            
            case 2 :
                // Vertical Line
                g.drawLine(100, 100, 200, 150);
                break;
            case 3 :
                // Rectangle
                g.fillRect(100, 200, 100, 50);
                break;
            case 4 :
                // Oval
                g.fillOval(300, 200, 100, 100);
                break;
            case 5 : 
                // Poly Line (?)
                int x[] = {100, 200, 300};
                int y[] = {400, 300, 400};
                int n = 3;
        
                g.drawPolyline(x, y, n);
                break;
            case 6 :
                // NGK - Logo
                String url = "NGKwanneweiß.png";
                Image image = new ImageIcon(url).getImage();
                g.drawImage(image, 300, 50, 200, 100, null);
                break;
            case 7 :
                // Arc
                g.fillArc(400, 200, 100, 100, 0, 90);
                break;
            default :
                //g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
                //g.drawString("Beaser", 50, 50);
                break;
        
        }
    }

    public void forcerepaint(){
        repaint();
    }
}
