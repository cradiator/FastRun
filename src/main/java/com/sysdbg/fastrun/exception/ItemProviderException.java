package com.sysdbg.fastrun.exception;

/**
 * Created by crady on 8/8/2015.
 */
public class ItemProviderException extends Exception {
    public ItemProviderException(String msg) {
        super(msg);
    }

    public ItemProviderException(Exception e) {
        super(e);
    }
}
