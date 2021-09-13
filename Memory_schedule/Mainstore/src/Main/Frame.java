//total frame
package Main;
import component.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Frame extends JFrame implements Runnable{
    int timegap=1000;//Variable for speed
    public int count=0;//count break times
    public int pos=-1;//position of block which will be changed
    public Font font;//font family
    public JButton Begin,Clear,Stop;//3 buttons
    public boolean isclear=false;
    public boolean isrun=false;
    public Mainstore MyMainstore;//Memory
    public Choose MyChoose;//choose algorithm
    public Wait WaitingOrder;//waiting queue
    public Operations Myoperation;//operation state
    int Turn=0;
    JButton SpeedUP,SpeedDown;//speed controlling button
    public Frame()
    {
        //initialize total frame
        font=new Font("宋体",Font.BOLD,20);
        setLayout(null);
        setTitle("内存管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10,10,1000,900);
        setResizable(false);
        getContentPane().setBackground(new Color(162, 178, 159));

        JLabel title=new JLabel("模拟内存");
        title.setSize(100,50);
        title.setLocation(280,10);
        title.setFont(font);
        add(title);

        MyMainstore=new Mainstore();
        add(MyMainstore);

        MyChoose=new Choose();
        add(MyChoose);

        WaitingOrder =new Wait();
        add(WaitingOrder);

        Myoperation=new Operations();
        add(Myoperation);
        //initialize speed button
        SpeedDown=new JButton("速度减慢");
        SpeedUP=new JButton("速度加快");
        SpeedUP.setFont(font);
        SpeedDown.setFont(font);
        SpeedUP.setBounds(630,590,150,40);
        SpeedDown.setBounds(820,590,150,40);
        SpeedUP.setBackground(new Color(250, 253, 246));
        SpeedDown.setBackground(new Color(250, 253, 246));
        SpeedDown.setOpaque(true);
        SpeedUP.setOpaque(true);
        SpeedDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                timegap*=2;//time gap multiply
            }
        });
        SpeedUP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timegap>50)//min 50ms
                {
                    timegap/=2;//time gap halve
                }
            }
        });
        add(SpeedUP);
        add(SpeedDown);
        //initialize speed buttons
        Begin=new JButton("开始");
        Begin.setFont(font);
        Begin.setBackground(new Color(250, 253, 246));
        Begin.setBounds(640,200,100,50);
        Begin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isrun=true;
                isclear=false;
                Stop.setEnabled(true);
                Clear.setEnabled(false);
                Begin.setEnabled(false);
            }
        });
        add(Begin);
        Clear=new JButton("清零");
        Clear.setFont(font);
        Clear.setBackground(new Color(250, 253, 246));
        Clear.setBounds(750,200,100,50);
        Clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isrun=false;
                isclear=true;
            }
        });
        add(Clear);
        Stop=new JButton("停止");
        Stop.setFont(font);
        Stop.setBackground(new Color(250, 253, 246));
        Stop.setBounds(860,200,100,50);
        Stop.setEnabled(false);
        Stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isrun=false;
                isclear=false;
                Clear.setEnabled(true);
                Begin.setEnabled(true);
                Stop.setEnabled(false);
            }
        });
        add(Stop);
    }
    public boolean Find(int order)
    {//find
        for (int i = 0; i < 4; i++) {
            if(MyMainstore.blocks[i].Order[0]<=order&&MyMainstore.blocks[i].Order[9]>=order)
            {
                pos=i;
                return true;
            }

        }
        return false;
    }
    public void getFI()
    {
        pos=Turn;
        Turn=(Turn+1)%4;
    }
    public void getLRU()
    {
        int max=-1;
        int maxi=0;
        for (int i = 0; i < 4; i++) {
            if(MyMainstore.blocks[i].times>max)
            {
                max=MyMainstore.blocks[i].times;
                maxi=i;
            }
        }
        pos=maxi;
    }

    @Override
    public void run()//run function
    {
        while(true)//loop
        {
            count=0;
            if(isrun)
            {
                Herwego();
                Myoperation.Opertion.setText("程序运行完成，共有"+count+"个中断");
            }
            if(isclear)
            {
                WaitingOrder.CreateOrder();
                count=0;
                Myoperation.Opertion.setText("清零成功！");
                for (int i = 0; i < 4; i++) {
                    WaitingOrder.Memorys[i].setText(""+(WaitingOrder.Myorder[i]));
                }
                isclear=false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void Herwego()
    {
        int i=0;
        while(isrun&&i<320)
        {
            boolean isin=Find(WaitingOrder.Myorder[i]);//look for whether this instruction in my memory,if in,record its position in pos
            if(!isin)//not in
            {
                count++;
                if(MyChoose.getNowchoose()==1)
                    getFI();//find the page which should be changed by FIFO algorithm,record position in pos
                else
                    getLRU();//find the page which should be changed by LRU algorithm,record position in pos
                int OUTpage=MyMainstore.blocks[pos].Order[0]/10+1;
                int INpage=WaitingOrder.Myorder[i]/10+1;
                Myoperation.change(OUTpage,INpage);
                //change Myoperation's show
                MyMainstore.blocks[pos].change(INpage);
                //change data of memory
            }
            else
            {
                MyMainstore.blocks[pos].times=0;
                Myoperation.change(0,0);
            }
            for (int j = 0; j < 10; j++) {
                if(MyMainstore.blocks[pos].Order[j]==WaitingOrder.Myorder[i])
                {//show the specific instruction
                    MyMainstore.blocks[pos].Memorys[j].setBackground(Color.pink);
                    MyMainstore.blocks[pos].Memorys[j].setOpaque(true);
                    try {// wait for a second
                        Thread.sleep(timegap);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    MyMainstore.blocks[pos].Memorys[j].setBackground(new Color(250, 253, 246));
                }
            }
            for (int j = 0; j < 4; j++) {
                MyMainstore.blocks[j].times++;
                if(i+j+2<320)
                    WaitingOrder.Memorys[j].setText(""+WaitingOrder.Myorder[i+j+2]);
                else
                    WaitingOrder.Memorys[j].setText("NULL");
            }
            i++;
        }
        isrun=false;//end of one run
    }

    public static void main(String[] args) {
        Frame MyFrame=new Frame();
        MyFrame.setVisible(true);
        new Thread(MyFrame).start();
    }
}
