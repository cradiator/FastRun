package com.sysdbg.fastrun.item;

import com.sysdbg.fastrun.exception.ItemCreationFailureException;
import com.sysdbg.fastrun.executor.ItemExecutorFactory;

/**
 * Created by crady on 8/8/2015.
 */
public interface ItemFactory {
    /**
     * Factory to create executor, and wire the executor with new created item.
     * @param executorFactory
     */
    void setItemExecutorFactory(ItemExecutorFactory executorFactory);

    /**
     * Get factory for executor creation.
     * @return
     */
    ItemExecutorFactory getItemExecutorFactory();

    /**
     * Create executor.
     * @return
     */
    Item createItem() throws ItemCreationFailureException;
}
