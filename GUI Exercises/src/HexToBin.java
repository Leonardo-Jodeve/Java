import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HexToBin extends JFrame implements ActionListener
{
    private JTextArea hextext,bintext;
    private JPanel panel,buttonpanel;
    private String hex,bin;
    private JButton button;

    public HexToBin(String title)
    {
        super(title);
        this.setBounds(300,500,500,500);
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        buttonpanel = new JPanel();
        this.add(panel);
        panel.setLayout(new GridLayout(3,1));
        buttonpanel.setLayout(new GridLayout(1,2));

        hextext = new JTextArea();
        bintext = new JTextArea();
        button=new JButton("十六进制转二进制");
        button.addActionListener(this);
        panel.add(hextext);
        panel.add(button);
        panel.add(bintext);

        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        HexToBin hexToBin = new HexToBin("HexToBin");
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==button)
        {
            bin="";
            hex=hextext.getText();
            for(int i=0;i<hex.length();i++)
            {
                String temp = hex.substring(i,i+1);
                switch (temp)
                {
                    case "0": bin+="0000";break;
                    case "1": bin+="0001";break;
                    case "2": bin+="0010";break;
                    case "3": bin+="0011";break;
                    case "4": bin+="0100";break;
                    case "5": bin+="0101";break;
                    case "6": bin+="0110";break;
                    case "7": bin+="0111";break;
                    case "8": bin+="1000";break;
                    case "9": bin+="1001";break;
                    case "a": case "A": bin+="1010";break;
                    case "b": case "B": bin+="1011";break;
                    case "c": case "C": bin+="1100";break;
                    case "d": case "D": bin+="1101";break;
                    case "e": case "E": bin+="1110";break;
                    case "f": case "F": bin+="1111";break;
                    default : throw new NumberFormatException();
                }
                if((i+1)%8==0)
                {
                    bin+="\n";
                }
            }
            bintext.setText(bin);
        }
    }
}
