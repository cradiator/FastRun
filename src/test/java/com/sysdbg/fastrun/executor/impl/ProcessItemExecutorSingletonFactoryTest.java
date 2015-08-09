package com.sysdbg.fastrun.executor.impl;

import com.sysdbg.fastrun.exception.ItemExecutorCreationFailureException;
import com.sysdbg.fastrun.executor.ItemExecutor;
import com.sysdbg.fastrun.platform.ProcessWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by crady on 8/6/2015.
 */
public class ProcessItemExecutorSingletonFactoryTest {
    private ProcessItemExecutorSingletonFactory mFactory;

    @Before
    public void setup() {
        mFactory = new ProcessItemExecutorSingletonFactory();
    }

    @Test(expected = ItemExecutorCreationFailureException.class)
    public void withoutProcessWrapper() throws Exception {
        mFactory.createExecutor();
    }

    @Test
    public void withProcessWrapper() throws Exception {
        ProcessWrapper wrapper = mock(ProcessWrapper.class);
        mFactory.setProcessWrapper(wrapper);

        ItemExecutor item = mFactory.createExecutor();
        Assert.assertNotNull(item);
        Assert.assertTrue(item instanceof ProcessItemExecutor);
        Assert.assertSame(wrapper, ((ProcessItemExecutor) item).getProcessWrapper());
    }

    @Test
    public void singletonCreation() throws Exception {
        mFactory.setProcessWrapper(mock(ProcessWrapper.class));
        ItemExecutor executor1 = mFactory.createExecutor();
        ItemExecutor executor2 = mFactory.createExecutor();

        Assert.assertSame(executor1, executor2);
    }
}
