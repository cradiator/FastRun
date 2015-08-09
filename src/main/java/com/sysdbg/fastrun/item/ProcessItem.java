package com.sysdbg.fastrun.item;

import java.util.List;

/**
 * Created by crady on 8/5/2015.
 */
public interface ProcessItem extends Item {
    /**
     * Get executable image path
     * @return
     */
    String getImagePath();

    /**
     * Set executable image path
     * @param imagePath
     */
    void setImagePath(String imagePath);

    /**
     * Get list of arguments
     * @return
     */
    List<String> getArguments();

    /**
     * Set list of arguments
     * @param arguments
     */
    void setArguments(List<String> arguments);

    /**
     * Add a argument to current list
     * @param argument
     */
    void addArgument(String argument);
}
