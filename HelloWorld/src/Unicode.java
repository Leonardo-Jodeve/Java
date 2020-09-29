public class Unicode
{
    public static void main(String[] args)
    {
        int a=0;
        for(char ch='\u4e00';ch<='\u9fa5';ch++)
        {
             System.out.print(ch);
             a++;
             if(a%90==0)
                 System.out.print("\n");
        }
        System.out.println(a);
    }
}
