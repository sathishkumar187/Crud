package com.tlc.crm.common.action.secure;

import com.tlc.commons.json.JsonObject;
import com.tlc.crm.common.action.CrmRequest;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.validator.TlcModel;

/**
 * @author Abishek
 * @version 1.0
 */
public abstract class CrmGetAction<T extends TlcModel> extends CrmSecureAction
{
    @Override
    public final JsonObject sProcess(CrmRequest request)
    {
        final JsonObject requestJson = request.getRequestJson();
        final JsonObject data = requestJson.getJsonObject("data");
        final Long id = data.getLong("id");

        final T model = get(id);

        return construct(model);
    }

    public T get(Long id)
    {
        return getConfigManager().get(id);
    }

    public abstract ConfigManager<T> getConfigManager();

    public abstract JsonObject construct(T model);
}
