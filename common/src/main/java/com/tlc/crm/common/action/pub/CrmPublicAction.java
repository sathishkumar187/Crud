package com.tlc.crm.common.action.pub;

import com.tlc.commons.json.JsonObject;
import com.tlc.crm.common.action.AbstractCrmAction;
import com.tlc.crm.common.action.CrmRequest;

/**
 * @author Abishek
 * @version 1.0
 */
public abstract class CrmPublicAction extends AbstractCrmAction
{
    @Override
    public final JsonObject process(CrmRequest request) throws Exception
    {
        return pProcess(request);
    }

    public abstract JsonObject pProcess(CrmRequest request) throws Exception;
}