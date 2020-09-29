import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class AddFrame extends Frame implements ActionListener
{
	private Button button1;
	private TextField text1,text2,text3;
	public JComboBox<String> tool;
	private static String[] symbol= {"+","-","*","/","%"};
	public AddFrame()
{	
	super("calculate");
	this.setSize(400,100);
	this.setLocation(300,240);
	this.setLayout(new FlowLayout(1,1,1));
	this.text1=new TextField("10",8);
	this.add(this.text1);
	this.add(this.tool=new JComboBox<String>(AddFrame.symbol));
	this.tool.addActionListener(this);
	this.text2=new TextField("20",8);
	this.add(this.text2);
	this.button1=new Button("=");
	this.add(this.button1);
	this.button1.addActionListener(this);
	this.text3=new TextField(15);
	text3.setEditable(false);
	this.add(this.text3);
	this.setVisible(true);
	this.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	});
}

	public void actionPerformed(ActionEvent event)
	{
		double a=0.0,b,c=0.0;
		int j=0;
		if(event.getSource()==this.tool)
		{
			
		}
		else if(event.getSource()==this.button1)
		{
			try               			//如何让2个错误同时出现？
			{			
				a=Double.parseDouble(text1.getText());
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "\""+text1.getText()+"\"字符串无法转为十进制整数,");
			}
			try
			{
				b=Double.parseDouble(text2.getText());
				j=this.tool.getSelectedIndex();
				switch(j)
				{
					case 0:c=a+b; break;
					case 1:c=a-b; break;
					case 2:c=a*b; break;
					case 3:c=a/b; break;
					case 4:c=a%b;
				}
				String str=String.valueOf(c);
				this.text3.setText(str);
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "\""+text2.getText()+"\"字符串无法转为十进制整数,");
			}
		}	
	}
	/*public class WinClose implements WindowListener
	{
		public void winClosing(WindowEvent event)
		{
			System.exit(0);
		}
	}*/
	public static void main(String[] args) 
	{
		AddFrame one=new AddFrame();
	}
}
