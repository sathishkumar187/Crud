package com.tlc.crm.common.action;

import com.tlc.commons.code.ErrorCode;
import com.tlc.commons.code.ErrorCodes;
import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonObject;
import com.tlc.web.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Abishek
 * @version 1.0
 */
public abstract class AbstractCrmAction implements Action, FullBytesCallback
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCrmAction.class.getName());

    @Override
    public final void process(WebExchange exchange)
    {
        exchange.requestReceiver().readBody(this);
    }

    public final void handle(WebExchange exchange, byte[] body)
    {
        final JsonObject jsonResponse = Json.object();
        try
        {
            final CrmRequest crmRequest = new CrmRequest(exchange, body);
            final JsonObject jResponse = process(crmRequest);
            jsonResponse.put("code", ErrorCodes.NO_ERROR.getCode());
            if(jsonResponse.size() > 0)
            {
                jsonResponse.put("data", jResponse);
            }
        }
        catch (ErrorCode errorCode)
        {
            errorCode.printStackTrace();
            jsonResponse.put("code", errorCode.getCode());
            jsonResponse.put("exception", errorCode.getProvider().getMessage());
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
            LOGGER.error("Web request Failed, Reason : "+throwable.getMessage());
            LOGGER.debug("Stack : ", throwable);
            jsonResponse.put("code", ErrorCodes.UNKNOWN_ERROR.getCode());
        jsonResponse.put("exception", ErrorCodes.UNKNOWN_ERROR.getMessage());
        }
        exchange.responseSender().send("application/json", jsonResponse.getBytes());
    }

    public abstract JsonObject process(CrmRequest request) throws Exception;
}
