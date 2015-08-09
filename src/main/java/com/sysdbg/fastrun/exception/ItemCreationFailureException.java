package com.sysdbg.fastrun.exception;

/**
 * Created by crady on 8/8/2015.
 */
public class ItemCreationFailureException extends Exception {
    public ItemCreationFailureException(String msg) {
        super(msg);
    }

    public ItemCreationFailureException(Exception e) {
        super(e);
    }
}
