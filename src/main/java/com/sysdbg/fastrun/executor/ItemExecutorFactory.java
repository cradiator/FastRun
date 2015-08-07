package com.sysdbg.fastrun.executor;

import com.sysdbg.fastrun.exception.ItemExecutorCreationFailureException;

/**
 * Created by crady on 8/6/2015.
 */
public interface ItemExecutorFactory {
    ItemExecutor createExecutor() throws ItemExecutorCreationFailureException;
}
