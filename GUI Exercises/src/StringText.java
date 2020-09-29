public class StringText
{
    public static void main(String[] args)
    {
        String string="abcdefABCDEF",temp;
        temp=string.substring(0,6)+"M"+string.substring(6);
        System.out.println(temp);

    }
}
