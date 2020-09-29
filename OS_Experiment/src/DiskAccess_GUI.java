import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DiskAccess_GUI extends JFrame implements ActionListener
{
    JPanel[] panels = new JPanel[9];
    JLabel[] labels = new JLabel[6];
    JButton[] buttons = new JButton[2];
    JTextArea[] textAreas =new JTextArea[5];
    JRadioButton[] radioButton=new JRadioButton[2];
    ButtonGroup group=new ButtonGroup();
    String[] labelStrings = {"输入磁头初始位置和请求序列，使用半角逗号隔开，不得含有全角字符",
            "磁盘请求序列:", "磁头初始位置:","先来先服务（FCFS）调度算法：",
            "最短寻找时间优先（SSTF）调度算法：", "电梯（Elevator）调度算法："};
    String[] buttonStrings = {"随机生成","计算"};

    public static String printArray(int[] keys, int begin, int end)
    {
        String string="";
        for(int i=begin; i<end; i++)
        {
            string += keys[i];
            if(i!=end-1)
                string += ",";
        }
        return string;
    }

    public static String printArray(int[] keys)
    {
        return printArray(keys, 0, keys.length);
    }

    public static int paragraphCounter(String str)
    {
        int paragraph=1;
        for(int i=0; i<str.length(); i++)
        {
            String s=str.substring(i,i+1);
            if(s.equals(",")) paragraph++;
        }
        return paragraph;
    }

    public static String[] splitString(String string,int paragraph)
    {
        String str=string;
        String[] strings=new String[paragraph];
        int temp=0;
        for(int i=0; i<str.length(); i++)
        {
            char ch=str.charAt(i);
            if(ch==',')
            {
                strings[temp]=str.substring(0,i);
                str=str.substring(i+1);
                temp++;
                i=0;
            }
            if(i==str.length()-1)
                strings[paragraph-1]=str;
            if(temp==paragraph) break;
        }
        return strings;
    }

    public static int[] StringToIntArray(String str)
    {
        int paragraph = paragraphCounter(str);
        String[] strings = splitString(str,paragraph);
        int[] result = new int[paragraph];
        for(int i=0; i<strings.length; i++)
        {
            try
            {
                result[i] = Integer.parseInt(strings[i]);
                if(result[i] < 0)
                    throw new NullPointerException();
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "请求序列存在不能转化为整形数字的字符！");
                return null;
            }
            catch (NullPointerException ex)
            {
                JOptionPane.showMessageDialog(null, "请求序列存在负数！");
            }
        }
        return result;
    }

    public static String FCFS(int current_position, int[] access_sequence, Disk disk)
    {
        String str = "磁头初始位置："+current_position+"\n"
                +"磁盘请求序列："+printArray(access_sequence)+"\n";
        int length=Math.abs(access_sequence[0]-current_position);
        for(int i=0; i<access_sequence.length; i++)
        {
            disk.get(access_sequence[i]);
            if(i>0)
            {
                length += Math.abs(access_sequence[i]-access_sequence[i-1]);
            }
        }
        str += "最终执行序列："+printArray(access_sequence)+"\n";
        str += "磁头移动距离："+length;
        return str;
    }

    public static String SSTF(int current_position, int[] access_sequence, Disk disk)
    {
        String str = "磁头初始位置："+current_position+"\n"+
                "磁盘请求序列："+printArray(access_sequence)+"\n";
        int[] sorted_access_sequence = DiskAccess.copyArray(access_sequence);
        int[] reverse_sorted_access_sequence = DiskAccess.copyArray(access_sequence);
        int[] final_access_sequence = new int[access_sequence.length];
        int min=access_sequence.length-1, max=0;
        int copy_current_position = current_position;
        int length;
        DiskAccess.advancedQuickSort(sorted_access_sequence);
        DiskAccess.advancedQuickSort(reverse_sorted_access_sequence,false);
        while(sorted_access_sequence[max] <= current_position && max<access_sequence.length-1)
            max++;
        while(sorted_access_sequence[min] > current_position && min>0)
            min--;
        if(min==max && max==0)
            final_access_sequence = sorted_access_sequence;
        else if(min==max && min==access_sequence.length-1)
            final_access_sequence = reverse_sorted_access_sequence;
        else
        {
            int min_distance,max_distance;
            min_distance = Math.abs(sorted_access_sequence[min]-current_position);
            max_distance = Math.abs(sorted_access_sequence[max]-current_position);
            for(int i=0; i<access_sequence.length; i++)
            {
                if(min_distance < max_distance)
                {
                    final_access_sequence[i] = sorted_access_sequence[min];
                    if(min != 0)
                    {
                        current_position = sorted_access_sequence[min];
                        min--;
                        min_distance = Math.abs(sorted_access_sequence[min]-current_position);
                        max_distance = Math.abs(sorted_access_sequence[max]-current_position);
                    }
                    else
                        min_distance = Integer.MAX_VALUE;
                }
                else
                {
                    final_access_sequence[i] = sorted_access_sequence[max];
                    if(max != access_sequence.length-1)
                    {
                        current_position = sorted_access_sequence[max];
                        max++;
                        max_distance = Math.abs(sorted_access_sequence[max]-current_position);
                        min_distance = Math.abs(sorted_access_sequence[min]-current_position);
                    }
                    else
                        max_distance = Integer.MAX_VALUE;
                }
            }
        }
        length=Math.abs(final_access_sequence[0]-copy_current_position);
        for(int i=0;i<access_sequence.length; i++)
        {
            disk.get(final_access_sequence[i]);
            if(i != 0)
                length += Math.abs(final_access_sequence[i]-final_access_sequence[i-1]);
        }
        str += "排序后的序列："+printArray(sorted_access_sequence)+"\n";
        str += "最终执行序列："+printArray(final_access_sequence)+"\n";
        str += "磁头移动距离："+length;
        return str;
    }

    public static String Elevator(int current_position, int[] access_sequence, Disk disk, Boolean mode)
    {
        String str = "磁头初始位置："+current_position+"\n"+
                "磁盘请求序列："+printArray(access_sequence)+"\n";
        int[] output = new int[access_sequence.length];
        int[] sorted_access_sequence = DiskAccess.copyArray(access_sequence);
        int[] reverse_sorted_access_sequence = DiskAccess.copyArray(access_sequence);
        int[] final_access_sequence = new int[access_sequence.length];
        int min=access_sequence.length-1, max=0;
        int copy_current_position = current_position;
        int length;
        DiskAccess.advancedQuickSort(sorted_access_sequence);
        DiskAccess.advancedQuickSort(reverse_sorted_access_sequence,false);
        while(sorted_access_sequence[max] <= current_position && max<access_sequence.length-1)
            max++;
        while(sorted_access_sequence[min] > current_position && min>0)
            min--;
        if(min==max && max==0)
            final_access_sequence = sorted_access_sequence;
        else if(min==max && min==access_sequence.length-1)
            final_access_sequence = reverse_sorted_access_sequence;
        else
        {
            if(mode)
            {
                int copymin = min, copymax=max;
                for (int i = 0; i <= copymin; i++)
                {
                    final_access_sequence[i] = sorted_access_sequence[min];
                    min--;
                }
                for(int i=copymax; i<access_sequence.length; i++)
                {
                    final_access_sequence[i] = sorted_access_sequence[max];
                    max++;
                }
            }
            else
            {
                int copymin = min, copymax=max;
                int j = 0;
                for(int i=copymax; i<access_sequence.length; i++)
                {
                    final_access_sequence[j] = sorted_access_sequence[max];
                    max++;
                    j++;
                }
                for (int i = 0; i <= copymin; i++)
                {
                    final_access_sequence[j] = sorted_access_sequence[min];
                    min--;
                    j++;
                }
            }
        }
        length=Math.abs(final_access_sequence[0]-copy_current_position);
        for(int i=0;i<access_sequence.length; i++)
        {
            output[i]=disk.get(final_access_sequence[i]);
            if(i != 0)
                length += Math.abs(final_access_sequence[i]-final_access_sequence[i-1]);
        }
        str += "排序后的序列："+printArray(sorted_access_sequence)+"\n";
        str += "最终执行序列："+printArray(final_access_sequence)+"\n";
        str += "磁头移动距离："+length;
        return str;
    }

    public DiskAccess_GUI(String title)
    {
        this.setTitle(title);
        this.setBounds(400, 10, 800, 750);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,1));

        for(int i=0; i<9; i++)
            panels[i] = new JPanel();
        for(int i=0; i<6; i++)
            labels[i] = new JLabel(labelStrings[i]);
        for(int i=0; i<2; i++)
            buttons[i] = new JButton(buttonStrings[i]);
        for(int i=0; i<5; i++)
            textAreas[i] = new JTextArea();

        for(int i=3; i<9; i++)
            panels[i].setLayout(new GridLayout(1,1));
        panels[0].setLayout(new GridLayout(8,1));
        panels[1].setLayout(new GridLayout(1,1));
        panels[2].setLayout(new GridLayout(2,2,2,2));
        panels[7].setLayout(new GridLayout(1,3,3,3));

        this.add(panels[0]);
        for(int i=1; i<9; i++)
            panels[0].add(panels[i]);
        panels[1].add(labels[0]);
        panels[3].add(labels[3]);
        panels[5].add(labels[4]);
        panels[7].add(labels[5]);
        panels[2].add(labels[1]);
        panels[2].add(textAreas[0]);
        panels[2].add(buttons[0]);
        panels[2].add(labels[2]);
        panels[2].add(textAreas[1]);
        panels[2].add(buttons[1]);
        panels[4].add(textAreas[2]);
        panels[6].add(textAreas[3]);
        panels[8].add(textAreas[4]);

        radioButton[0]=new JRadioButton("上行优先",true);
        radioButton[1]=new JRadioButton("下行优先",false);
        for(int i=0; i<2; i++)
        {
            group.add(radioButton[i]);
            panels[7].add(radioButton[i]);
        }

        buttons[0].addActionListener(this);
        buttons[1].addActionListener(this);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent act)
    {
        if(act.getSource() == buttons[0])
        {
            int[] access_sequence = RamPageReplace.getRandomArray(15,0,99);
            int current_position = PasswordGenerator.getRandomInt(0,99);
            String access_sequence_str = printArray(access_sequence);
            textAreas[0].setText(access_sequence_str);
            textAreas[1].setText(current_position+"");
        }
        if(act.getSource() ==buttons[1])
        {
            String access_sequence_str = textAreas[0].getText();
            try
            {
                int[] access_sequence = StringToIntArray(access_sequence_str);
                int current_position= -1;
                Disk disk = new Disk(access_sequence[RamPageReplace.max(access_sequence)]*2);
                current_position = Integer.parseInt(textAreas[1].getText());
                if(current_position<0)
                    throw new NumberFormatException();
                textAreas[2].setText(FCFS(current_position,access_sequence,disk));
                textAreas[3].setText(SSTF(current_position,access_sequence,disk));
                textAreas[4].setText(Elevator(current_position,access_sequence,disk,radioButton[1].isSelected()));
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "磁头位置不是非负整数！");
                textAreas[2].setText("Error");
                textAreas[3].setText("Error");
                textAreas[4].setText("Error");
            }
            catch (NullPointerException | ArrayIndexOutOfBoundsException ex)
            {
                textAreas[2].setText("Error");
                textAreas[3].setText("Error");
                textAreas[4].setText("Error");
            }
        }
    }

    public static void main(String[] args)
    {
        DiskAccess_GUI gui = new DiskAccess_GUI("移动臂调度计算器");
    }
}