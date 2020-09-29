import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RamPageReplace_GUI implements ActionListener
{
    public static void fillArrayInRow(int[][] writenTo, int[] readFrom, int row)
    {
        int writenToColumn, readFromColumn;
        writenToColumn = writenTo[0].length;
        readFromColumn = readFrom.length;
        if(writenToColumn ==readFromColumn)
        {
            for(int i=0; i<writenToColumn; i++)
            {
                writenTo[row][i] = readFrom[i];
            }
        }
        else
            throw new UnsupportedOperationException();
    }

    public static int[][] FIFO(int[] access_sequence, Ram ram)
    {
        int[][] result = new int[access_sequence.length][ram.ram_page.length];

        for(int i=0; i<access_sequence.length; i++)
        {
            if(ram.search(access_sequence[i]) == -1)
            {
                ram.shiftArray(access_sequence[i]);
            }
            fillArrayInRow(result,ram.getRam_page(),i);
        }
        return result;
    }

    public static int max(int[] array)
    {
        int max_value=array[0], max_subscript=0;
        for(int i=0; i<array.length; i++)
        {
            if(array[i]>max_value)
            {
                max_value=array[i];
                max_subscript=i;
            }
        }
        return max_subscript;
    }

    public static int[] future_access_array(int[] access_sequence, int next, Ram ram)
    {
        int[] future_access=new int[ram.ram_page.length];

        for(int i=0; i<ram.ram_page.length; i++)
        {
            for(int j=next; j<access_sequence.length; j++)
            {
                if(ram.ram_page[i] == access_sequence[j])
                {
                    future_access[i] = j;
                    break;
                }
                if(j == access_sequence.length-1)
                    future_access[i]=Integer.MAX_VALUE;
            }
        }
        return future_access;
    }

    public static int[][] OPT(int[] access_sequence, Ram ram)
    {
        int[][] result = new int[access_sequence.length][ram.ram_page.length];
        int[] future_access;
        int replace;
        for(int i=0; i<access_sequence.length; i++)
        {
            for(int j=0; j<ram.ram_page.length; j++)
            {
                if(access_sequence[i] == ram.ram_page[j])
                {
                    fillArrayInRow(result, ram.getRam_page(), i);
                    break;
                }
                if(j == ram.ram_page.length-1)
                {
                    if(ram.isFull())
                    {
                        future_access = future_access_array(access_sequence, i + 1, ram);
                        replace = RamPageReplace_GUI.max(future_access);
                        ram.ram_page[replace] = access_sequence[i];
                        fillArrayInRow(result, ram.getRam_page(), i);
                    }
                    else
                    {
                        ram.shiftArray(access_sequence[i]);
                        fillArrayInRow(result, ram.getRam_page(), i);
                    }
                }
            }
        }
        return result;
    }

    public static int[][] LRU(int[] access_sequence, Ram ram)
    {
        int[][] result = new int[access_sequence.length][ram.ram_page.length];
        for(int i=0; i<access_sequence.length; i++)
        {
            ram.shiftArray(access_sequence[i]);
            fillArrayInRow(result, ram.getRam_page(), i);
        }
        return result;
    }

    public static int[] getRandomArray(int length, int min, int max)
    {
        int[] array=new int[length];
        for(int i=0; i<length; i++)
            array[i]=PasswordGenerator.getRandomInt(min,max);
        return array;
    }

    Ram ramOPT, ramFIFO,ramLRU;
    JPanel background = new JPanel();
    JPanel[] panels = new JPanel[7];
    JPanel buttonPanel = new JPanel();
    JTextField in

        put = new JTextField();
    JTextField[][] OPT,FIFO,LRU;
    JButton[] buttons = new JButton[5];
    JLabel[] labels = new JLabel[4];
    String[] labelText = {"访问序列:","OPT（最佳置换算法）","FIFO（先进先出置换算法）","LRU（最近最久未用置换算法）"};
    String[] buttonText = {"随机生成","计算","色彩演示","色彩演示","色彩演示"};

    public RamPageReplace_GUI()
    {
        new RamPageReplace_GUI(10,3);
    }

    public static void fillTextField(JTextField[][] textFields, int[][] source)
    {
        int targetRows,targetCols,sourceRows,sourceCols;
        targetRows = textFields.length;
        targetCols = textFields[0].length;
        sourceRows = source.length;
        sourceCols = source[0].length;
        if(targetRows == sourceRows && targetCols == sourceCols)
        {
            for(int i=0; i<sourceRows; i++)
            {
                for(int j=0; j<sourceCols; j++)
                {
                    textFields[i][j].setText(source[i][j]+"");
                }
            }
        }
        else
            JOptionPane.showMessageDialog(null,"数组大小不一致");
    }

    public RamPageReplace_GUI(int sequenceLength, int ramPageLength)
    {
        ramOPT = new Ram(ramPageLength);
        ramLRU = new Ram(ramPageLength);
        ramFIFO = new Ram(ramPageLength);
        JFrame frame = new JFrame();
        background.setLayout(new GridLayout(7,1,8,8));
        frame.setTitle("页面置换算法演示工具");
        frame.add(background);
        for(int i=0; i<7; i++)
        {
            panels[i] = new JPanel();
            background.add(panels[i]);
        }

        for(int i=0; i<4; i++)
        {
            labels[i] = new JLabel(labelText[i],JLabel.CENTER);
        }

        for(int i=0; i<5; i++)
        {
            buttons[i] = new JButton(buttonText[i]);
            buttons[i].addActionListener(this);
        }

        panels[0].setLayout(new GridLayout(1,3));
        panels[0].add(labels[0]);
        panels[0].add(input);
        buttonPanel.setLayout(new GridLayout(2,1,5,5));
        panels[0].add(buttonPanel);
        buttonPanel.add(buttons[0]);
        buttonPanel.add(buttons[1]);

        panels[1].setLayout(new GridLayout(1,2));
        panels[1].add(labels[1]);
        panels[1].add(buttons[2]);
        buttons[2].setEnabled(false);

        panels[3].setLayout(new GridLayout(1,2));
        panels[3].add(labels[2]);
        panels[3].add(buttons[3]);
        buttons[3].setEnabled(false);

        panels[5].setLayout(new GridLayout(1,2));
        panels[5].add(labels[3]);
        panels[5].add(buttons[4]);
        buttons[4].setEnabled(false);

        OPT = new JTextField[sequenceLength][ramPageLength];
        FIFO = new JTextField[sequenceLength][ramPageLength];
        LRU = new JTextField[sequenceLength][ramPageLength];
        for(int i=0; i<sequenceLength; i++)
        {
            for(int j=0; j<ramPageLength; j++)
            {
                OPT[i][j] = new JTextField("");
                FIFO[i][j] = new JTextField("");
                LRU[i][j] = new JTextField("");
            }
        }

        panels[2].setLayout(new GridLayout(ramPageLength,sequenceLength,2,2));
        panels[4].setLayout(new GridLayout(ramPageLength,sequenceLength,2,2));
        panels[6].setLayout(new GridLayout(ramPageLength,sequenceLength,2,2));
        for(int i=0; i<ramPageLength; i++)
        {
            for(int j=0; j<sequenceLength; j++)
            {
                panels[2].add(OPT[j][ramPageLength-i-1]);
                panels[4].add(FIFO[j][ramPageLength-i-1]);
                panels[6].add(LRU[j][ramPageLength-i-1]);
            }
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(300,50,1000,750);
        frame.setVisible(true);
    }

    public static int find(int[] source, int keyword)
    {
        for(int i=0; i<source.length; i++)
        {
            if(source[i] == keyword)
                return i;
        }
        return -1;
    }

    public static void demonstration(JTextField[][] textFields, int[] sequence)
    {
        int[][] sourceInt = new int[textFields.length][textFields[0].length];
        int[][] greenAndYellow = new int[textFields.length][textFields[0].length];
        int[][] red = new int[textFields.length][textFields[0].length];
        int[] emptyRAM = new int[textFields[0].length];
        for(int i=0; i<textFields.length; i++)
        {
            for(int j=0; j<textFields[0].length; j++)
            {
                sourceInt[i][j] = Integer.parseInt(textFields[i][j].getText());
            }
        }
        for(int i=0; i<textFields.length; i++)
        {
            for(int j=0; j<textFields[0].length; j++)
            {
                if(i==0)
                {
                    int findResult = find(emptyRAM,sourceInt[i][j]);
                    if(findResult == -1)
                        greenAndYellow[i][j] = 1;   // 1代表绿色
                    else
                        greenAndYellow[i][j] = 2;   // 2代表黄色
                }
                else
                {
                    int findResult = find(sourceInt[i-1],sourceInt[i][j]);
                    if(findResult == -1)
                        greenAndYellow[i][j] = 1;   // 1代表绿色
                    else
                        greenAndYellow[i][j] = 2;   // 2代表黄色
                }
            }
        }

        for(int i=textFields.length-2; i>-1; i--)
        {
            for(int j=0; j<textFields[0].length; j++)
            {
                int findResult = find(sourceInt[i+1], sourceInt[i][j]);
                if(findResult == -1)
                    red[i][j] = 1;
            }
        }

        for(int i=0; i<textFields.length; i++)
        {
            for(int j=0; j<textFields[0].length; j++)
            {
                if(i == 0)
                {
                    if(greenAndYellow[i][j] == 1)
                        textFields[i][j].setBackground(new Color(0,214,152));
                    if(greenAndYellow[i][j] == 2)
                        textFields[i][j].setBackground(new Color(208,212,17));
                    try
                    {
                        Thread.sleep(10);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    if(greenAndYellow[i][j] == 1)
                        textFields[i][j].setBackground(new Color(0,214,152));
                    if(greenAndYellow[i][j] == 2)
                        textFields[i][j].setBackground(new Color(208,212,17));
                    if(red[i-1][j] == 1)
                        textFields[i-1][j].setBackground(new Color(212,98,71));
                }
            }
        }
    }

    public static void cleanTextField(JTextField[][] textFields)
    {
        for(int i=0; i<textFields.length; i++)
        {
            for(int j=0; j<textFields[0].length; j++)
            {
                textFields[i][j].setText("");
                textFields[i][j].setBackground(Color.WHITE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent act)
    {
        if(act.getSource() == buttons[0])
        {
            String sequenceStr;
            sequenceStr = DiskAccess_GUI.printArray(RamPageReplace.getRandomArray(RamPageDialog.sequenceLength,1,10));
            input.setText(sequenceStr);
            cleanTextField(OPT);
            cleanTextField(FIFO);
            cleanTextField(LRU);
            buttons[2].setEnabled(false);
            buttons[3].setEnabled(false);
            buttons[4].setEnabled(false);
            ramOPT.reset();
            ramFIFO.reset();
            ramLRU.reset();
        }
        if(act.getSource() == buttons[1])
        {
            cleanTextField(OPT);
            cleanTextField(FIFO);
            cleanTextField(LRU);
            int[] accessSequence = DiskAccess_GUI.StringToIntArray(input.getText());
            fillTextField(OPT,OPT(accessSequence,ramOPT));
            fillTextField(FIFO,FIFO(accessSequence,ramFIFO));
            fillTextField(LRU,LRU(accessSequence,ramLRU));
            buttons[2].setEnabled(true);
            buttons[3].setEnabled(true);
            buttons[4].setEnabled(true);
        }
        if(act.getSource() == buttons[2])
        {
            int[] accessSequence = DiskAccess_GUI.StringToIntArray(input.getText());
            demonstration(OPT,accessSequence);

        }
        if(act.getSource() == buttons[3])
        {
            int[] accessSequence = DiskAccess_GUI.StringToIntArray(input.getText());
            demonstration(FIFO,accessSequence);
        }

        if(act.getSource() == buttons[4])
        {
            int[] accessSequence = DiskAccess_GUI.StringToIntArray(input.getText());
            demonstration(LRU,accessSequence);
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        RamPageDialog dialog = new RamPageDialog();
        while(RamPageDialog.isSet != 1)
        {
            Thread.sleep(500);
        }
        RamPageReplace_GUI gui = new RamPageReplace_GUI(RamPageDialog.sequenceLength,RamPageDialog.ramPageLength);
    }
}