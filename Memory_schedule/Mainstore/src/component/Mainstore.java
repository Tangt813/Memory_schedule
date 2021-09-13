package component;

import javax.swing.*;
import java.awt.*;
public class Mainstore extends JPanel {
    public Block blocks[]=new Block[5];//5 blocks
    public Mainstore()
    {
        setLayout(null);
        setBounds(10,70,600,710);
        setBackground(new Color(189, 210, 182));
        setOpaque(true);
        //initialize
        for (int i = 0; i < 4; i++) {
            blocks[i]=new Block(i);
            add(blocks[i]);//create 4 blocks

        }
    }
}
