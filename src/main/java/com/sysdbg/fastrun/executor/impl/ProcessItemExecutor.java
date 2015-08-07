package com.sysdbg.fastrun.executor.impl;

import com.sysdbg.fastrun.exception.ExecutionFailureException;
import com.sysdbg.fastrun.executor.ItemExecutor;
import com.sysdbg.fastrun.item.Item;
import com.sysdbg.fastrun.item.ProcessItem;
import com.sysdbg.fastrun.platform.ProcessWrapper;
import com.sysdbg.fastrun.util.ArgumentsUtils;
import com.sysdbg.fastrun.util.StringUtils;

import java.util.List;

/**
 * Created by crady on 8/5/2015.
 */
public class ProcessItemExecutor implements ItemExecutor{
    private ProcessWrapper mProcessWrapper;

    ProcessItemExecutor(ProcessWrapper processWrapper) {
        mProcessWrapper = processWrapper;
    }

    public ProcessWrapper getProcessWrapper() {
        return mProcessWrapper;
    }

    public void setProcessWrapper(ProcessWrapper wrapper) {
        mProcessWrapper = wrapper;
    }

    @Override
    public void execute(Item item) throws ExecutionFailureException {
        if (mProcessWrapper == null) {
            throw new ExecutionFailureException("no platform related process wrapper.");
        }

        if (!(item instanceof ProcessItem)) {
            throw new ExecutionFailureException(String.format("%s is not a ProcessItem", item));
        }

        ProcessItem procItem = (ProcessItem)item;
        if (StringUtils.isStringEmpty(procItem.getImagePath())) {
            throw new ExecutionFailureException("create process with null imagePath");
        }

        List<String> argList = procItem.getArguments();
        String arguments = null;
        if (argList != null && argList.size() != 0) {
            arguments = ArgumentsUtils.argumentsListToString(argList);
        }

        mProcessWrapper.createProcess(procItem.getImagePath(), arguments);
    }
}
