package com.sysdbg.fastrun.item.impl;

import com.sysdbg.fastrun.exception.ExecutionFailureException;
import com.sysdbg.fastrun.executor.ItemExecutor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * Created by crady on 8/4/2015.
 */
public class GenericItemTest {
    private final static String FOO_BAR = "Foo_Bar";
    private GenericItem mItem;
    private ItemExecutor mExecutor;

    @Before
    public void setup() {
        mItem = new GenericItem();
        mExecutor = mock(ItemExecutor.class);
    }

    @Test
    public void name() {
        mItem.setName(FOO_BAR);
        Assert.assertEquals(FOO_BAR, mItem.getName());
    }

    @Test
    public void description() {
        mItem.setDescription(FOO_BAR);
        Assert.assertEquals(FOO_BAR, mItem.getDescription());
    }

    @Test
    public void executor() {
        mItem.setExecutor(mExecutor);
        Assert.assertEquals(mExecutor, mItem.getExecutor());
    }

    @Test(expected = ExecutionFailureException.class)
    public void executeWithoutExecutor() throws Exception {
        mItem.execute();
    }

    @Test
    public void execute() throws Exception {
        mItem.setExecutor(mExecutor);
        mItem.execute();

        verify(mExecutor, times(1)).execute(mItem);
    }
}
