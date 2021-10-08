import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Point;

import javax.swing.*;

public class DRectangle extends JPanel{
    // Default Rectangle
    private static final int PREF_W = 500;
    private static final int PREF_H = PREF_W;

    int x;
    int y;
    int width;
    int height;
    Rectangle main;
    Color maincolor;

    public DRectangle(int tempx, int tempy, int tempwidth, int tempheight, int red, int green, int blue){
        x = tempx;
        y = tempy;
        width = tempwidth;
        height = tempheight;
        maincolor = new Color(red, green, blue);
        main = new Rectangle(x, y, tempwidth, tempheight);
    }

    public void moveit(int tempx, int tempy){
        x = tempx;
        y = tempy;
        main.setLocation(x, y);
    }

    public void setsize(int tempwidth, int tempheight){
        width = tempwidth;
        height = tempheight;
        main.setSize(width, height);
    }

    public void setcolor(int red, int green, int blue){
        maincolor = new Color(red, green, blue);
    }

    public Point getposition(){
        return main.getLocation();
    }

    public Dimension getsize(){
        return main.getSize();
    }

    @Override
    public Dimension getPreferredSize() {
       return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(maincolor); //use g2 instead
        g2.fillRect(x, y, width, height);
        setBackground(Color.black);
        g2.draw(main);
    }
}
