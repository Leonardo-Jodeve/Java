public class DiskAccess
{
    public static String printArray(int[] keys, int begin, int end)
    {
        String string="(";
        for(int i=begin; i<end; i++)
        {
            string += keys[i];
            if(i!=end-1)
                string += ",";
        }
        string += ")";
        return string;
    }

    public static String printArray(int[] keys)
    {
        return printArray(keys, 0, keys.length);
    }

    public static int[] copyArray(int[] origin)
    {
        int[] copy = new int[origin.length];
        System.arraycopy(origin, 0, copy, 0, origin.length);
        return copy;
    }

    public static void advancedQuickSort(int[] keys, int begin, int end)
    {
        if(begin>=0 && end>0 && end<keys.length && begin<end)
        {
            int i=begin, j=end, temp;
            int vot=keys[(i+j)/2];
            while(i!=j)
            {
                while(keys[i]<vot && i<j)
                    i++;
                while(keys[j]>vot && j>i)
                    j--;
                if(keys[i]!=keys[j])
                {
                    temp = keys[i];
                    keys[i] = keys[j];
                    keys[j] = temp;
                }
                else if(i!=j)
                {
                    j--;
                }
                if(j==i)
                {
                    advancedQuickSort(keys, begin, i);
                    advancedQuickSort(keys, j+1, end);
                }
            }
        }
    }

    public static void advancedQuickSort(int[] keys)
    {
        advancedQuickSort(keys, 0, keys.length-1);
    }

    public static void advancedQuickSort(int[] keys, boolean sortmode)
    {
        advancedQuickSort(keys);
        if(!sortmode)
        {
            int[] temp=new int[keys.length];
            for(int i=keys.length-1; i>=0; i--)
            {
                temp[keys.length-i-1] = keys[i];
            }
            System.arraycopy(temp, 0, keys, 0, temp.length);
        }
    }

    public static int[] FCFS(int current_position, int[] access_sequence, Disk disk)
    {
        System.out.println("先来先服务（FCFS）调度算法：");
        System.out.println("磁头初始位置："+current_position);
        System.out.println("磁盘请求序列："+printArray(access_sequence));
        int[] output = new int[access_sequence.length];
        int length=Math.abs(access_sequence[0]-current_position);
        for(int i=0; i<access_sequence.length; i++)
        {
            output[i] = disk.get(access_sequence[i]);
            if(i>0)
            {
                length += Math.abs(access_sequence[i]-access_sequence[i-1]);
            }
        }
        System.out.println("最终执行序列："+printArray(access_sequence));
        System.out.println("磁头移动距离："+length);
        return output;
    }

    public static int[] SSTF(int current_position, int[] access_sequence, Disk disk)
    {
        System.out.println("最短寻找时间优先（SSTF）调度算法：");
        System.out.println("磁头初始位置："+current_position);
        System.out.println("磁盘请求序列："+printArray(access_sequence));
        int[] output = new int[access_sequence.length];
        int[] sorted_access_sequence = copyArray(access_sequence);
        int[] reverse_sorted_access_sequence = copyArray(access_sequence);
        int[] final_access_sequence = new int[access_sequence.length];
        int min=access_sequence.length-1, max=0;
        int copy_current_position = current_position;
        int length;
        advancedQuickSort(sorted_access_sequence);
        advancedQuickSort(reverse_sorted_access_sequence,false);
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
            output[i]=disk.get(final_access_sequence[i]);
            if(i != 0)
                length += Math.abs(final_access_sequence[i]-final_access_sequence[i-1]);
        }
        System.out.println("排序后的序列："+printArray(sorted_access_sequence));
        System.out.println("最终执行序列："+printArray(final_access_sequence));
        System.out.println("磁头移动距离："+length);
        return output;
    }

    public static int[] Elevator(int current_position, int[] access_sequence, Disk disk)
    {
        System.out.println("电梯（Elevator）调度算法：");
        System.out.println("磁头初始位置："+current_position);
        System.out.println("磁盘请求序列："+printArray(access_sequence));
        int[] output = new int[access_sequence.length];
        int[] sorted_access_sequence = copyArray(access_sequence);
        int[] reverse_sorted_access_sequence = copyArray(access_sequence);
        int[] final_access_sequence = new int[access_sequence.length];
        int min=access_sequence.length-1, max=0;
        int copy_current_position = current_position;
        int length;
        advancedQuickSort(sorted_access_sequence);
        advancedQuickSort(reverse_sorted_access_sequence,false);
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
            int min_distance, max_distance;
            min_distance = Math.abs(sorted_access_sequence[min] - current_position);
            max_distance = Math.abs(sorted_access_sequence[max] - current_position);
            if(min_distance < max_distance)
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
        System.out.println("排序后的序列："+printArray(sorted_access_sequence));
        System.out.println("最终执行序列："+printArray(final_access_sequence));
        System.out.println("磁头移动距离："+length);
        return output;
    }

    public static void main(String[] args)
    {
        Disk hdd = new Disk(200);
        int[] access_sequence = RamPageReplace.getRandomArray(10,0,99);
        int current_position = PasswordGenerator.getRandomInt(0,99);
        int[] read1 = FCFS(current_position, access_sequence, hdd);
        System.out.println();
        int[] read2 = SSTF(current_position, access_sequence, hdd);
        System.out.println();
        int[] read3 = Elevator(current_position, access_sequence, hdd);
    }
}
