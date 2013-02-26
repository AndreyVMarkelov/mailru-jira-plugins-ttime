package ru.mail.jira.plugins.ttime;

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
