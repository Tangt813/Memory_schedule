//choose algorithm
package component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choose extends JPanel{
    public int Nowchoose=1;
    JLabel Show;
    JButton FIFO;
    JButton LRU;
    public Choose()
    {
        setLayout(null);
        setBounds(650,10,300,160);
        setBackground(new Color(189, 210, 182));
        setOpaque(true);

        Show=new JLabel("请选择执行的算法",JLabel.CENTER);
        Show.setBounds(0,0,300,60);
        Show.setFont(new Font("宋体",Font.BOLD,20));
        add(Show);
        FIFO=new JButton("FIFO先进先出算法");
        FIFO.setBounds(0,60,300,50);
        FIFO.setBackground(new Color(250, 253, 246)) ;
        FIFO.setFont(new Font("宋体",Font.BOLD,20));
        FIFO.setEnabled(false);
        FIFO.setBackground(new Color(254, 255, 222));
        FIFO.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FIFO.setBackground(new Color(254, 255, 222));
                LRU.setBackground(new Color(250, 253, 246));
                LRU.setEnabled(true);
                FIFO.setEnabled(false);
                Nowchoose=1;
            }
        });
        add(FIFO);
        LRU=new JButton("LRU算法");
        LRU.setBounds(0,110,300,50);
        LRU.setBackground(new Color(250, 253, 246));
        LRU.setFont(new Font("宋体",Font.BOLD,20));
        LRU.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                LRU.setBackground(new Color(254, 255, 222));
                LRU.setEnabled(false);
                FIFO.setBackground(new Color(250, 253, 246));
                FIFO.setEnabled(true);
                Nowchoose=2;
            }
        });
        add(LRU);
    }
    public int getNowchoose()
    {
        return Nowchoose;
    }
}
