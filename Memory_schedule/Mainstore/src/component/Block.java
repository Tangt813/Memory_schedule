//Single memory block
package component;
import javax.swing.*;
import java.awt.*;

public class Block extends JPanel{
    public int Order[]={-10,-10,-10,-10,-10,
            -10,-10,-10,-10,-10};
    public JLabel Memorys[]=new JLabel[10];//10 instructions
    public int VirtualMemory=-1;
    public int times=0;//Variables for LRU
    JLabel Physicsmenory;
    JLabel Virtualmemory;

    public Block(int th)
    {
        setLayout(null);
        setOpaque(true);
        setBackground(new Color(189, 210, 182));
        setBounds(th*140+40,10,100,710);

        Physicsmenory=new JLabel("物理"+(th+1)+"页",JLabel.CENTER);
        Physicsmenory.setFont(new Font("宋体",Font.BOLD,20));
        Physicsmenory.setBounds(0,10,100,50);
        add(Physicsmenory);

        Virtualmemory=new JLabel("虚拟"+VirtualMemory+"页",JLabel.CENTER);
        Virtualmemory.setFont(new Font("宋体",Font.BOLD,20));
        Virtualmemory.setBounds(0,660,100,50);
        add(Virtualmemory);
        //basic frame

        for (int j = 0; j < 10; j++) {
            Memorys[j]=new JLabel("NULL",JLabel.CENTER);
            Memorys[j].setBounds(0,60*(j+1)+10,100,50);
            Memorys[j].setBackground(new Color(250, 253, 246));
            Memorys[j].setOpaque(true);
            add(Memorys[j]);
        }//Initialize all as NULL
    }

    public void change(int INpage)
    {//insert new page,change function
        for (int i = 0; i < 10; i++) {
            Order[i]=(INpage-1)*10+i;
            this.Memorys[i].setText(""+((INpage-1)*10+i));
            Memorys[i].setOpaque(true);
            Virtualmemory.setText("虚拟"+(INpage)+"页");
        }
        times=0;
    }
}
