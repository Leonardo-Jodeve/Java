public class RamPageReplace
{
    public static void FIFO(int[] access_sequence, Ram ram)
    {
        System.out.println("先进先出置换算法：");
        for(int i=0; i<access_sequence.length; i++)
        {
            for(int j=0; j<ram.ram_page.length; j++)
            {
                if(access_sequence[i] == ram.ram_page[j])
                {
                    ram.printRam();
                    System.out.println();
                    break;
                }
                if(j == ram.ram_page.length-1)
                {
                    ram.shiftArray(access_sequence[i]);
                    ram.printRam();
                    System.out.println(",缺页中断");
                }
            }
        }
    }

    public static int max(int[] array)
    {
        int max_value=array[0], max_inferiority=0;
        for(int i=0; i<array.length; i++)
        {
            if(array[i]>max_value)
            {
                max_value=array[i];
                max_inferiority=i;
            }
        }
        return max_inferiority;
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

    public static void OPT(int[] access_sequence, Ram ram)
    {
        System.out.println("最佳置换算法：");
        int[] future_access;
        int replace;
        for(int i=0; i<access_sequence.length; i++)
        {
            for(int j=0; j<ram.ram_page.length; j++)
            {
                if(access_sequence[i] == ram.ram_page[j])
                {
                    ram.printRam();
                    System.out.println();
                    break;
                }
                if(j == ram.ram_page.length-1)
                {
                    future_access=future_access_array(access_sequence, i+1, ram);
                    replace=RamPageReplace.max(future_access);
                    ram.ram_page[replace]=access_sequence[i];
                    ram.printRam();
                    System.out.println(",缺页中断");
                }
            }
        }
    }

    public static int[] getRandomArray(int length, int min, int max)
    {
        int[] array=new int[length];
        for(int i=0; i<length; i++)
            array[i]=PasswordGenerator.getRandomInt(min,max);
        return array;
    }

    public static void main(String[] args)
    {
        Ram ram = new Ram(3);
        int[] access_sequence = getRandomArray(10,1,8);
        FIFO(access_sequence,ram);
        ram.reset();
        System.out.println();
        OPT(access_sequence,ram);
        ram.reset();
    }
}
