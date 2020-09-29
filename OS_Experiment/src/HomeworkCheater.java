public class HomeworkCheater implements Runnable
{
    public final Homework provider;
    public Homework cheater;

    public HomeworkCheater(Homework provider,Homework cheater)
    {
        this.provider=provider;
        this.cheater=cheater;
    }

    @Override
    public void run()
    {
        synchronized (provider)
        {
            while(cheater.complete_rate != 100)
            {
                try
                {
                    Thread.sleep(10);
                }
                catch(InterruptedException ex)
                {}
                try
                {
                    cheater.complete_rate += provider.borrowHomework(1);
                }
                catch (Exception e)
                {
                    System.out.println("由于学渣们粗鲁地抢夺，"+provider.name+"的作业被撕碎");
                    break;
                }
            }
            if(cheater.complete_rate == 100)
            {
                provider.returnHomework(cheater.complete_rate);
                System.out.println("学渣" + cheater.name + "已经抄完" + provider.name + "的作业并归还，"+
                        "此时"+provider.name+"的作业完整性为"+provider.complete_rate+"%，完好无损。");
            }
        }
    }

    public static void main(String[] args)
    {
        Homework physics=new Homework("物理课代表",100),
                chemistry=new Homework("化学课代表",100);
        Homework
                bad_student_1_physics=new Homework("Xu",0),
                bad_student_1_chemistry=new Homework("Xu",0),
                bad_student_2_physics=new Homework("Yang",0),
                bad_student_2_chemistry=new Homework("Yang",0);;

        Runnable cheat_physics_1_target = new HomeworkCheater(physics,bad_student_1_physics);
        Runnable cheat_physics_2_target = new HomeworkCheater(physics,bad_student_2_physics);
        Runnable cheat_chemistry_1_target = new HomeworkCheater(chemistry,bad_student_1_chemistry);
        Runnable cheat_chemistry_2_target = new HomeworkCheater(chemistry,bad_student_2_chemistry);

        Thread cheat_physics_1 = new Thread(cheat_physics_1_target);
        Thread cheat_physics_2 = new Thread(cheat_physics_2_target);
        Thread cheat_chemistry_1 = new Thread(cheat_chemistry_1_target);
        Thread cheat_chemistry_2 = new Thread(cheat_chemistry_2_target);

        cheat_physics_1.start();
        cheat_physics_2.start();
        cheat_chemistry_1.start();
        cheat_chemistry_2.start();
    }
}
