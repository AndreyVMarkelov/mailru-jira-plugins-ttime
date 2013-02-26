package ru.mail.jira.plugins.ttime;

/**
 * Plugin data.
 * 
 * @author Andrey Markelov
 */
public interface MailTimingData
{
    DataStruct getDataStruct();

    String getDaysBits();

    String getEndDayTime();

    String getStartDayTime();

    void setDaysBits(String daysBits);

    void setEndDayTime(String endDayTime);

    void setStartDayTime(String startDayTime);
}
