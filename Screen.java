import javax.swing.*;

public class Screen extends JFrame{
    public Screen(){
        //Create Objects and shit
        super("Beaser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Square square = new Square();
        getContentPane().add(square);
        square.addSquare(100, 100, 100, 100);
    
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
