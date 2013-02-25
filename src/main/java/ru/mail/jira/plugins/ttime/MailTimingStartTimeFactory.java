package ru.mail.jira.plugins.ttime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory;
import com.atlassian.jira.plugin.workflow.WorkflowPluginFunctionFactory;
import com.opensymphony.workflow.loader.AbstractDescriptor;
import com.opensymphony.workflow.loader.FunctionDescriptor;

/**
 * Mail.Ru timing function factory for start event.
 * 
 * @author Andrey Markelov
 */
public class MailTimingStartTimeFactory
    extends AbstractWorkflowPluginFactory
    implements WorkflowPluginFunctionFactory
{
    @Override
    public Map<String, ?> getDescriptorParams(
        Map<String, Object> functionParams)
    {
        Map<String, Object> map = new HashMap<String, Object>();

        if (functionParams != null &&
            functionParams.containsKey(Consts.LATEST_START_FIELD) &&
            functionParams.containsKey(Consts.CALCULATED_FIELD))
        {
            map.put(Consts.LATEST_START_FIELD, extractSingleParam(functionParams, Consts.LATEST_START_FIELD));
            map.put(Consts.CALCULATED_FIELD, extractSingleParam(functionParams, Consts.CALCULATED_FIELD));
            return map;
        }

        map.put(Consts.CALCULATED_FIELD, "");
        map.put(Consts.LATEST_START_FIELD, "");
        return map;
    }

    /**
     * Get custom fields by type.
     */
    private Map<String, String> getFieldsByType(String type)
    {
        Map<String, String> params = new TreeMap<String, String>();

        List<CustomField> fields = ComponentAccessor.getCustomFieldManager().getCustomFieldObjects();
        if (fields != null)
        {
            for (CustomField cf : fields)
            {
                if (cf.getCustomFieldType().getKey().equals(type))
                {
                    params.put(cf.getId(), cf.getName());
                }
            }
        }

        return params;
    }

    /**
     * Get parameter.
     */
    private String getParam(
        AbstractDescriptor descriptor,
        String param)
    {
        if (!(descriptor instanceof FunctionDescriptor))
        {
            throw new IllegalArgumentException("Descriptor must be a FunctionDescriptor.");
        }

        FunctionDescriptor validatorDescriptor = (FunctionDescriptor) descriptor;
        String value = (String) validatorDescriptor.getArgs().get(param);

        if (value!=null && value.trim().length() > 0)
        {
            return value;
        }
        else 
        {
            return "";
        }
    }

    @Override
    protected void getVelocityParamsForEdit(
        Map<String, Object> velocityParams,
        AbstractDescriptor descriptor)
    {
        velocityParams.put(Consts.LATEST_START_FIELD, getParam(descriptor, Consts.LATEST_START_FIELD));
        velocityParams.put(Consts.CALCULATED_FIELD, getParam(descriptor, Consts.CALCULATED_FIELD));
        velocityParams.put("lastests", getFieldsByType("ru.mail.jira.plugins.ttime.ttime:mail-timing-keeper-field"));
        velocityParams.put("calcs", getFieldsByType("ru.mail.jira.plugins.ttime.ttime:mail-timing-calc-field"));
    }

    @Override
    protected void getVelocityParamsForInput(
        Map<String, Object> velocityParams)
    {
        velocityParams.put(Consts.LATEST_START_FIELD, "");
        velocityParams.put(Consts.CALCULATED_FIELD, "");
        velocityParams.put("lastests", getFieldsByType("ru.mail.jira.plugins.ttime.ttime:mail-timing-keeper-field"));
        velocityParams.put("calcs", getFieldsByType("ru.mail.jira.plugins.ttime.ttime:mail-timing-calc-field"));
    }

    @Override
    protected void getVelocityParamsForView(
        Map<String, Object> velocityParams,
        AbstractDescriptor descriptor)
    {
        String lCf = getParam(descriptor, Consts.LATEST_START_FIELD);
        String cCf = getParam(descriptor, Consts.CALCULATED_FIELD);
        CustomFieldManager cfMgr = ComponentAccessor.getCustomFieldManager();

        if (lCf != null && lCf.length() > 0)
        {
            velocityParams.put("nolCf", Boolean.FALSE);

            CustomField lCfObj = cfMgr.getCustomFieldObject(lCf);
            if (lCfObj != null)
            {
                velocityParams.put("nolCfObj", Boolean.FALSE);
                velocityParams.put("lCfName", lCfObj.getName());
            }
            else
            {
                velocityParams.put("nolCfObj", Boolean.TRUE);
            }
        }
        else
        {
            velocityParams.put("nolCf", Boolean.TRUE);
        }

        if (cCf != null && cCf.length() > 0)
        {
            velocityParams.put("nocCf", Boolean.FALSE);

            CustomField cCfObj = cfMgr.getCustomFieldObject(cCf);
            if (cCfObj != null)
            {
                velocityParams.put("nocCfObj", Boolean.FALSE);
                velocityParams.put("cCfName", cCfObj.getName());
            }
            else
            {
                velocityParams.put("nocCfObj", Boolean.TRUE);
            }
        }
        else
        {
            velocityParams.put("nocCf", Boolean.TRUE);
        }

        velocityParams.put(Consts.LATEST_START_FIELD, getParam(descriptor, Consts.LATEST_START_FIELD));
        velocityParams.put(Consts.CALCULATED_FIELD, getParam(descriptor, Consts.CALCULATED_FIELD));
    }
}
