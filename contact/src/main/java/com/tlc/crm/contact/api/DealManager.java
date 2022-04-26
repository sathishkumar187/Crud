package com.tlc.crm.contact.api;

/**
 * @author Abishek
 * @version 1.0
 */
public class DealManager
{
    private static class Instance
    {
        private static final DealManager INSTANCE = new DealManager();
    }

    private DealManager() {}

    public static DealManager getInstance()
    {
        return Instance.INSTANCE;
    }

}
