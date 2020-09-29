public class BankAccount
{
    private int accountnumber;
    private String ID;
    private String password;
    private String name;
    private double money;
    private int count=0;

    public BankAccount(String name,String ID,double money,String password)
    {
        set(name,ID,money,password);
        count++;
        this.accountnumber=count;
    }


    public void set(String name,String ID,double money,String password)
    {
        this.name=name;
        this.ID=ID;
        this.money=money;
        this.password=password+"";
    }

    public void deposit(String ID,double money)
    {
        if(this.ID.equals(ID))
        {
            this.money += money;
            System.out.println(name+"成功存款"+money+"元,"+"账户余额："+this.money);
        }
        else
            System.out.println("账户不存在");
    }

    public void withdraw(String ID,String password,double money)
    {
        if(this.ID.equals(ID))
        {
            if(this.password.equals(password))
            {
                if(this.money>=money)
                {
                    this.money-=money;
                    System.out.println(name+"成功取款"+money+"元"+"账户余额："+this.money);
                }
                else
                    System.out.println("余额不足!");
            }
            else
                System.out.println("密码错误！");
        }
        else
            System.out.println("账户不存在");
    }

    public String toString()
    {
        return this.name+"，身份证号码"+this.ID+" 账户余额："+this.money;
    }

    public void finalize()
    {
        count--;
    }

    public static void main(String[] args)
    {
        BankAccount account[]=new BankAccount[3];
        String[] name={"张三","李四","王五"}
                , id={"001","002","003"}
                ,password={"001","002","003"};
        double[] money={100,1000,10000};
        for(int i=0;i<name.length;i++)
        {
            account[i] = new BankAccount(name[i], id[i], money[i], password[i]);
            System.out.println(account[i].toString());
        }
        account[0].deposit("001",1000);
        account[0].withdraw("001","001",50);
        account[0].withdraw("001","123",100);
        account[0].withdraw("001","001",10000);
    }
}
