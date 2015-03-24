package com.sysdbg.fastrun.item;

import java.io.IOException;

/**
 * Created by xinzhao on 3/24/2015.
 */
public class NewProcessExecutor implements Executor {
    @Override
    public void run(Item item) throws SecurityException, IOException, IllegalArgumentException {
        // check item is NewProcessItem
        if (!(item instanceof NewProcessItem)) {
            throw new IllegalArgumentException("item is not a NewProcessItem");
        }

        // create the process
        Runtime.getRuntime().exec(((NewProcessItem) item).getCommandLine());
    }
}
