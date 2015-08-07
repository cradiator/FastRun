package com.sysdbg.fastrun.executor;

import com.sysdbg.fastrun.exception.ExecutionFailureException;
import com.sysdbg.fastrun.item.Item;

/**
 * Created by crady on 8/4/2015.
 */
public interface ItemExecutor {
    /**
     * Execute the item
     * @param item
     * @throws ExecutionFailureException
     */
    void execute(Item item) throws ExecutionFailureException;
}
