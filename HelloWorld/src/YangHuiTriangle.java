public class YangHuiTriangle
{
    public static void main(String[] args)
    {
        //int triangle[][] = new int[10][10];
        int n=10;
        int triangle[][]=new int[n][];
        for(int i=0;i<n;i++)
            triangle[i]=new int[i+1];
        for(int i=0;i<triangle.length;i++)
        {
            triangle[i][0]=1;
            triangle[i][i]=1;
        }
        for(int i=2;i<triangle.length;i++)
        {
            for(int j=1;j<i;j++)
            {
                triangle[i][j]=triangle[i-1][j-1]+triangle[i-1][j];
            }
        }
        for(int i=0;i<triangle.length;i++)
        {
            for(int j=0;j<=i;j++)
            {
                System.out.print(triangle[i][j]+"\t");
            }
            System.out.print("\n");
        }
    }
}
