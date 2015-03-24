package com.sysdbg.fastrun.item;

import java.io.IOException;

/**
 * Created by xinzhao on 3/24/2015.
 */

/**
 * Interface to execute item.
 */
public interface Executor {
    void run(Item item) throws SecurityException, IOException, IllegalArgumentException;
}
