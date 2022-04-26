package com.tlc.crm.contact.action;

import com.tlc.commons.json.JsonArray;
import com.tlc.commons.json.JsonObject;
import com.tlc.crm.common.action.secure.CrmConfigAction;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.contact.api.Contact;
import com.tlc.crm.contact.api.ContactManager;
import com.tlc.web.WebAction;

import java.util.Collection;

/**
 * @author Abishek
 * @version 1.0
 */
@WebAction(path = "/contact/mgmt")
public class ContactManagement extends CrmConfigAction<Contact>
{
    @Override
    public ConfigManager<Contact> getConfigManager()
    {
        return ContactManager.getInstance();
    }

    @Override
    public Contact construct(JsonObject jsonObject, String type)
    {
        return null;
    }

    @Override
    public void constructResponse(Contact model, JsonObject responseJson) {

    }

    @Override
    protected void constructResponse(Collection<Contact> model, JsonObject responseJson) {

    }

    @Override
    protected Collection<Contact> construct(JsonArray modelsArray, String type) {
        return null;
    }

    @Override
    protected Collection<Long> getIds(JsonArray modelsArray) {
        return null;
    }
}