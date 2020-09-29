
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class WeighedAverage extends JFrame implements ActionListener,Runnable
{
    private double[] value,weight;
    private JTextField[] valuetext,weighttext;
    private JTextField average=new JTextField("Average")
                    ,flow=new JTextField("欢迎使用                                 ");
    private JButton calculate=new JButton("CALCULATE");
    private JPanel panel=new JPanel();
    private String defaultvaluetext;
    private int valueerrorindex,weighterrorindex;
    private JScrollPane scroll;
    private int i;
    private double defaultvalue;
    private Thread thread;
    public WeighedAverage(String s)
    {
        this.setTitle(s);
        this.setBounds(400,150,1000,400);
        this.setLayout(new GridLayout(1,1));
        this.getContentPane().add(scroll=new JScrollPane(panel));
//        this.add(scroll);

        String num=JOptionPane.showInputDialog(this,"Input the number of value","1");
        try
        {
            i = Integer.parseInt(num);
        }catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this,num+"不能转化为数值");
        }
        defaultvalue=100/i;
        defaultvaluetext=(String.valueOf(defaultvalue));

        panel.setLayout(new GridLayout(i+3,3));
        panel.add(flow,0);
        flow.setBackground(Color.LIGHT_GRAY);
        panel.add(new JLabel(""),1);
        panel.add(new JLabel(""),2);
        panel.add(new JLabel("Value"),3);
        panel.add(new JLabel("Weight(%)"),4);
        panel.add(new JLabel(""),5);

        value=new double[i];
        weight=new double[i];

        valuetext=new JTextField[i];
        weighttext=new JTextField[i];

        for(int a=0;a<i;a++)
        {
            valuetext[a]=new JTextField(String.valueOf(100*Math.random()),20);
            weighttext[a]=new JTextField(defaultvaluetext,20);
            panel.add(valuetext[a]);
            panel.add(weighttext[a]);
            panel.add(new JLabel("%"));
        }
        panel.add(calculate);
        calculate.addActionListener(this);
        panel.add(average);
        average.setEditable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setVisible(true);
        this.setVisible(true);
    }



    public void actionPerformed(ActionEvent e)
    {
        double sumvalue=0,sumweight=0,aver;
        this.thread=new Thread(this);
        if(e.getSource()==calculate)
        {
            if(!this.thread.isAlive())
                thread.start();
            try
            {
                for(int a=0;a<i;a++)
                {
                    value[a]=Double.parseDouble(valuetext[a].getText());
                    valueerrorindex=a;
                }

            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,valuetext[valueerrorindex].getText()+"不能转化为数值,请修改标红的框");
                valuetext[valueerrorindex].setBackground(new Color(0,158,91));;

            }
            try
            {
                for(int a=0;a<i;a++)
                {
                    weight[a]=Double.parseDouble(weighttext[a].getText());
                    weighterrorindex=a;
                }
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,valuetext[weighterrorindex].getText()+"不能转化为数值,请修改标红的框");
                weighttext[weighterrorindex].setBackground(new Color(0,158,91));
            }


            for(int a=0;a<i;a++)
            {
                sumweight+=weight[a];
            }
            if(sumweight<=95.5||sumweight>=100.1)
            {
                JOptionPane.showMessageDialog(this, "权重溢出");
                average.setText("错误");
            }
            else
            {
                for(int a=0;a<i;a++)
                {
                    sumvalue+=value[a]*weight[a];
                }
                average.setText(String.valueOf(sumvalue/100));
            }
        }
    }
    public void run()
    {
        while(true)
        {
            String str = this.flow.getText();
            flow.setText(str.substring(1)+ str.substring(0,1));
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                break;
            }
        }
    }


    public static void main(String[] args)
    {
        WeighedAverage average=new WeighedAverage("Weighted Average Calculator");
    }


}
