package ru.mail.jira.plugins.ttime;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.security.Permissions;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.ApplicationProperties;

/**
 * 
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
     * Constructor.
     */
    public MailTimingActionConfig(
        ApplicationProperties applicationProperties)
    {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public String doDefault()
    throws Exception
    {
        return SUCCESS;
    }

    /**
     * Get context path.
     */
    public String getBaseUrl()
    {
        return applicationProperties.getBaseUrl();
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
}
