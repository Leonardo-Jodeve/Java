public class DateCalculator
{
    public static void main(String[] args)
    {
        int year1=1926,month1=8,day1=17;
        int year2=2019,month2=3,day2=24;
        int year=year1,month,gapyear=year2-year1,leapday=0;                       //year为中间变量，leapday为间隔的闰日总天数
        int total=0;
        boolean leap=year%400==0||(year%100!=0&&year%4==0);
        if(month1>2||month2<3&&day2<=28)
            leapday--;
        for(year=year1;year<=year2;year++)
        {
            if(leap)
                leapday++;
        }
        for(month=12;month>month1;month--)
        {
            switch(month)
            {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:total+=31;break;
                case 4: case 6: case 9: case 11: total+=30;break;
                case 2:total+=28;break;
            }
        }
        for(month=1;month<month2;month++)
        {
            switch(month)
            {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:total+=31;break;
                case 4: case 6: case 9: case 11: total+=30;break;
                case 2:total+=28;break;
            }
        }
        switch(month1)
        {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:total+=(31-day1);break;
            case 4: case 6: case 9: case 11: total+=(30-day1);break;
            case 2:total+=(28-day1);break;
        }
        total+=((day2+leapday)+365*gapyear);
        if(year1>year2||(year1==year2&&month1>=month2)||(year1==year2&&month1==month2&&day1>day2))
            total=-total;
        System.out.println("一共有"+total+"天");
    }
}