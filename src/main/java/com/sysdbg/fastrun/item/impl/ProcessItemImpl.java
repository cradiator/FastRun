package com.sysdbg.fastrun.item.impl;

import com.sysdbg.fastrun.item.ProcessItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by crady on 8/5/2015.
 */
public class ProcessItemImpl extends GenericItem implements ProcessItem{
    private String mImagePath;
    private List<String> mArguments;

    public ProcessItemImpl() {

    }

    @Override
    public String getImagePath() {
        return mImagePath;
    }

    @Override
    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    @Override
    public List<String> getArguments() {
        if (mArguments == null) {
            return null;
        }

        return Collections.unmodifiableList(mArguments);
    }

    @Override
    public void setArguments(List<String> arguments) {
        if (arguments == null) {
            mArguments = null;
            return;
        }

        mArguments = new ArrayList<String>(arguments);
    }

    @Override
    public void addArgument(String argument) {
        if (mArguments == null) {
            mArguments = new ArrayList<String>();
        }

        mArguments.add(argument);
    }
}
