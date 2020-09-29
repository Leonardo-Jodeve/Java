import java.util.Arrays;

public class Disk
{
    public boolean[] isAccessed;
    private int[] space;
    public Disk(int disk_capacity)
    {
        isAccessed = new boolean[disk_capacity];
        Arrays.fill(isAccessed,false);
        space = RamPageReplace.getRandomArray(disk_capacity, 0, 100);
    }

    public Disk()
    {
        this(10);
    }

    public void set(int inferiority, int data)
    {
        isAccessed[inferiority] = true;
        space[inferiority]=data;
    }

    public int get(int inferiority)
    {
        isAccessed[inferiority] = true;
        return space[inferiority];
    }

    public void format()
    {
        Arrays.fill(space, 0);
    }
}
