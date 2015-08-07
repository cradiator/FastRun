package com.sysdbg.fastrun.item;

import com.sysdbg.fastrun.exception.ExecutionFailureException;
import com.sysdbg.fastrun.executor.ItemExecutor;

/**
 * Created by crady on 8/4/2015.
 */
public interface Item {
    /**
     * Get persistent id
     * @return
     */
    Long getId();

    /**
     * Get item display name
     * @return
     */
    String getName();

    /**
     * Set item display name
     */
    void setName(String name);

    /**
     * Get item display description
     * @return
     */
    String getDescription();

    /**
     * Set item display description
     * @param description
     */
    void setDescription(String description);

    /**
     * Get executor for this item
     * @return
     */
    ItemExecutor getExecutor();

    /**
     * Set executor for this item
     * @param executor
     */
    void setExecutor(ItemExecutor executor);


    void execute() throws ExecutionFailureException;
}
