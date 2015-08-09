package com.sysdbg.fastrun.item.impl;

import com.sysdbg.fastrun.exception.ExecutionFailureException;
import com.sysdbg.fastrun.item.Item;
import com.sysdbg.fastrun.executor.ItemExecutor;

/**
 * Created by crady on 8/4/2015.
 */
public class GenericItem implements Item {
    private Long mId;
    private String mName;
    private String mDescription;
    private ItemExecutor mExecutor;

    public GenericItem() {

    }

    public void setId(Long id) {
        mId = id;
    }

    @Override
    public Long getId() {
        return mId;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String name) {
        mName = name;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public void setDescription(String description) {
        mDescription = description;
    }

    @Override
    public ItemExecutor getExecutor() {
        return mExecutor;
    }

    @Override
    public void setExecutor(ItemExecutor executor) {
        mExecutor = executor;
    }

    @Override
    public void execute() throws ExecutionFailureException {
        if (mExecutor == null) {
            throw new ExecutionFailureException(String.format("ItemExecutor is null in %s", this));
        }

        mExecutor.execute(this);
    }
}
