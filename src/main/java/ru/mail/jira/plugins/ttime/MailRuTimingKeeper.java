package ru.mail.jira.plugins.ttime;

import java.util.Map;
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
 * 
 * 
 * @author Andrey Markelov
 */
public class MailRuTimingKeeper
    extends AbstractSingleFieldType<Long>
{
    /**
     * Constructor.
     */
    protected MailRuTimingKeeper(
        CustomFieldValuePersister customFieldValuePersister,
        GenericConfigManager genericConfigManager)
    {
        super(customFieldValuePersister, genericConfigManager);
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
        return Long.valueOf(obj.toString());
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
            return -1L;
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

        return params;
    }
}
