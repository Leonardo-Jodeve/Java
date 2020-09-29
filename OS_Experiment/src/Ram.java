import java.util.Arrays;

public class Ram
{
    public int[] ram_page;

    public Ram(int capacity)
    {
        ram_page = new int[capacity];
    }

    public void shiftArray(int page)
    {
        int subscript = -1;
        if(ram_page==null || ram_page.length==0)
        {
            throw new NullPointerException();
        }
        for(int i=0; i<ram_page.length; i++)
        {
            if(ram_page[i] == page)
                subscript = i;
        }
        if(subscript == -1)
        {
            for(int i=ram_page.length-1; i>0; i--)
            {
                ram_page[i]=ram_page[i-1];
            }
        }
        else
        {
            for(int i=subscript; i>0; i--)
            {
                ram_page[i]=ram_page[i-1];
            }
        }
        ram_page[0]=page;
        subscript = -1;
    }

    public void printRam()
    {
        for (int value : ram_page) System.out.print(value + " ");
    }

    public int[] getRam_page()
    {
        return ram_page;
    }

    public int search(int page)
    {
        int result = -1;
        for(int i=0; i<ram_page.length; i++)
        {
            if(ram_page[i] == page)
                result = i;
        }
        return result;
    }

    public void reset()
    {
        Arrays.fill(ram_page, 0);
    }

    public boolean isFull()
    {
        return search(0) == -1;
    }
}
