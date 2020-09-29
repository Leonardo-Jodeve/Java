
public class Homework
{
    public String name;
    public int complete_rate;

    public Homework(String name,int complete_rate)
    {
        this.name=name;
        this.complete_rate=complete_rate;
    }

    public int borrowHomework(int rate) throws Exception
    {
        if(this.complete_rate>=rate)
            this.complete_rate -= rate;
        else
            throw new Exception("ERROR");
        return rate;
    }

    public void returnHomework(int rate)
    {
        this.complete_rate=rate;
    }
}
