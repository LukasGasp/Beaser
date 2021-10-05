import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

class Squares extends JPanel {
    private static final int PREF_W = 500;
    private static final int PREF_H = PREF_W;
    private List<Rectangle> squares = new ArrayList<Rectangle>();
 
    public void addSquare(int x, int y, int width, int height) {
       Rectangle rect = new Rectangle(x, y, width, height);
       squares.add(rect);
    }
 
    @Override
    public Dimension getPreferredSize() {
       return new Dimension(PREF_W, PREF_H);
    }
 
    @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D) g;
       for (Rectangle rect : squares) {
          g2.draw(rect);
       }
    }
 
 }