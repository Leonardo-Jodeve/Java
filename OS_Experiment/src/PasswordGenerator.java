import java.util.Random;

public class PasswordGenerator implements Runnable
{
    //定义类变量，静态字符库
    private int length;
    private static Character[] lower=new Character[26],
            higher=new Character[26],
            symble=new Character[26];
    private static Integer[] number=new Integer[10];
    private Object obj;

    //自己封装了一个指定范围的随机数发生器
    public static int getRandomInt(int min,int max)
    {
        Random random=new Random();
        return min+random.nextInt(max-min);
    }

    //构造方法，传参数
    public PasswordGenerator(int length,Object[] obj)
    {
        this.length=length;
        this.obj=obj;
    }

    //运行方法
    @Override
    public void run()
    {
        int[] order=new int[this.length];   //这里是选取字符的顺序，随机
        if(this.obj instanceof Character[]) //类型转换，方便调用函数
        {
            Character[] temp=(Character[])obj;
            for(int i=0; i<this.length; i++)
            {
                order[i]=getRandomInt(0,temp.length);
            }
            for(int i=0; i<this.length; i++)
                System.out.print(temp[order[i]]);
        }
        if(this.obj instanceof Integer[])
        {
            Integer[] temp=(Integer[])obj;
            for(int i=0; i<this.length; i++)
            {
                order[i]=getRandomInt(0,temp.length);
            }
            for(int i=0; i<this.length; i++)
                System.out.print(temp[order[i]]);
        }

    }

    public static void main(String[] args)
    {
        String symble_str="!@#$%^&*()/~-_=+[{]};:'<,.";
        //方便起见只选取26个常用标点，静态库初始化
        for(int i=0; i<26; i++)
        {
            lower[i]=(char)('a'+i);
            higher[i]=(char)('A'+i);
            symble[i]=symble_str.charAt(i);
        }
        for(int i=0; i<10; i++)
        {
            number[i] = i;
        }
        System.out.print("本次生成的强密码: ");

        //生成目标
        Runnable lower_target = new PasswordGenerator(25,lower);
        Runnable higher_target = new PasswordGenerator(25,higher);
        Runnable symble_target = new PasswordGenerator(25,symble);
        Runnable number_target = new PasswordGenerator(25,number);

        //生成线程
        Thread lower_thread = new Thread(lower_target);
        Thread higher_thread = new Thread(higher_target);
        Thread symble_thread = new Thread(symble_target);
        Thread nummber_thread = new Thread(number_target);

        lower_thread.start();
        higher_thread.start();
        symble_thread.start();
        nummber_thread.start();
    }
}
