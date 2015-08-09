package com.sysdbg.fastrun.executor.impl;

import com.sysdbg.fastrun.exception.ItemExecutorCreationFailureException;
import com.sysdbg.fastrun.executor.ItemExecutor;
import com.sysdbg.fastrun.executor.ItemExecutorFactory;
import com.sysdbg.fastrun.platform.ProcessWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by crady on 8/6/2015.
 */
@Component
public class ProcessItemExecutorSingletonFactory implements ItemExecutorFactory{
    @Autowired
    private ProcessWrapper mProcessWrapper;
    private ProcessItemExecutor mExecutor;

    public ProcessItemExecutorSingletonFactory() {

    }

    public ProcessWrapper getProcessWrapper() {
        return mProcessWrapper;
    }

    public void setProcessWrapper(ProcessWrapper wrapper) {
        mProcessWrapper = wrapper;
    }

    @Override
    public ItemExecutor createExecutor() throws ItemExecutorCreationFailureException {
        if (mExecutor != null) {
            return mExecutor;
        }

        synchronized(this) {
            if (mExecutor != null) {
                return mExecutor;
            }

            if (mProcessWrapper == null) {
                throw new ItemExecutorCreationFailureException("Create ProcessItemExecutor without ProcessWrapper");
            }

            mExecutor = new ProcessItemExecutor(mProcessWrapper);
            return mExecutor;
        }

    }
}
