package com.tlc.crm.common.action.secure;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonArray;
import com.tlc.commons.json.JsonObject;
import com.tlc.crm.common.action.CrmRequest;
import com.tlc.crm.common.config.ConfigManager;

import com.tlc.validator.TlcModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Abishek
 * @version 1.0
 */
public abstract class CrmConfigAction<T extends TlcModel> extends CrmSecureAction
{
    @Override
    public final JsonObject sProcess(CrmRequest request)
    {
        final JsonObject responseJson = Json.object();
        final JsonObject requestJson = request.getRequestJson();

        final String type = requestJson.getString("type");
        final JsonObject data = requestJson.getJsonObject("data");
        final JsonArray modelsArray = data.getJsonArray("models");

        switch (type)
        {
            case "create" ->
                    {
                        if (modelsArray.size() == 1) {
                            final T model = construct(modelsArray.getJsonObject(0), type);

                            create(model);
                            responseJson.put("id", model.id());
                        } else {
                            final Collection<T> models = construct(modelsArray, type);
                            final List insertedIds = new ArrayList<>();

                            create(models);
                            models.forEach(model -> insertedIds.add(model.id()));
                            responseJson.put("inserted ids", insertedIds);
                        }
                    }
            case "update" ->
                    {
                        if (modelsArray.size() == 1) {
                            final T model = construct(modelsArray.getJsonObject(0), type);

                            update(model);
                            responseJson.put("id", model.id());
                        } else {
                            final Collection<T> models = construct(modelsArray, type);
                            final List updatedIds = new ArrayList<>();

                            update(models);
                            models.forEach(model -> {
                                if (exists(model)) {
                                    updatedIds.add(model.id());
                                }
                            });
                            responseJson.put("updated ids", updatedIds);
                        }
                    }
            case "delete" ->
                    {
                        if (modelsArray.size() == 1) {
                            final T model = construct(modelsArray.getJsonObject(0), type);

                            delete(model);
                            responseJson.put("id", model.id());
                        } else {
                            final Collection<T> models = construct(modelsArray, type);
                            final List deletedIds = new ArrayList<>();

                            models.forEach(model -> {
                                if (exists(model)) {
                                    deletedIds.add(model.id());
                                }
                            });
                            delete(models);
                            responseJson.put("deleted ids", deletedIds);
                        }
                    }
            case "exists" ->
                    {
                        if (modelsArray.size() == 1) {
                            final T model = construct(modelsArray.getJsonObject(0), type);

                            responseJson.put(String.valueOf(model.id()), exists(model));
                        } else {
                            final Collection<T> models = construct(modelsArray, type);
                            final Map<Long, Boolean> exists = exists(models);

                            responseJson.put("existedIds", exists.toString());
                        }
                    }
            default ->
                    {
                        if (modelsArray.size() == 1) {
                            final T model = get(modelsArray.getLong(0));

                            constructResponse(model, responseJson);
                        } else {
                            final Collection<Long> ids = getIds(modelsArray);
                            final Collection<T> models = get(ids);

                            constructResponse(models, responseJson);
                        }
                    }
        }
        return responseJson;
    }

    private Collection<T> get(Collection<Long> ids) {
        return getConfigManager().get(ids);
    }

    private Map<Long, Boolean> exists(Collection<T> models) {
        return getConfigManager().exists(models);
    }

    private void update(Collection<T> models) {
        getConfigManager().update(models);
    }

    private void delete(Collection<T> models) {
        getConfigManager().delete(models);
    }

    private void create(Collection<T> models)
    {
        getConfigManager().create(models);
    }

    public void create(T model)
    {
        getConfigManager().create(model);
    }

    public void update(T model)
    {
        getConfigManager().update(model);
    }

    public void delete(T model)
    {
        getConfigManager().delete(model);
    }

    public boolean exists(T model)
    {
        return getConfigManager().exists(model);
    }

    public T partialGet(Long id)
    {
        return getConfigManager().partialGet(id);
    }

    public T get(Long id)
    {
        return getConfigManager().get(id);
    }

    public abstract ConfigManager<T> getConfigManager();

    public abstract T construct(final JsonObject jsonObject, final String type);

    protected abstract void constructResponse(final T model, final JsonObject responseJson);

    protected abstract void constructResponse(final Collection<T> model, final JsonObject responseJson);

    protected abstract Collection<T> construct(final JsonArray modelsArray, final String type);

    protected abstract Collection<Long> getIds(final JsonArray modelsArray);
}
