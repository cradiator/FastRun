package com.sysdbg.fastrun.exception;

/**
 * Created by crady on 8/4/2015.
 */
public class ExecutionFailureException extends Exception {
    public ExecutionFailureException(String msg) {
        super(msg);
    }

    public ExecutionFailureException(Exception e) {
        super(e);
    }

    public ExecutionFailureException(String msg, Exception e) {
        super(msg, e);
    }
}
