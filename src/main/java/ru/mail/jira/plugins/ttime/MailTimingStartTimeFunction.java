package ru.mail.jira.plugins.ttime;

import java.util.Map;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.workflow.function.issue.AbstractJiraFunctionProvider;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.WorkflowException;

/**
 * Start function.
 * 
 * @author Andrey Markelov
 */
public class MailTimingStartTimeFunction
    extends AbstractJiraFunctionProvider
{
    /**
     * Plugin data.
     */
    private final MailTimingData pluginData;

    /**
     * Constructor.
     */
    public MailTimingStartTimeFunction(
        MailTimingData pluginData)
    {
        this.pluginData = pluginData;
    }

    @Override
    public void execute(
        Map transientVars,
        Map args,
        PropertySet ps)
    throws WorkflowException
    {
        MutableIssue issue = getIssue(transientVars);

        String cCfId = (String) args.get(Consts.CALCULATED_FIELD);
        String lCfId = (String) args.get(Consts.LATEST_START_FIELD);
    }
}
