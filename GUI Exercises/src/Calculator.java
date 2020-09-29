import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import  javax.swing.JFrame;

public class Calculator extends JFrame implements ActionListener  //, ComponentListener
{
    private JTextField text_first, text_second, answer;
    private JButton equal;
    public Calculator()
    {
        super("计算器");
        this.setBackground(Color.WHITE);
        this.setBounds(300,240,500,150);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.add(this.text_first=new JTextField("10",10));
        this.text_first.addActionListener(this);

        this.add(new Label("+"));

        this.add(this.text_second=new JTextField("20",10));
        this.text_second.addActionListener(this);

        this.add(this.equal=new JButton("="));
        this.equal.addActionListener(this);

        this.add(this.answer=new JTextField(10));
        this.answer.setEditable(false);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource()==this.equal
                ||event.getSource()==this.text_first
                ||event.getSource()==this.text_second)
        {
            double a=Double.parseDouble(this.text_first.getText());
            double b=Double.parseDouble(this.text_second.getText());
            double c=a+b;
            this.answer.setText(""+c);
        }
    }


    public static void main(String[] args)
    {
        new Calculator();
    }


}
