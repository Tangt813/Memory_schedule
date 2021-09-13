//Operation illustration
package component;
import javax.swing.*;
import java.awt.*;

public class Operations extends JPanel{
    public JLabel Show;
    public JLabel cut;
    public JLabel Opertion;//Specific operation
    public Operations()
    {
        setLayout(null);
        setBounds(620,650,350,160);
        setBackground(new Color(189, 210, 182));
        setOpaque(true);

        Opertion=new JLabel();
        Opertion.setBounds(20,50,310,40);
        Opertion.setBackground(new Color(189, 210, 182));
        Opertion.setFont(new Font("宋体",Font.BOLD,20));
        Opertion.setOpaque(true);
        add(Opertion);

        Show=new JLabel("运行状态：",JLabel.CENTER);
        Show.setBounds(0,0,350,40);
        Show.setFont(new Font("宋体",Font.BOLD,20));
        add(Show);

        cut=new JLabel("----------------------------",JLabel.CENTER);
        cut.setBounds(0,40,350,10);
        cut.setFont(new Font("宋体",Font.BOLD,20));
        add(cut);
        //Basic frame and initialization
    }

    public void change(int OUTpage,int INpage)
    {//Renew function
        if(OUTpage==0&&INpage==0)//no need to change
            Opertion.setText("无中断产生！");
        else
        {
            if(OUTpage==0)
            {
                Opertion.setText("中断产生！调入"+INpage+"块");
            }
            else
            {
                Opertion.setText("中断产生！调出"+OUTpage+"块,调入"+INpage+"块");
            }
        }
    }
}
