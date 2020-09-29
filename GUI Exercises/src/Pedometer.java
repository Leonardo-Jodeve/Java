import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Pedometer extends JFrame implements ActionListener {
    private JTextField[] texts;
    private JSpinner spin_year,spin_month;
    private JButton button;
    private DefaultTableModel tablemodel;

    public static int getDays(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    public Pedometer() {
        super("计步器");
        this.setBounds(300,240,380,400);
        this.setBackground(java.awt.Color.lightGray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        this.getContentPane().add(panel, "North");
        String[] str= {"起始日期","年","月"};
        for (int i=0; i<str.length; i++)
            panel.add(new JLabel(str[i]));
        Calendar today = Calendar.getInstance();
        int year=today.get(Calendar.YEAR);
        int month=today.get(Calendar.MONTH)+1;

        spin_year = new JSpinner();
        spin_year.setValue(year);
        panel.add(spin_year,1);
        spin_month=new JSpinner(new SpinnerNumberModel(month, 1, 12, 1));
        panel.add(spin_month,3);
        panel.add(this.button =new JButton("计算"));
        button.addActionListener(this);
        String[] titles={"日期","步数","周平均值（前七天）"};
        this.tablemodel = new DefaultTableModel(titles,50);
        JTable jtable = new JTable(this.tablemodel);
        this.getContentPane().add(new JScrollPane(jtable));
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent event) {

        int year = Integer.parseInt(""+spin_year.getValue());
        int month = Integer.parseInt(""+spin_month.getValue());
        int[] data= {1000,2000,4000,3500,4000,6000,7000,8000,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1} ;
        int sum=0;
        String a[]=new String[31];
        int[] average=new int[50];
        this.tablemodel.setRowCount(getDays(year,month)+1);

        for (int i=0; i<getDays(year,month); i++) {
            this.tablemodel.setValueAt(year+"年"+month+"月"+(i+1)+"日", i,0);
            this.tablemodel.setValueAt(data[i], i,1);
            sum+=data[i];
        }
        for (int i=6; i<getDays(year,month); i++){
            average[i]=(data[i-6]+data[i-5]+data[i-4]+data[i-3]+data[i-2]+data[i-1]+data[i])/7;
            this.tablemodel.setValueAt(average[i], i,2);
        }
        this.tablemodel.setValueAt("月平均", getDays(year,month),0);

        this.tablemodel.setValueAt(sum/getDays(year,month), getDays(year,month),1);


    }

    public static void main(String arg[])
    {
        new Pedometer();
    }

}