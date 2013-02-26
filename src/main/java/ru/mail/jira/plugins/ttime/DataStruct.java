package ru.mail.jira.plugins.ttime;

/**
 * This class contains plugin data.
 * 
 * @author Andrey Markelov
 */
public class DataStruct
{
    /**
     * End day time.
     */
    private int endDayTime;

    /*
     * Friday
     */
    private boolean friday;

    /*
     * Monday
     */
    private boolean monday;

    /*
     * Saturday
     */
    private boolean saturday;

    /**
     * Start day time.
     */
    private int startDayTime;

    /*
     * Sunday
     */
    private boolean sunday;

    /*
     * Thursday
     */
    private boolean thursday;

    /*
     * Tuesday
     */
    private boolean tuesday;

    /*
     * Wednesday
     */
    private boolean wednesday;

    public int getEndDayTime()
    {
        return endDayTime;
    }

    public int getStartDayTime()
    {
        return startDayTime;
    }

    public boolean isFriday()
    {
        return friday;
    }

    public boolean isMonday()
    {
        return monday;
    }

    public boolean isSaturday()
    {
        return saturday;
    }

    public boolean isSunday()
    {
        return sunday;
    }

    public boolean isThursday()
    {
        return thursday;
    }

    public boolean isTuesday()
    {
        return tuesday;
    }

    public boolean isWednesday()
    {
        return wednesday;
    }

    public void setEndDayTime(int endDayTime)
    {
        this.endDayTime = endDayTime;
    }

    public void setFriday(boolean friday)
    {
        this.friday = friday;
    }

    public void setMonday(boolean monday)
    {
        this.monday = monday;
    }

    public void setSaturday(boolean saturday)
    {
        this.saturday = saturday;
    }

    public void setStartDayTime(int startDayTime)
    {
        this.startDayTime = startDayTime;
    }

    public void setSunday(boolean sunday)
    {
        this.sunday = sunday;
    }

    public void setThursday(boolean thursday)
    {
        this.thursday = thursday;
    }

    public void setTuesday(boolean tuesday)
    {
        this.tuesday = tuesday;
    }

    public void setWednesday(boolean wednesday)
    {
        this.wednesday = wednesday;
    }

    @Override
    public String toString()
    {
        return "DataStruct[endDayTime=" + endDayTime + ", friday=" + friday
            + ", monday=" + monday + ", saturday=" + saturday
            + ", startDayTime=" + startDayTime + ", sunday=" + sunday
            + ", thursday=" + thursday + ", tuesday=" + tuesday
            + ", wednesday=" + wednesday + "]";
    }
}
