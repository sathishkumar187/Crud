package com.tlc.crm.common.action.secure;

import com.tlc.commons.json.JsonObject;
import com.tlc.crm.common.action.AbstractCrmAction;
import com.tlc.crm.common.action.CrmRequest;

/**
 * @author Abishek
 * @version 1.0
 */
public abstract class CrmSecureAction extends AbstractCrmAction
{
    @Override
    public final JsonObject process(CrmRequest request) throws Exception
    {
        //TODO authentication check
        return sProcess(request);
    }

    public abstract JsonObject sProcess(CrmRequest request) throws Exception;
}
