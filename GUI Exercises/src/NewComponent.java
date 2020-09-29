import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.*;

public class NewComponent extends JFrame implements ActionListener      //, Serializable
{
    private JPanel panel=new JPanel(),
        checkPanel=new JPanel(new FlowLayout()),
        radioPanel=new JPanel(new FlowLayout()),
        tablePanel=new JPanel(new GridLayout(1,1));

    private SpinnerNumberModel year,month,day;
    private JSpinner[] spinner=new JSpinner[3];
    private JCheckBox checkBox1,checkBox2;
    private JRadioButton[] radioButton=new JRadioButton[3];
    private ButtonGroup group=new ButtonGroup();
    private JComboBox comboBox=new JComboBox();
    private JTextField namefield=new JTextField("姓名"),
                        resultfield=new JTextField();
    private String[] gun={"使用5.56mm子弹","使用7.62mm子弹","使用9mm子弹"},
            lenth={"使用5m绞刑架","使用7m绞刑架","使用10m绞刑架"},
            location={"西伯利亚","塔克拉玛干沙漠","热带荒岛","冰岛"},
            tabletitle={"姓名","身份","时间","刑罚"};
    private String punish;
    private JButton button=new JButton("审判");
    private JScrollPane scrollPane;
    private JTable table=new JTable(new DefaultTableModel(tabletitle,0));

    public NewComponent(String title)
    {
        this.setTitle(title);
        this.setBounds(500, 300, 1300, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2,1));
        panel.setLayout(new GridLayout(3, 3));
        this.getContentPane().add(panel);
        this.getContentPane().add(tablePanel);
        year = new SpinnerNumberModel(2019, 1989, 2030, 1);
        month = new SpinnerNumberModel(6, 1, 12, 1);
        day = new SpinnerNumberModel(9, 1, 31, 1);
        spinner[0] = new JSpinner(year);
        spinner[1] = new JSpinner(month);
        spinner[2] = new JSpinner(day);
        for (int a = 0; a < spinner.length; a++)
        {
            panel.add(spinner[a]);
        }
        namefield.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(namefield);
        panel.add(checkPanel);
        panel.add(radioPanel);
        checkBox1=new JCheckBox("中共党员");
        checkBox2=new JCheckBox("干部");
        if(!checkBox1.isSelected())
            checkBox2.setEnabled(false);
        else
            checkBox2.setEnabled(true);
        checkBox1.addActionListener(this);
        checkBox2.addActionListener(this);
        checkPanel.add(checkBox1);
        checkPanel.add(checkBox2);

        radioButton[0]=new JRadioButton("枪毙",false);
        radioButton[1]=new JRadioButton("绞刑",false);
        radioButton[2]=new JRadioButton("流放",false);
        for(int a=0;a<3;a++)
        {
            group.add(radioButton[a]);
            radioPanel.add(radioButton[a]);
            radioButton[a].addActionListener(this);
        }

        panel.add(comboBox);
        panel.add(button);
        panel.add(resultfield);
        button.addActionListener(this);


        scrollPane=new JScrollPane(table);
        tablePanel.add(scrollPane);

        tablePanel.setVisible(true);
        panel.setVisible(true);
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==radioButton[0])
        {
            punish=radioButton[0].getText();
            comboBox.removeAllItems();
            for(int a=0;a<gun.length;a++)
            {
                comboBox.addItem(gun[a]);
            }
        }
        if(e.getSource()==radioButton[1])
        {
            punish=radioButton[1].getText();
            comboBox.removeAllItems();
            for(int a=0;a<lenth.length;a++)
            {
                comboBox.addItem(lenth[a]);
            }
        }
        if(e.getSource()==radioButton[2])
        {
            punish=radioButton[2].getText();
            comboBox.removeAllItems();
            for(int a=0;a<location.length;a++)
            {
                comboBox.addItem(location[a]);
            }
        }
        if(e.getSource()==checkBox1||e.getSource()==checkBox2)
        {
            if(!checkBox1.isSelected())
            {
                checkBox2.setEnabled(false);
                checkBox2.setSelected(false);
            }
            else
                checkBox2.setEnabled(true);

            if(checkBox1.isSelected()&&checkBox2.isSelected())
            {
                if(radioButton[2].isSelected())
                {
                    comboBox.removeAllItems();
                    radioButton[2].setSelected(false);
                }
                radioButton[2].setEnabled(false);
            }
            else{
                radioButton[2].setEnabled(true);
            }
        }
        if(e.getSource()==button)
        {
            resultfield.setText(this.toString());
        }
    }

    public String toString()
    {
        String name=namefield.getText(),
                date=spinner[0].getValue()+"年"+spinner[1].getValue()+"月"+spinner[2].getValue()+"日"
        ;
        return name+","+
                ((((checkBox1.isSelected()?checkBox1.getText():"") +(checkBox2.isSelected()?checkBox2.getText():"")).equals(""))
                        ? "平民":(((checkBox1.isSelected()?checkBox1.getText():"") +(checkBox2.isSelected()?checkBox2.getText():""))))
                +",于"+date+",判处"
                +punish+","
                +comboBox.getSelectedItem();
    }

    public static void main(String[] args)
    {
        NewComponent component = new NewComponent("人质处理办法");
//        try {
//            OutputStream outputStream=new FileOutputStream("object.txt");
//            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
//            objectOutputStream.writeObject(component);
//            objectOutputStream.close();
//            outputStream.close();
//        } catch (FileNotFoundException e) { }
//        catch(IOException e){}



    }

}

