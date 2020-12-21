import java.util.Date;

public class PersonEntity
{
    private String no;
    private String name;
    private String sex;
    private Date birthday;
    private String professor;
    private String deptno;


    public String getNo()
    {
        return no;
    }

    public void setNo(String no)
    {
        this.no = no;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public String getProfessor()
    {
        return professor;
    }

    public void setProfessor(String professor)
    {
        this.professor = professor;
    }

    public String getDeptno()
    {
        return deptno;
    }

    public void setDeptno(String deptno)
    {
        this.deptno = deptno;
    }

}
