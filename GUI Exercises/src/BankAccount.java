import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankAccount extends JPanel implements ActionListener
{
    private String ID,password,name;
    private double money;
    private JButton createaccount,login,changepassword,
            withdraw,deposit,logout,destroyaccount;
    private JTextField IDtext,nametext,moneytext;
    private JPasswordField passwordtext1,passwordtext2,newpasswordtext;

    public BankAccount()
    {

    }



    public void actionPerformed(ActionEvent e)
    {

    }
}
