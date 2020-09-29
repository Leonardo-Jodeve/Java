import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LayoutExercise extends JFrame implements ActionListener
{
    JPanel panel;
    JTextField valuetext[],weighttext[];
    double value[],weight[];
    JButton calculate,cancel;
    public LayoutExercise(String s)
    {
        super(s);
        this.setLayout(new FlowLayout());
        String num = JOptionPane.showInputDialog("输入", 1);
        int i = Integer.parseInt(num);
        this.add(panel);
        panel.setVisible(true);
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JTextField("2",10));


//        valuetext=new JTextField[i];
//        weighttext=new JTextField[i];
//        for(int a=0;a<i;a++)
//        {
//            panel.add(valuetext[a]);
//            panel.add(weighttext[i]);
//        }


//        this.setLayout(new GridLayout(2,2));
//        this.setBounds(400,300,100,50*i);
//        this.add(new Label("2"),1);
//        this.add(new Label("1"),0);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {

    }

    public static void main(String[] args)
    {
        LayoutExercise layout=new LayoutExercise("Grid");
    }
}
