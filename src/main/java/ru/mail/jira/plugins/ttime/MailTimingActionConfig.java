package ru.mail.jira.plugins.ttime;

import java.util.BitSet;
import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.security.Permissions;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.ApplicationProperties;

/**
 * Configuration action.
 * 
 * @author Andrey Markelov
 */
public class MailTimingActionConfig
    extends JiraWebActionSupport
{
    /**
     * Unique ID.
     */
    private static final long serialVersionUID = 5471008397567323367L;

    /**
     * Application properties.
     */
    private final ApplicationProperties applicationProperties;

    /**
     * End day time.
     */
    private String endDayTime;

    /*
     * Friday
     */
    private boolean friday;
 
    /**
     * Is saved?
     */
    private boolean isSaved = false;

    /*
     * Monday
     */
    private boolean monday;

    /**
     * Plugin data.
     */
    private final MailTimingData pluginData;

    /*
     * Saturday
     */
    private boolean saturday;

    /**
     * Start day time.
     */
    private String startDayTime;

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

    /**
     * Constructor.
     */
    public MailTimingActionConfig(
        ApplicationProperties applicationProperties,
        MailTimingData pluginData)
    {
        this.applicationProperties = applicationProperties;
        this.pluginData = pluginData;
    }

    @Override
    public String doDefault()
    throws Exception
    {
        setStartDayTime(pluginData.getStartDayTime());
        setEndDayTime(pluginData.getEndDayTime());

        BitSet bitSet = Utils.getBitSetFromStr(pluginData.getDaysBits());
        if (bitSet.get(0))
        {
            setMonday(true);
        }
        if (bitSet.get(1))
        {
            setTuesday(true);
        }
        if (bitSet.get(2))
        {
            setWednesday(true);
        }
        if (bitSet.get(3))
        {
            setThursday(true);
        }
        if (bitSet.get(4))
        {
            setFriday(true);
        }
        if (bitSet.get(5))
        {
            setSaturday(true);
        }
        if (bitSet.get(6))
        {
            setSunday(true);
        }

        return SUCCESS;
    }

    @Override
    @com.atlassian.jira.security.xsrf.RequiresXsrfCheck
    protected String doExecute()
    throws Exception
    {
        BitSet bitSet = new BitSet(7);
        if (isMonday())
        {
            bitSet.set(0);
        }
        if (isTuesday())
        {
            bitSet.set(1);
        }
        if (isWednesday())
        {
            bitSet.set(2);
        }
        if (isThursday())
        {
            bitSet.set(3);
        }
        if (isFriday())
        {
            bitSet.set(4);
        }
        if (isSaturday())
        {
            bitSet.set(5);
        }
        if (isSunday())
        {
            bitSet.set(6);
        }

        pluginData.setStartDayTime(startDayTime);
        pluginData.setEndDayTime(endDayTime);
        pluginData.setDaysBits(Utils.bitSetToStr(bitSet));
        setSaved(isSaved);

        return getRedirect("MailTimingActionConfig!default.jspa?saved=true");
    }

    @Override
    protected void doValidation()
    {
        super.doValidation();

        long startTimeLong, endTimeLong;
        try
        {
            startTimeLong = Long.parseLong(startDayTime);
            endTimeLong = Long.parseLong(endDayTime);
        }
        catch (NumberFormatException nex)
        {
            addErrorMessage(ComponentAccessor.getJiraAuthenticationContext().getI18nHelper().getText("mailru.timing.config.error.formattime"));
            return;
        }

        if (startTimeLong < 0 || startTimeLong > 23 || endTimeLong < 0 || endTimeLong > 23 || endTimeLong < startTimeLong)
        {
            addErrorMessage(ComponentAccessor.getJiraAuthenticationContext().getI18nHelper().getText("mailru.timing.config.error.timevalue"));
            return;
        }
    }

    /**
     * Get context path.
     */
    public String getBaseUrl()
    {
        return applicationProperties.getBaseUrl();
    }

    public String getEndDayTime()
    {
        return endDayTime;
    }

	public String getStartDayTime()
    {
        return startDayTime;
    }

    /**
     * Check administer permissions.
     */
    public boolean hasAdminPermission()
    {
        User user = getLoggedInUser();
        if (user == null)
        {
            return false;
        }

        if (getPermissionManager().hasPermission(Permissions.ADMINISTER, getLoggedInUser()))
        {
            return true;
        }

        return false;
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

    public boolean isSaved()
    {
        return isSaved;
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

    public void setEndDayTime(String endDayTime) {
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

    public void setSaved(boolean isSaved)
    {
        this.isSaved = isSaved;
    }

    public void setStartDayTime(String startDayTime)
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
}
