public class Color
{
    private int color,red,green,blue;
    public Color(int red,int green,int blue)
    {
        color=0xFF000000+(red<<16)+(green<<8)+blue;
        this.red=red;
        this.green=green;
        this.blue=blue;
    }
    public Color(int RGB)
    {
        color=0xFF000000+RGB;
        this.blue=RGB&255;
        this.green=(RGB>>8)&255;
        this.red=(RGB>>16)&255;
    }
    public int getRGB()
    {
        return color&0x00FFFFFF;
    }

    public int getRed()
    {
        return red;
    }

    public int getBlue()
    {
        return blue;
    }

    public int getGreen()
    {
        return green;
    }

    public static void main(String[] args)
    {
        Color a=new Color(15,185,255);    //1030655
        Color b=new Color(0xF021FF);                //(240,33,255)

        System.out.println("(15,185,255)的RGB整数值是："+a.getRGB());
        System.out.println("RGB值0xF021FF的红色值是："+b.getRed());
        System.out.println("RGB值0xF021FF的绿色值是："+b.getGreen());

    }
}

class RGBException extends Exception
{

}