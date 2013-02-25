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

public class MailTimingCalcCf
    extends AbstractSingleFieldType<Long>
{
    protected MailTimingCalcCf(
			CustomFieldValuePersister customFieldValuePersister,
			GenericConfigManager genericConfigManager) {
		super(customFieldValuePersister, genericConfigManager);
		// TODO Auto-generated constructor stub
	}

	/**
     * Constructor.
     */

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

	@Override
	public Long getSingularObjectFromString(String arg0)
			throws FieldValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringFromSingularObject(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@NotNull
	protected PersistenceFieldType getDatabaseType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Nullable
	protected Object getDbValueFromObject(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Nullable
	protected Long getObjectFromDbValue(@NotNull Object arg0)
			throws FieldValidationException {
		// TODO Auto-generated method stub
		return null;
	}
}
