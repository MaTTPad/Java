public class Alarm
{
    int hour;
    int minutes;
    int seconds;

    public Alarm(int hour, int minutes, int seconds)
    {
        this.hour=hour;
        this.minutes=minutes;
        this.seconds=seconds;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
