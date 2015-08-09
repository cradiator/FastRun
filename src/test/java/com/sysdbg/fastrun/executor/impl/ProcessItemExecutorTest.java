package com.sysdbg.fastrun.executor.impl;

import com.sysdbg.fastrun.exception.ExecutionFailureException;
import com.sysdbg.fastrun.executor.ItemExecutor;
import com.sysdbg.fastrun.item.Item;
import com.sysdbg.fastrun.item.ProcessItem;
import com.sysdbg.fastrun.platform.ProcessWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * Created by crady on 8/5/2015.
 */
public class ProcessItemExecutorTest {
    private static final String FOO_BAR = "FooBar";
    private static final String HELLO = "Hello";

    private ProcessItemExecutor mExecutor;
    private ProcessWrapper mWrapper;
    private ProcessItem mProcItem;

    @Before
    public void setup() {
        mWrapper = mock(ProcessWrapper.class);
        mProcItem = mock(ProcessItem.class);
        mExecutor = new ProcessItemExecutor(mWrapper);
    }

    @Test
    public void getWrapper() {
        Assert.assertSame(mWrapper, mExecutor.getProcessWrapper());
    }

    @Test
    public void setWrapper() {
        ProcessWrapper newWrapper = mock(ProcessWrapper.class);
        mExecutor.setProcessWrapper(newWrapper);

        Assert.assertSame(newWrapper, mExecutor.getProcessWrapper());
    }

    @Test(expected = ExecutionFailureException.class)
    public void noProcessWrapper() throws Exception {
        ItemExecutor executor = new ProcessItemExecutor(null);
        executor.execute(mProcItem);
    }

    @Test(expected = ExecutionFailureException.class)
    public void noItem() throws Exception {
        mExecutor.execute(null);
    }

    @Test(expected = ExecutionFailureException.class)
    public void noProcessItem() throws Exception {
        mExecutor.execute(mock(Item.class));
    }

    @Test(expected = ExecutionFailureException.class)
    public void noImagePath() throws Exception {
        when(mProcItem.getImagePath()).thenReturn(null);

        mExecutor.execute(mProcItem);
    }

    @Test
    public void wrapperArguments() throws Exception {
        ArrayList<String> args = new ArrayList<String>();
        args.add(HELLO);
        args.add(FOO_BAR);

        when(mProcItem.getImagePath()).thenReturn(FOO_BAR);
        when(mProcItem.getArguments()).thenReturn(args);

        mExecutor.execute(mProcItem);

        verify(mWrapper, times(1)).createProcess(FOO_BAR, "\"Hello\" \"FooBar\"");
    }
}
