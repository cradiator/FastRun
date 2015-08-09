package com.sysdbg.fastrun.platform;

import com.sysdbg.fastrun.exception.ExecutionFailureException;

import java.util.List;

/**
 * Created by crady on 8/5/2015.
 */
public interface ProcessWrapper {
    /**
     * Create process
     * @param imagePath
     * @param arguments
     * @return  The status code system returned
     */
    int createProcess(String imagePath, String arguments);
}

