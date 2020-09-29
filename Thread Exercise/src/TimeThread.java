import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeThread extends JFrame implements Runnable, ActionListener
{
    private int day,hour,min,sec;
    private JTextField[] text;
    private JPanel panel=new JPanel();
    private JButton confirm=new JButton("确认")
            ,erase=new JButton("清除")
            ,pause=new JButton("暂停")
            ,continuebutton=new JButton("继续")
            ,timestart=new JButton("开始计时")
            ,timereset=new JButton("清零");
    private Thread thread;

    public TimeThread()
    {
        this.setTitle("倒计时");
        this.setBounds(500,300,650,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.getContentPane().add(panel);
        panel.setVisible(true);
        panel.setLayout(new GridLayout(6,1));
        String[] str={"Day","Hour","Minute","Second"};
        text=new JTextField[4];
        for(int i=0;i<4;i++)
        {
            text[i]=new JTextField();
            panel.add(new JLabel(str[i],JLabel.CENTER));
            panel.add(text[i]);
        }

        confirm.addActionListener(this);
        erase.addActionListener(this);
        pause.addActionListener(this);
        continuebutton.addActionListener(this);
        timereset.addActionListener(this);
        timestart.addActionListener(this);
//        text[4]=new JTextField();
//        text[5]=new JTextField();
//        text[4].setEditable(false);
//        text[5].setEditable(false);
        panel.add(confirm);
        panel.add(erase);
        panel.add(pause);
        panel.add(continuebutton);
//        panel.add(text[4]);
//        panel.add(text[5]);
        pause.setEnabled(false);
        continuebutton.setEnabled(false);
        panel.setVisible(true);
//        this.thread=new Thread(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==confirm)
        {
            try
            {
                day = Integer.parseInt(text[0].getText());
                hour = Integer.parseInt(text[1].getText());
                min = Integer.parseInt(text[2].getText());
                sec = Integer.parseInt(text[3].getText());
                confirm.setEnabled(false);
                pause.setEnabled(true);
                erase.setEnabled(false);
                this.thread=new Thread(this);
                this.thread.start();
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,"存在不能转化为数字的符号，请检查");
            }
        }
        if(e.getSource()==erase)
        {
            if(this.thread.isAlive())
            this.thread.interrupt();
            for(int i=0;i<4;i++)
                text[i].setText("");
            confirm.setEnabled(true);
            pause.setEnabled(true);
            erase.setEnabled(true);
        }
        if(e.getSource()==pause)
        {
            this.thread.interrupt();
            pause.setEnabled(false);
            erase.setEnabled(true);
            continuebutton.setEnabled(true);
        }
        if(e.getSource()==continuebutton)
        {
            this.thread = new Thread(this);
            this.thread.start();
            continuebutton.setEnabled(false);
            pause.setEnabled(true);
        }
    }

    public void run()
    {
        while(this.thread.isAlive())
        {
            try
            {
                Thread.sleep(1000);
                if(day==0&&hour==0&&min==0&sec==0)
                {
                    JOptionPane.showMessageDialog(this,"时间到！");
                    break;
                }
                sec-=1;
                if(sec==-1&&(day!=0||hour!=0||min!=0))
                {
                    sec=59;
                    min-=1;
                    if(min==-1&&(day!=0||hour!=0))
                    {
                        min=59;
                        hour-=1;
                        if(hour==-1&&day!=0)
                        {
                            hour=23;
                            day-=1;
                        }
                    }
                }
                text[0].setText(String.valueOf(day));
                text[1].setText(String.valueOf(hour));
                text[2].setText(String.valueOf(min));
                text[3].setText(String.valueOf(sec));
            }
            catch (InterruptedException e)
            {
                break;
            }
        }

    }
    public static void main(String[] args)
    {
        TimeThread time=new TimeThread();
    }

}
