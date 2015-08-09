package com.sysdbg.fastrun.item.impl;

import com.sysdbg.fastrun.exception.ItemCreationFailureException;
import com.sysdbg.fastrun.executor.ItemExecutor;
import com.sysdbg.fastrun.executor.ItemExecutorFactory;
import com.sysdbg.fastrun.executor.impl.ProcessItemExecutor;
import com.sysdbg.fastrun.item.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import static org.mockito.Mockito.*;

/**
 * Created by crady on 8/8/2015.
 */
public class ProcessItemFactoryTest {
    ProcessItemFactory mFactory;

    @Before
    public void setup() {
        mFactory = new ProcessItemFactory();
    }

    @Test(expected = ItemCreationFailureException.class)
    public void withoutExecutorFactory() throws Exception {
        mFactory.createItem();
    }

    @Test(expected = ItemCreationFailureException.class)
    public void notProcessItemExecutor() throws Exception {
        ItemExecutorFactory factory = mock(ItemExecutorFactory.class);
        when(factory.createExecutor()).thenReturn(mock(ItemExecutor.class));

        mFactory.setItemExecutorFactory(factory);
        mFactory.createItem();
    }

    @Test
    public void setExecutorFactory() {
        ItemExecutorFactory factory = mock(ItemExecutorFactory.class);
        mFactory.setItemExecutorFactory(factory);

        Assert.assertSame(factory, mFactory.getItemExecutorFactory());
    }

    @Test
    public void passThrough() throws Exception {
        ItemExecutorFactory factory = mock(ItemExecutorFactory.class);
        ProcessItemExecutor executor = mock(ProcessItemExecutor.class);
        when(factory.createExecutor()).thenReturn(executor);

        mFactory.setItemExecutorFactory(factory);
        Item item = mFactory.createItem();

        Assert.assertNotNull(item);
        Assert.assertSame(executor, item.getExecutor());
    }
}
