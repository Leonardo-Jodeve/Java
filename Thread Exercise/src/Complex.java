import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Complex extends JFrame implements ActionListener{
    private JComboBox[] com_operator;
    private JTextField[] t1,t2;
    private JButton button;
    public static int a;
    private static String[] ope= {"+","-","*","/"};

    public Complex(int b) {
        super("复数运算器");
        this.setSize(400, 600);
        this.setLocation(300, 240);
        this.setLayout(new FlowLayout(0));
        this.setBackground(java.awt.Color.lightGray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.t1=new JTextField[b+1];
        this.t2=new JTextField[b+1];
        this.com_operator=new JComboBox[b];
        a=b;

        int i=0,zhi1,zhi2;
        for(i=0;i<b;i=i++)
        {
            zhi1=(int)(Math.random()*100+1);
            zhi2=(int)(Math.random()*100+1);
            this.add(this.t1[i]=new JTextField(""+zhi1,20));
            this.t1[i].setEditable(true);
            this.add(new Label("+"));
            this.add(this.t2[i]=new JTextField(""+zhi2,20));
            this.t2[i].setEditable(true);
            this.add(new Label("i"));
            if(i<b-1)
            {
                this.add(this.com_operator[i]=new JComboBox<String>(Complex.ope));
            }
        }

        JButton button=new JButton("=");
        this.add(button);
        this.button.addActionListener(this);

        this.add(this.t1[b]=new JTextField("",8));
        this.t1[b].setEditable(false);
        this.add(new Label("+"));
        this.add(this.t2[b]=new JTextField("",8));
        this.t2[b].setEditable(false);
        this.add(new Label("i"));
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent event) {
        int number1[] = new int[a],number2[]=new int[a];
        int sign[] = new int[a-1];
        int i,sum1=0,sum2=0;
        for (i = 0; i < a-1; i++) {
            sign[i] = this.com_operator[i].getSelectedIndex();
        }
        for (i = 0; i<a; i++) {
            try {
                number1[i] = Integer.parseInt(this.t1[i].getText());
                number2[i] = Integer.parseInt(this.t2[i].getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, this.t1[i].getText() + "不能转换为整数");
                JOptionPane.showMessageDialog(this, this.t2[i].getText() + "不能转换为整数");
            }
        }
        for (i = 0; i <a-1; i++) {
            if (sign[i] == 3) {
                if (number1[i + 1] == 0&&number2[i+1]==0) {
                    JOptionPane.showMessageDialog(this, "除数不能为0");
                }
            }
        }

        if (sign[0] == 0) {
            sum1 = number1[0] + number1[1];
            sum2 = number2[0] + number2[1];
        } else {
            if (sign[0] == 1) {
                sum1 = number1[0] - number1[1];
                sum2 = number2[0] - number2[1];
            } else {
                if (sign[0] == 2) {
                    sum1 = number1[0] * number1[1]-number2[0]*number2[1];
                    sum2 = number1[0] * number2[1]+number2[0]*number1[1];
                } else {
                    sum1 = (number1[0] * number1[1]+number2[0] * number2[1])/(number1[1]*number1[1]+number2[1]*number2[1]);
                    sum2 = (number2[0] * number1[1]-number1[0]*number2[1])/(number1[1]*number1[1]+number2[1]*number2[1]);
                }
            }
        }

        for(i=1;i<a-1;i++) {
            if (sign[0] == 0) {
                sum1 = sum1 + number1[i+1];
                sum2 = sum2 + number2[i+1];
            } else {
                if (sign[0] == 1) {
                    sum1 = sum1 - number1[i+1];
                    sum2 = sum2 - number2[i+1];
                } else {
                    if (sign[0] == 2) {
                        sum1 = sum1 * number1[i+1]-sum2*number2[i+1];
                        sum2 = sum1 * number2[i+1]+sum2*number1[i+1];
                    } else {
                        sum1 = (sum1 * number1[i+1]+sum2 * number2[i+1])/(number1[i+1]*number1[i+1]+number2[i+1]*number2[i+1]);
                        sum2 = (sum2 * number1[i+1]-sum1*number2[i+1])/(number1[i+1]*number1[i+1]+number2[i+1]*number2[i+1]);
                    }
                }
            }
        }
        this.t1[a].setText("" + sum1);
        this.t2[a].setText("" + sum2);
    }
    public static void main(String[] args) {
        String color=JOptionPane.showInputDialog("运算数个数","");
        int gs;
        gs = Integer.parseInt(color);
        new Complex(gs);
    }
}