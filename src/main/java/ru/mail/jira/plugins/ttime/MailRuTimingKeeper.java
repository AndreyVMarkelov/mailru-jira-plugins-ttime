package ru.mail.jira.plugins.ttime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.util.NotNull;
import com.atlassian.util.concurrent.Nullable;

/**
 * Mail.Ru timing time keeper.
 * 
 * @author Andrey Markelov
 */
public class MailRuTimingKeeper
    extends AbstractSingleFieldType<Long>
{
    /**
     * Date format.
     */
    private DateFormat df;

    /**
     * Constructor.
     */
    protected MailRuTimingKeeper(
        CustomFieldValuePersister customFieldValuePersister,
        GenericConfigManager genericConfigManager)
    {
        super(customFieldValuePersister, genericConfigManager);
        this.df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    @NotNull
    protected PersistenceFieldType getDatabaseType()
    {
        return PersistenceFieldType.TYPE_LIMITED_TEXT;
    }

    @Override
    @Nullable
    protected Object getDbValueFromObject(Long l)
    {
        return l;
    }

    @Override
    @Nullable
    protected Long getObjectFromDbValue(@NotNull Object obj)
    throws FieldValidationException
    {
        return (obj == null) ? 0L : Long.valueOf(obj.toString());
    }

    @Override
    public Long getSingularObjectFromString(String str)
    throws FieldValidationException
    {
        try
        {
            return Long.valueOf(str);
        }
        catch (NumberFormatException nex)
        {
            return 0L;
        }
    }

    @Override
    public String getStringFromSingularObject(Long l)
    {
        return l.toString();
    }

    @Override
    @NotNull
    public Map<String, Object> getVelocityParameters(
        Issue issue,
        CustomField field,
        FieldLayoutItem fieldLayoutItem)
    {
        Map<String, Object> params = super.getVelocityParameters(issue, field, fieldLayoutItem);

        Long fVal = Utils.getObjectAsLong(issue.getCustomFieldValue(field));
        if (fVal > 0)
        {
            params.put("realVal", df.format(new Date(fVal)));
        }
        else
        {
            params.put("realVal", ComponentAccessor.getJiraAuthenticationContext().getI18nHelper().getText("mailru.timing.keeper.cf.nostarted"));
        }

        return params;
    }
}
