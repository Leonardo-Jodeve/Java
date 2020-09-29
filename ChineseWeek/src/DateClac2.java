public class DateClac2
{
    public static void main(String[] args)
    {
        int year1=1926,month1=8,day1=17;
        int year2=2019,month2=3,day2=24;
        if((year1>year2)||(year1==year2&&month1>month2)||(year1==year2&&month1==month2&&day1>day2))
        {
            int year,month,day;
            year=year1;month=month1;day=day1;
            year1=year2;month1=month2;day1=day2;
            year2=year;month2=month;
        }
        int gapyear=year2-year1,totalday=0;
        if(gapyear==0&&month1==month2)
            totalday=day2-day1;
        if(gapyear==0&&month1!=month2)
        {
            int year=year1;
            switch(month1)
            {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:totalday+=(31-day1);break;
                case 4: case 6: case 9: case 11: totalday+=(30-day1);break;
                case 2:totalday+=(((year % 400) == 0) || (((year % 100) != 0) && ((year % 4) == 0)))?(29-day1):(28-day1);break;
            }
            for(int month=month1+1;month<month2;month++)
            {
                switch(month)
                {
                    case 1: case 3: case 5: case 7: case 8: case 10: case 12:totalday+=31;break;
                    case 4: case 6: case 9: case 11: totalday+=30;break;
                    case 2:totalday+=(((year % 400) == 0) || (((year % 100) != 0) && ((year % 4) == 0)))?29:28;break;
                }
            }
            totalday+=day2;
        }
        else if(gapyear!=0)
        {
            int year=year1;
            switch(month1)
            {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:totalday+=(31-day1);break;
                case 4: case 6: case 9: case 11: totalday+=(30-day1);break;
                case 2:totalday+=(((year % 400) == 0) || (((year % 100) != 0) && ((year % 4) == 0)))?(29-day1):(28-day1);break;
            }
            for(int month=month1+1;month<13;month++)
            {
                switch(month)
                {
                    case 1: case 3: case 5: case 7: case 8: case 10: case 12:totalday+=31;break;
                    case 4: case 6: case 9: case 11: totalday+=30;break;
                    case 2:totalday+=(((year % 400) == 0) || (((year % 100) != 0) && ((year % 4) == 0)))?29:28;break;
                }
            }
            year=year2;
            for(int month=month2-1;month>0;month--)
            {
                switch(month)
                {
                    case 1: case 3: case 5: case 7: case 8: case 10: case 12:totalday+=31;break;
                    case 4: case 6: case 9: case 11: totalday+=30;break;
                    case 2:totalday+=(((year % 400) == 0) || (((year % 100) != 0) && ((year % 4) == 0)))?29:28;break;
                }
            }
            totalday+=day2;
            if(gapyear>1)
            {
                for(int middleyear=year1+1;middleyear<year2;middleyear++)
                {
                    totalday+=(((middleyear % 400) == 0) || (((middleyear % 100) != 0) && ((middleyear % 4) == 0)))?366:365;
                }
            }
        }
        System.out.println("一共有"+totalday+"天");
    }
}
