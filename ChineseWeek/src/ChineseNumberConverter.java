public class ChineseNumberConverter
{
    public static void main(String[] args)
    {
        double money=190020047.99;
        int intmoney=(int)money*100;
        int length=0;
        for(long i=intmoney;intmoney>0;intmoney/=10)
            length++;
        int a[]=new int[length];
        for(int i=length-1;i>-1;i--)
        {
            a[i]=intmoney%10;
            intmoney=intmoney/10;
        }
        for(int i=0;i<length;i++)
            System.out.print(a[i]+" ");
    }
}
