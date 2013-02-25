package ru.mail.jira.plugins.ttime;

import java.util.Map;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutManager;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.workflow.function.issue.AbstractJiraFunctionProvider;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.WorkflowException;

/**
 * End function.
 * 
 * @author Andrey Markelov
 */
public class MailTimingEndTimeFunction
    extends AbstractJiraFunctionProvider
{
    /**
     * Plugin data.
     */
    private final MailTimingData pluginData;

    /**
     * Custom field manager.
     */
    private final CustomFieldManager cfMgr;

    /**
     * Layout manager.
     */
    private final FieldLayoutManager layoutMgr;

    /**
     * Constructor.
     */
    public MailTimingEndTimeFunction(
        MailTimingData pluginData,
        CustomFieldManager cfMgr,
        FieldLayoutManager layoutMgr)
    {
        this.pluginData = pluginData;
        this.cfMgr = cfMgr;
        this.layoutMgr = layoutMgr;
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

        if (!Utils.isValid(lCfId) || !Utils.isValid(cCfId))
        {
            throw new RuntimeException("Mail.Ru timing end post-function is not configured");
        }

        CustomField cCfObj = cfMgr.getCustomFieldObject(cCfId);
        CustomField lCfObj = cfMgr.getCustomFieldObject(lCfId);
        if (cCfObj == null || lCfObj == null)
        {
            throw new RuntimeException("Custom fields are not exist for function 'Mail.Ru timing end'");
        }

        Long currTime = Long.valueOf(System.currentTimeMillis());
        Long fakeData = Long.valueOf(-1);

        FieldLayoutItem cLayoutItem = layoutMgr.getFieldLayout(issue).getFieldLayoutItem(cCfObj);
        FieldLayoutItem lLayoutItem = layoutMgr.getFieldLayout(issue).getFieldLayoutItem(lCfObj);
        cCfObj.updateValue(cLayoutItem, issue, new ModifiedValue(issue.getCustomFieldValue(cCfObj), currTime), new DefaultIssueChangeHolder());
        lCfObj.updateValue(lLayoutItem, issue, new ModifiedValue(issue.getCustomFieldValue(lCfObj), fakeData), new DefaultIssueChangeHolder());
    }
}
