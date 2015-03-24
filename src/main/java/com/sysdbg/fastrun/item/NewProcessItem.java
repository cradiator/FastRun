package com.sysdbg.fastrun.item;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by xinzhao on 3/23/2015.
 */

/**
 * Item for create a new process.
 */
public class NewProcessItem implements Item {
    private static final String EMPTY_STRING = "";
    private static final String[] EMPTY_COMMANDLINE = {};

    private String mName;
    private String mDescription;
    private String[] mCommandLine;
    private Executor mExecutor;

    private final UUID mId;

    /**
     * Create a process item
     * @param id  The id for this process item, the id is the global identity for this item.
     *            If null, would generate one automatically. This field is immutable.
     */
    public NewProcessItem(UUID id)
    {
        if (id == null) {
            id = UUID.randomUUID();
        }

        // set the fields
        mExecutor = null;
        mId = id;
        mName = EMPTY_STRING;
        mDescription = EMPTY_STRING;
        mCommandLine = EMPTY_COMMANDLINE;
    }

    /**
     * Same as NewProcessItem(null)
     */
    public NewProcessItem() {
        this(null);
    }

    @Override
    public UUID getId() {
        return mId;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String name) {
        if (name == null) {
            name = EMPTY_STRING;
        }

        mName = name;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public void setDescription(String description) {
        if (description == null) {
            description = EMPTY_STRING;
        }
        mDescription = description;
    }

    @Override
    public Executor getExecutor() {
        return mExecutor;
    }

    @Override
    public void setExecutor(Executor executor) {
        mExecutor = executor;
    }

    /**
     * Get command line
     * @return This method would not return null.
     */
    public String[] getCommandLine() {
        String[] dest = Arrays.copyOf(mCommandLine, mCommandLine.length);
        return dest;
    }

    /**
     * Set the command line
     * @param commandLine The command line array, can be null, but can not contain null or empty string.
     * @throws IllegalArgumentException
     */
    public void setCommandLine(String[] commandLine) throws IllegalArgumentException {
        if (commandLine == null) {
            commandLine = EMPTY_COMMANDLINE;
        }

        checkCommandLine(commandLine);
        mCommandLine = Arrays.copyOf(commandLine, commandLine.length);
    }

    @Override
    public void run() throws SecurityException, IOException, IllegalStateException {
        if (mExecutor == null) {
            throw new IllegalStateException("executor is null");
        }

        try {
            mExecutor.run(this);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("uncompatible executor", e);
        }
    }

    // commandLine shouldn't contain null or empty value
    private void checkCommandLine(String[] commandLine) throws IllegalArgumentException {
        for(String cmd : commandLine) {
            if (cmd == null || cmd.length() == 0)
                throw new IllegalArgumentException("command line contain null or empty string");
        }
    }
}
