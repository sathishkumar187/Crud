package com.tlc.crm.common.config;

import com.tlc.validator.TlcModel;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Abishek
 * @version 1.0
 */
public interface ConfigManager<T extends TlcModel>
{
    void create(T model);

    void create(Collection<T> models);

    void update(T model);

    void update(Collection<T> models);

    void delete(T model);

    boolean exists(T model);

    Map<Long, Boolean> exists(Collection<T> models);

    void delete(Collection<T> models);

    T partialGet(Long id);

    T get(Long id);

    Collection<T> get(Collection<Long> id);

    AuditEntry auditEntry(T model);
}
