package com.tlc.crm.common.action.secure.rest;

import com.tlc.commons.json.JsonObject;
import com.tlc.crm.common.action.CrmRequest;
import com.tlc.crm.common.action.AbstractCrmAction;

/**
 * @author Abishek
 * @version 1.0
 */
public abstract class CrmRestAction extends AbstractCrmAction
{
    @Override
    public final JsonObject process(CrmRequest request) throws Exception
    {
        return rProcess(request);
    }

    public abstract JsonObject rProcess(CrmRequest request) throws Exception;
}
