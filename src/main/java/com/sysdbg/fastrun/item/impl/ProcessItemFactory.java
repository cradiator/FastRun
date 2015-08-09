package com.sysdbg.fastrun.item.impl;

import com.sysdbg.fastrun.exception.ItemCreationFailureException;
import com.sysdbg.fastrun.exception.ItemExecutorCreationFailureException;
import com.sysdbg.fastrun.executor.ItemExecutor;
import com.sysdbg.fastrun.executor.ItemExecutorFactory;
import com.sysdbg.fastrun.executor.impl.ProcessItemExecutor;
import com.sysdbg.fastrun.item.Item;
import com.sysdbg.fastrun.item.ItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by crady on 8/8/2015.
 */
@Component
public class ProcessItemFactory implements ItemFactory {
    @Autowired
    private ItemExecutorFactory mExecutorFactory;

    @Override
    public void setItemExecutorFactory(ItemExecutorFactory executorFactory) {
        mExecutorFactory = executorFactory;
    }

    @Override
    public ItemExecutorFactory getItemExecutorFactory() {
        return mExecutorFactory;
    }

    @Override
    public Item createItem() throws ItemCreationFailureException {
        if (getItemExecutorFactory() == null) {
            throw new ItemCreationFailureException("Create proces item without executor factory.");
        }

        try {
            ItemExecutor executor = getItemExecutorFactory().createExecutor();

            if (!(executor instanceof ProcessItemExecutor)) {
                throw new ItemCreationFailureException(String.format("%s is not a ProcessItemExecutor", executor));
            }

            Item item = new ProcessItemImpl();
            item.setExecutor(executor);

            return item;
        } catch (ItemExecutorCreationFailureException e) {
            throw new ItemCreationFailureException(e);
        }
    }
}
