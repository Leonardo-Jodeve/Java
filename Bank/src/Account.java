//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Account extends JFrame implements ActionListener
//{
//    private String ID;
//    private String password;
//
//    private String name;
//    private double money;
//    private JButton newaccount,login,withdraw,deposit,logout;
//    private JTextField monitor;
//    private JPanel buttonarea;
//
//
//    public Account()
//    {
//        super("银行操作界面");
//        this.setBackground(Color.WHITE);
//        this.setBounds(500,300,500,300);
//        this.setLayout(new GridLayout(2,1));
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//        this.add(this.monitor= new JTextField("欢迎使用自助银行"),0);
//        this.monitor.setFont(new Font("宋体",1,40));
//
//        this.add(buttonarea=new JPanel(),1);
//        buttonarea.add(this.newaccount=new JButton("开户"));
//        this.newaccount.addActionListener(this);
//
//        buttonarea.add(this.login=new JButton("登录账户"));
//        this.login.addActionListener(this);
//
////        buttonarea.add(this.idinput=new JTextField("1",40));
//
//
////        buttonarea.add(this.deposit=new JButton("存款"));
//
//
//
//
//
//
//
//
//
//
//
//        setVisible(true);
////        accountID=ID;
////        set(name,ID,money,password);
////        System.out.println(name+"成功开户，存款"+money+"元。");
//    }
//    public void set(String name,String ID,double money,String password)
//    {
//        this.name=name;
//        this.ID=ID;
//        this.money=money;
//        this.password=password+"";
//    }
//    public void deposit(String ID,double money)
//    {
//        if(this.ID.equals(ID))
//        {
//            this.money += money;
//            System.out.println(name+"成功存款"+money+"元");
//        }
//        else
//            System.out.println("账户不存在");
//    }
//    public void withdraw(String ID,String password,double money)
//    {
//        if(this.ID.equals(ID))
//        {
//            if(this.password.equals(password))
//            {
//                if(this.money>=money)
//                {
//                    this.money-=money;
//                    System.out.println(name+"成功取款"+money+"元");
//                }
//                else
//                    System.out.println("余额不足!");
//            }
//            else
//                System.out.println("密码错误！");
//        }
//        else
//            System.out.println("账户不存在");
//    }
//    public String toString()
//    {
//        return this.name+"的账户余额："+this.money;
//    }
//
//
//    public void actionPerformed(ActionEvent e)
//    {
//        if(e.getSource()==login)
//        {
//            JTextField ID;
//            JPasswordField password;
//            JDialog login=new JDialog();
//            login.setTitle("输入账号密码");
//            login.setDefaultCloseOperation(HIDE_ON_CLOSE);
//            login.setBounds(530,330,300,150);
//            login.setLayout(new GridLayout(3,2));
//            login.add(new JLabel("ID"),0);
//            login.add(ID=new JTextField(),1);
//            login.add(new JLabel("Password"),2);
//            login.add(password=new JPasswordField(),3);
//            login.add(new JButton("Cancel"),4);
//            login.add(new JButton("OK"));
//            ID.addActionListener(this);
//            password.addActionListener(this);
//            login.setVisible(true);
//        }
//
//
//        if(e.getSource()==newaccount){}
//        if(e.getSource()==deposit){}
//        if(e.getSource()==withdraw){}
//
//    }
//
//
//    public static void main(String[] args)
//    {
//        Account a=new Account();
//    }
//}
