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
 * Start function.
 * 
 * @author Andrey Markelov
 */
public class MailTimingStartTimeFunction
    extends AbstractJiraFunctionProvider
{
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
    public MailTimingStartTimeFunction(
        CustomFieldManager cfMgr,
        FieldLayoutManager layoutMgr)
    {
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
            throw new RuntimeException("Mail.Ru timing start post-function is not configured");
        }

        CustomField cCfObj = cfMgr.getCustomFieldObject(cCfId);
        CustomField lCfObj = cfMgr.getCustomFieldObject(lCfId);
        if (cCfObj == null || lCfObj == null)
        {
            throw new RuntimeException("Custom fields are not exist for function 'Mail.Ru timing start'");
        }

        Long currTime = Long.valueOf(System.currentTimeMillis());
        Long calcVal = Utils.getObjectAsLong(issue.getCustomFieldValue(cCfObj));
        Long newCalcVal = (calcVal > 0) ? (calcVal * -1) : calcVal;

        FieldLayoutItem cLayoutItem = layoutMgr.getFieldLayout(issue).getFieldLayoutItem(cCfObj);
        FieldLayoutItem lLayoutItem = layoutMgr.getFieldLayout(issue).getFieldLayoutItem(lCfObj);
        cCfObj.updateValue(cLayoutItem, issue, new ModifiedValue(calcVal, newCalcVal), new DefaultIssueChangeHolder());
        lCfObj.updateValue(lLayoutItem, issue, new ModifiedValue(issue.getCustomFieldValue(lCfObj), currTime), new DefaultIssueChangeHolder());
    }
}
