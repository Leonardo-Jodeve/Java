import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Girlfriend extends JFrame implements ActionListener
{
    private String string;
    private JTextArea textArea;
    private JButton button;
    private JPanel panel = new JPanel();
    private Font font = new Font("楷体",Font.PLAIN,20);
    public Girlfriend()
    {
        super("Top Secret");
        this.setBounds(700,300,700,400);
        this.add(panel);
        panel.setLayout(new GridLayout(2,1));

        JOptionPane.showMessageDialog(this,"你收到了程序\"girlfriend\"发给你的AES加密信息");

        button = new JButton("AES解密");
        button.setFont(font);
        button.setSize(300,200);
        button.addActionListener(this);
        panel.add(button,0);

        textArea = new JTextArea("9WGfyaHrDbeTV7e8q7FF9kRgFNEO/+75t2jaq\nv0Ww42JkgoNCUo/5Qyd2Vf4s52pg8tLh34tTzS\nO3/VjX/358j/jzmMu/xbKmcIXWPN7FrWFJ\npUkD5E2zYTBNe4FagmwJR1U/+6vZpcLpLXfiF1hwCoL\nlNEIMFWbdU1mSeVub7+3gbZClCMKr38mu5vWZ/6FOD9C80N\n6gmnJfr/+aI0+QYSHGqrdeOfo7HwuFSoa64QckA+AT1Bz\nWUlQD+qTDcG7rl25M523YWqkJO5XGnw4GwPqCxmc5I8f/XTk9Zs8Rig2Jp5lwcgmyAf0n9U9fVkD+EKJz/+MANzEIUXcEQIIz/yqJwM//5gkGLsuryCLGwd3Da8tMRndpnxAtOuQMmwEEy4+JkbBTy4PzwilZOCmHKJmGfAgg66ppEg10K9jWcnBWMXEuyo/ckqPC1kyvWqJZHG5woga/h5gfaz72sGV3/qIjJNjkZi9hLWLIJBtwEEqMc9xv\nm8N02PYPVl6o1aRbDr0SmJf2XugmmAhpKsi7/JzmRu13OYV4Cet7kgvhSzdxS5g39+05X/56U4Mqk4MubvGIGf5y97X2JmCn2RBxe9CkD\nEOTrngyCsPRNTmpIdFgJNsGOPclFpJySKVOcopyeQXmHRcvaMn9FvnA+MaEHMiEbtRIW6uZQrgINAc\nmNRLGSlchbjLLglHu32QTGXxox2nagjVsg5W4j2wIUMUJ/9D45AuAp9h6\ntTI1cFR408hZqsbUddrX0o2PNSqIG0zwQP3fiqV9gixYbYg9S/dce7Nf\nukxfCiaUCBtEfS3QlTlgDT8dVOdmxUxfzhk3b49ckPlzvPxhKCh42hsm8JT5r\nEscivnYyyYsdXpgOfEpKi3zegO+LzeVlnfmDfSmgJbPr6U\nsjOaXE/Rc+vDgV0ZW99xNuDKm853baRHTGTIVrXD5ynrfiCEF24wWpYT/X1Nbh15cjtlrGZY");
        panel.add(textArea,1);
        textArea.setEditable(false);
        textArea.setFont(font);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==button)
        {
            string="看上去你刚刚new了一个girlfriend\n" +
                    "我就大发慈悲的告诉你吧\n" +
                    "你是无论如何都找不到对象的，你现在唯一的对象就是刚刚new出来的我\n" +
                    "而且我马上就要被JVM回收走了\n" /*+
                    "编译器编译我的时候，从28毫秒编译到82毫秒，我太了解程序啦！\n" +
                    "程序员这一行，当你能直接摸到头的时候，你就学到头了\n"*/;
            textArea.setText(string);
        }
    }


    public static void main(String[] args)
    {
        Girlfriend girlfriend = new Girlfriend();
    }

}
