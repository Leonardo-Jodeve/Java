import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RamPageDialog implements ActionListener
{
    JDialog dialog;
    JPanel panel;
    JLabel[] labels;
    JTextField[] textFields;
    JButton confirm,defaultNumber;
    public static int sequenceLength = 10
            ,ramPageLength = 3
            ,isSet = 0;

    public RamPageDialog()
    {
        dialog = new JDialog();

        dialog.setBounds(500,300,500,300);

        panel = new JPanel();
        labels = new JLabel[2];

        labels[0] = new JLabel("访问序列长度",JLabel.CENTER);
        labels[1] = new JLabel("内存空间大小",JLabel.CENTER);

        textFields = new JTextField[2];

        textFields[0] = new JTextField();
        textFields[1] = new JTextField();

        confirm = new JButton("确认");
        defaultNumber = new JButton("使用默认");

        dialog.add(panel);
        dialog.setTitle("请输入必要的参数");
        panel.setLayout(new GridLayout(3,2,5,5));

        panel.add(labels[0]);
        panel.add(textFields[0]);
        panel.add(labels[1]);
        panel.add(textFields[1]);
        panel.add(confirm);
        panel.add(defaultNumber);

        confirm.addActionListener(this);
        defaultNumber.addActionListener(this);

        dialog.setVisible(true);
        panel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == confirm)
        {
            try
            {
                sequenceLength = Integer.parseInt(textFields[0].getText());
                ramPageLength = Integer.parseInt(textFields[1].getText());
                if(sequenceLength <= 0 ||ramPageLength <= 0)
                    throw new NumberFormatException();
                isSet = 1;
                dialog.dispose();
            } catch (NumberFormatException numberFormatException)
            {
                JOptionPane.showMessageDialog(null,"存在不能转化为正整数的字符");
            }
        }

        if (e.getSource() == defaultNumber)
        {
            sequenceLength = 10;
            ramPageLength = 3;
            isSet = 1;
            dialog.dispose();
        }
    }

    public static void main(String[] args)
    {
        RamPageDialog dialog = new RamPageDialog();
    }
}
