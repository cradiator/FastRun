package com.sysdbg.fastrun;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by xinzhao on 3/23/2015.
 */
public interface Item {
    UUID getId();

    /**
     * Get item name
     * @return
     */
    String getName();

    /**
     * Set item name
     * @param name The item name, can be null
     */
    void setName(String name);

    /**
     * Get item description
     * @return
     */
    String getDescription();

    /**
     * Set item description
     * @param description The item description, can be null
     */
    void setDescription(String description);

    /**
     * Get the item executor
     * @return
     */
    Executor getExecutor();

    /**
     * Set the item executor
     * @param executor
     */
    void setExecutor(Executor executor);

    /**
     * Run the item with executor
     * @throws SecurityException
     * @throws IOException
     * @throws java.lang.IllegalStateException When executor is not compatible with item, throw IllegalStateException.
     */
    void run() throws SecurityException, IOException, IllegalStateException;
}
