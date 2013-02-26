package ru.mail.jira.plugins.ttime;

import java.util.BitSet;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

/**
 * Plugin data implementation.
 * 
 * @author Andrey Markelov
 */
public class MailTimingDataImpl
    implements MailTimingData
{
    /**
     * PlugIn key.
     */
    private static final String PLUGIN_KEY = "MailTimingDataImpl";

    /**
     * Plug-In settings.
     */
    private final PluginSettings pluginSettings;

    /**
     * Constructor.
     */
    public MailTimingDataImpl(PluginSettingsFactory pluginSettingsFactory)
    {
        this.pluginSettings = pluginSettingsFactory.createSettingsForKey(PLUGIN_KEY);
    }

    public DataStruct getDataStruct()
    {
        DataStruct dataStruct = new DataStruct();

        String startDayTime = getStartDayTime();
        String endDayTime = getEndDayTime();

        //--> start time
        try
        {
            dataStruct.setStartDayTime(Integer.parseInt(startDayTime));
        }
        catch (Exception ex)
        {
            dataStruct.setStartDayTime(-1);
        }

        //--> end time
        try
        {
            dataStruct.setEndDayTime(Integer.parseInt(endDayTime));
        }
        catch (Exception ex)
        {
            dataStruct.setEndDayTime(-1);
        }

        BitSet bitSet = Utils.getBitSetFromStr(getDaysBits());
        if (bitSet.get(0))
        {
            dataStruct.setMonday(true);
        }
        if (bitSet.get(1))
        {
            dataStruct.setTuesday(true);
        }
        if (bitSet.get(2))
        {
            dataStruct.setWednesday(true);
        }
        if (bitSet.get(3))
        {
            dataStruct.setThursday(true);
        }
        if (bitSet.get(4))
        {
            dataStruct.setFriday(true);
        }
        if (bitSet.get(5))
        {
            dataStruct.setSaturday(true);
        }
        if (bitSet.get(6))
        {
            dataStruct.setSunday(true);
        }

        return dataStruct;
    }

    @Override
    public String getDaysBits()
    {
        Object obj = getPluginSettings().get("daysBits");
        if (obj == null)
        {
            return "";
        }

        return obj.toString();
    }

    @Override
    public String getEndDayTime()
    {
        return (String)getPluginSettings().get("endDayTime");
    }

    private synchronized PluginSettings getPluginSettings()
    {
        return pluginSettings;
    }

    @Override
    public String getStartDayTime()
    {
        return (String)getPluginSettings().get("startDayTime");
    }

    @Override
    public void setDaysBits(String daysBits)
    {
        getPluginSettings().put("daysBits", daysBits);
    }

    @Override
    public void setEndDayTime(String endDayTime)
    {
        getPluginSettings().put("endDayTime", endDayTime);
    }

    @Override
    public void setStartDayTime(String startDayTime)
    {
        getPluginSettings().put("startDayTime", startDayTime);
    }
}
