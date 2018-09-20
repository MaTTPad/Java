import java.util.Comparator;

public class alarmComparator implements Comparator  {
    @Override
    public int compare(Object o1, Object o2) {
       Alarm s1=(Alarm)o1;
        Alarm s2=(Alarm)o2;

        if(s1.getHour()==s2.getHour())
        {
            if(s1.getMinutes()>s2.getMinutes())
                return 1;

            else if(s1.getMinutes()<s2.getMinutes())
                return -1;

            else
                return 0;
        }
        else if(s1.getHour()>s2.getHour())
            return 1;
        else
            return -1;
    }
}

