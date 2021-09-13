//waiting instruction queue
package component;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Wait extends JPanel{
    public int Myorder[]=new int[320];
    boolean isfornt=true;
    int last=0;
    JLabel Show;
    public JLabel Memorys[]=new JLabel[5];
    Font font=new Font("宋体",Font.BOLD,20);
    public void CreateOrder()//create new instruction list
    {
        for (int i = 0; i < 320; i+=2) {
            Random ran=new Random();
            int t1=ran.nextInt(319);
            if(isfornt)
            {
                if(t1!=318&&t1!=317)
                {
                    t1=t1%(320-last)+last;
                    isfornt=false;
                    last=t1+2;
                }

            }
            else if(!isfornt)
            {
                if(t1!=0&&t1!=1)
                {
                    t1=t1%(last);
                    isfornt=true;
                    last=t1+2;
                }
            }
            int t2=t1+1;
            Myorder[i]=t1;
            Myorder[i+1]=t2;

        }

    }
    public Wait()
    {
        CreateOrder();
        setLayout(null);
        setBounds(700,270,200,300);
        setBackground(new Color(189, 210, 182));
        setOpaque(true);
        Show=new JLabel("接下4个指令地址:",JLabel.CENTER);
        Show.setFont(font);
        Show.setBounds(0,0,200,50);
        add(Show);
        for (int i = 0; i < 4; i++) {
            Memorys[i]=new JLabel(""+(Myorder[i]),JLabel.CENTER);
            Memorys[i].setFont(font);
            Memorys[i].setOpaque(true);
            Memorys[i].setBackground(new Color(250, 253, 246));
            Memorys[i].setBounds(25,50+60*i,150,50);
            add(Memorys[i]);
        }
    }

}
